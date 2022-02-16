package ru.nsu.fit.oop.Task_2_1_1;

import java.util.*;
import java.util.concurrent.*;

public class PrimeNumberChecker {

    private boolean isCompositeNumber(int p) {
        for (int i = 2; i < p; i++) {
            if (p % i == 0) return true;
        }

        return false;
    }

    public boolean linearSolution(int[] ps) {
        for (int p : ps) {
            if (isCompositeNumber(p)) return true;
        }

        return false;
    }

    private int threadNum = 4;

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public boolean threadSolution(int[] ps) {
        boolean solution = false;

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        Map<Integer, Integer> positionMap = calculatePositions(ps, threadNum);
        List<Callable<Boolean>> toRun = new ArrayList<>(threadNum);

        for (Map.Entry<Integer, Integer> entry : positionMap.entrySet())
            toRun.add(() -> {
                if (entry.getKey() == -1) return false;

                int[] subPs = new int[entry.getValue() - entry.getKey()];
                System.arraycopy(ps, entry.getKey(), subPs,0, subPs.length);

                return linearSolution(subPs);
            });


        try {
            List<Future<Boolean>> futures = executorService.invokeAll(toRun);
            solution = futures.stream().anyMatch(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return false;
                }
            });
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        executorService.shutdownNow();
        return solution;
    }

    private Map<Integer, Integer> calculatePositions(int[] ps, int threadNum) {
        Map<Integer, Integer> positionMap = new LinkedHashMap<>(threadNum);

        int range = ps.length / threadNum;
        int start = 0;
        if (range == 0) range = 1;
        for (int i = 0; i < threadNum - 1; i++) {
            if (start >= ps.length)
                positionMap.put(-1, -1);
            else {
                positionMap.put(start, start + range);
                start += range;
            }
        }

        if (ps.length >= threadNum) {
            positionMap.put(start, start + range + (ps.length % threadNum));
        } else {
            positionMap.put(-1, -1);
        }

        return positionMap;
    }

    public boolean parallelSolution(int[] ps) {
        return Arrays.stream(ps)
                .parallel()
                .anyMatch(this::isCompositeNumber);
    }

}

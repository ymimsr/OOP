package ru.nsu.fit.oop.Task_2_1_1;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PrimeNumberChecker {

    public PrimeNumberChecker() {
        setThreadNum(4);
    }

    private boolean isCompositeNumber(int p) {
        for (int i = 2; i < p; i++) {
            if (p % i == 0) return true;
        }

        return false;
    }

    public ImmutablePair<Boolean, Long> linearSolution(int[] ps) {
        StopWatch timer = new StopWatch();
        timer.start();

        for (int p : ps) {
            if (isCompositeNumber(p)) {
                timer.stop();
                return new ImmutablePair<>(true, timer.getTime());
            }
        }

        timer.stop();
        return new ImmutablePair<>(false, timer.getTime());
    }

    private ImmutablePair<Boolean, Long> singleThreadSolution(int[] ps) {
        StopWatch timer = new StopWatch();
        timer.start();

        for (int p : ps) {
            for (int i = 2; i < p; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    timer.stop();
                    return new ImmutablePair<>(false, timer.getTime());
                } else if (p % i == 0) {
                    timer.stop();
                    return new ImmutablePair<>(true, timer.getTime());
                }
            }
        }

        timer.stop();
        return new ImmutablePair<>(false, timer.getTime());
    }

    private int threadNum;

    public void setThreadNum(int threadNum)
    {

        this.threadNum = threadNum;
    }

    public ImmutablePair<Boolean, long[]> threadSolution(int[] ps) {
        long[] threadSolutionTime = new long[threadNum];
        Arrays.fill(threadSolutionTime, 0);

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        Map<Integer, Integer> positionMap = calculatePositions(ps, threadNum);
        List<Callable<Boolean>> toRun = new ArrayList<>(threadNum);

        for (Map.Entry<Integer, Integer> entry : positionMap.entrySet())
            toRun.add(() -> {
                if (entry.getKey() == -1) return false;

                int[] subPs = new int[entry.getValue() - entry.getKey()];
                System.arraycopy(ps, entry.getKey(), subPs,0, subPs.length);

                ImmutablePair<Boolean, Long> singleThreadSolution = singleThreadSolution(subPs);
                int range = ps.length / threadNum == 0 ? 1 : ps.length / threadNum;
                threadSolutionTime[entry.getKey() / range] += singleThreadSolution.right;

                return singleThreadSolution.left;
            });


        boolean solution = false;
        try {
            List<Future<Boolean>> futures = executorService.invokeAll(toRun);
            while(true) {
                if (futures.isEmpty()) break;
                List<Future<Boolean>> doneFutures = futures
                        .stream()
                        .filter(Future::isDone)
                        .collect(Collectors.toList());

                if (!doneFutures.isEmpty()) {
                    solution = doneFutures
                            .stream()
                            .anyMatch(future -> {
                                try {
                                    return future.get();
                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                    return false;
                                }
                            });
                    if (solution)
                        break;
                    futures.removeAll(doneFutures);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdownNow();
        return new ImmutablePair<>(solution, threadSolutionTime);
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

    public ImmutablePair<Boolean, Long> parallelSolution(int[] ps) {
        StopWatch timer = new StopWatch();
        timer.start();

        boolean solution = Arrays.stream(ps)
                .parallel()
                .anyMatch(this::isCompositeNumber);

        timer.stop();
        return new ImmutablePair<>(solution, timer.getTime());
    }


}

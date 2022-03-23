package ru.nsu.fit.oop.Task_2_1_1;

import com.googlecode.charts4j.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.nsu.fit.oop.Task_2_1_1.util.StringArrayConverter;

import java.util.Collections;

import static com.googlecode.charts4j.Color.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeNumberCheckerTest {

    private final PrimeNumberChecker primeNumberChecker = new PrimeNumberChecker();

    private static int testIndex = 1;

    @ParameterizedTest
    @CsvFileSource(resources = "/tests.csv", numLinesToSkip = 1)
    public void chartTest(@ConvertWith(StringArrayConverter.class) int[] ps, boolean expSolution) {
        ImmutablePair<Boolean, Long> linearSolutionPair = primeNumberChecker.linearSolution(ps);
        ImmutablePair<Boolean, long[]> threadSolutionPair = primeNumberChecker.threadSolution(ps);
        ImmutablePair<Boolean, Long> parallelSolutionPair = primeNumberChecker.parallelSolution(ps);

        BarChartPlot[] plots = new BarChartPlot[threadSolutionPair.right.length + 2];
        double maxRange = 1.3 * Math.max(linearSolutionPair.right, parallelSolutionPair.right);
        if (maxRange == 0) maxRange = 1;

        for (int i = 0; i < threadSolutionPair.right.length; i++) {
            plots[i] = Plots.newBarChartPlot(
                    DataUtil.scaleWithinRange(0, maxRange, Collections.singletonList(threadSolutionPair.right[i])),
                    ORANGERED,
                    "Thread " + (i + 1)
            );
        }
        plots[plots.length - 2] = Plots.newBarChartPlot(
                DataUtil.scaleWithinRange(0, maxRange, Collections.singletonList(linearSolutionPair.right)),
                LIGHTPINK,
                "Linear"
        );
        plots[plots.length - 1] = Plots.newBarChartPlot(
                DataUtil.scaleWithinRange(0, maxRange, Collections.singletonList(parallelSolutionPair.right)),
                LIMEGREEN,
                "Parallel"
        );

        BarChart barChart = GCharts.newBarChart(plots);

        AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels timeLabel = AxisLabelsFactory.newAxisLabels("Time", 50.0);
        timeLabel.setAxisStyle(axisStyle);

        barChart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 1.3 * linearSolutionPair.right));
        barChart.addYAxisLabels(timeLabel);
        barChart.setSize(600, 450);
        barChart.setBarWidth(60);
        barChart.setSpaceWithinGroupsOfBars(20);
        barChart.setTitle("Test " + testIndex, BLACK, 16);
        barChart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, LAVENDER, 100);
        fill.addColorAndOffset(WHITE, 0);
        barChart.setAreaFill(fill);

        System.out.println("Chart #" + testIndex + ":");
        System.out.println(barChart.toURLString());

        testIndex++;
        assertEquals(expSolution, linearSolutionPair.left);
        assertEquals(expSolution, threadSolutionPair.left);
        assertEquals(expSolution, parallelSolutionPair.left);
    }

    @Nested
    protected class NoChartTests {
        @ParameterizedTest
        @CsvFileSource(resources = "/tests.csv", numLinesToSkip = 1)
        public void linearSolutionTest(@ConvertWith(StringArrayConverter.class) int[] ps, boolean expSolution) {
            ImmutablePair<Boolean, Long> solutionPair = primeNumberChecker.linearSolution(ps);

            assertEquals(expSolution, solutionPair.left);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/tests.csv", numLinesToSkip = 1)
        public void threadSolutionTest(@ConvertWith(StringArrayConverter.class) int[] ps, boolean expSolution) {
            ImmutablePair<Boolean, long[]> solutionPair = primeNumberChecker.threadSolution(ps);

            assertEquals(expSolution, solutionPair.left);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/tests.csv", numLinesToSkip = 1)
        public void parallelSolutionTest(@ConvertWith(StringArrayConverter.class) int[] ps, boolean expSolution) {
            ImmutablePair<Boolean, Long> solutionPair = primeNumberChecker.parallelSolution(ps);

            assertEquals(expSolution, solutionPair.left);
        }
    }
}

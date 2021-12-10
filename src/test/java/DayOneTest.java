import org.testng.annotations.Test;

import java.util.Objects;

public class DayOneTest
{
    private static final String TEST_DATA_PATH = "src/main/resources/test.txt";
    private static final String INPUT_DATA_PATH = "src/main/resources/input.txt";

    @Test
    public void dayOneTestPartOne() {
        System.out.println(
                DayOne.deptIncreaseCounter(
                        Objects.requireNonNull(DayOne.getMeasurements(INPUT_DATA_PATH))
                )
        );
    }

    @Test
    public void dayOneTestPartTwo() {
        System.out.println(
                DayOne.depthIncreaseCounterExtended(
                        Objects.requireNonNull(DayOne.getMeasurements(INPUT_DATA_PATH))
                )
        );
    }
}

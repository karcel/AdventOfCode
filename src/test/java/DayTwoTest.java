import org.testng.annotations.Test;

import java.util.Objects;

public class DayTwoTest
{
    private static final String TEST_DATA_PATH = "src/main/resources/dayTwo/test.txt";
    private static final String INPUT_DATA_PATH = "src/main/resources/dayTwo/input.txt";

    @Test
    public void dayTwoTestPartOne()
    {
        System.out.println(
                "Final multiplier for given input is:\n" + DayTwo.getVectorSize(
                        DayTwo.calculateFinalPosition(
                                Objects.requireNonNull(DayTwo.getOrders(INPUT_DATA_PATH)), false
                        ))
        );
    }

    @Test
    public void dayTwoTestPartTwo()
    {
        System.out.println(
                "Final aimed multiplier for given input is:\n" + DayTwo.getVectorSize(
                        DayTwo.calculateFinalPosition(
                                Objects.requireNonNull(DayTwo.getOrders(INPUT_DATA_PATH)), true
                        ))
        );
    }

}

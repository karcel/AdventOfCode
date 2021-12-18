import org.testng.annotations.Test;

public class DayFourTest
{
    private static final String TEST_DATA_PATH = "src/main/resources/dayFour/test.txt";
    private static final String INPUT_DATA_PATH = "src/main/resources/dayFour/input.txt";

    @Test
    public void dayTwoTestPartOne()
    {
        DayFour dayFour = new DayFour(INPUT_DATA_PATH);
        dayFour.play();
    }

    @Test
    public void dayTwoTestPartTwo()
    {
        DayFour dayFour = new DayFour(INPUT_DATA_PATH);
        dayFour.playForSquid();
    }
}

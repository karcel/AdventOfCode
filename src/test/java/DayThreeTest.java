import org.testng.annotations.Test;

public class DayThreeTest
{
    private static final String TEST_DATA_PATH = "src/main/resources/dayThree/test.txt";
    private static final String INPUT_DATA_PATH = "src/main/resources/dayThree/input.txt";

    @Test
    public void dayTwoTestPartOne()
    {
        DayThree dayThree = new DayThree(INPUT_DATA_PATH);
        System.out.println("Total power consumption of the submarine is " + dayThree.getPowerConsumption());
    }

    @Test
    public void dayTwoTestPartTwo()
    {
        DayThree dayThree = new DayThree(INPUT_DATA_PATH);
        System.out.println("Life support rating of the submarine is: " + dayThree.getLifeSupportRating());
    }

}

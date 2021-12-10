import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.file.Files.readAllLines;

public class DayOne
{
    public static int deptIncreaseCounter(List<Integer> measurements)
    {
        return (int) IntStream.range(1, measurements.size())
                .filter(i -> measurements.get(i) > measurements.get(i - 1))
                .count();
    }

    public static List<Integer> getMeasurements(String filePath)
    {
        try {
            return Objects.requireNonNull(readAllLines(Paths.get(filePath))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int depthIncreaseCounterExtended(List<Integer> measurements)
    {
        return (int) IntStream.range(3, measurements.size())
                .filter(i ->
                        sum(measurements.get(i),
                                measurements.get(i - 1),
                                measurements.get(i - 2))
                                > sum(measurements.get(i - 1),
                                measurements.get(i - 2),
                                measurements.get(i - 3)))
                .count();
    }

    public static int depthIncreaseCounterOverExtended(List<Integer> measurements, Integer depth)
    {
        return (int) IntStream.range(depth+1, measurements.size()+1)
                .filter(i ->
                        sum(measurements.subList(i-depth, i))
                                > sum(measurements.subList(i-depth-1, i-1)))
                .count();
    }

    public static Integer sum(Integer... numbers)
    {
        return Arrays.stream(numbers).mapToInt(number -> number).sum();
    }

    public static Integer sum(List<Integer> numbers)
    {
        return numbers.stream().mapToInt(number -> number).sum();
    }

}

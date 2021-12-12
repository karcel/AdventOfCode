import org.testng.internal.collections.Pair;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class DayTwo
{
    public static List<Pair<String, Integer>> getOrders(String filePath)
    {
        try {
            return Objects.requireNonNull(readAllLines(Paths.get(filePath))
                    .stream()
                    .map(s ->
                            {
                                String[] tmp = s.split(" ");
                                return Pair.of(tmp[0], Integer.parseInt(tmp[1]));
                            }
                    )
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Pair<Integer, Integer> calculateFinalPosition(List<Pair<String, Integer>> orders, boolean aimed)
    {
        Integer horizontalPos = 0;
        Integer depth = 0;
        Integer aim = 0;

        if (aimed) {
            for (Pair<String, Integer> order : orders) {
                switch (order.first()) {
                    case "forward" -> {
                        horizontalPos += order.second();
                        depth += (aim * order.second());
                    }
                    case "down" -> aim += order.second();
                    case "up" -> aim -= order.second();
                }
            }
        } else {
            for (Pair<String, Integer> order : orders) {
                switch (order.first()) {
                    case "forward" -> horizontalPos += order.second();
                    case "down" -> depth += order.second();
                    case "up" -> depth -= order.second();
                }
            }
        }

        System.out.printf("Calculated position [x,y] = [%s,%s]%n", horizontalPos, depth);

        return Pair.of(horizontalPos, depth);
    }

    public static Integer getVectorSize(Pair<Integer, Integer> finalPosition)
    {
        return finalPosition.first() * finalPosition.second();
    }
}

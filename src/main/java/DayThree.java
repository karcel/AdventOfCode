import org.testng.internal.collections.Pair;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class DayThree
{
    List<String> measurements;
    Integer[] positionSums;

    public DayThree(String filePath)
    {
        measurements = getMeasurements(filePath);
        assert measurements != null;
        positionSums = new Integer[measurements.get(0).length()];
        Arrays.fill(positionSums, 0);

        System.out.println("Measurements provided:\n" + measurements);
        System.out.println("Initial positionSums set to:\n" + Arrays.toString(positionSums));
    }

    private List<String> getMeasurements(String filePath)
    {
        try {
            return readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void calculatePositionSums()
    {
        for (String measurement : measurements) {
            for (int i = 0; i < positionSums.length; i++) {
                if (measurement.charAt(i) == '1')
                    positionSums[i]++;
            }
        }
        System.out.println("Position sums calculated to:\n" + Arrays.toString(positionSums));
    }

    public Pair<Integer, Integer> provideRates()
    {
        Pair<Integer, Integer> rates = new Pair<>(provideRate(Rate.GAMMA), provideRate(Rate.EPSILON));
        System.out.printf("Provided rates are:\nGamma= %s\nEpsilon=%s\n", rates.first(), rates.second());
        return rates;
    }

    private Integer provideRate(Rate rate)
    {
        String valueBits = "";
        switch (rate) {
            case GAMMA:
                for (Integer pos : positionSums)
                    valueBits = valueBits.concat((pos * 2 > measurements.size()) ? "1" : "0");
                break;
            case EPSILON:
                for (Integer pos : positionSums)
                    valueBits = valueBits.concat((pos * 2 < measurements.size()) ? "1" : "0");
                break;
        }

        System.out.println(rate + " raw value is " + valueBits);

        return Integer.parseInt(valueBits, 2);
    }

    public Integer getPowerConsumption()
    {
        calculatePositionSums();

        Pair<Integer, Integer> rates = provideRates();

        return rates.first() * rates.second();
    }


    private enum Rate
    {
        GAMMA,
        EPSILON
    }
}

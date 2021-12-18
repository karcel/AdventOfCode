import org.testng.internal.collections.Pair;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;

public class DayThree
{
    List<String> measurements;

    public DayThree(String filePath)
    {
        try {
            measurements = readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read input data!!!\n" + e);
        }

        System.out.println("Measurements provided:\n" + measurements);
    }

    public Integer[] calculatePositionSums(List<String> values)
    {
        Integer[] positionSums = new Integer[values.get(0).length()];
        Arrays.fill(positionSums, 0);

        for (String measurement : values) {
            for (int i = 0; i < positionSums.length; i++) {
                if (measurement.charAt(i) == '1')
                    positionSums[i]++;
            }
        }
        System.out.println("Position sums calculated to:\n" + Arrays.toString(positionSums));
        return positionSums;
    }

    public Pair<Integer, Integer> provideRates()
    {
        Integer[] positionSums = calculatePositionSums(measurements);

        Pair<Integer, Integer> rates = new Pair<>(provideRate(PowerConsumptionRate.GAMMA, positionSums),
                provideRate(PowerConsumptionRate.EPSILON, positionSums));
        System.out.printf("Provided rates are:\nGamma= %s\nEpsilon=%s\n", rates.first(), rates.second());
        return rates;
    }

    private Integer provideRate(PowerConsumptionRate powerConsumptionRate, Integer[] positionSums)
    {
        String valueBits = "";
        switch (powerConsumptionRate) {
            case GAMMA:
                for (Integer pos : positionSums)
                    valueBits = valueBits.concat((pos * 2 > measurements.size()) ? "1" : "0");
                break;
            case EPSILON:
                for (Integer pos : positionSums)
                    valueBits = valueBits.concat((pos * 2 < measurements.size()) ? "1" : "0");
                break;
        }

        System.out.println(powerConsumptionRate + " raw value is " + valueBits);

        return Integer.parseInt(valueBits, 2);
    }

    public Integer getPowerConsumption()
    {
        Pair<Integer, Integer> rates = provideRates();

        return rates.first() * rates.second();
    }

    public Integer getLifeSupportRating()
    {
        Integer oxygenRating = calculateOxygenGeneratorRating();
        Integer co2ScrubberRating = calculateCO2ScrubberRating();
        System.out.println("Oxygen: " + oxygenRating);
        System.out.println("CO2: " + co2ScrubberRating);
        return oxygenRating * co2ScrubberRating;
    }

    public Integer calculateOxygenGeneratorRating()
    {
        return calculateLifeSupportRate(LifeSupportRate.OXYGEN);
    }

    public Integer calculateCO2ScrubberRating()
    {
        return calculateLifeSupportRate(LifeSupportRate.CO2);
    }

    public Integer calculateLifeSupportRate(LifeSupportRate lifeSupportRate)
    {
        System.out.println("Calculating life support rate for " + lifeSupportRate);
        return Integer.parseInt(
                findValue(measurements, 0, (lifeSupportRate.equals(LifeSupportRate.OXYGEN))), 2);
    }

    public String findValue(List<String> measurements, Integer currentBitPosition, boolean mostCommon)
    {
        Integer[] positionSums = calculatePositionSums(measurements);
        char mask = ((positionSums[currentBitPosition] * 2 >= measurements.size()) == mostCommon) ? '1' : '0';
        List<String> extractedValues = measurements.stream()
                .filter(x -> x.charAt(currentBitPosition) == mask).collect(Collectors.toList());
        if (extractedValues.size() > 1)
            return findValue(extractedValues, currentBitPosition + 1, mostCommon);
        return extractedValues.get(0);
    }

    private enum PowerConsumptionRate
    {
        GAMMA,
        EPSILON
    }

    private enum LifeSupportRate
    {
        OXYGEN,
        CO2
    }
}

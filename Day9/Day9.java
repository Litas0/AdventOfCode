package Day9;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day9/input9.txt"));
        int sum = 0;
        for(String line : allLines)
        {
            String[] numbers = line.split(" ");
            int[] values = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) values[i] = Integer.parseInt(numbers[i]);
            sum += Extrapolate(values);
        }
        System.out.println(sum);
    }
    private static int Extrapolate (int[] values) {
        boolean foundNotZero = false;
        for (int i = 0; i < values.length; i++) if (values[i] != 0) foundNotZero = true;
        if(!foundNotZero) return 0;
        else 
        {
            int newValues[] = new int[values.length - 1];
            for (int i = 0; i < newValues.length; i++) newValues[i] = values[i + 1] - values[i];
            return values[0] - Extrapolate(newValues);
        }
    }
}
package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day4 {
    

    public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("Day4/input4.txt"));
        HashSet<Integer> numbers = new HashSet<Integer>();
        int[] copies = new int[allLines.size()];
        Arrays.fill(copies,1);
        int n = 0;
        int index = 0;
        int won = 0;
        for (String line : allLines)
        {
            char[] charLine = line.substring(10).toCharArray();
            boolean winning = true;
            for(int i = 0; i < charLine.length; i++)
            {                 
                if (charLine[i] == '|') winning = false;
                else if (Character.isDigit(charLine[i])) n = n * 10 + Character.getNumericValue(charLine[i]);
                else if (winning && n != 0)
                {
                    numbers.add(n);
                    n = 0;
                }
                else if (n != 0)
                {
                    if(numbers.contains(n)) won++;
                    n = 0;
                }
            }
            if(numbers.contains(n)) won++;
            n = 0;
            numbers.clear();
            for(int i = index + 1; i <= index + won; i++)
            {
                copies[i] += copies[index];
            }
            index++;
            won = 0;
        }
        int sum = 0;
        for (int i : copies) sum += i;
        System.out.println(sum);
    }

}

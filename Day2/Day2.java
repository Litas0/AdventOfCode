package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {
      public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("Day2/input2.txt"));
        int sum = 0;
		for (String line : allLines)
        {
            int[] colorsRevealed = new int[3]; // 0- red 1-green 2-blue
            line = line.substring(8);
            for(String reveal : line.split(";"))
            {
                for(String color : reveal.split(","))
                {
                    color = color.trim();
                    int i = 0;
                    int n = Character.getNumericValue(color.charAt(i++));
                    if(Character.isDigit(color.charAt(i))) 
                    {
                        n = n * 10 + Character.getNumericValue(color.charAt(1));
                        i++;
                    }
                    i++;
                    if(color.charAt(i) == 'r') colorsRevealed[0] = Math.max(colorsRevealed[0], n);
                    else if (color.charAt(i) == 'g') colorsRevealed[1] = Math.max(colorsRevealed[1], n);
                    else if (color.charAt(i) == 'b') colorsRevealed[2] = Math.max(colorsRevealed[2], n);
                }
            }
            sum += colorsRevealed[0] * colorsRevealed[1] * colorsRevealed[2];
        }
            System.out.println(sum);
	}
}
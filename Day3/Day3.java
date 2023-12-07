package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {
    public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("Day3/input3.txt"));
        String[] lines = allLines.toArray(new String[allLines.size()]);
        int sum = 0;
        int counter = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '*') {
                    if (validate(lines, counter, i))
                    {
                        int ratio = 0;
                        for (int j = counter - 1; j < counter + 2; j++) {
                            for (int k = i - 1; k < i + 2; k++)
                            {
                                if(Character.isDigit(lines[j].charAt(k)))
                                {
                                    int start = numberStart(lines,j, k);
                                    int end = numberEnd(lines,j,k);
                                    int num = 0;
                                    while (start <= end)
                                    {
                                        num = num * 10 + Character.getNumericValue(lines[j].charAt(start++));
                                    }
                                    if(ratio == 0) ratio += num;
                                    else ratio *= num;
                                    k = end;
                                }
                            }
                        }
                        sum += ratio;
                    }
                }
            }
            counter++;
        }
        System.out.println(sum);
    }

    public static int numberStart(String[] lines, int counter, int index)
    {
        if (index == 0) return index;
        if (!Character.isDigit(lines[counter].charAt(index - 1))) return index;
        else return numberStart(lines, counter, index - 1);
    }

    public static int numberEnd(String[] lines, int counter, int index)
    {
        if (index == lines[counter].length() - 1) return index;
        if (!Character.isDigit(lines[counter].charAt(index + 1))) return index;
        else return numberEnd(lines, counter, index + 1);
    }

    public static boolean validate (String[] lines, int counter, int index)
    {
        int adjacent = 0;
        if (counter != 0 && counter != 139)
        {
            adjacent += isValid(lines, counter - 1, index) + isValid(lines, counter, index) + isValid(lines, counter + 1, index);
        }
        else if (counter == 0)
        {
            adjacent += isValid(lines, counter, index) + isValid(lines, counter + 1, index);
        }
        else
        {
            adjacent += isValid(lines, counter - 1, index) + isValid(lines, counter, index);
        }
        return adjacent == 2;
    }

    public static int isValid(String[] lines, int counter, int index){
        if(Character.isDigit(lines[counter].charAt(index - 1)) || Character.isDigit(lines[counter].charAt(index)) || Character.isDigit(lines[counter].charAt(index + 1)))
        {
            if(Character.isDigit(lines[counter].charAt(index - 1)) && !Character.isDigit(lines[counter].charAt(index)) && Character.isDigit(lines[counter].charAt(index + 1))) return 2;
            else return 1;
        }
        else return 0;
    }
}
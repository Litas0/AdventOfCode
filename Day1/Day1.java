package Day1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day1 {

	public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("Day1/input1.txt"));
            HashMap<String, Integer> numbers = new HashMap<String, Integer>();
            numbers.put("one",1);
            numbers.put("two",2);
            numbers.put("three",3);
            numbers.put("four",4);
            numbers.put("five",5);
            numbers.put("six",6);
            numbers.put("seven",7);
            numbers.put("eight",8);
            numbers.put("nine",9);
            int sum = 0;
		    for (String line : allLines)
            {
                int first = 0;
                int last = 0;
                StringBuilder value = new StringBuilder();
			    for (Character c : line.toCharArray())
                {
                    if (Character.isDigit(c))
                    {
                        if(first == 0) first = Character.getNumericValue(c);
                        last = Character.getNumericValue(c);    
                        value.delete(0,value.length());                   
                    }
                    else
                    {
                        value.append(c);
                        StringBuilder check = new StringBuilder(value.toString());
                        while(check.length() > 2)
                        {
                            if(numbers.containsKey(check.toString()))
                            {
                                if(first == 0) first = numbers.get(check.toString());
                                last = numbers.get(check.toString());
                                value.delete(0,value.length() - 1);
                                check.delete(0,check.length() - 1);
                            }
                            else check.deleteCharAt(0);
                        }
                        
                    }
                }
                sum = sum + 10*first + last;
                first = 0;
                last = 0;
            }
            System.out.println(sum);
        }
        catch (Exception e) {
        }
        finally
        {

        }
	}

}


package Day8;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day8 {

	public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day8/input8.txt"));
        char[] instructions = allLines.get(0).toCharArray();
        HashMap<String, String[]> nodes = new HashMap<String, String[]>();
        for (int i = 2; i < allLines.size(); i++) {
            String[] value = allLines.get(i).split("=")[1].trim().split(",");
            value[0] = value[0].substring(1);
            value[1] = value[1].substring(1, 4);
            nodes.put(allLines.get(i).split("=")[0].trim(), value);
        }
        String[] currents = new String[] {"DFA", "BLA", "TGA", "AAA", "PQA", "CQA"};
        int[] steps = new int[currents.length];
        for(int j = 0; j < steps.length; j++) {
            int i = 0;
            while(currents[j].charAt(2) != 'Z') 
            {   
                if(instructions[i] == 'L')
                {
                    currents[j] = nodes.get(currents[j])[0];
                }
                else if(instructions[i] == 'R')
                {
                     currents[j] = nodes.get(currents[j])[1];
                }
                i++;
                if (i == instructions.length) i = 0;
                steps[j]++;
            }
        }
        BigInteger[] bigSteps = new BigInteger[steps.length];
        for (int i = 0; i < steps.length; i++) bigSteps[i] = BigInteger.valueOf(steps[i]);
        BigInteger ans = BigInteger.valueOf(1);
        for (BigInteger bigStep : bigSteps) ans = lcm(ans, bigStep);
        System.out.println(ans);
    } 

    private static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }
}


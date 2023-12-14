package Day11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    private static long UniverseExpandCalculate(int startIndex, int endIndex, boolean[] dimension) {
        int distance = 0;
        while (startIndex < endIndex) {
            if(!dimension[startIndex]) distance += 999999;
            startIndex++;
        }
        return distance;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day11/input11.txt"));
        List<Integer[]> stars = new ArrayList<Integer[]>();
        boolean[] columns = new boolean[allLines.size()];
        boolean[] rows = new boolean[allLines.get(0).length()];
        for (int i = 0; i < allLines.size(); i++) {

            for (int j = 0; j < allLines.get(i).length(); j++) {

                if(allLines.get(i).charAt(j) == '#')
                {
                    columns[j] = true;
                    rows[i] = true;
                    stars.add(new Integer[]{i,j});
                }
            }
        }
        long distanceSum = 0;
        for (int i = 0; i < stars.size(); i++)
        {
            for (int j = i + 1; j < stars.size(); j++)
            {
                distanceSum += Math.abs(stars.get(i)[0] - stars.get(j)[0]) + Math.abs(stars.get(i)[1] - stars.get(j)[1]);
                distanceSum += UniverseExpandCalculate(Math.min(stars.get(i)[0], stars.get(j)[0]),Math.max(stars.get(i)[0], stars.get(j)[0]) ,rows);
                distanceSum += UniverseExpandCalculate(Math.min(stars.get(i)[1], stars.get(j)[1]),Math.max(stars.get(i)[1], stars.get(j)[1]) ,columns);
            }
        }
        System.out.println(distanceSum);
    }
}

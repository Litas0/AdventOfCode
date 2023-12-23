package Day21fail;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class Day21 {
    public static char[][] garden;
    public static HashSet<String> possibleLocations;
    public static void takeStep()
    {
        HashSet<String> newLocations = new HashSet<String>();
        for (String location : possibleLocations){
            int x = Integer.parseInt(location.split(",")[0]);
            int y = Integer.parseInt(location.split(",")[1]);
            if(isValidPosition(x + 1, y)) {
                String s = Integer.toString(x + 1) + "," + Integer.toString(y);
                newLocations.add(s);
            }
            if(isValidPosition(x - 1, y)) {
                String s = Integer.toString(x - 1) + "," + Integer.toString(y);
                newLocations.add(s);
            }
            if(isValidPosition(x, y + 1)) {
                String s = Integer.toString(x) + "," + Integer.toString(y + 1);
                newLocations.add(s);
            }
            if(isValidPosition(x, y - 1)) {
                String s = Integer.toString(x) + "," + Integer.toString(y - 1);
                newLocations.add(s);
            }
        }
        possibleLocations = newLocations;
    }
    public static boolean isValidPosition(int x, int y)
    {
        while (x < 0) x += garden.length;
        while (y < 0) y += garden[0].length;
        if(garden[x % garden.length][y % garden[0].length] == '#') return false;
        else return true;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day21/input21.txt"));
        garden = new char[allLines.size()][allLines.get(0).length()];
        possibleLocations = new HashSet<String>();
        int[] startingPosition = new int[2];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++){
                garden[i][j] = allLines.get(i).charAt(j);
                if (allLines.get(i).charAt(j) == 'S') {
                    startingPosition[0] = i;
                    startingPosition[1] = j;
                }
            }
        }
        garden[startingPosition[0]][startingPosition[1]] = '.';
        String start = Integer.toString(startingPosition[0]) + "," + Integer.toString(startingPosition[1]);
        possibleLocations.add(start);

        for (int i = 0; i < 26501365; i++) 
        {
            takeStep();
        }

        System.out.println(possibleLocations.size());
    }
}

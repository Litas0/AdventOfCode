package Day16;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day16 {
    /*
     * 0 - Right
     * 1 - Down
     * 2 - Left
     * 3 - Up
     */
    public static int FindBestEntrence(char[][] input, String[][] copy)
    {
        int ans = 0;
        for (int i = 0; i < input.length; i++) {
            Energize(input, copy, 0, new int[] {i,0});
            ans = Math.max(ans,CountEnergized(copy));
            copy = new String[input.length][input[0].length];
            Energize(input, copy, 2, new int[] {i,input[0].length - 1});
            ans = Math.max(ans,CountEnergized(copy));
            copy = new String[input.length][input[0].length];
        }
        for (int i = 0; i < input[0].length; i++) {
            Energize(input, copy, 1, new int[] {0,i});
            ans = Math.max(ans,CountEnergized(copy));
            copy = new String[input.length][input[0].length];
            Energize(input, copy, 3, new int[] {input.length - 1,i});
            ans = Math.max(ans,CountEnergized(copy));
            copy = new String[input.length][input[0].length];
        }
        return ans;
    }
    public static void Energize(char[][] input, String[][] copy,int direction, int[] location)
    {
        while (location[0] >= 0 && location[0] < input.length && location[1] >= 0 && location[1] < input[0].length) {
            if (copy[location[0]][location[1]] == null || !copy[location[0]][location[1]].contains(String.valueOf(direction)))
            {
                if (copy[location[0]][location[1]] == null) copy[location[0]][location[1]] = String.valueOf(direction);
                else copy[location[0]][location[1]] += String.valueOf(direction);
                switch (input[location[0]][location[1]]) {
                    case '.':
                        switch (direction) {
                            case 0:
                                location[1] += 1;
                                break;
                            case 1:
                                location[0] += 1;
                                break;
                            case 2:
                                location[1] -= 1;
                                break;
                            case 3:
                                location[0] -= 1;
                                break;
                        }
                        break;
                    case '/':
                        switch (direction) {
                            case 0:
                                direction = 3;
                                location[0] -= 1;
                                break;
                            case 1:
                                direction = 2;
                                location[1] -= 1;
                                break;
                            case 2:
                                direction = 1;
                                location[0] += 1;
                                break;
                            case 3:
                                direction = 0;
                                location[1] += 1;
                                break;
                        }
                        break;
                    case '\\':
                        switch (direction) {
                            case 0:
                                direction = 1;
                                location[0] += 1;
                                break;
                            case 1:
                                direction = 0;
                                location[1] += 1;
                                break;
                            case 2:
                                direction = 3;
                                location[0] -= 1;
                                break;
                            case 3:
                                direction = 2;
                                location[1] -= 1;
                                break;
                        }
                        break;
                    case '|':
                        switch (direction) {
                            case 0:
                                Energize(input, copy, 3, new int[] {location[0] - 1, location[1]});
                                direction = 1;
                                location[0] += 1;
                                break;
                            case 1:
                                location[0] += 1;
                                break;
                            case 2:
                                Energize(input, copy, 3, new int[] {location[0] - 1, location[1]});
                                direction = 1;
                                location[0] += 1;
                                break;
                            case 3:
                                location[0] -= 1;
                                break;
                        }
                        break;
                    case '-':
                        switch (direction) {
                            case 0:
                                location[1] += 1;
                                break;
                            case 1:
                                Energize(input, copy, 0, new int[] {location[0], location[1] + 1});
                                direction = 2;
                                location[1] -= 1;
                                break;
                            case 2:                          
                                location[1] -= 1;
                                break;
                            case 3:
                                Energize(input, copy, 0, new int[] {location[0], location[1] + 1});
                                direction = 2;
                                location[1] -= 1;
                                break;
                        }
                        break;
                }
            }
            else return;
        }
    }
    public static int CountEnergized(String[][] copy)
    {
        int ans = 0;
        for (int i = 0; i < copy.length; i++)
        {
            for (int j = 0; j < copy[0].length; j++)
            {
                if (copy[i][j] != null) {
                    ans++;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day16/input16.txt"));
        char[][] input = new char[allLines.size()][allLines.get(0).length()];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                input[i][j] = allLines.get(i).charAt(j);
            }
        }
        String[][] copy = new String[input.length][input[0].length];
        System.out.println(FindBestEntrence(input,copy));
    }
}

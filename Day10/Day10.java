package Day10;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day10 {
    private static int ReStart(int[] currentPosition, int[]lastPosition, int tryCount, int[] startingPosition){
        lastPosition[0] = startingPosition[0];
        lastPosition[1] = startingPosition[1];
        if (tryCount == 0) 
        {
            tryCount += 1;
            currentPosition[0] = startingPosition[0];
            currentPosition[1] = startingPosition[1] + 1;
        }
        else if (tryCount == 1) 
        {
            tryCount += 1;
            currentPosition[0] = startingPosition[0] + 1;
            currentPosition[1] = startingPosition[1];
        }
        else if (tryCount == 2) 
        {
            tryCount += 1;
            currentPosition[0] = startingPosition[0];
            currentPosition[1] = startingPosition[1] - 1;
        }
        else
        {
            tryCount += 1;
            currentPosition[0] = startingPosition[0] - 1;
            currentPosition[1] = startingPosition[1];
        }
        return tryCount;
    }
    private static int CountEnclosed(char[][] pipes)
    {
        boolean inside = false;
        int count = 0;
        for (char[] line : pipes)
        {
            for (char c : line)
            {
                if(c == '|' || c == '7' || c == 'F' || c == 'S') inside = !inside;
                else if (inside && c != 'L' && c != 'J' && c != '-') count++;
            }
        }
        return count;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day10/input10.txt"));
        char[][] pipes = new char[allLines.size()][allLines.get(0).length()];
        char[][] pipesRoad = new char[allLines.size()][allLines.get(0).length()];
        int[] startingPosition = new int[2];
        for (int i = 0; i < pipes.length; i++) {
            for (int j = 0; j < pipes[i].length; j++) {
                pipes[i][j] = allLines.get(i).charAt(j);
                pipesRoad[i][j] = '*';
                if(pipes[i][j] == 'S') 
                {
                    startingPosition[0] = i;
                    startingPosition[1] = j;
                }
            }
        }
        int tryCount = 0;
        int distance = 1;
        int[] lastPosition = new int[2];
        int[] currentPosition = new int[2];
        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
        while(currentPosition[0] != startingPosition[0] || currentPosition[1] != startingPosition[1])
        {
            distance += 1;
            int[] tmp = new int[]{currentPosition[0],currentPosition[1]};
            switch (pipes[currentPosition[0]][currentPosition[1]]) {
                case '|':
                    if (lastPosition[0] == currentPosition[0] - 1 && lastPosition[1] == currentPosition[1])
                    {
                        currentPosition[0] += 1;
                        lastPosition = tmp;
                    } 
                    else if (lastPosition[0] == currentPosition[0] + 1 && lastPosition[1] == currentPosition[1]) 
                    {
                        currentPosition[0] -= 1;
                        lastPosition = tmp;
                    }
                    else
                    {
                        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                        distance = 0;
                    }
                    break;
                case '-':
                    if (lastPosition[0] == currentPosition[0] && lastPosition[1] == currentPosition[1] - 1) 
                    {
                        currentPosition[1] += 1;
                        lastPosition = tmp;
                    }
                    
                    else if (lastPosition[0] == currentPosition[0] && lastPosition[1] == currentPosition[1] + 1)
                    {
                        currentPosition[1] -= 1;
                        lastPosition = tmp;
                    }
                    else
                    {
                        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                        distance = 0;
                    }
                    break;
                case 'L':
                    if (lastPosition[0] == currentPosition[0] - 1 && lastPosition[1] == currentPosition[1])
                    {
                        currentPosition[1] += 1;
                        lastPosition = tmp;
                    } 
                    else if (lastPosition[0] == currentPosition[0] && lastPosition[1] == currentPosition[1] + 1)
                    {
                        currentPosition[0] -= 1;
                        lastPosition = tmp;
                    } 
                    else
                    {
                        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                        distance = 0;
                    }
                    break;
                case 'J':
                    if (lastPosition[0] == currentPosition[0] - 1 && lastPosition[1] == currentPosition[1])
                    {
                        currentPosition[1] -= 1;
                        lastPosition = tmp;
                    } 
                    else if (lastPosition[0] == currentPosition[0] && lastPosition[1] == currentPosition[1] - 1)
                    {
                        currentPosition[0] -= 1;
                        lastPosition = tmp;
                    } 
                    else
                    {
                        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                        distance = 0;
                    }
                    break;
                case '7':
                    if (lastPosition[0] == currentPosition[0] + 1 && lastPosition[1] == currentPosition[1])
                    {
                        currentPosition[1] -= 1;
                        lastPosition = tmp;
                    } 
                    else if (lastPosition[0] == currentPosition[0] && lastPosition[1] == currentPosition[1] - 1)
                    {
                        currentPosition[0] += 1;
                        lastPosition = tmp;
                    } 
                    else
                    {
                        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                        distance = 0;
                    }
                    break;
                case 'F':
                    if (lastPosition[0] == currentPosition[0] + 1 && lastPosition[1] == currentPosition[1])
                    {
                        currentPosition[1] += 1;
                        lastPosition = tmp;
                    } 
                    else if (lastPosition[0] == currentPosition[0] && lastPosition[1] == currentPosition[1] + 1)
                    {
                        currentPosition[0] += 1;
                        lastPosition = tmp;
                    } 
                    else
                    {
                        tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                        distance = 0;
                    }
                    break;
                case '.':
                    tryCount = ReStart(currentPosition, lastPosition, tryCount, startingPosition);
                    distance = 0;
                    break;
            }
            pipesRoad[lastPosition[0]][lastPosition[1]] = pipes[lastPosition[0]][lastPosition[1]];
        }
        pipesRoad[lastPosition[0]][lastPosition[1]] = pipes[lastPosition[0]][lastPosition[1]];
        pipesRoad[startingPosition[0]][startingPosition[1]] = 'S';
        System.out.println(distance/2);
        System.out.println(CountEnclosed(pipesRoad));
    }
}

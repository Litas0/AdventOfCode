package Day14;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    private static Map<String, Long> configurations = new HashMap<String, Long>();

    private static char[][] ShiftN(char[][] rocks) {
        int lastStaticRockIndex = 0;
        int rocksCount = 0;
        for (int i = 0 ; i < rocks[0].length ; i++)
        {
            for (int j = 0 ; j < rocks.length ; j++)
            {
                if (rocks[j][i] == 'O') 
                {
                    rocks[j][i] = '.';
                    rocksCount++;
                }
                else if (rocks[j][i] == '#') 
                {
                    while (rocksCount > 0) {
                        rocks[lastStaticRockIndex][i] = 'O';
                        lastStaticRockIndex++;
                        rocksCount--;
                    }
                    lastStaticRockIndex = j + 1;
                }
            }
            while (rocksCount > 0) {
                rocks[lastStaticRockIndex][i] = 'O';
                lastStaticRockIndex++;
                rocksCount--;
            }
            lastStaticRockIndex = 0;
        }
        return rocks;
    }
    private static char[][] ShiftS(char[][] rocks) {
        int lastStaticRockIndex = rocks.length - 1;
        int rocksCount = 0;
        for (int i = rocks[0].length - 1; i >= 0 ; i--)
        {
            for (int j = rocks.length - 1; j >= 0; j--)
            {
                if (rocks[j][i] == 'O') 
                {
                    rocks[j][i] = '.';
                    rocksCount++;
                }
                else if (rocks[j][i] == '#') 
                {
                    while (rocksCount > 0) {
                        rocks[lastStaticRockIndex][i] = 'O';
                        lastStaticRockIndex--;
                        rocksCount--;
                    }
                    lastStaticRockIndex = j - 1;
                }
            }
            while (rocksCount > 0) {
                rocks[lastStaticRockIndex][i] = 'O';
                lastStaticRockIndex--;
                rocksCount--;
            }
            lastStaticRockIndex = rocks.length - 1;
        }
        return rocks;
    }
    private static char[][] ShiftW(char[][] rocks) {
        int lastStaticRockIndex = 0;
        int rocksCount = 0;
        for (int i = 0 ; i < rocks.length ; i++)
        {
            for (int j = 0 ; j < rocks[0].length ; j++)
            {
                if (rocks[i][j] == 'O') 
                {
                    rocks[i][j] = '.';
                    rocksCount++;
                }
                else if (rocks[i][j] == '#') 
                {
                    while (rocksCount > 0) {
                        rocks[i][lastStaticRockIndex] = 'O';
                        lastStaticRockIndex++;
                        rocksCount--;
                    }
                    lastStaticRockIndex = j + 1;
                }
            }
            while (rocksCount > 0) {
                rocks[i][lastStaticRockIndex] = 'O';
                lastStaticRockIndex++;
                rocksCount--;
            }
            lastStaticRockIndex = 0;
        }
        return rocks;
    }
    private static char[][] ShiftE(char[][] rocks) {
        int lastStaticRockIndex = rocks[0].length - 1;
        int rocksCount = 0;
        for (int i = rocks.length - 1; i >= 0 ; i--)
        {
            for (int j = rocks[0].length - 1; j >= 0; j--)
            {
                if (rocks[i][j] == 'O') 
                {
                    rocks[i][j] = '.';
                    rocksCount++;
                }
                else if (rocks[i][j] == '#') 
                {
                    while (rocksCount > 0) {
                        rocks[i][lastStaticRockIndex] = 'O';
                        lastStaticRockIndex--;
                        rocksCount--;
                    }
                    lastStaticRockIndex = j - 1;
                }
            }
            while (rocksCount > 0) {
                rocks[i][lastStaticRockIndex] = 'O';
                lastStaticRockIndex--;
                rocksCount--;
            }
            lastStaticRockIndex = rocks.length - 1;
        }
        return rocks;
    }
    private static String RocksToString(char[][] rocks)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < rocks.length ; i++)
        {
            for (int j = 0 ; j < rocks[i].length ; j++)
            {
                sb.append(rocks[i][j]);
            }
        }
        return sb.toString();
    }
    private static int Cycle(char[][] rocks, long times)
    {
        for (long i = 0; i < times; i++)
        {
            rocks = ShiftE(ShiftS(ShiftW(ShiftN(rocks))));
            String str = RocksToString(rocks);
            if (configurations.containsKey(str))
            {
                long delta = i - configurations.get(str);
				i += delta * ((1000000000-i) / delta);
            }
            configurations.put(str, i);
        }
        return CalculateLoad(rocks);
    }
    private static int CalculateLoad(char[][] rocks)
    {
        int ans = 0;
        for (int i = 0 ; i < rocks.length ; i++)
        {
            for (int j = 0 ; j < rocks[i].length ; j++)
            {
                if(rocks[i][j] == 'O')
                {
                    ans += rocks.length - i;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day14/input14.txt"));
        char[][] rocks = new char[allLines.size()][];
        for (int i = 0 ; i < allLines.size() ; i++)
        {
            rocks[i] = allLines.get(i).toCharArray();
        }
        System.out.println(Cycle(rocks, 1000000000));
    }
}

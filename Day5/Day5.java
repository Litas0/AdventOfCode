package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("Day5/input5.txt"));
        String[] preSeeds = allLines.get(0).substring(7).split(" ");
        int[] index = new int[]{3};
        int x = 0;
        List[] maps = new List[7];
        while(index[0] < allLines.size())
        {
            maps[x] = createMap(allLines, index);
            x++;
        }

        long minimum = Long.MAX_VALUE;
        for (int i = 0; i < preSeeds.length; i+=2)
        {
            for(Long j = Long.parseLong(preSeeds[i]); j < Long.parseLong(preSeeds[i]) + Long.parseLong(preSeeds[i + 1]); j++)
            {
                long tmp = j;
                for (List<Long[]> map : maps)
                {
                    tmp = convert(tmp, map);
                }
                minimum = Math.min(minimum, tmp);
            }
            
        }
        System.out.println(minimum);   
    }

    public static List<Long[]> createMap(List<String> allLines, int[] index) {
        List<Long[]> map = new ArrayList<>();
        while(index[0] < allLines.size() && allLines.get(index[0]).length() != 0) {
            Long[] tmp = new Long[3];
            for (int j = 0; j < 3; j++) tmp[j] = Long.parseLong(allLines.get(index[0]).split(" ")[j]);
            map.add(tmp);
            index[0]++;
        }
        index[0] += 2;
        return map;
    }

    public static Long convert(Long seed, List<Long[]> map) {   
        for(int j = 0; j < map.size(); j++)
        {
            if(seed >= map.get(j)[1] && seed <= map.get(j)[1] + map.get(j)[2])
            {
                seed = map.get(j)[0] + seed - map.get(j)[1];
                j += map.size();
            }
        }
    return seed;
    }
}

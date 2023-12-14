package Day13;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    private static int ReflectRows(String[] pattern)
    {
        boolean firstError = true;
        boolean reflect = true;
        for (int p = 1; p < pattern.length; p++)
        {
            firstError = true;
            for (int i = 0; p + i < pattern.length && p - i > 0; i++)
            {
                reflect = true;
                for (int j = 0; j < pattern[0].length(); j++)
                {
                    if(pattern[p - i - 1].charAt(j) != pattern[p + i].charAt(j)) 
                    {
                        if (firstError) firstError = false;
                        else 
                        {
                            reflect = false;
                            j += 1000;
                        }                      
                    }
                }
                if (!reflect) i += 1000;
            }
            if (reflect && !firstError) return p * 100;
        }
        return 0;
    }

    private static int ReflectColumns(String[] pattern)
    {
        boolean firstError = true;
        boolean reflect = true;
        for (int p = 1; p < pattern[0].length(); p++)
        {
            firstError = true;
            for (int i = 0; p + i < pattern[0].length() && p - i > 0; i++)
            {
                reflect = true;
                for (int j = 0; j < pattern.length; j++)
                {
                    if(pattern[j].charAt(p - i - 1) != pattern[j].charAt(p + i)) 
                    {
                        if (firstError) firstError = false;
                        else 
                        {
                            reflect = false;
                            j += 1000;
                        }                      
                    }
                }
                if (!reflect) i += 1000;
            }
            if (reflect && !firstError) return p;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day13/input13.txt"));
        List<String[]> patterns = new ArrayList<String[]>();
        List<String> pattern = new ArrayList<String>();
        for (String line : allLines) {
            if(line.length() != 0) pattern.add(line);
            else
            {
                patterns.add(pattern.toArray(String[]::new));
                pattern.clear();
            }
        }
        patterns.add(pattern.toArray(String[]::new));
        long ans = 0;
        for (String[] p : patterns)
        {
            int tmp = ReflectRows(p);
            if (tmp != 0)  ans += tmp;
            else ans += ReflectColumns(p);
        }
        System.out.println(ans);
    }
}
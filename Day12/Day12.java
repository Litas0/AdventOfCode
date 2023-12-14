package Day12;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day12 {

    static Map<String, Long> endingCache = new HashMap<>();
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day12/input12.txt"));
        long sum = 0;
        for(String line : lines) {
            int repeat = 5; // 1 for part 1.
            String[] parts = line.split(" ");
            parts[0] = (parts[0]+"?").repeat(repeat);
            parts[0] = parts[0].substring(0, parts[0] .length()-1);
            parts[1] = (parts[1]+",").repeat(repeat);
            parts[1] = parts[1].substring(0, parts[1].length()-1);
            List<Integer> targetConfiguration = Arrays.stream(parts[1].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int[] minimalGroups = new int[targetConfiguration.size() * 2 + 1];
            for(int i = 1; i < minimalGroups.length-1; i++) {
                minimalGroups[i] = (i%2==0) ? 1 : targetConfiguration.get(i/2);
            }
            int emptySpotsToFill = parts[0].length() - Arrays.stream(minimalGroups).sum();
            sum += countSprings(emptySpotsToFill, 0, minimalGroups, parts[0]);
            endingCache.clear();
        }
        System.out.println(sum);
    }
    private static long countSprings(int emptySpotsToFill, int groupId, int[] groups, String target) {
        if(emptySpotsToFill == 0) return isValidUpTo(groups.length, groups, target) ? 1 : 0;
        if(groupId > groups.length) return 0;
        long solutions = 0;
        for(int amountFilled = 0; amountFilled <= emptySpotsToFill; amountFilled++) {
            groups[groupId] += amountFilled;
            if(isValidUpTo(groupId + 1, groups, target)) {
                String key = (groupId+2)+" "+(emptySpotsToFill-amountFilled);
                if(!endingCache.containsKey(key)) {
                    endingCache.put(key, countSprings(emptySpotsToFill - amountFilled, groupId + 2, groups, target));
                }
                solutions += endingCache.get(key);
            }
            groups[groupId] -= amountFilled;
        }
        return solutions;
    }
    private static boolean isValidUpTo(final int groupPtr, final int[] groups, final String target) {
        String result = "";
        for (int x = 0; x < groupPtr; x++) {
            result +=  ((x % 2 == 0)?".":"#").repeat(groups[x]);
        }
        for (int x = 0; x < result.length(); x++) {
            if (target.charAt(x) != '?' && result.charAt(x) != target.charAt(x)) {
                return false;
            }
        }
        return true;
    }
}

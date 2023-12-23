package Day19;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day19 {
    public static long MapRangeOfParts(HashMap<String,String> workflows, String target, long[][] range)
    {
        long ans = 0;
        if (target.charAt(0) == 'R' || (range[0][1] - range[0][0]) == 0 || (range[1][1] - range[1][0]) == 0 || (range[2][1] - range[2][0]) == 0 || (range[3][1] - range[3][0]) == 0) return 0;
        else if (target.charAt(0) == 'A') return (range[0][1] - range[0][0]) * (range[1][1] - range[1][0]) * (range[2][1] - range[2][0]) * (range[3][1] - range[3][0]);
        else 
        {
            String[] rules = workflows.get(target).split(",");
            for (int i = 0; i < rules.length; i++) {
                if (i == rules.length - 1) ans += MapRangeOfParts(workflows, rules[i], range);
                else  
                {
                    boolean more = rules[i].charAt(1) == '>';
                    String[] rule = rules[i].substring(2).split(":");
                    switch (rules[i].charAt(0)) {
                        case 'x':    
                            Integer number1 = Integer.parseInt(rule[0]);
                            long[][] fufillsConditionRange1 = range.clone();
                            if(more) {                             
                                range[0] = new long[]{range[0][0], number1};
                                fufillsConditionRange1[0] = new long[]{number1, fufillsConditionRange1[0][1]};
                            }
                            else {
                                range[0] = new long[]{number1 - 1, range[0][1]};
                                fufillsConditionRange1[0] = new long[]{fufillsConditionRange1[0][0], number1 - 1};
                            }
                            ans += MapRangeOfParts(workflows, rule[1], fufillsConditionRange1);
                            break;
                        case 'm':
                            Integer number2 = Integer.parseInt(rule[0]);
                            long[][] fufillsConditionRange2 = range.clone();
                            if(more) {                             
                                range[1] = new long[]{range[1][0], number2};
                                fufillsConditionRange2[1] = new long[]{number2, fufillsConditionRange2[1][1]};
                            }
                            else {
                                range[1] = new long[]{number2 - 1, range[1][1]};
                                fufillsConditionRange2[1] = new long[]{fufillsConditionRange2[1][0], number2 - 1};
                            }
                            ans += MapRangeOfParts(workflows, rule[1], fufillsConditionRange2);
                            break;
                        case 'a':
                            Integer number3 = Integer.parseInt(rule[0]);
                            long[][] fufillsConditionRange3 = range.clone();
                            if(more) {                             
                                range[2] = new long[]{range[2][0], number3};
                                fufillsConditionRange3[2] = new long[]{number3, fufillsConditionRange3[2][1]};
                            }
                            else {
                                range[2] = new long[]{number3 - 1, range[2][1]};
                                fufillsConditionRange3[2] = new long[]{fufillsConditionRange3[2][0], number3 - 1};
                            }
                            ans += MapRangeOfParts(workflows, rule[1], fufillsConditionRange3);
                            break;
                        case 's':
                            Integer number4 = Integer.parseInt(rule[0]);
                            long[][] fufillsConditionRange4 = range.clone();
                            if(more) {                             
                                range[3] = new long[]{range[3][0], number4};
                                fufillsConditionRange4[3] = new long[]{number4, fufillsConditionRange4[3][1]};
                            }
                            else {
                                range[3] = new long[]{number4 - 1, range[3][1]};
                                fufillsConditionRange4[3] = new long[]{fufillsConditionRange4[3][0], number4 - 1};
                            }
                            ans += MapRangeOfParts(workflows, rule[1], fufillsConditionRange4);
                            break;
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day19/input19.txt"));
        HashMap<String,String> workflows = new HashMap<String,String>();
        for (String line : allLines) {
            String[] l = line.split(" ");
            workflows.put(l[0], l[1]);
        }
        long[][] range = new long[][]{new long[]{0,4000},new long[]{0,4000},new long[]{0,4000},new long[]{0,4000}};
        long ans = MapRangeOfParts(workflows, "in", range);
        System.out.println(ans);
    }
}
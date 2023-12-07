package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static int GetCardValue(char c){
        switch (c) {
            case 'J':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;
            case '9':
                return 8;
            case 'T':
                return 9;
            case 'Q':
                return 10;
            case 'K':
                return 11;
            case 'A':
                return 12;
            default:
                throw new IllegalArgumentException();
        }
    }
    public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("Day7/input7.txt"));
        List<List<String>> types = new ArrayList<List<String>>();
        for (int i = 0; i < 7 ; i ++) types.add(new ArrayList<String>());
        /*
         * 0 - high card
         * 1 - one pair
         * 2 - two pairs
         * 3 - three of a kind
         * 4 - Full house
         * 5 - Four of a kind
         * 6 - Five of a kind
         */
        HashMap<Character, Integer> values = new HashMap<Character, Integer>();
        for (String line : allLines) {
            char[] hand = line.split(" ")[0].toCharArray();
            for (char c : hand) {
                if(!values.containsKey(c)) values.put(c,1);
                else values.replace(c, values.get(c) + 1);
            }
            if (values.containsKey('J')) {
                int j = values.get('J');
                if (j != 5)
                {
                    values.remove('J');
                    values.replace(Collections.max(values.entrySet(), Map.Entry.comparingByValue()).getKey(), Collections.max(values.entrySet(), Map.Entry.comparingByValue()).getValue() + j);
                }                
            }
            switch (Collections.max(values.entrySet(), Map.Entry.comparingByValue()).getValue()) {
                case 1:
                    types.get(0).add(line);
                    break;
                case 2:
                    values.remove(Collections.max(values.entrySet(), Map.Entry.comparingByValue()).getKey());
                    if(values.containsValue(2)) types.get(2).add(line);
                    else types.get(1).add(line);
                    break;
                case 3:
                    if(values.containsValue(2)) types.get(4).add(line);
                    else types.get(3).add(line);
                    break;
                case 4:
                    types.get(5).add(line);
                    break;
                case 5:
                    types.get(6).add(line);
                    break;
                default:
                    System.out.println("Coś jest nie tak");
                    break;
            }
            values.clear();
        }
        Comparator<String> comparator = new Comparator<String>(){
            @Override
            public int compare(String firstHand, String secondHand) {
            int i = 0;
            while (true) {
                if(GetCardValue(firstHand.charAt(i)) > GetCardValue(secondHand.charAt(i))) return 1;
                else if (GetCardValue(firstHand.charAt(i)) < GetCardValue(secondHand.charAt(i))) return -1;
                else i++;
            }
            }
        };
        int rank = 1;
        long win = 0;
        for (List<String> type : types) 
        {
            type.sort(comparator);
            for (String s : type)
            {
                win += rank * Integer.parseInt(s.split(" ")[1]);
                rank++;
            }
        }
        System.out.println(win);
    }
}

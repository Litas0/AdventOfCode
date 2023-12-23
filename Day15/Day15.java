import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day15 {
    public static ArrayList<String>[] boxes = new ArrayList[256];
    private static void FillBoxes(){
        for (int i = 0; i < boxes.length; i++) boxes[i] = new ArrayList<String>();
    }
    private static void RemoveLens(int number,String label) {
        ArrayList<String> box = boxes[number];
        for(int i = 0; i < box.size(); i++){
            if(box.get(i).substring(1).equals(label))
            {
                for(int j = i + 1; j < box.size(); j++)
                {
                    box.set(j - 1, box.get(j));
                }
                box.remove(box.size() - 1);
                i += box.size();
            }
        }
    }
    private static void InsertLens(int number,String label,char focalLength) {
        ArrayList<String> box = boxes[number];
        for(int i = 0; i < box.size(); i++){
            if(box.get(i).substring(1).equals(label))
            {
                box.set(i,focalLength + label);
                return;
            }
        }
        box.add(focalLength + label);
    }
    private static long CalculateFocusingPower(ArrayList<String>[] boxes)
    {
        long ans = 0;
        for (int i = 0; i < boxes.length; i++)
        {
            for(int j = 0; j < boxes[i].size(); j++)
            {
                ans += (1 + i) * (j + 1) * Character.getNumericValue(boxes[i].get(j).charAt(0));
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        String[] hashes = Files.readAllLines(Paths.get("Day15/input15.txt")).get(0).split(",");
        FillBoxes();
        for (String hash : hashes) {
            int number = 0;
            StringBuffer label = new StringBuffer();
            for (int i = 0; i < hash.length(); i++) {
            
                if(Character.isLetter(hash.charAt(i)))
                {
                    label.append(hash.charAt(i));
                    number += hash.charAt(i);
                    number *= 17;
                    number = number % 256;
                }
                else if(hash.charAt(i) == '-') RemoveLens(number, label.toString());
                else if(hash.charAt(i) =='=') InsertLens(number,label.toString(),hash.charAt(++i));
            }
        }
        System.out.println(CalculateFocusingPower(boxes));
    }
}

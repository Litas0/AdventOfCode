package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day6 {
    public static void main(String[] args) throws IOException {
        long time = Long.parseLong(Files.readAllLines(Paths.get("Day6/input6.txt")).get(0).substring(13).replaceAll(" ",""));
        long distance = Long.parseLong(Files.readAllLines(Paths.get("Day6/input6.txt")).get(1).substring(12).replaceAll(" ",""));
        long waysToBeat = 0;
        boolean beaten = false;
        for (long j = 0 ; j < time; j++) {
            if(j * (time - j) > distance)
            {
                waysToBeat++;
                beaten = true;
            } 
            else if (beaten) j += time;
        }
        System.out.println(waysToBeat);
    }
}

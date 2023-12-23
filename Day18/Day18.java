package Day18;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day18 {
    public static long calculateArea(List<long[][]> edges){
        long area = 0;
        for (long[][] edge : edges) {
            long[] x1 = new long[]{edge[0][0], edge[0][1]};
            long[] x2 = new long[]{edge[1][0], edge[1][1]};
            area += x1[1] * x2[0] - x1[0] * x2[1];
        }
        return Math.abs(area) / 2;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day18/input18.txt"));
        long[][] input = new long[allLines.size()][2];
        for (int i = 0; i < allLines.size(); i++) {
            input[i][0] = Long.parseLong(allLines.get(i).split(" ")[2].substring(7,8));
            input[i][1] = Long.parseLong(allLines.get(i).split(" ")[2].substring(2,7),16);
        }
        List<long[][]> edges = new ArrayList<long[][]>();
        long[] current = new long[] {0,0};
        long[] previous = new long[] {0,0};
        long answer = 0;
        for (long[] line : input) {
            previous[0] = current[0];
            previous[1] = current[1];
            long n = line[1];
            int d = (int)line[0];
            switch (d) {
                case 0:
                    current[1] += n;
                    break;
                case 2:
                    current[1] -= n;
                    break;
                case 3:
                    current[0] -= n;
                    break;
                case 1:
                    current[0] += n;
                    break;
              }
            edges.add(new long[][]{previous.clone(), current.clone()});
            answer += n;
        }

        long area = calculateArea(edges);
        long square_area = area - (answer / 2) + 1;
        answer += square_area;

        System.out.println(answer);
    }
}

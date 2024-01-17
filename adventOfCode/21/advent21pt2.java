import java.io.*;
import java.util.*;

public class Advent21pt2 {
    public static void main(String[] args) throws Exception {
        int size = 131;
        char[][] nums = new char[size][size];
        int sr = -1, sc = -1;

        File file = new File("C:\\Users\\18438\\Desktop\\adventDay21.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter = 0;

        while ((s = br.readLine()) != null) {
            for (int i = 0; i < s.length(); i++) {
                nums[counter][i] = s.charAt(i);
                if (s.charAt(i) == 'S') {
                    sr = counter;
                    sc = i;
                }
            }
            counter++;
        }

        long steps = 26501365;

        assert sr == sc && sr == size / 2;
        assert steps % size == size / 2;

        long gridWidth = steps / size - 1;

        long odd = (gridWidth / 2 * 2 + 1) * (gridWidth / 2 * 2 + 1);
        long even = ((gridWidth + 1) / 2 * 2) * ((gridWidth + 1) / 2 * 2);

        long oddPoints = fill(nums, sr, sc, size * 2 + 1);
        long evenPoints = fill(nums, sr, sc, size * 2);

        long cornerT = fill(nums, size - 1, sc, size - 1);
        long cornerR = fill(nums, sr, 0, size - 1);
        long cornerB = fill(nums, 0, sc, size - 1);
        long cornerL = fill(nums, sr, size - 1, size - 1);

        long smallTR = fill(nums, size - 1, 0, size / 2 - 1);
        long smallTL = fill(nums, size - 1, size - 1, size / 2 - 1);
        long smallBR = fill(nums, 0, 0, size / 2 - 1);
        long smallBL = fill(nums, 0, size - 1, size / 2 - 1);

        long largeTR = fill(nums, size - 1, 0, size * 3 / 2 - 1);
        long largeTL = fill(nums, size - 1, size - 1, size * 3 / 2 - 1);
        long largeBR = fill(nums, 0, 0, size * 3 / 2 - 1);
        long largeBL = fill(nums, 0, size - 1, size * 3 / 2 - 1);

        long result = odd * oddPoints +
                even * evenPoints +
                cornerT + cornerR + cornerB + cornerL +
                (gridWidth + 1) * (smallTR + smallTL + smallBR + smallBL) +
                gridWidth * (largeTR + largeTL + largeBR + largeBL);

        System.out.println(result);
    }

    static long fill(char[][] grid, int sr, int sc, int ss) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        long ans = 0;
        long size = grid.length;
        Set<String> seen = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, ss});
        seen.add(sr + "," + sc);

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int s = curr[2];

            if (s % 2 == 0) {
                ans++;
            }
            if (s == 0) {
                continue;
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < size && nc >= 0 && nc < size && grid[nr][nc] != '#' && !seen.contains(nr + "," + nc)) {
                    seen.add(nr + "," + nc);
                    queue.offer(new int[]{nr, nc, s - 1});
                }
            }
        }
        return ans;
    }
}

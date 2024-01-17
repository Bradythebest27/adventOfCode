import java.io.*;
import java.util.*;

public class AdventDay23Pt2ButBetter {

    static int size = 141;
    static int maxPathLength = 0;

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay23.2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        char[][] nums = new char[size][size];
        int counter = 0;

        String s;
        while ((s = br.readLine()) != null) {
            for (int i = 0; i < s.length(); i++) {
                nums[counter][i] = s.charAt(i);
            }
            counter++;
        }

        int longestHike = findLongestHike(nums);
        System.out.println("Longest hike length: " + longestHike);
    }

    public static int findLongestHike(char[][] nums) {
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Set<String> visited = new HashSet<>();
        dfs(nums, 0, 1, directions, visited, 1);
        return maxPathLength;
    }

    public static void dfs(char[][] nums, int r, int c, int[][] directions,
                           Set<String> visited, int pathLength) {
        if (r == size - 1 && c == size - 2) {
            maxPathLength = Math.max(maxPathLength, pathLength);
            return;
        }

        visited.add(r + "," + c);
        for (int[] dir : directions) {
            int adjRow = r + dir[0];
            int adjCol = c + dir[1];
            if (isValid(nums, adjRow, adjCol, visited)) {
                dfs(nums, adjRow, adjCol, directions, visited, pathLength + 1);
            }
        }
        visited.remove(r + "," + c);
    }

    public static boolean isValid(char[][] nums, int r, int c, Set<String> visited) {
        return r >= 0 && r < size && c >= 0 && c < size &&
                nums[r][c] != '#' && !visited.contains(r + "," + c);
    }
}

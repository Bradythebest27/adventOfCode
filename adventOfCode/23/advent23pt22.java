import java.io.*;
import java.util.*;

public class Advent23pt22 {

    static final int size = 141;

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay23.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter = 0;
        char[][] nums = new char[size][size];
        while ((s = br.readLine()) != null) {
            if (counter < size) {
                for (int i = 0; i < size; i++) {
                    nums[counter][i] = s.charAt(i);
                }
            }
            counter++;
        }
        nums[0][1] = '#';
        System.out.println(findLongestPath(nums));
    }

    public static int findLongestPath(char[][] nums) {
        int longestPath = 0;
        Set<Integer> visited = new HashSet<>();
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(1, 1, 1, 0));

        while (!stack.isEmpty()) {
            Point current = stack.pop();
            int row = current.row;
            int col = current.col;
            int length = current.length;
            int accumulator = current.accumulator;

            visited.add(hash(row, col));

            if (row == size - 1 && col == size - 2) {
                longestPath = Math.max(longestPath, accumulator + length);
            }

            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                        nums[newRow][newCol] != '#' && !visited.contains(hash(newRow, newCol))) {
                    stack.push(new Point(newRow, newCol, length + 1, accumulator));
                }
            }

            visited.remove(hash(row, col)); // Backtrack
        }
        return longestPath;
    }

    static class Point {
        public int row;
        public int col;
        public int length;
        public int accumulator;

        Point(int r, int c, int l, int a) {
            row = r;
            col = c;
            length = l;
            accumulator = a;
        }
    }

    public static int hash(int row, int col) {
        return row * size + col;
    }
}

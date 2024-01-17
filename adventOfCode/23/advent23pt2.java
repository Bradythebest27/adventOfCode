import java.io.*;
import java.util.*;

public class Advent23pt2 {
    static final int size=23;
    public static void main(String[] args) throws Exception {
        
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay23.2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter = 0;
        //final int size = 141    ; // 23 //141
        char[][] nums = new char[size][size];
        while ((s = br.readLine()) != null) {
            // Use string s as input line
            if (counter<size)
                for (int i = 0; i < size; i++) {
                    nums[counter][i] = s.charAt(i);
                }
            counter++;
        }
        br.close();
        nums[0][1] = '#';
        // Start is 1,1
        System.out.println(path(nums, new Point(1, 1), new HashSet<>(), 1));
        
    }

    public static int path(char[][] nums, Point current, Set<Integer> visited, int length) {
        if (current.row == size - 1 && current.col == size - 2) {
            return length;
        }
        visited.add(hash(current.row, current.col));
        int maxLength = -1;
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int[] dir : directions) {
            int newRow = current.row + dir[0];
            int newCol = current.col + dir[1];
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                nums[newRow][newCol] != '#' && !visited.contains(hash(newRow, newCol))) {
                int result = path(nums, new Point(newRow, newCol), visited, length + 1);
                maxLength = Math.max(maxLength, result);
            }
        }
        visited.remove(hash(current.row, current.col));
        return maxLength;
    }

    static class Point {
        public int row;
        public int col;

        Point(int r, int c) {
            row = r;
            col = c;
        }
    }

    public static int hash(int row, int col) {
        return row * size + col;
    }
}

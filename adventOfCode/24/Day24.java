import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Day24 {

    public static void main(String[] args) {
        List<BigDecimal[]> stones = readStonesFromFile("C:\\Users\\18438\\Desktop\\adventDay24.txt");

        BigDecimal tmin = new BigDecimal("200000000000000");
        BigDecimal tmax = new BigDecimal("400000000000000");

        // part 1
        long count = countValidPairs(stones, tmin, tmax);
        System.out.println("Part 1: " + count);

        // part 2
        BigDecimal[] result = calculatePartTwo(stones);
        System.out.println("Part 2: " + result[0].toBigInteger());
    }

    private static List<BigDecimal[]> readStonesFromFile(String filePath) {
        List<BigDecimal[]> stones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                BigDecimal[] stone = parseLine(line);
                stones.add(stone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stones;
    }

    private static BigDecimal[] parseLine(String line) {
        String[] parts = line.trim().split(", |\\ @ ");
        BigDecimal[] stone = new BigDecimal[6];
        for (int i = 0; i < 6; i++) {
            stone[i] = new BigDecimal(parts[i]);
        }
        return stone;
    }

    private static long countValidPairs(List<BigDecimal[]> stones, BigDecimal tmin, BigDecimal tmax) {
        long count = 0;
        for (int i = 0; i < stones.size(); i++) {
            for (int j = i + 1; j < stones.size(); j++) {
                BigDecimal[] a = stones.get(i);
                BigDecimal[] b = stones.get(j);

                BigDecimal ax = a[0], ay = a[1], adx = a[3], ady = a[4];
                BigDecimal bx = b[0], by = b[1], bdx = b[3], bdy = b[4];

                if (adx.equals(BigDecimal.ZERO) || ady.equals(BigDecimal.ZERO) ||
                    bdx.equals(BigDecimal.ZERO) || bdy.equals(BigDecimal.ZERO)) {
                    // Skip if division by zero could occur
                    continue;
                }

                BigDecimal adyDiv = ady.equals(BigDecimal.ZERO) ? BigDecimal.ONE : ady;
                BigDecimal bdyDiv = bdy.equals(BigDecimal.ZERO) ? BigDecimal.ONE : bdy;

                BigDecimal tempDivisor = bdyDiv.divide(adyDiv, BigDecimal.ROUND_HALF_UP)
                                        .subtract(bdx.divide(adx, BigDecimal.ROUND_HALF_UP));

                if (tempDivisor.equals(BigDecimal.ZERO)) {
                    // Skip division if the divisor is zero
                    continue;
                }

                BigDecimal bt = ((bx.subtract(ax)).divide(adx, BigDecimal.ROUND_HALF_UP)
                                .subtract(by.subtract(ay).divide(adyDiv, BigDecimal.ROUND_HALF_UP)))
                                .divide(tempDivisor, BigDecimal.ROUND_HALF_UP);

                BigDecimal at = bx.add(bt.multiply(bdx)).subtract(ax).divide(adx, BigDecimal.ROUND_HALF_UP);

                BigDecimal ix = ax.add(adx.multiply(at));
                BigDecimal iy = ay.add(ady.multiply(at));

                if (bt.compareTo(BigDecimal.ZERO) >= 0 && at.compareTo(BigDecimal.ZERO) >= 0 &&
                    tmin.compareTo(ix) <= 0 && ix.compareTo(tmax) <= 0 &&
                    tmin.compareTo(iy) <= 0 && iy.compareTo(tmax) <= 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static BigDecimal[] calculatePartTwo(List<BigDecimal[]> stones) {
        BigDecimal[] x = calculateValue(stones, 0, 1, 3, 4);
        BigDecimal[] y = calculateValue(stones, 2, 1, 5, 4);
        BigDecimal[] z = new BigDecimal[1];

        for (int i = 0; i < x.length; i++) {
            x[i] = x[i].add(y[i]);
        }
        return x;
    }

    private static BigDecimal[] calculateValue(List<BigDecimal[]> stones, int xIndex, int yIndex, int dxIndex, int dyIndex) {
        BigDecimal[][] m = new BigDecimal[stones.size()][5];
        for (int i = 0; i < stones.size(); i++) {
            BigDecimal[] s = stones.get(i);
            m[i][0] = s[dyIndex].negate();
            m[i][1] = s[dxIndex];
            m[i][2] = s[yIndex];
            m[i][3] = s[xIndex].negate();
            m[i][4] = s[yIndex].multiply(s[dxIndex]).subtract(s[xIndex].multiply(s[dyIndex]));
        }

        BigDecimal[][] reducedMatrix = eliminate(m);

        BigDecimal[] result = new BigDecimal[reducedMatrix.length];
        for (int i = 0; i < reducedMatrix.length; i++) {
            result[i] = reducedMatrix[i][4];
        }
        return result;
    }

    private static BigDecimal[][] eliminate(BigDecimal[][] m) {
        int rows = m.length;
        int cols = m[0].length;

        for (int i = 0; i < rows - 1; i++) {
            if (i >= m[0].length) {
                continue;
            }
            if (m[i][i].equals(BigDecimal.ZERO)) {
                int k = i + 1;
                while (k < rows && m[k][i].equals(BigDecimal.ZERO)) {
                    k++;
                }
                if (k == rows) {
                    continue;
                }
                BigDecimal[] temp = m[i];
                m[i] = m[k];
                m[k] = temp;
            }

            for (int j = i + 1; j < rows; j++) {
                if (m[i][i].equals(BigDecimal.ZERO)) {
                    // Skip division by zero
                    continue;
                }
                BigDecimal factor = m[j][i].divide(m[i][i], BigDecimal.ROUND_HALF_UP);
                for (int k = i; k < cols; k++) {
                    m[j][k] = m[j][k].subtract(factor.multiply(m[i][k]));
                }
            }
        }
        return m;
    }
}

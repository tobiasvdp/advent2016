/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.List;

/**
 *
 * @author tobia
 */
public class Advent02 extends AdventChallenge {

    private static final int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private static final char[][] matrix2 = {{0, 0, '1', 0, 0}, {0, '2', '3', '4', 0}, {'5', '6', '7', '8', '9'}, {0, 'A', 'B', 'C', 0}, {0, 0, 'D', 0, 0}};

    @Override
    String getInputFilename() {
        return "Advent02.txt";
    }

    @Override
    Object getResult(List<String> input) {
        //part 1
        StringBuilder result = new StringBuilder("Code: ");
        for (String input_s : input) {
            //5
            int x = 1;
            int y = 1;

            char[] letters = input_s.toCharArray();
            for (char c : letters) {
                switch (c) {
                    case 'U':
                        if (y > 0) {
                            y--;
                        }
                        break;
                    case 'D':
                        if (y < (matrix.length - 1)) {
                            y++;
                        }
                        break;
                    case 'L':
                        if (x > 0) {
                            x--;
                        }
                        break;
                    case 'R':
                        if (x < (matrix.length - 1)) {
                            x++;
                        }
                        break;
                }
            }
            result.append(matrix[y][x]);
        }

        result.append(" part2:");
        for (String input_s : input) {
            //5
            int x = 0;
            int y = 2;

            char[] letters = input_s.toCharArray();
            for (char c : letters) {
                switch (c) {
                    case 'U':
                        if (y > 0) {
                            if (matrix2[y - 1][x] != 0) {
                                y--;
                            }
                        }
                        break;
                    case 'D':
                        if (y < (matrix2.length - 1)) {
                            if (matrix2[y + 1][x] != 0) {
                                y++;
                            }
                        }
                        break;
                    case 'L':
                        if (x > 0) {
                            if (matrix2[y][x - 1] != 0) {
                                x--;
                            }
                        }
                        break;
                    case 'R':
                        if (x < (matrix2.length - 1)) {
                            if (matrix2[y][x+1] != 0) {
                                x++;
                            }
                        }
                        break;
                }
            }
            result.append(matrix2[y][x]);
        }

        return result.toString();
    }

}

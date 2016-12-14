/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tobias
 */
public class Advent08 extends AdventChallenge {

    Pattern p1 = Pattern.compile("rect ([0-9]*)x([0-9]*)");
    Pattern p2 = Pattern.compile("rotate column x=([0-9]*) by ([0-9]*)");
    Pattern p3 = Pattern.compile("rotate row y=([0-9]*) by ([0-9]*)");

    @Override
    Object getResult(List<String> input) {
        boolean[][] arr = new boolean[50][6];

        for (String input_s : input) {
            Matcher m = p1.matcher(input_s);
            if (m.matches()) {
                //rect
                for (int i = 0; i < Integer.parseInt(m.group(1)); i++) {
                    for (int j = 0; j < Integer.parseInt(m.group(2)); j++) {
                        arr[i][j] = true;
                    }

                }
            } else {
                m = p2.matcher(input_s);
                if (m.matches()) {
                    //column
                    int i = Integer.parseInt(m.group(2));
                    int colomn = Integer.parseInt(m.group(1));

                    boolean[] helper = new boolean[arr[0].length];
                    System.arraycopy(arr[colomn], 0, helper, 0, arr[0].length);

                    for (int j = 0; j < arr[0].length; j++) {
                        int h = j + i;
                        if (h >= arr[0].length) {
                            h -= arr[0].length;
                        }
                        arr[colomn][h] = helper[j];
                    }
                } else {
                    m = p3.matcher(input_s);
                    if (m.matches()) {
                        //row
                        int i = Integer.parseInt(m.group(2));
                        int row = Integer.parseInt(m.group(1));

                        boolean[] helper = new boolean[arr.length];
                        for (int x = 0; x < arr.length; x++) {
                            helper[x] = arr[x][row];
                        }

                        for (int j = 0; j < arr.length; j++) {
                            int h = j + i;
                            if (h >= arr.length) {
                                h -= arr.length;
                            }
                            arr[h][row] = helper[j];
                        }
                    } else {
                        System.out.println(input_s);
                        throw new NotImplementedException();
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i]) {
                    System.out.print("1");
                    count++;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }

        return count;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.List;

/**
 *
 * @author Tobias
 */
public class Advent18 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        char[] arr = input.get(0).toCharArray();
        double count = 0;
        boolean[] isTrap = toTraps(arr);
        for (int i = 0; i < isTrap.length; i++) {
            if (!isTrap[i]) {
                count++;
            }
        }
        //printout(isTrap);
        for (int i = 1; i < 400000; i++) {
            isTrap = generateNext(isTrap);
            for (int x = 0; x < isTrap.length; x++) {
                if (!isTrap[x]) {
                    count++;
                }
            }
            //printout(isTrap);
        }

        return count;
    }

    public void printout(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                System.out.print('^');
            } else {
                System.out.print('.');
            }
        }
        System.out.println("");
    }

    private boolean[] toTraps(char[] arr) {
        boolean[] traps = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '^') {
                traps[i] = true;
            }
        }
        return traps;
    }

    private boolean[] generateNext(boolean[] prevTraps) {
        boolean[] traps = new boolean[prevTraps.length];
        for (int i = 0; i < prevTraps.length; i++) {
            boolean left = false, center = false, right = false;
            center = prevTraps[i];
            if (i > 0) {
                left = prevTraps[i - 1];
            }
            if (i < prevTraps.length - 1) {
                right = prevTraps[i + 1];
            }

            if (left && center && !right) {
                traps[i] = true;
            }
            if (!left && center && right) {
                traps[i] = true;
            }
            if (left && !center && !right) {
                traps[i] = true;
            }
            if (!left && !center && right) {
                traps[i] = true;
            }
        }
        return traps;
    }

}

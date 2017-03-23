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
public class Advent16 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        String input_s = input.get(0);
        String p1 = input_s;

        while (p1.length() < 35651584) {
            p1 = generate(p1);
        }
        p1 = p1.substring(0, 35651584);
        String checksumP1 = generateChecksum(p1);
        while (checksumP1.length() % 2 == 0) {
            checksumP1 = generateChecksum(checksumP1);
        }
        return checksumP1;
    }

    String generate(String a) {
        String b = new StringBuilder(a).reverse().toString().replace('0', '2').replace('1', '0').replace('2', '1');
        return a + "0" + b;
    }

    private String generateChecksum(String p) {
        StringBuilder b = new StringBuilder();
        char[] arr = p.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == arr[i - 1]) {
                b.append("1");
            } else {
                b.append("0");
            }
        }
        return b.toString();
    }

}

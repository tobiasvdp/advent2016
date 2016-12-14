/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tobia
 */
public class Advent09 extends AdventChallenge {

    Pattern repeatingPattern = Pattern.compile("\\(([0-9]*)x([0-9]*)\\)");

    @Override
    Object getResult(List<String> input) {
        //part1
        for (String input_s : input) {
            input_s = input_s.replace(" ", "");
            StringReader sr = new StringReader(input_s);

            double count = 0;
            double ci = 0;

            while ((ci = appendChar(sr)) != -1) {
                count += ci;
            }

            return count;
        }
        return 0;
    }

    private double appendChar(StringReader sr) {
        try {
            int ci = sr.read();
            if (ci == -1) {
                return -1;
            }

            char c = (char) ci;

            if (c == '(') {

                StringBuilder b = new StringBuilder();
                b.append(c);
                char ct = 0;
                while ((ct = (char) sr.read()) != ')') {
                    b.append(ct);
                }
                b.append(ct);
                Matcher m = repeatingPattern.matcher(b.toString());
                if (m.matches()) {
                    int amount = Integer.parseInt(m.group(1));
                    int times = Integer.parseInt(m.group(2));

                    StringBuilder b2 = new StringBuilder();
                    for (int i = 0; i < amount; i++) {
                        b2.append((char) sr.read());
                    }
                    StringReader sr2 = new StringReader(b2.toString());

                    double count = 0;
                    double cii = 0;

                    //p1
                    //return amount * times;
                    
                    //p2
                    while ((cii = appendChar(sr2)) != -1) {
                        count += cii;
                    }

                    return count * times;
                }

            } else {
                return 1;
            }

        } catch (IOException ex) {
            return -1;
        }

        return 0;
    }

    /*
    private int[] appendCharCount(char[] arr, int i, int count) {
        char arr_i = arr[i];
        int skipcount = 0;
        if (arr_i == '(') {

            StringBuilder bi = new StringBuilder();
            while (arr[i] != ')') {
                bi.append(arr[i]);
                i++;
            }
            bi.append(')'); 
            Matcher m = repeatingPattern.matcher(bi.toString());
            
            if (m.matches()) {
                int amount = Integer.parseInt(m.group(1));
                int times = Integer.parseInt(m.group(2));

                if (i > 0) {

                    int localcount = 0;
                    for (int ii = 0; ii < amount; ii++) {
                        i++;
                        int[] temp = appendCharCount(arr,i,localcount);
                        i = temp[0];
                        localcount = temp[1];
                        int sc = temp[2];
                        if(sc != 0){
                            ii += sc;
                        }
                    }
                    for (int ii = 0; ii < times; ii++) {
                        count += localcount;
                    }
                    skipcount = localcount + bi.toString().length();
                }

            }
        } else {
            count++;
        }
        return new int[]{i,count,skipcount};
    }*/
}

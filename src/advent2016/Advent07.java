/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tobias
 */
public class Advent07 extends AdventChallenge {

    Pattern p1 = Pattern.compile(".*([a-zA-Z])([a-zA-Z])\\2\\1.*");
    Pattern p2 = Pattern.compile("\\[[^\\[\\]]*([a-zA-Z])([a-zA-Z])\\2\\1[^\\[\\]]*\\]");

    Pattern p21 = Pattern.compile(".*([a-zA-Z])([a-zA-Z])\\1.*");
    Pattern p22 = Pattern.compile("\\[[^\\[\\]]*([a-zA-Z])([a-zA-Z])\\1[^\\[\\]]*\\]");

    @Override
    Object getResult(List<String> input) {
        int count = 0;
        for (String input_s : input) {
            Matcher m1 = p1.matcher(input_s);
            Matcher m2 = p2.matcher(input_s);

            if (m1.find() && !m2.find()) {
                if (!m1.group(1).equals(m1.group(2))) {
                    count++;
                }
            }
        }
        StringBuilder b2 = new StringBuilder("P1: ");
        b2.append(count);
        b2.append(" P2:");
        count = 0;
        for (String input_s : input) {

            String sec = input_s;
            Matcher m2 = p22.matcher(input_s);
            Set<String> m2pat = new HashSet<>();
            while (m2.find()) {
                if (!m2.group(1).equals(m2.group(2))) {
                    m2pat.add(m2.group(1) + m2.group(2));
                }
                sec = sec.substring(1);
                m2 = p22.matcher(sec);
            }
            if (!m2pat.isEmpty()) {
                sec = input_s;
                Matcher m1 = p21.matcher(sec);
                Set<String> m1pat = new HashSet<>();
                while (m1.matches()) {
                    if (!m1.group(1).equals(m1.group(2))) {
                        m1pat.add(m1.group(2) + m1.group(1));
                    }
                    sec = sec.substring(1);
                    m1 = p21.matcher(sec);
                }
                
                for(String s : m2pat){
                    if(m1pat.contains(s)){
                        count++;
                        break;
                    }
                }
                
                System.out.println("");
            }

            
        }
        b2.append(count);

        return b2.toString();
    }

}

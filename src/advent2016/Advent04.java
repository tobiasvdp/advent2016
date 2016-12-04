/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent04.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author tobia
 */
public class Advent04 extends AdventChallenge {
    
    Pattern p1 = Pattern.compile("(.*)-([0-9]*)\\[([^\\]]*)\\]");
    
    @Override
    Object getResult(List<String> input) {
        int count = 0;
        for (String input_s : input) {
            Matcher m1 = p1.matcher(input_s);
            if (m1.matches()) {
                String chars = m1.group(1);
                int sector = Integer.parseInt(m1.group(2));
                String code = m1.group(3);
                
                List<Pair> pairs = getNumeration(chars);
                StringBuilder b = new StringBuilder();
                for (Pair pair : pairs) {
                    if (pair.count > 0) {
                        b.append(pair.c);
                    }
                }
                String result = b.toString().substring(0, 5);
                
                if (result.equals(code)) {
                    //true room
                    count += sector;
                    
                    //part2
                    int shift = sector % 26;
                    char[] chararray = chars.toCharArray();
                    StringBuilder ret = new StringBuilder();
                    for (char c : chararray) {
                        if (c == '-') {
                            ret.append(" ");
                        } else {
                            c += shift;
                            if (c > 'z') {
                                c -= 26;
                            }
                            ret.append(c);
                        }
                    }
                    code = ret.toString();
                    if (code.contains("pole")) {
                        System.out.println("P2:"+code + "  -  " + sector);
                    }
                } else {
                    //false room
                }
            }
        }
        StringBuilder sb = new StringBuilder("P1:");
        sb.append(count + "");
        return sb.toString();
    }
    
    private List<Pair> getNumeration(String chars) {
        List<Pair> t = new ArrayList<>();
        for (char a = 'a'; a <= 'z'; a++) {
            int count = chars.length() - chars.replace(a + "", "").length();
            Pair p = new Pair();
            p.c = a;
            p.count = count;
            t.add(p);
        }
        t.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                int compareTo = o1.count.compareTo(o2.count);
                if (compareTo == 0) {
                    return o1.c.compareTo(o2.c);
                } else {
                    return 0 - compareTo;
                }
            }
        });
        return t;
    }
    
}

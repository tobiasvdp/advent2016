/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tobia
 */
public class Advent03 extends AdventChallenge {

    Pattern p = Pattern.compile("[ ]*([0-9]*)[ ]*([0-9]*)[ ]*([0-9]*)[ ]*");

    @Override
    Object getResult(List<String> input) {
        int count = 0;
        int valid = 0, invalid = 0;
        for (String input_s : input) {
            Matcher m = p.matcher(input_s);
            if (m.matches()) {
                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= 3; i++) {
                    list.add(Integer.parseInt(m.group(i)));
                }

                List<List<Integer>> arclist = new ArrayList<>();
                arclist = perm(list, arclist);
                boolean isValidTriangle = true;
                for (List<Integer> l : arclist) {
                    if (l.size() == 3) {
                        if (l.get(0) + l.get(1) > l.get(2)) {
                            //valid
                        } else {
                            isValidTriangle = false;
                        }
                    }
                }
                if (isValidTriangle) {
                    valid++;
                } else {
                    invalid++;
                }
            }
        }
        StringBuilder b = new StringBuilder("P1: Valid:" + valid + " Invalid: " + invalid);

        //part2
        count = 0;
        valid = 0;
        invalid = 0;
        for (int i = 0; i + 2 < input.size(); i+=3) {
            String r1 = input.get(i);
            String r2 = input.get(i + 1);
            String r3 = input.get(i + 2);
            Matcher m1 = p.matcher(r1);
            Matcher m2 = p.matcher(r2);
            Matcher m3 = p.matcher(r3);
            if (m1.matches() && m2.matches() && m3.matches()) {
                List<List<Integer>> list = new ArrayList<>();
                for (int x = 1; x <= 3; x++) {
                    List<Integer> l = new ArrayList<>();
                    l.add(Integer.parseInt(m1.group(x)));
                    l.add(Integer.parseInt(m2.group(x)));
                    l.add(Integer.parseInt(m3.group(x)));
                    list.add(l);
                }

                for (List<Integer> list_s : list) {
                    List<List<Integer>> arclist = new ArrayList<>();
                    arclist = perm(list_s, arclist);
                    boolean isValidTriangle = true;
                    for (List<Integer> l : arclist) {
                        if (l.size() == 3) {
                            if (l.get(0) + l.get(1) > l.get(2)) {
                                //valid
                            } else {
                                isValidTriangle = false;
                            }
                        }
                    }
                    if (isValidTriangle) {
                        valid++;
                    } else {
                        invalid++;
                    }
                }

            }
        }
        
        b.append("   P2: Valid:" + valid + " Invalid: " + invalid);

        return b.toString();
    }

    private List<List<Integer>> perm(List<Integer> list, List<List<Integer>> arclist) {
        if (list.size() == 1) {
            List<List<Integer>> r = new ArrayList<>();
            r.add(list);
            return r;
        }
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Integer> reducedList = new ArrayList<>(list);
            reducedList.remove(i);
            List<List<Integer>> iterList = perm(reducedList, arclist);

            int x = list.get(i);
            for (List<Integer> iterList_s : iterList) {
                iterList_s.add(0, x);
                l.add(iterList_s);
            }
        }
        return l;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Tobias
 */
public class Advent19 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        String input_s = input.get(0);
        int amountOfElves = Integer.parseInt(input_s);

        //p1
//                
//        Map<Integer, Integer> arr = new TreeMap<>();
//        for (int i = 0; i < amountOfElves; i++) {
//            arr.put(i, 1);
//        }
//
//        while (arr.size() > 1) {
//            Iterator<Map.Entry<Integer, Integer>> it = arr.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<Integer, Integer> entry = it.next();
//                if (it.hasNext()) {
//                    Map.Entry<Integer, Integer> nextEntry = it.next();
//                    entry.setValue(entry.getValue() + nextEntry.getValue());
//                    it.remove();
//                } else {
//                    if(arr.size() == 1){
//                        return arr.entrySet().iterator().next().getKey() +1;
//                    }
//                    it = arr.entrySet().iterator();
//                    Map.Entry<Integer, Integer> nextEntry = it.next();
//                    entry.setValue(entry.getValue() + nextEntry.getValue());
//                    it.remove();
//                }
//            }
//        }
        //p2
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= amountOfElves; i++) {
            arr.add(i);
        }

        int i = 0;
        int size = arr.size();
        while (size > 1) {
            if (size % 10000 == 0) {
                System.out.println(size);
            }
            int op = getOpposite(size, i);
            //System.out.println(arr.get(i) + " removes " + arr.get(op));
            arr.remove(op);
            if (op > i) {
                i++;
            }
            size--;
            if (i >= size) {
                i = 0;
            }

        }
        return arr.get(0);
    }

    private int getOpposite(int size, int i) {
        if (size % 2 == 1) {
            return ((size / 2) + i) % size;
        } else {
            return ((size / 2) + i) % size;
        }
    }

}

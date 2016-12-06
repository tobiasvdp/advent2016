/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tobia
 */
public class Advent06 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        List<Map<Character, Integer>> map = new ArrayList<>();
        for (String input_s : input) {
            char[] arr = input_s.toCharArray();
            if (map.isEmpty()) {
                map = new ArrayList<>(arr.length);
                for (int j = 0; j < arr.length; j++) {
                    map.add(null);
                }
            }
            for (int i = 0; i < arr.length; i++) {
                if (map.get(i) == null) {
                    map.set(i, new HashMap<>());
                }

                Map<Character, Integer> store = map.get(i);
                if (store.containsKey(arr[i])) {
                    store.put(arr[i], (store.get(arr[i]) + 1));
                } else {
                    store.put(arr[i], 1);
                }
            }
        }
        List<Character> list1 = getHighestCharacters(map);
        List<Character> list2 = getLowestCharacters(map);

        StringBuilder sb = new StringBuilder("p1:");
        for (char c : list1) {
            sb.append(c);
        }
        sb.append(" p2:");
        for (char c : list2) {
            sb.append(c);
        }
        String result = sb.toString();
        return sb.toString();
    }

    List<Character> getHighestCharacters(List<Map<Character, Integer>> chars) {
        List<Character> list = new ArrayList<>();
        for (Map<Character, Integer> c : chars) {
            int highest = 0;
            char res = 0;
            for (Map.Entry<Character, Integer> p : c.entrySet()) {
                if (p.getValue() > highest) {
                    highest = p.getValue();
                    res = p.getKey();
                }
            }
            list.add(res);
        }
        return list;
    }

    List<Character> getLowestCharacters(List<Map<Character, Integer>> chars) {
        List<Character> list = new ArrayList<>();
        for (Map<Character, Integer> c : chars) {
            int lowest = Integer.MAX_VALUE;
            char res = 0;
            for (Map.Entry<Character, Integer> p : c.entrySet()) {
                if (p.getValue() < lowest) {
                    lowest = p.getValue();
                    res = p.getKey();
                }
            }
            list.add(res);
        }
        return list;
    }

}

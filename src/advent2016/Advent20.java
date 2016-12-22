/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Tobias
 */
public class Advent20 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        Map<Double, Double> arr = new TreeMap<>();
        for (String input_s : input) {
            String[] range = input_s.split("-");
            if (arr.containsKey(Double.parseDouble(range[0]))) {
                throw new UnsupportedOperationException();
            }
            arr.put(Double.parseDouble(range[0]), Double.parseDouble(range[1]));
        }

        int count = 0;
        double minimum = -1;
        double max = Double.parseDouble("4294967295");
        while (minimum < max) {
           minimum = getMin(minimum+1, arr);
           count++;
        }

        //
        //for (; minimum <= max; minimum++) {
        //    count++;
        //}
        System.out.printf("dexp: %f\n", minimum);
        return count;
    }

    public double getMin(Double minimum, Map<Double, Double> arr) {
        for (Map.Entry<Double, Double> entry : arr.entrySet()) {
            if (entry.getKey() <= minimum && entry.getValue() >= minimum) {
                minimum = entry.getValue() + 1;
            }
        }
        return minimum;
    }

}

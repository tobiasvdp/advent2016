/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tobia
 */
public class Advent01 extends AdventChallenge {

    @Override
    String getInputFilename() {
        return "Advent01.txt";
    }

    private static final char[] matrix = {'N', 'E', 'S', 'W'};

    @Override
    Object getResult(List<String> input) {
        
        int distance;
        
        input = splitLinesOnSeperator(input, ",");

        //testcode
        /*String inputs = new String("R5, L5, R5, R3");
        input = new ArrayList<>();
        input.add(inputs);
        input = splitLinesOnSeperator(input, ",");*/
        
        int x = 0, y = 0;

        int directionIndex = 0;
        char direction = matrix[directionIndex];

        for (String s : input) {
            char turn = s.charAt(0);
            int amount = Integer.parseInt(s.substring(1));

            switch (turn) {
                case 'R':
                    directionIndex++;
                    if (directionIndex == matrix.length) {
                        directionIndex = 0;
                    }
                    break;
                case 'L':
                    directionIndex--;
                    if (directionIndex == -1) {
                        directionIndex = matrix.length - 1;
                    }
                    break;
            }
            direction = matrix[directionIndex];
            Logger.getGlobal().info(turn + ": now facing " + direction + " for " + amount);
            switch (direction) {
                case 'N':
                    y += amount;
                    break;
                case 'E':
                    x += amount;
                    break;
                case 'S':
                    y -= amount;
                    break;
                case 'W':
                    x -= amount;
                    break;
            }
            Logger.getGlobal().info("x:" + x + " y:" + y);
        }
        
        distance = Math.abs(x) + Math.abs(y);

        return distance;
    }

}

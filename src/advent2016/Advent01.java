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

        StringBuilder b = new StringBuilder("Part1:");
        b.append(distance + "");
        b.append(" Part2:");

        //part2
        distance = 0;
        Map<Integer, List<Integer>> visited = new HashMap<>();
        List<Integer> visitedl = new ArrayList<>();
        visitedl.add(0);
        visited.put(0, visitedl);

        x = 0;
        y = 0;

        directionIndex = 0;
        direction = matrix[directionIndex];

        boolean cont = true;
        int xres = 0, yres = 0;
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
                    for (int yi = y + 1; yi <= (y + amount); yi++) {
                        if (visited.containsKey(x)) {
                            List<Integer> list = visited.get(x);
                            if (list.contains(yi)) {
                                //condition met
                                cont = false;
                                xres = x;
                                yres = yi;
                                break;
                            } else {
                                list.add(yi);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<>();
                            l.add(yi);
                            visited.put(x, l);
                        }
                    }
                    y += amount;
                    break;
                case 'E':
                    for (int xi = x + 1; xi <= (x + amount); xi++) {
                        if (visited.containsKey(xi)) {
                            List<Integer> list = visited.get(xi);
                            if (list.contains(y)) {
                                //condition met
                                cont = false;
                                xres = xi;
                                yres = y;
                                break;
                            } else {
                                list.add(y);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<>();
                            l.add(y);
                            visited.put(xi, l);
                        }
                    }
                    x += amount;
                    break;
                case 'S':
                    for (int yi = y - 1; yi >= (y - amount); yi--) {
                        if (visited.containsKey(x)) {
                            List<Integer> list = visited.get(x);
                            if (list.contains(yi)) {
                                //condition met
                                cont = false;
                                xres = x;
                                yres = yi;
                                break;
                            } else {
                                list.add(yi);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<>();
                            l.add(yi);
                            visited.put(x, l);
                        }
                    }
                    y -= amount;
                    break;
                case 'W':
                    for (int xi = x - 1; xi >= (x - amount); xi--) {
                        if (visited.containsKey(xi)) {
                            List<Integer> list = visited.get(xi);
                            if (list.contains(y)) {
                                //condition met
                                cont = false;
                                xres = xi;
                                yres = y;
                                break;
                            } else {
                                list.add(y);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<>();
                            l.add(y);
                            visited.put(xi, l);
                        }
                    }
                    x -= amount;
                    break;
            }
            Logger.getGlobal().info("x:" + x + " y:" + y);
            
            if(!cont){
                break;
            }

        }

        distance = Math.abs(xres) + Math.abs(yres);
        b.append(distance + "");

        return b.toString();
    }

}

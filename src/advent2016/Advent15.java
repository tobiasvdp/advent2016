/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent15.Wheel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tobias
 */
public class Advent15 extends AdventChallenge {

    private List<Wheel> wheels;
    Pattern p = Pattern.compile("Disc #([0-9]*) has ([0-9]*) positions; at time=([0-9]*), it is at position ([0-9]*)\\.");

    @Override
    Object getResult(List<String> input) {
        wheels = new ArrayList<>();

        int i = 1;
        for (String input_s : input) {
            Matcher m = p.matcher(input_s);

            if (m.matches()) {
                int wheel_number = Integer.parseInt(m.group(1));
                int wheel_positions = Integer.parseInt(m.group(2));
                int wheel_time = Integer.parseInt(m.group(3));
                int wheel_position = Integer.parseInt(m.group(4));
                if (i != wheel_number || wheel_time != 0) {
                    throw new NotImplementedException();
                }
                Wheel wheel = new Wheel(wheel_number,wheel_positions, wheel_position);
                wheels.add(wheel);
                i++;
            }
        }

        int[] firstValidPositions = new int[wheels.size()];
        
        
        for (i = 0; i < 100000000; i++) {
            boolean b = true;
            for(int x = 0;x<wheels.size() && b;x++){
                if(!wheels.get(x).isPositionValid(i)){
                    b = false;
                }
            }
            if(b){
                return i;
            }
        }

        return null;
    }

}

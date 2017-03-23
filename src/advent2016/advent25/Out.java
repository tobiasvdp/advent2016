/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent25;

import advent2016.Advent25;
import advent2016.advent23.Instruction;
import java.util.Map;

/**
 *
 * @author tobia
 */
public class Out extends Instruction {

    public Out(String s1, String s2, int i1, int i2) {
        super(s1, s2, i1, i2);
    }

    @Override
    public int doInstruction(int num, Map<String, Integer> registers) {
        int i = 0;
        if (s1 == null) {
            i = i1;
        } else {
            i = registers.getOrDefault(s1, 0);
        }
        System.out.print(i);
        Advent25.prevValue = Advent25.value;
        Advent25.value = i;
        return num+1;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent12;

import java.util.Map;

/**
 *
 * @author Tobias
 */
public class Jnz extends Instruction {

    public Jnz(String s1, String s2, int i1, int i2) {
        super(s1, s2, i1, i2);
    }

    @Override
    public int doInstruction(int num, Map<String, Integer> registers) {
        if (s1 == null) {
            if (i1 != 0) {
                if (s2 == null) {
                    return num + i2;
                } else if (registers.containsKey(s2)) {
                    if (registers.get(s2) != 0) {
                        return num + registers.get(s2);
                    }
                }
            }
        } else if (registers.containsKey(s1)) {
            if (registers.get(s1) != 0) {
                if (s2 == null) {
                    return num + i2;
                } else if (registers.containsKey(s2)) {
                    if (registers.get(s2) != 0) {
                        return num + registers.get(s2);
                    }
                }
            }
        }

        return num + 1;
    }

}

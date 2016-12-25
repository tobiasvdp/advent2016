/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent23;

import advent2016.Advent23;
import java.util.Map;

/**
 *
 * @author Tobias
 */
public class Tgl extends Instruction {

    public Tgl(String s1, String s2, int i1, int i2) {
        super(s1, s2, i1, i2);
    }

    @Override
    public int doInstruction(int num, Map<String, Integer> registers) {
        int i = i1;
        if (s1 != null) {
            i = registers.getOrDefault(s1, 0);
        }
        if (num+i < 0 || num+i >= Advent23.instrutions.size()) {
            return num + 1;
        }
        
        Instruction instr = Advent23.instrutions.get(num+i);
        instr.toggle(num + i);
        return num + 1;
    }

}

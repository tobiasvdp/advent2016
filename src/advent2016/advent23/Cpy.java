/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent23;

import advent2016.advent12.*;
import java.util.Map;

/**
 *
 * @author Tobias
 */
public class Cpy extends Instruction {

    public Cpy(String s1, String s2, int i1, int i2) {
        super(s1, s2, i1, i2);
    }

    @Override
    public int doInstruction(int num, Map<String, Integer> registers) {
        if(s2 == null){
            return num + 1;
        }
        
        if (s1 == null) {
            registers.put(s2, i1);
        }else{
            if(registers.containsKey(s1)){
                registers.put(s2, registers.get(s1));
            }else{
                registers.put(s2, 0);
            }
        }

        return num + 1;
    }

}

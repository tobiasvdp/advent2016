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
public class Inc extends Instruction{

    public Inc(String s1, String s2, int i1, int i2) {
        super(s1, s2, i1, i2);
    }

    @Override
    public int doInstruction(int num, Map<String, Integer> registers) {
        if(registers.containsKey(s1)){
            registers.put(s1, registers.get(s1)+1);
        }else{
            registers.put(s1, 1);
        }
        return num+1;
    }
    
}

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
public abstract class Instruction {

    String s1, s2;
    int i1, i2;

    public Instruction(String s1, String s2, int i1, int i2) {
        this.s1 = s1;
        this.s2 = s2;
        this.i1 = i1;
        this.i2 = i2;
    }

    public abstract int doInstruction(int num, Map<String, Integer> registers);

    void toggle(int i) {
        if (this instanceof Inc) {
            Instruction dec = new Dec(s1, s2, i1, i2);
            advent2016.Advent23.instrutions.set(i, dec);
        } else if (this instanceof Dec) {
            Instruction inc = new Inc(s1, s2, i1, i2);
            advent2016.Advent23.instrutions.set(i, inc);
        } else if (this instanceof Tgl) {
            Instruction inc = new Inc(s1, s2, i1, i2);
            advent2016.Advent23.instrutions.set(i, inc);
        } else if (this instanceof Jnz) {
            Instruction cpy = new Cpy(s1, s2, i1, i2);
            advent2016.Advent23.instrutions.set(i, cpy);
        } else if (this instanceof Cpy) {
            Instruction jnz = new Jnz(s1, s2, i1, i2);
            advent2016.Advent23.instrutions.set(i, jnz);
        }
    }
}

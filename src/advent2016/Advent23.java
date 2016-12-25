/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent23.Cpy;
import advent2016.advent23.Dec;
import advent2016.advent23.Inc;
import advent2016.advent23.Instruction;
import advent2016.advent23.Jnz;
import advent2016.advent23.Tgl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tobias
 */
public class Advent23 extends AdventChallenge {

    Pattern cpy = Pattern.compile("cpy (.*) (.*)");
    Pattern inc = Pattern.compile("inc (.*)");
    Pattern dec = Pattern.compile("dec (.*)");
    Pattern jnz = Pattern.compile("jnz (.*) (.*)");
    Pattern tgl = Pattern.compile("tgl (.*)");
    public static List<Instruction> instrutions;

    @Override
    Object getResult(List<String> input) {
        instrutions = new ArrayList<>();
        for (String input_s : input) {
            Matcher m = cpy.matcher(input_s);
            if (m.matches()) {
                String s1 = m.group(1);
                String s2 = m.group(2);
                int i1 = 0;
                int i2 = 0;
                try {
                    i1 = Integer.parseInt(s1);
                    s1 = null;
                } catch (NumberFormatException e) {

                }
                try {
                    i2 = Integer.parseInt(s2);
                    s2 = null;
                } catch (NumberFormatException e) {

                }
                instrutions.add(new Cpy(s1, s2, i1, i2));
            } else {
                m = inc.matcher(input_s);
                if (m.matches()) {

                    String s1 = m.group(1);
                    int i1 = 0;
                    try {
                        i1 = Integer.parseInt(s1);
                        s1 = null;
                    } catch (NumberFormatException e) {

                    }
                    instrutions.add(new Inc(s1, null, i1, 0));
                } else {
                    m = dec.matcher(input_s);
                    if (m.matches()) {
                        String s1 = m.group(1);
                        int i1 = 0;
                        try {
                            i1 = Integer.parseInt(s1);
                            s1 = null;
                        } catch (NumberFormatException e) {

                        }
                        instrutions.add(new Dec(s1, null, i1, 0));
                    } else {
                        m = jnz.matcher(input_s);
                        if (m.matches()) {
                            String s1 = m.group(1);
                            String s2 = m.group(2);
                            int i1 = 0;
                            int i2 = 0;
                            try {
                                i1 = Integer.parseInt(s1);
                                s1 = null;
                            } catch (NumberFormatException e) {

                            }
                            try {
                                i2 = Integer.parseInt(s2);
                                s2 = null;
                            } catch (NumberFormatException e) {

                            }
                            instrutions.add(new Jnz(s1, s2, i1, i2));
                        } else {
                            m = tgl.matcher(input_s);
                            if (m.matches()) {
                                String s1 = m.group(1);
                                int i1 = 0;
                                try {
                                    i1 = Integer.parseInt(s1);
                                    s1 = null;
                                } catch (NumberFormatException e) {

                                }
                                instrutions.add(new Tgl(s1, null, i1, 0));
                            } else {
                                throw new NotImplementedException();
                            }
                        }
                    }
                }
            }
        }
        int counter = 0;
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 12);
        while (counter < instrutions.size()) {
            counter = instrutions.get(counter).doInstruction(counter, registers);
        }
        
        return registers.get("a");
    }

}

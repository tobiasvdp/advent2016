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
import advent2016.advent25.Out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author tobia
 */
public class Advent25 extends AdventChallenge {

    Pattern cpy = Pattern.compile("cpy (.*) (.*)");
    Pattern inc = Pattern.compile("inc (.*)");
    Pattern dec = Pattern.compile("dec (.*)");
    Pattern jnz = Pattern.compile("jnz (.*) (.*)");
    Pattern tgl = Pattern.compile("tgl (.*)");
    Pattern tr = Pattern.compile("out (.*)");

    public static int prevValue = -1;
    public static int value = -1;

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
                                m = tr.matcher(input_s);
                                if (m.matches()) {
                                    String s1 = m.group(1);
                                    int i1 = 0;
                                    try {
                                        i1 = Integer.parseInt(s1);
                                        s1 = null;
                                    } catch (NumberFormatException e) {

                                    }
                                    instrutions.add(new Out(s1, null, i1, 0));
                                } else {
                                    throw new NotImplementedException();
                                }
                            }
                        }
                    }
                }
            }
        }
        int counter = 0;
        Map<String, Integer> registers = new HashMap<>();

        int i = 0;
        List<Instruction> instrutions = new ArrayList<>();
        instrutions.addAll(Advent25.instrutions);
        while (true) {
            registers.put("a", i);
            boolean cont = true;
            int l = 0;
            //System.out.print(i + ": ");
            while (cont && counter < instrutions.size()) {
                counter = instrutions.get(counter).doInstruction(counter, registers);
                if (prevValue != -1 && value != -1) {
                    if (value > 1 || value < 0) {
                        //bad
                        cont = false;
                    } else if ((prevValue == 0 && value != 1) || (prevValue == 1 && value != 0)) {
                        //bad
                        cont = false;
                    }
                }
                l++;
            }
            if(cont){
                System.out.print("EOF");
            }
            System.out.println(" :" + i);
            i+=1;
            instrutions = new ArrayList<>();
            instrutions.addAll(Advent25.instrutions);
            registers.clear();
            prevValue = -1;
            value = -1;
            counter = 0;
        }
        //return registers.get("a");
    }

}


import advent2016.Advent01;
import advent2016.Advent02;
import advent2016.Advent03;
import advent2016.Advent04;
import advent2016.Advent05;
import advent2016.Advent06;
import advent2016.Advent07;
import advent2016.Advent08;
import advent2016.Advent09;
import advent2016.Advent11;
import advent2016.Advent12;
import advent2016.Advent13;
import advent2016.Advent14;
import advent2016.Advent15;
import advent2016.Advent18;
import advent2016.Advent19;
import advent2016.Advent20;
import advent2016.Advent21;
import advent2016.Advent22;
import advent2016.Advent24;
import advent2016.Advent25;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tobia
 */
public class Advent2016 {

    /**
     * @param args the command line arguments
     */
    private static boolean debug = false;

    public static void main(String[] args) {
        //Logger.getGlobal().addHandler(new StreamHandler(System.out, new SimpleFormatter()));
        if (debug) {
            Logger.getGlobal().setLevel(Level.ALL);
        } else {
            Logger.getGlobal().setLevel(Level.OFF);
        }

        Advent25 adv08 = new Advent25();
        adv08.getResult();
    }

}

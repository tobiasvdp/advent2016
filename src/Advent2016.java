
import advent2016.Advent01;
import advent2016.Advent02;
import advent2016.Advent03;
import advent2016.Advent04;
import advent2016.Advent05;
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

        Advent05 adv05 = new Advent05();
        adv05.getResult();
    }

}

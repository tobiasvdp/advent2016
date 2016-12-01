/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tobia
 */
public abstract class AdventChallenge {

    abstract String getInputFilename();

    List<String> getInput() {
        String filename = "advent2016/" + getInputFilename();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        List<String> input = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdventChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return input;
    }

    abstract Object getResult(List<String> input);

    public void getResult() {
        Logger.getGlobal().info(getInputFilename() + " start.");
        List<String> input = getInput();
        Object o = getResult(input);
        System.out.println(o);
        Logger.getGlobal().info(getInputFilename() + " end.");
    }

    List<String> splitLinesOnSeperator(List<String> input, String seperator) {
        List<String> inputParts = new ArrayList<>();
        for (String s : input) {
            String[] split = s.split(seperator);
            for (int i = 0; i < split.length; i++) {
                inputParts.add(split[i].trim());
            }
        }
        return inputParts;
    }
}

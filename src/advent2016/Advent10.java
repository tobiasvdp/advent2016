/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent10.Bot;
import advent2016.advent10.Chip;
import advent2016.advent10.Output;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tobia
 */
public class Advent10 extends AdventChallenge {

    private static final Pattern pickup = Pattern.compile("value ([0-9]*) goes to ([a-zA-A]*) ([0-9]*)");
    private static final Pattern pass = Pattern.compile("bot ([0-9]*) gives low to ([a-zA-A]*) ([0-9]*) and high to ([a-zA-A]*) ([0-9]*)");

    @Override
    Object getResult(List<String> input) {

        Map<Integer, Bot> bots = new HashMap<>();
        Map<Integer, Output> outputs = new HashMap<>();
        Map<Integer, Chip> chips = new HashMap<>();

        for (String input_s : input) {
            Matcher m = pickup.matcher(input_s);
            if (m.matches()) {
                int chip_num = Integer.parseInt(m.group(1));
                Chip chip = chips.get(chip_num);
                if (chip == null) {
                    chip = new Chip(chip_num);
                    chips.put(chip_num, chip);
                }

                if (m.group(2).equals("bot")) {
                    int bot_num = Integer.parseInt(m.group(3));
                    Bot bot = bots.get(bot_num);
                    if (bot == null) {
                        bot = new Bot(bot_num);
                        bots.put(bot_num, bot);
                    }
                    bot.giveBrick(chip);
                } else {
                    throw new RuntimeException("chip passed to output");
                }
            } else {
                m = pass.matcher(input_s);
                if (m.matches()) {
                    int bot_num = Integer.parseInt(m.group(1));
                    Bot bot = bots.get(bot_num);
                    if (bot == null) {
                        bot = new Bot(bot_num);
                        bots.put(bot_num, bot);
                    }

                    //low
                    if (m.group(2).equals("bot")) {
                        int bot_num2 = Integer.parseInt(m.group(3));
                        Bot bot2 = bots.get(bot_num2);
                        if (bot2 == null) {
                            bot2 = new Bot(bot_num2);
                            bots.put(bot_num2, bot2);
                        }
                        bot.setConnectionLow(bot2);
                    } else {
                        int out_num = Integer.parseInt(m.group(3));
                        Output out = outputs.get(out_num);
                        if (out == null) {
                            out = new Output(out_num);
                            outputs.put(out_num, out);
                        }
                        bot.setConnectionLow(out);
                    }

                    //high
                    if (m.group(4).equals("bot")) {
                        int bot_num2 = Integer.parseInt(m.group(5));
                        Bot bot2 = bots.get(bot_num2);
                        if (bot2 == null) {
                            bot2 = new Bot(bot_num2);
                            bots.put(bot_num2, bot2);
                        }
                        bot.setConnectionHigh(bot2);
                    } else {
                        int out_num = Integer.parseInt(m.group(5));
                        Output out = outputs.get(out_num);
                        if (out == null) {
                            out = new Output(out_num);
                            outputs.put(out_num, out);
                        }
                        bot.setConnectionHigh(out);
                    }
                }
            }
        }

        //initial state done, start passing the chips
        int count = 1;
        while (count != 0) {
            count = 0;
            for (Map.Entry<Integer, Bot> bot : bots.entrySet()) {
                if (bot.getValue().passBricks()) {
                    count++;
                }
            }
            System.out.println("Done: " + count);
        }

        int c = 1;
        for (int i = 0; i < 3; i++) {
            Output out = outputs.get(i);
            if(out != null){
                List<Chip> outChips = out.getChips();
                for(Chip outChip : outChips){
                    c *= outChip.getNumber();
                }
            }
        }
                System.out.println("Multiplication: " + c);

        return null;
    }

}

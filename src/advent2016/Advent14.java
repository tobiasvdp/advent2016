/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tobias
 */
public class Advent14 extends AdventChallenge {

    Pattern p1 = Pattern.compile("([a-z0-9])\\1\\1");
    Pattern p2 = Pattern.compile("([a-z0-9])\\1\\1\\1\\1");

    @Override
    Object getResult(List<String> input) {
        String salt = input.get(0);
        int i = 0;
        int count = 0;

        MessageDigest md = null;
        Map<Integer, Character> hashes3 = new TreeMap<>();
        Map<Character, List<Integer>> hashes5 = new HashMap<>();
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Advent14.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (i < 100000) {
            if (i % 100 == 0) {
                System.out.println("i");
            }

            String s = bytesToHex(md.digest((salt + i).getBytes()));
            for (int x = 0; x < 2016; x++) {
                s = bytesToHex(md.digest((s).getBytes()));
            }
            Matcher m = p1.matcher(s);
            if (m.find()) {
                hashes3.put(i, m.group(1).charAt(0));
            }
            m = p2.matcher(s);

            while (m.find()) {
                char c = m.group(1).charAt(0);
                List<Integer> ints = hashes5.get(c);
                if (ints == null) {
                    ints = new ArrayList<>();
                    ints.add(i);
                    hashes5.put(c, ints);
                } else {
                    ints.add(i);
                }
            }
            i++;
        }

        for (Map.Entry<Integer, Character> entry : hashes3.entrySet()) {
            List<Integer> ints = hashes5.get(entry.getValue());
            if (ints != null) {
                boolean found = false;
                for (Integer x : ints) {
                    if (x > entry.getKey() && x < (entry.getKey() + 1001)) {
                        found = true;
                    }
                }
                if (found) {
                    count++;
                    if (count == 64) {
                        return entry.getKey();
                    }

                }
            }
        }

        return null;
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();

        for (byte b : in) {
            String s = String.format("%02x", b);
            builder.append(s);
        }
        return builder.toString().toLowerCase();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OPGNT007923
 */
public class Advent05 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        int count = 0;
        StringBuilder b = new StringBuilder("P1: ");
        for (String input_s : input) {

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Advent05.class.getName()).log(Level.SEVERE, null, ex);
            }

            boolean cont = true;
            int i = 0;

            //p1
            while (cont) {
                String result = bytesToHex(md.digest((input_s + i).getBytes()));

                if (result.startsWith("00000")) {
                    b.append(result.charAt(5));
                    System.out.println(i + " : " + result);
                    count++;
                    if (count >= 8) {
                        cont = false;
                    }
                }

                if (i % 100000 == 0) {
                    System.out.println(i);
                }

                i++;
            }

            //p2
            cont = true;
            i = 0;
            count = 0;

            b.append(" P2: ");
            char[] arr = new char[8];
            while (cont) {
                String result = bytesToHex(md.digest((input_s + i).getBytes()));

                if (result.startsWith("00000")) {
                    char posc = result.charAt(5);
                    if (Character.isDigit(posc)) {
                        int pos = Integer.parseInt(posc + "");
                        if (pos >= 0 && pos < 8) {
                            if (arr[pos] == 0) {
                                arr[pos] = result.charAt(6);
                                count++;
                                if (count >= 8) {
                                    cont = false;
                                }
                                System.out.println(i + " : " + result);
                            }
                        }

                    }

                }

                if (i % 100000 == 0) {
                    System.out.println(i);
                }

                i++;
            }
            b.append(arr);
        }

        return b.toString();
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();

        int count = 0;
        for (byte b : in) {
            String s = String.format("%02x", b);
            builder.append(s);
            if (count < 2) {
                if (!s.equals("00")) {
                    return builder.toString();
                }
            }
            count++;
        }
        return builder.toString();
    }

}

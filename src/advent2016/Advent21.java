/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tobias
 */
public class Advent21 extends AdventChallenge {

    Pattern rotateLR = Pattern.compile("rotate ([a-zA-Z]*) ([0-9]*) step([a-zA-Z]?)");
    Pattern rotateChar = Pattern.compile("rotate based on position of letter ([a-zA-Z])");
    Pattern swapChar = Pattern.compile("swap letter ([a-zA-Z]) with letter ([a-zA-Z])");
    Pattern swapInt = Pattern.compile("swap position ([0-9]*) with position ([0-9]*)");
    Pattern reverse = Pattern.compile("reverse positions ([0-9]*) through ([0-9]*)");
    Pattern move = Pattern.compile("move position ([0-9]*) to position ([0-9]*)");

    private void swap(int x, int y, char[] arr) {
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void swap(char x, char y, char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                arr[i] = y;
            } else if (arr[i] == y) {
                arr[i] = x;
            }
        }
    }

    private void rotateRight(int amount, char[] arr) {
        for (int x = 0; x < amount; x++) {
            char temp = arr[arr.length - 1];
            for (int i = (arr.length - 1); i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = temp;
        }
    }

    private void rotateLeft(int amount, char[] arr) {
        for (int x = 0; x < amount; x++) {
            char temp = arr[0];
            for (int i = 0; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[arr.length - 1] = temp;
        }
    }

    private void rotateByChar(char x, char[] arr) {
        boolean found = false;
        int position = -1;
        for (int i = 0; i < arr.length && !found; i++) {
            if (arr[i] == x) {
                found = true;
                position = i;
            }
        }
        if (position != -1) {
            rotateRight(1, arr);
            rotateRight(position, arr);
            if (position >= 4) {
                rotateRight(1, arr);
            }
        }
    }

    private void rotateByCharInv(char x, char[] arr) {
        boolean found = false;
        int position = -1;
        for (int i = 0; i < arr.length && !found; i++) {
            if (arr[i] == x) {
                found = true;
                position = i;
            }
        }

        if (position != -1) {
            switch (position) {
                case 0:
                    rotateLeft(1, arr);
                    break;
                case 1:
                    rotateLeft(1, arr);
                    break;
                case 2:
                    rotateRight(2, arr);
                    break;
                case 3:
                    rotateLeft(2, arr);
                    break;
                case 4:
                    rotateRight(1, arr);
                    break;
                case 5:
                    rotateLeft(3, arr);
                    break;
                case 6:
                    break;
                case 7:
                    rotateLeft(4, arr);
                    break;
            }
        }
    }

    private void reverse(int x, int y, char[] arr) {
        char[] temp = new char[y - x + 1];
        int j = 0;
        for (int i = y; i >= x; i--) {
            temp[j] = arr[i];
            j++;
        }
        j = 0;
        for (int i = x; i <= y; i++) {
            arr[i] = temp[j];
            j++;
        }
    }

    private void move(int x, int y, char[] arr) {
        char temp = arr[x];
        if (x == y) {
            return;
        }
        if (y > x) {
            for (int i = x + 1; i <= y; i++) {
                arr[i - 1] = arr[i];
            }
        } else {
            for (int i = x; i > y; i--) {
                arr[i] = arr[i - 1];
            }
        }
        arr[y] = temp;
    }

    @Override
    Object getResult(List<String> input) {
        //p1
        String answer = "abcdefgh";
        //String answer = "abcde";
        char[] arr = answer.toCharArray();

        for (String input_s : input) {
            Matcher m = rotateLR.matcher(input_s);
            if (m.matches()) {
                if (m.group(1).equals("left")) {
                    rotateLeft(Integer.parseInt(m.group(2)), arr);
                } else {
                    rotateRight(Integer.parseInt(m.group(2)), arr);
                }
            } else if ((m = rotateChar.matcher(input_s)).matches()) {
                rotateByChar(m.group(1).charAt(0), arr);
            } else if ((m = swapChar.matcher(input_s)).matches()) {
                swap(m.group(1).charAt(0), m.group(2).charAt(0), arr);
            } else if ((m = swapInt.matcher(input_s)).matches()) {
                swap(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), arr);
            } else if ((m = reverse.matcher(input_s)).matches()) {
                reverse(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), arr);
            } else if ((m = move.matcher(input_s)).matches()) {
                move(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), arr);
            }
            //System.out.println(new String(arr));
        }
        System.out.println("p1 " + new String(arr));

        //p2
        //answer = "fbgdceah";
        answer = "fbgdceah";
        arr = answer.toCharArray();
        Collections.reverse(input);
        for (String input_s : input) {
            Matcher m = rotateLR.matcher(input_s);
            if (m.matches()) {
                if (m.group(1).equals("left")) {
                    rotateRight(Integer.parseInt(m.group(2)), arr);
                } else {
                    rotateLeft(Integer.parseInt(m.group(2)), arr);
                }
            } else if ((m = rotateChar.matcher(input_s)).matches()) {
                rotateByCharInv(m.group(1).charAt(0), arr);
            } else if ((m = swapChar.matcher(input_s)).matches()) {
                swap(m.group(1).charAt(0), m.group(2).charAt(0), arr);
            } else if ((m = swapInt.matcher(input_s)).matches()) {
                swap(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(1)), arr);
            } else if ((m = reverse.matcher(input_s)).matches()) {
                reverse(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), arr);
            } else if ((m = move.matcher(input_s)).matches()) {
                move(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(1)), arr);
            }
            System.out.println(new String(arr));
        }
        return new String(arr);
    }

}

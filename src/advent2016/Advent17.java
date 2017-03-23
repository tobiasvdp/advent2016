/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent17.Node;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tobia
 */
public class Advent17 extends AdventChallenge {

    public static MessageDigest md;
    public static final int roomSizeX = 4;
    public static final int roomSizeY = 4;

    @Override
    Object getResult(List<String> input) {
        String input_s = input.get(0);
        md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Advent05.class.getName()).log(Level.SEVERE, null, ex);
        }

        int xPos = 0, yPos = 0;
        Node start = new Node(xPos, yPos, input_s, null);
        start.generateNeighbours();
        List<Node> currentRooms = new ArrayList<>();
        currentRooms.add(start);
        List<Node> nextRooms = new ArrayList<>();
        while (true) {
            if (currentRooms.size() == 0) {
                return null;
            }
            for (Node n : currentRooms) {
                //victory
                if (n.getX() == 3 && n.getY() == 3) {
                    System.out.println(n.getPath());
                    System.out.println("Length: " + (n.getPath().length() - 8));
                } else {
                    n.generateNeighbours();
                    nextRooms.addAll(n.getNeighbours());
                }
            }
            currentRooms.clear();
            currentRooms.addAll(nextRooms);
            nextRooms.clear();
        }
    }

}

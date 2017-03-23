/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent22.Move;
import advent2016.advent22.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tobia
 */
public class Advent22 extends AdventChallenge {

    Pattern p = Pattern.compile("\\/dev\\/grid\\/node-x([0-9]*)-y([0-9]*)[ ]*([0-9]*)T[ ]*([0-9]*)T[ ]*([0-9]*)T[ ]*([0-9]*)%");

    @Override
    Object getResult(List<String> input) {
        int maxX = 0, maxY = 0;
        for (String input_s : input) {
            Matcher m = p.matcher(input_s);
            if (m.matches()) {
                if (Integer.parseInt(m.group(1)) > maxX) {
                    maxX = Integer.parseInt(m.group(1));
                }
                if (Integer.parseInt(m.group(2)) > maxY) {
                    maxY = Integer.parseInt(m.group(2));
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }

        Node[][] rooms = generateRooms(maxX, maxY);
        connectNeigbours(rooms);

        for (String input_s : input) {
            Matcher m = p.matcher(input_s);
            if (m.matches()) {
                int x = Integer.parseInt(m.group(1));
                int y = Integer.parseInt(m.group(2));
                rooms[x][y].setSize(Integer.parseInt(m.group(3)));
                rooms[x][y].setUsed(Integer.parseInt(m.group(4)));
                rooms[x][y].setAvail(Integer.parseInt(m.group(5)));
            } else {
                throw new UnsupportedOperationException();
            }
        }

        //p1
        int count = 0;
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                Node room1 = rooms[x][y];
                if (room1.getUsed() != 0) {
                    for (int xi = 0; xi <= maxX; xi++) {
                        for (int yi = 0; yi <= maxY; yi++) {
                            if (xi == x && yi == y) {
                            } else if (rooms[xi][yi].getAvail() >= room1.getUsed()) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("p1: " + count);

        rooms[0][0].setTarget2(true);
        rooms[maxX][0].setTarget1(true);

        printout(rooms);

        int xempty = 0, yempty = 0;
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                if (rooms[x][y].getUsed() == 0) {
                    xempty = x;
                    yempty = y;
                }
            }
        }

        count = 0;
        while (true) {
            try {
                int r = System.in.read();
                char c = (char) r;
                System.out.println("");
                System.out.println(c);
                switch (c) {
                    case 's':
                        rooms[xempty][yempty].setUsed(rooms[xempty][yempty + 1].getUsed());
                        rooms[xempty][yempty].setAvail(rooms[xempty][yempty].getSize() - rooms[xempty][yempty + 1].getUsed());
                        rooms[xempty][yempty + 1].setAvail(rooms[xempty][yempty + 1].getSize());
                        rooms[xempty][yempty + 1].setUsed(0);
                        if (rooms[xempty][yempty + 1].isTarget1()) {
                            rooms[xempty][yempty].setTarget1(true);
                            rooms[xempty][yempty + 1].setTarget1(false);
                        }
                        yempty++;
                        count++;
                        break;
                    case 'q':
                        rooms[xempty][yempty].setUsed(rooms[xempty - 1][yempty].getUsed());
                        rooms[xempty][yempty].setAvail(rooms[xempty][yempty].getSize() - rooms[xempty - 1][yempty].getUsed());
                        rooms[xempty - 1][yempty].setAvail(rooms[xempty - 1][yempty].getSize());
                        rooms[xempty - 1][yempty].setUsed(0);
                        if (rooms[xempty - 1][yempty].isTarget1()) {
                            rooms[xempty][yempty].setTarget1(true);
                            rooms[xempty - 1][yempty].setTarget1(false);
                        }
                        xempty--;
                        count++;
                        break;
                    case 'd':
                        rooms[xempty][yempty].setUsed(rooms[xempty + 1][yempty].getUsed());
                        rooms[xempty][yempty].setAvail(rooms[xempty][yempty].getSize() - rooms[xempty + 1][yempty].getUsed());
                        rooms[xempty + 1][yempty].setAvail(rooms[xempty + 1][yempty].getSize());
                        rooms[xempty + 1][yempty].setUsed(0);
                        if (rooms[xempty + 1][yempty].isTarget1()) {
                            rooms[xempty][yempty].setTarget1(true);
                            rooms[xempty + 1][yempty].setTarget1(false);
                        }
                        xempty++;
                        count++;
                        break;
                    case 'z':
                        rooms[xempty][yempty].setUsed(rooms[xempty][yempty - 1].getUsed());
                        rooms[xempty][yempty].setAvail(rooms[xempty][yempty].getSize() - rooms[xempty][yempty - 1].getUsed());
                        rooms[xempty][yempty - 1].setAvail(rooms[xempty][yempty - 1].getSize());
                        rooms[xempty][yempty - 1].setUsed(0);
                        if (rooms[xempty][yempty - 1].isTarget1()) {
                            rooms[xempty][yempty].setTarget1(true);
                            rooms[xempty][yempty - 1].setTarget1(false);
                        }
                        yempty--;
                        count++;
                        break;
                }

                System.out.println(count + "");
                printout(rooms);
            } catch (IOException ex) {
                Logger.getLogger(Advent22.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*        
        Node start = rooms[maxX][0];
        ArrayList<Node> todo = new ArrayList<>();
        todo.add(start);
        
        List<Move> todoMoves = new ArrayList<>();
        todoMoves.add(new Move(rooms,todo));
        
        List<Move> childeren = new ArrayList();
        
        while(!todo.isEmpty()){
            for(Move n : todoMoves){
                childeren.addAll(n.getAvailableMoves());
            }
            todoMoves.clear();
            todoMoves.addAll(childeren);
            childeren.clear();
        }*/
    }

    Node[][] generateRooms(int i, int j) {
        Node[][] out = new Node[i + 1][j + 1];
        for (int x = 0; x <= i; x++) {
            for (int y = 0; y <= j; y++) {
                out[x][y] = new Node(x, y);
            }
        }
        return out;
    }

    public static void connectNeigbours(Node[][] rooms) {
        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms[0].length; x++) {
                if (y > 0) {

                    rooms[y][x].addNeigbour(rooms[y - 1][x]);

                }
                if (y < rooms.length - 1) {

                    rooms[y][x].addNeigbour(rooms[y + 1][x]);

                }
                if (x > 0) {

                    rooms[y][x].addNeigbour(rooms[y][x - 1]);

                }
                if (x < rooms[0].length - 1) {

                    rooms[y][x].addNeigbour(rooms[y][x + 1]);

                }

            }
        }
    }

    private void printout(Node[][] rooms) {
        for (int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                System.out.print(rooms[x][y].toString());
            }
            System.out.println("");
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent13.Node;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tobias
 */
public class Advent13 extends AdventChallenge {

    private int inputChallenge = 1364;
    //private int inputChallenge = 1350;

    @Override
    Object getResult(List<String> input) {
        Node[][] rooms = generateRooms(50, 50);
        connectNeigbours(rooms);
        findPath(rooms, 1, 1, 31, 39);
        //output
        int count = 0;
        System.out.print(" ");
        for (int x = 0; x < rooms[0].length; x++) {
            if (x < 10) {
                System.out.print(" ");
            }
            System.out.print(x + "");
        }
        System.out.println("");
        for (int y = 0; y < rooms.length; y++) {
            if (y < 10) {
                System.out.print(" ");
            }
            System.out.print(y + "");
            for (int x = 0; x < rooms[0].length; x++) {
                if (x == y && y == 1) {
                    System.out.print("0");
                } else if (x == 31 && y == 39) {
                    System.out.print("R");
                    count++;
                } else if(rooms[y][x].isPath()){
                    System.out.print("X");
                    count++;
                }else if (rooms[y][x].getValue()) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
           
        }
        
        //p2
        int maxReach = 0;
        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms[0].length; x++) {
                if(rooms[y][x].getDistance()<=50){
                    maxReach++;
                }
            }
        }
        return "P1: " + count + "P2: " + maxReach;
    }

    Node[][] generateRooms(int i, int j) {
        Node[][] out = new Node[i][j];
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < j; x++) {
                if (isWall(x, y, inputChallenge)) {
                    out[y][x] = new Node(true);
                } else {
                    out[y][x] = new Node(false);
                }
            }
        }
        return out;
    }

    private boolean isWall(int x, int y, int inputChallenge) {
        int z = x * x + 3 * x + 2 * x * y + y + y * y;
        z += inputChallenge;
        String s = Integer.toBinaryString(z);
        int count = s.length() - s.replace("1", "").length();
        if (count % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void findPath(Node[][] rooms, int x, int y, int xg, int yg) {
        Set<Node> evaluated = new HashSet<Node>();
        Set<Node> knownNodes = new HashSet<Node>();
        Node goal = rooms[yg][xg];
        knownNodes.add(rooms[y][x]);
        rooms[y][x].setDistance(0);
        
        
        while(!knownNodes.isEmpty()){
            //get node with lowest distance
            int distance = Integer.MAX_VALUE;
            Node currentNode = null;
            for(Node n : knownNodes){
                if(n.getDistance() < distance){
                    distance = n.getDistance();
                    currentNode = n;
                }
            }
            if(currentNode == null){
                currentNode = knownNodes.iterator().next();
            }
            
            if(currentNode.equals(goal)){
                hilightRoad(goal);
                return;
            }
            
            //remove from known list and add to checkedList
            knownNodes.remove(currentNode);
            evaluated.add(currentNode);
            
            for(Node n : currentNode.getNeighbours()){
                if(!evaluated.contains(n)){
                    int dist = currentNode.getDistance()+n.getWeight();
                    if(!knownNodes.contains(n)){
                        knownNodes.add(n);
                        n.setDistance(dist);
                        n.setOrign(currentNode);
                    }else{
                        if(dist < n.getDistance()){
                            n.setDistance(dist);
                            n.setOrign(currentNode);
                        } 
                    }
                }
            }
            
            
        }
    }

    private void connectNeigbours(Node[][] rooms) {
        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms[0].length; x++) {
                if (y > 0) {
                    if (!rooms[y - 1][x].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y - 1][x]);
                    }
                }
                if (y < rooms.length-1) {
                    if (!rooms[y + 1][x].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y + 1][x]);
                    }
                }
                if (x > 0) {
                    if (!rooms[y][x - 1].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y][x - 1]);
                    }
                }
                if (x < rooms[0].length-1) {
                    if (!rooms[y][x + 1].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y][x + 1]);
                    }
                }
                
            }
        }
    }

    private void hilightRoad(Node goal) {
        Node n = goal;
        do{
            n.setPath();
            n = n.whereICamefrom();
        }while(n != null);
    }

}

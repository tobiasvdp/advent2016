/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent24.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tobia
 */
public class Advent24 extends AdventChallenge {

    @Override
    Object getResult(List<String> input) {
        Node[][] rooms = generateRooms(input);
        connectNeigbours(rooms);

        Node start = findNumber(rooms, 0);
        List<Node> toConnect = new ArrayList<>();
        int i = 1;
        Node next = findNumber(rooms, i);
        while (next != null) {
            toConnect.add(next);
            i++;
            next = findNumber(rooms, i);
        }
        List<List<Node>> perms = new ArrayList<>();
        permute(toConnect, 0, perms);

        int minimum = Integer.MAX_VALUE;
        List<Node> result = null;
        for (List<Node> dest : perms) {
            int count = 0;
            int l = 0;
            Node startL = start;
            Node nextL = dest.get(l++);
            while (l < dest.size()) {
                count += findPath(rooms, startL.getX(), startL.getY(), nextL.getX(), nextL.getY());
                startL = nextL;
                nextL = dest.get(l++);
            }
            count += findPath(rooms, startL.getX(), startL.getY(), nextL.getX(), nextL.getY());
            
            //p2
            count += findPath(rooms, nextL.getX(), nextL.getY(), start.getX(), start.getY());
            if(minimum > count){
                minimum = count;
                result = dest;
            }
            System.out.println(count);
        }

        System.out.println(Arrays.toString(result.toArray()));
        return minimum;
    }

    private int findPath(Node[][] rooms, int x, int y, int xg, int yg) {
        Set<Node> evaluated = new HashSet<Node>();
        Set<Node> knownNodes = new HashSet<Node>();
        Node goal = rooms[yg][xg];
        knownNodes.add(rooms[y][x]);

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                rooms[i][j].setDistance(Integer.MAX_VALUE);
                rooms[i][j].setOrign(null);
            }
        }
        rooms[y][x].setDistance(0);

        while (!knownNodes.isEmpty()) {
            //get node with lowest distance
            int distance = Integer.MAX_VALUE;
            Node currentNode = null;
            for (Node n : knownNodes) {
                if (n.getDistance() < distance) {
                    distance = n.getDistance();
                    currentNode = n;
                }
            }
            if (currentNode == null) {
                currentNode = knownNodes.iterator().next();
            }

            if (currentNode.equals(goal)) {
                //hilightRoad(goal);
                return countRoad(goal);
            }

            //remove from known list and add to checkedList
            knownNodes.remove(currentNode);
            evaluated.add(currentNode);

            for (Node n : currentNode.getNeighbours()) {
                if (!evaluated.contains(n)) {
                    int dist = currentNode.getDistance() + n.getWeight();
                    if (!knownNodes.contains(n)) {
                        knownNodes.add(n);
                        n.setDistance(dist);
                        n.setOrign(currentNode);
                    } else if (dist < n.getDistance()) {
                        n.setDistance(dist);
                        n.setOrign(currentNode);
                    }
                }
            }

        }
        return 0;
    }

    private Node[][] generateRooms(List<String> input) {
        List<Node[]> arr = new ArrayList<>();
        int y = 0;
        for (String input_s : input) {
            char[] carr = input_s.toCharArray();
            Node[] arr1 = new Node[carr.length];
            for (int i = 0; i < arr1.length; i++) {
                switch (carr[i]) {
                    case '#':
                        arr1[i] = new Node(false, i, y);
                        break;
                    case '.':
                        arr1[i] = new Node(true, i, y);
                        break;
                    default:
                        arr1[i] = new Node(true, i, y);
                        arr1[i].setNumber(Integer.parseInt(carr[i] + ""));
                }
            }
            arr.add(arr1);
            y++;
        }
        Node[][] n = new Node[arr.size()][];
        return arr.toArray(n);
    }

    private void connectNeigbours(Node[][] rooms) {
        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms[0].length; x++) {
                if (y > 0) {
                    if (rooms[y - 1][x].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y - 1][x]);
                    }
                }
                if (y < rooms.length - 1) {
                    if (rooms[y + 1][x].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y + 1][x]);
                    }
                }
                if (x > 0) {
                    if (rooms[y][x - 1].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y][x - 1]);
                    }
                }
                if (x < rooms[0].length - 1) {
                    if (rooms[y][x + 1].getValue()) {
                        rooms[y][x].addNeigbour(rooms[y][x + 1]);
                    }
                }

            }
        }
    }

    private void printout(Node[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                if (arr[y][x].getNumber() != -1) {
                    System.out.print(arr[y][x].getNumber());
                } else if (arr[y][x].isPath()) {
                    System.out.print("X");
                } else if (arr[y][x].getValue()) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println("");
        }
    }

    private void hilightRoad(Node goal) {
        Node n = goal;
        do {
            n.setPath();
            n = n.whereICamefrom();
        } while (n != null);
    }

    private int countRoad(Node goal) {
        Node n = goal;
        int count = 0;
        do {
            count++;
            n = n.whereICamefrom();
        } while (n != null);
        return count - 1;
    }

    private Node findNumber(Node[][] arr, int number) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                if (arr[y][x].getNumber() == number) {
                    return arr[y][x];
                }
            }
            System.out.println("");
        }
        return null;
    }

    static void permute(java.util.List<Node> arr, int k, List<List<Node>> perms) {
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1, perms);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            //System.out.println(java.util.Arrays.toString(arr.toArray()));
            List<Node> x = new ArrayList<>();
            x.addAll(arr);
            perms.add(x);
        }
    }
}

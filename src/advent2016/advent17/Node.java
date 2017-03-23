/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent17;

import advent2016.Advent17;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tobia
 */
public class Node {

    int x, y;
    String path;
    Node prevNode;
    List<Node> neighbours;

    public Node(int x, int y, String path, Node prevNode) {
        this.x = x;
        this.y = y;
        this.path = path;
        this.prevNode = prevNode;
        //this.neighbours = generateNeighbours();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void generateNeighbours() {
        List<Node> neigbours = new ArrayList<>();
        char[] arr = bytesToHexFirst4(Advent17.md.digest(path.getBytes())).toCharArray();
        //left
        if (x > 0) {
            if (isDoorOpen(arr[2])) {
                neigbours.add(new Node(x - 1, y, path + "L", this));
            }
        }
        //right
        if (x < Advent17.roomSizeX-1) {
            if (isDoorOpen(arr[3])) {
                neigbours.add(new Node(x + 1, y, path + "R", this));
            }
        }
        //top
        if (y > 0) {
            if (isDoorOpen(arr[0])) {
                neigbours.add(new Node(x, y -1, path + "U", this));
            }
        }
        //down
        if (y < Advent17.roomSizeY-1) {
            if (isDoorOpen(arr[1])) {
                neigbours.add(new Node(x, y+1, path + "D", this));
            }
        }

        this.neighbours = neigbours;
    }

    public static String bytesToHexFirst4(byte[] in) {
        final StringBuilder builder = new StringBuilder();

        int count = 0;
        for (byte b : in) {
            String s = String.format("%02x", b);
            builder.append(s);
            count++;

            if (count == 2) {
                return builder.toString().toLowerCase();
            }
        }
        return builder.toString();
    }

    private boolean isDoorOpen(char c) {
        switch (c) {
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                return true;
            default:
                return false;
        }
    }

}

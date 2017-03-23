/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent22;

import advent2016.advent17.*;
import advent2016.Advent17;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tobia
 */
public class Node {

    int x, y;
    List<Node> neighbours;
    int size, avail, used;
    boolean target1;
    boolean target2;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        neighbours = new ArrayList<>();

        //this.neighbours = generateNeighbours();
    }

    public Node(int x, int y, int size, int avail, int used) {
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
        this.size = size;
        this.avail = avail;
        this.used = used;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
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

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeigbour(Node neighbour) {
        this.neighbours.add(neighbour);
    }

    public int getAvailableMoveCount() {
        int count = 0;
        if (getAvail() == getSize()) {
            return count;
        }
        for (Node neigb : neighbours) {
            if (getUsed() <= neigb.getAvail()) {
                count++;
            }
        }

        return count;
    }

    Node Clone() {
        return new Node(x, y, size, avail, used);
    }

    public boolean isTarget1() {
        return target1;
    }

    public void setTarget1(boolean target1) {
        this.target1 = target1;
    }

    public boolean isTarget2() {
        return target2;
    }

    public void setTarget2(boolean target2) {
        this.target2 = target2;
    }
    
    

    @Override
    public String toString() {
        if(target1)
            return "O";
        if(target2)
            return "G";
        if (getUsed() == 0) {
            return "_";
        }
        if (getSize() > 200) {
            return "#";
        }
        return ".";
    }

}

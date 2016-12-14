/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent13;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tobias
 */
public class Node {
    private boolean value;
    
    private List<Node> neigbours;
    private Node cameFrom;
    private int distance,weight;
    private boolean isPath;

    public Node(boolean value) {
        this.value = value;
        isPath = false;
        neigbours = new ArrayList<>();
        weight = 1;
        distance = Integer.MAX_VALUE;
    }
    
    public boolean getValue(){
        return this.value;
    }

    public void addNeigbour(Node node) {
        neigbours.add(node);
    }
    
    public void setDistance(int i){
        distance = i;
    }

    public int getDistance() {
        return distance;
    }

    public Iterable<Node> getNeighbours() {
        return neigbours; 
    }

    public int getWeight() {
        return weight;
    }

    public void setOrign(Node currentNode) {
        cameFrom = currentNode;
    }
    public boolean isPath(){
        return isPath;
    }
    public void setPath(){
        this.isPath = true;
    }
    
    public Node whereICamefrom(){
        return cameFrom;
    }
}

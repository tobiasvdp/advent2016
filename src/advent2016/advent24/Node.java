/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent24;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tobias
 */
public class Node {
    private boolean value;
    private int number;
    private int x,y;
    
    private List<Node> neigbours;
    private Node cameFrom;
    private int distance,weight;
    private boolean isPath;

    public Node(boolean value, int x , int y) {
        this.value = value;
        isPath = false;
        neigbours = new ArrayList<>();
        weight = 1;
        distance = Integer.MAX_VALUE;
        number = -1;
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return getNumber()+"";
    }
    
    

    public void setY(int y) {
        this.y = y;
    }
    
    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

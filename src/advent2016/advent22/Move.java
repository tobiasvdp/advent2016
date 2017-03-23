/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent22;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tobia
 */
public class Move {

    private Node[][] nodes;
    ArrayList<Node> todo;

    public Move(Node[][] nodes, ArrayList<Node> todo) {
        this.nodes = new Node[nodes.length][nodes[0].length];
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[0].length; y++) {
                this.nodes[x][y] = nodes[x][y].Clone();
            }
        }
        advent2016.Advent22.connectNeigbours(this.nodes);
        this.todo = new ArrayList<>();
        for (Node n : todo) {
            this.todo.add(this.nodes[n.x][n.y]);
        }
    }

    private Move(Node[][] nodes, Node n, Node neigb) {
        this.nodes = new Node[nodes.length][nodes[0].length];
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[0].length; y++) {
                this.nodes[x][y] = nodes[x][y].Clone();
                advent2016.Advent22.connectNeigbours(this.nodes);
            }
        }
        this.todo = new ArrayList<>();
        neigb = this.nodes[neigb.x][neigb.y];
        n = this.nodes[n.x][n.y];
        this.todo.add(neigb);
        neigb.setAvail(neigb.getAvail() - n.getUsed());
        neigb.setUsed(neigb.getUsed() + n.getUsed());
        n.setUsed(0);
        n.setAvail(n.getSize());
    }

    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();
        for (Node n : todo) {
            for (Node neigb : n.neighbours) {
                if (neigb.getUsed() != 0 && n.getUsed() <= neigb.getAvail()) {
                    Move m = new Move(nodes, n, neigb);
                    moves.add(m);
                }
            }
        }
        return moves;
    }

}

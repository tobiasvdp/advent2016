/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016;

import advent2016.advent11.Node;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tobias
 */
public class Advent11 extends AdventChallenge {

    public static String[] names = {"s", "p", "t", "r", "c"};

    @Override
    Object getResult(List<String> input) {
        List<Node> nodes = new ArrayList<>();
        boolean[][] gens = new boolean[4][7];
        boolean[][] chips = new boolean[4][7];
        gens[0][0] = true;
        chips[0][0] = true;
        gens[0][1] = true;
        chips[0][1] = true;
        gens[1][2] = true;
        chips[2][2] = true;
        gens[1][3] = true;
        chips[1][3] = true;
        gens[1][4] = true;
        chips[1][4] = true;
        //p2 new parts
        chips[0][5] = true;
        chips[0][6] = true;
        gens[0][5] = true;
        gens[0][6] = true;

        /*boolean[][] gens = new boolean[4][2];
        boolean[][] chips = new boolean[4][2];
        chips[0][0] = true;
        chips[0][1] = true;
        gens[1][0] = true;
        gens[2][1] = true;*/
        Node n = new Node(gens, chips, 0, 0, -1);
        Set<String> signatures = new HashSet<>();
        if (n.isValid()) {
            signatures.add(n.generateSignature());
            nodes.add(n);
        }

        while (!nodes.isEmpty()) {
            System.out.println(nodes.get(0).getDepth());
            List<Node> childeren = new ArrayList<>();
            for (Node node : nodes) {
                //System.out.println("p:" + node.generateSignature());
                List<Node> newNodes = node.generateNextNodes(signatures);
                childeren.addAll(newNodes);
                for (Node c : newNodes) {
                    //System.out.println("c:" + c.generateSignature());
                    //if (c.generateSignature().endsWith("3333333333")) {
                    //    return c.getDepth();
                    //}
                    if (c.generateSignature().endsWith("33333333333333")) {
                        return c.getDepth();
                    }
                }
            }
            nodes.clear();
            nodes.addAll(childeren);
        }
        return null;
    }

}

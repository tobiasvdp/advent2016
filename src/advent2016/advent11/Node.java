/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent11;

import advent2016.Advent11;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tobias
 */
public class Node {

    boolean[][] gens, chips;
    int depth;
    int elev;
    int prevElev;

    public Node(boolean[][] gens, boolean[][] chips, int depth, int elev, int prevElev) {
        this.gens = gens;
        this.chips = chips;
        this.depth = depth;
        this.elev = elev;
        this.prevElev = prevElev;
    }

    public List<Node> generateNextNodes(Set<String> signatures) {

        List<Node> out = new ArrayList<>();
        int[] dirs = new int[]{-1, 1};

        for (int dir : dirs) {
            int newElev = elev + dir;
            if (newElev >= 0 && newElev < gens.length) {
                //single
                for (int i = 0; i < gens[0].length; i++) {
                    if (gens[elev][i]) {
                        boolean[][] newGens = copyArray(gens);
                        boolean[][] newChips = copyArray(chips);
                        newGens[newElev][i] = true;
                        newGens[elev][i] = false;
                        Node n = new Node(newGens, newChips, depth + 1, newElev, elev);
                        if (n.isValid() && !signatures.contains(n.generateSignature())) {
                            signatures.add(n.generateSignature());
                            out.add(n);
                        }
                    }
                    if (chips[elev][i]) {
                        boolean[][] newGens = copyArray(gens);
                        boolean[][] newChips = copyArray(chips);
                        newChips[newElev][i] = true;
                        newChips[elev][i] = false;
                        Node n = new Node(newGens, newChips, depth + 1, newElev, elev);
                        if (n.isValid() && !signatures.contains(n.generateSignature())) {
                            signatures.add(n.generateSignature());
                            out.add(n);
                        }
                    }
                }

                //double
                for (int i = 0; i < gens[0].length; i++) {
                    if (gens[elev][i]) {
                        for (int j = 0; j < gens[0].length; j++) {
                            if (gens[elev][j]) {
                                //allowed
                                boolean[][] newGens = copyArray(gens);
                                boolean[][] newChips = copyArray(chips);
                                newGens[newElev][i] = true;
                                newGens[elev][i] = false;
                                newGens[newElev][j] = true;
                                newGens[elev][j] = false;
                                Node n = new Node(newGens, newChips, depth + 1, newElev, elev);
                                if (n.isValid() && !signatures.contains(n.generateSignature())) {
                                    signatures.add(n.generateSignature());
                                    out.add(n);
                                }
                            }
                            if (chips[elev][j]) {
                                //only when chip is same as gen
                                boolean[][] newGens = copyArray(gens);
                                boolean[][] newChips = copyArray(chips);
                                newGens[newElev][i] = true;
                                newGens[elev][i] = false;
                                newChips[newElev][j] = true;
                                newChips[elev][j] = false;
                                Node n = new Node(newGens, newChips, depth + 1, newElev, elev);
                                if (n.isValid() && !signatures.contains(n.generateSignature())) {
                                    signatures.add(n.generateSignature());
                                    out.add(n);
                                }
                            }
                        }
                    }
                    if (chips[elev][i]) {
                        for (int j = 0; j < gens[0].length; j++) {
                            if (gens[elev][j]) {
                                //already done in above
                            }
                            if (chips[elev][j]) {
                                //always
                                boolean[][] newGens = copyArray(gens);
                                boolean[][] newChips = copyArray(chips);
                                newChips[newElev][i] = true;
                                newChips[elev][i] = false;
                                newChips[newElev][j] = true;
                                newChips[elev][j] = false;
                                Node n = new Node(newGens, newChips, depth + 1, newElev, elev);
                                if (n.isValid() && !signatures.contains(n.generateSignature())) {
                                    signatures.add(n.generateSignature());
                                    out.add(n);
                                }
                            }
                        }
                    }
                }
            }
        }
        return out;
    }

    public boolean[][] copyArray(boolean[][] gens) {
        boolean[][] newGens = new boolean[gens.length][gens[0].length];
        for (int x = 0; x < gens.length; x++) {
            System.arraycopy(gens[x], 0, newGens[x], 0, gens[0].length);
        }
        return newGens;
    }

    public String generateSignature() {
        StringBuilder b = new StringBuilder();
        b.append(elev + "");
        for (int x = 0; x < gens[0].length; x++) {
            for (int i = 0; i < gens.length; i++) {
                if (gens[i][x]) {
                    b.append(i + "");
                }
            }
            for (int i = 0; i < chips.length; i++) {
                if (chips[i][x]) {
                    b.append(i + "");
                }
            }
        }

        return b.toString();
    }

    @Override
    public String toString() {
        for (int i = 0; i < gens.length; i++) {
            if (prevElev == i) {
                System.out.print("0! ");
            } else if (elev == i) {
                System.out.print("E! ");
            } else {
                System.out.print(" ! ");
            }
            for (int j = 0; j < gens[0].length; j++) {
                if (gens[i][j]) {
                    System.out.print(Advent11.names[j] + "g - ");
                } else {
                    System.out.print("   - ");
                }
                if (chips[i][j]) {
                    System.out.print(Advent11.names[j] + "m - ");
                } else {
                    System.out.print("   - ");
                }
            }
            System.out.println("");
        }
        return "";
    }

    public int getDepth() {
        return this.depth;
    }

    public boolean isValid() {
        int el = elev;
        boolean isGenPresent = false;
        for (int i = 0; i < gens[0].length; i++) {
            if (gens[el][i]) {
                isGenPresent = true;
            }
        }

        for (int i = 0; i < gens[0].length; i++) {
            if (isGenPresent) {
                if (chips[el][i] && !gens[el][i]) {
                    return false;
                }
            }
        }

        if (prevElev != -1) {
            el = prevElev;
            isGenPresent = false;
            for (int i = 0; i < gens[0].length; i++) {
                if (gens[el][i]) {
                    isGenPresent = true;
                }
            }

            for (int i = 0; i < gens[0].length; i++) {
                if (isGenPresent) {
                    if (chips[el][i] && !gens[el][i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

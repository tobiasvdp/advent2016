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

    public Node(boolean[][] gens, boolean[][] chips, int depth, int elev,int prevElev) {
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

                //single moves
                List<Integer> movesGen = new ArrayList<>();
                List<Integer> movesChip = new ArrayList<>();
                for (int i = 0; i < gens[0].length; i++) {
                    if (gens[elev][i]) {
                        if (chips[elev][i]) {
                            if (noGens(gens[elev], i)) {
                                //may leave, no corruption for chip
                                if (noUnprotectedChips(gens[newElev], chips[newElev], i)) {
                                    //may arrive, no problems for chips
                                    movesGen.add(i);
                                }
                            }
                        } else if (noUnprotectedChips(gens[newElev], chips[newElev], i)) {
                            movesGen.add(i);
                        }
                    }
                    if (chips[elev][i]) {
                        if (gens[newElev][i]) {
                            //compat gen present on new floor, ok to move
                            movesChip.add(i);
                        } else if (noGens(gens[newElev])) {
                            movesChip.add(i);
                        }
                    }
                }

                //dual moves
                List<Integer[]> gensDual = new ArrayList<>();
                List<Integer[]> chipDual = new ArrayList<>();
                List<Integer[]> genAndChipDual = new ArrayList<>();
                for (int i = 0; i < gens[0].length; i++) {
                    for (int j = 0; j < gens[0].length; j++) {
                        if (gens[elev][i]) {
                            if (gens[elev][j] && i != j) {
                                if (noUnprotectedChips(gens[newElev], chips[newElev], i, j)) {
                                    //can only go if gen does not irradiate everything
                                    if(chips[elev][i] || chips[elev][j]){
                                        if(noGens(gens[elev], i, j)){
                                            gensDual.add(new Integer[]{i, j});
                                        }
                                    }else{
                                        gensDual.add(new Integer[]{i, j});
                                    }
                                }
                            }
                            if (i == j && chips[elev][j]) {

                                //only chip with same generator can travel together
                                if (noUnprotectedChips(gens[newElev], chips[newElev], i)) {
                                    //can only go if gen does not irradiate everything
                                    genAndChipDual.add(new Integer[]{j, i});
                                }

                            }
                        }
                        if (chips[elev][i]) {
                            if (i == j && gens[elev][j]) {
                                //already containt in gen
                            }
                            if (i != j && chips[elev][j]) {
                                //2 chips can only move if both are allowed
                                if (noGens(gens[newElev], i, j)) {
                                    chipDual.add(new Integer[]{i, j});
                                }
                            }
                        }
                    }
                }

                //generate nodes
                for (int i : movesGen) {

                    boolean[][] newGens = copyArray(gens);
                    boolean[][] newChips = copyArray(chips);

                    newGens[elev][i] = false;
                    newGens[newElev][i] = true;

                    Node n = new Node(newGens, newChips, depth + 1, newElev,elev);
                    String sign = n.generateSignature();
                    if (!signatures.contains(sign)) {
                        out.add(n);
                        signatures.add(sign);
                    }
                }
                for (int i : movesChip) {

                    boolean[][] newGens = copyArray(gens);
                    boolean[][] newChips = copyArray(chips);

                    newChips[elev][i] = false;
                    newChips[newElev][i] = true;

                    Node n = new Node(newGens, newChips, depth + 1, newElev,elev);
                    String sign = n.generateSignature();
                    if (!signatures.contains(sign)) {
                        out.add(n);
                        signatures.add(sign);
                    }
                }
                for (Integer[] i : chipDual) {

                    boolean[][] newGens = copyArray(gens);
                    boolean[][] newChips = copyArray(chips);

                    newChips[elev][i[0]] = false;
                    newChips[newElev][i[0]] = true;
                    newChips[elev][i[1]] = false;
                    newChips[newElev][i[1]] = true;

                    Node n = new Node(newGens, newChips, depth + 1, newElev,elev);
                    String sign = n.generateSignature();
                    if (!signatures.contains(sign)) {
                        out.add(n);
                        signatures.add(sign);
                    }
                }
                for (Integer[] i : gensDual) {

                    boolean[][] newGens = copyArray(gens);
                    boolean[][] newChips = copyArray(chips);

                    newGens[elev][i[0]] = false;
                    newGens[newElev][i[0]] = true;
                    newGens[elev][i[1]] = false;
                    newGens[newElev][i[1]] = true;

                    Node n = new Node(newGens, newChips, depth + 1, newElev,elev);
                    String sign = n.generateSignature();
                    if (!signatures.contains(sign)) {
                        out.add(n);
                        signatures.add(sign);
                    }
                }
                for (Integer[] i : genAndChipDual) {

                    boolean[][] newGens = copyArray(gens);
                    boolean[][] newChips = copyArray(chips);

                    newGens[elev][i[0]] = false;
                    newGens[newElev][i[0]] = true;
                    newChips[elev][i[1]] = false;
                    newChips[newElev][i[1]] = true;

                    Node n = new Node(newGens, newChips, depth + 1, newElev,elev);
                    String sign = n.generateSignature();
                    if (!signatures.contains(sign)) {
                        out.add(n);
                        signatures.add(sign);
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
        
        for (int x = 0; x < gens[0].length; x++) {
            for(int i =0;i<gens.length;i++){
                if(gens[i][x]){
                    b.append(i+"");
                }
            }
            for(int i =0;i<chips.length;i++){
                if(chips[i][x]){
                    b.append(i+"");
                }
            }
        }

        return b.toString();
    }

    private boolean noGens(boolean[] gen) {
        return noGens(gen, -1);
    }

    private boolean noGens(boolean[] gen, int x) {
        return noGens(gen, x, x);
    }

    private boolean noUnprotectedChips(boolean[] gen, boolean[] chip, int x) {
        return noUnprotectedChips(gen, chip, x, x);
    }

    private boolean noUnprotectedChips(boolean[] gen, boolean[] chip, int x, int y) {
        for (int i = 0; i < chip.length; i++) {
            if (i != x && i != y && chip[i]) {
                if (!gen[i]) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean noGens(boolean[] gen, int x, int y) {
        for (int i = 0; i < gen.length; i++) {
            if (i != x && i != y && gen[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        for (int i = 0; i < gens.length; i++) {
            if(prevElev == i){
                System.out.print("0! ");
            }else if (elev==i){
                System.out.print("E! ");
            }else{
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

}

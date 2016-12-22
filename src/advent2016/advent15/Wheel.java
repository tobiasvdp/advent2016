/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent15;

/**
 *
 * @author Tobias
 */
public class Wheel {
    private int positions, number,offset;

    public Wheel(int number,int positions, int position) {
        this.positions = positions;
        this.number = number;
        this.offset = positions - position;
    }
    
    public boolean isPositionValid(int i){
        i = i +number - offset;
        if(i%this.positions == 0){
            return true;
        }
        return false;
    }
    
    
}

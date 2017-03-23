/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent10;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tobia
 */
public class Output implements IContainer {

    private List<Chip> chips;
    private IContainer low, high;
    private int number;

    public Output(int number) {
        chips = new ArrayList<>();
        this.number = number;
    }

    @Override
    public void giveBrick(Chip c) {
        chips.add(c);
    }

    @Override
    public void setConnectionLow(IContainer container) {
        low = container;
    }

    @Override
    public void setConnectionHigh(IContainer container) {
        high = container;
    }

    @Override
    public int getBrickAmount() {
        return chips.size();
    }

    @Override
    public boolean passBricks() {
        return false;
    }

    @Override
    public List<Chip> getChips() {
        return chips;
    }

}

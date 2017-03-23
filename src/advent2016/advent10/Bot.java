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
public class Bot implements IContainer {

    private List<Chip> chips;
    private IContainer low, high;
    private int number;

    public Bot(int number) {
        chips = new ArrayList<>();
        this.number = number;
    }

    @Override
    public void giveBrick(Chip c) {
        if (chips.size() == 2) {
            throw new ArithmeticException("To many bricks");
        }
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
        if (chips.size() == 2 && low != null && high != null) {
            Chip c1 = chips.get(0);
            Chip c2 = chips.get(1);
            
            if((c1.getNumber() == 61 && c2.getNumber() == 17) || (c2.getNumber() == 61 && c1.getNumber() == 17)){
                System.out.println(this.number);
            }

            if (c1.getNumber() < c2.getNumber()) {
                low.giveBrick(c1);
                high.giveBrick(c2);
            } else {
                low.giveBrick(c2);
                high.giveBrick(c1);
            }
            chips.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<Chip> getChips() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

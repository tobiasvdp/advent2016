/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent2016.advent10;

import java.util.List;

/**
 *
 * @author tobia
 */
public interface IContainer {
    void giveBrick(Chip c);
    int getBrickAmount();
    void setConnectionLow(IContainer container);
    void setConnectionHigh(IContainer container);
    boolean passBricks();
    List<Chip> getChips();
}

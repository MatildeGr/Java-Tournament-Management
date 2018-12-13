/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import model.ListeTournois;

/**
 *
 * @author Matilde
 */
public class Ctrl {

    private final ListeTournois lstournois;

    public Ctrl(ListeTournois lstournois) {
        this.lstournois = lstournois;
    }

    public void lineSelection(int numLine) {
        if (numLine >= 0 && numLine < lstournois.nbLines()) {
            lstournois.select(numLine);
        } else {
            lstournois.unselect();
        }
    }
}

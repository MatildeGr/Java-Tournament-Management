package ctrl;

import model.ListeTournois;

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

    public void cb1Selection(int joueur) {
        if (joueur >= 0 && joueur < lstournois.joueurSize()) {
            lstournois.selectJoueurs(joueur);
        }
    }
    public void cb2Selection() {
        lstournois.selectJoueur2();
    }

}

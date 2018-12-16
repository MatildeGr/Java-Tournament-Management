package ctrl;

import model.Joueur;
import model.ListeTournois;
import model.Match;

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
    public void addMatch(Joueur j1,Joueur j2,Match.Resultats r){
        lstournois.addMatch(j1,j2,r);
    }

}

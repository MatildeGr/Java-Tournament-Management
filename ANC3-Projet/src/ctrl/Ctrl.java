package ctrl;

import java.util.List;
import model.Joueur;
import model.ListeTournois;
import model.Match;
import model.Match.Resultats;
import model.Tournoi;

public class Ctrl {

    private final ListeTournois lstournois;

    public Ctrl(ListeTournois lstournois) {
        this.lstournois = lstournois;
    }

    public void lineSelection(int numLine) {
        if (numLine >= 0 && numLine < lstournois.nbLines()) {
            lstournois.select(numLine);
        } else {
            lstournois.unselectTournoi();
        }
    }

    public int getNumLineSelected() {
        return lstournois.getNumLineSelected();
    }

    public void cb1Selection(int joueur) {
        if (joueur >= 0 && joueur < lstournois.joueurSize()) {
            lstournois.selectJoueurs(joueur);
        }
    }

    public void cb2Selection() {
        lstournois.selectJoueur2();
    }

    public void addMatch(Joueur j1, Joueur j2, Resultats r) {
        lstournois.addMatch(j1, j2, r);
    }

    public void updMatch(Joueur j1, Joueur j2, Resultats r) {
        lstournois.updMatch(j1, j2, r);
    }

    public void deleteMatch(Match m) {
        lstournois.deleteMatch(m);
    }

    public void selectMatch(int index) {
        if (index >= 0 && index < lstournois.matchSize()) {
            lstournois.selectMatch(index);
        } else {
            lstournois.unselectMatch();
        }
    }

    public Match getSelectMatch() {
        return lstournois.getSelectedMatch();
    }

    public List<Joueur> getAllInscrit(int numLineSelected) {
        return lstournois.getAllInscrit(numLineSelected);
    }

    public List<Match> getAllMatch(int numLineSelected) {
        return lstournois.getAllMatch(numLineSelected);
    }

    public List<Joueur> getAdversaire() {
        return lstournois.getAdversaire();
    }

    public List<Tournoi> getLines() {
        return lstournois.getLines();
    }

    public void unselectMatch() {
        lstournois.unselectMatch();
    }

}

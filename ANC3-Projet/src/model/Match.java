package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Match extends Observable {

    private int numMatchSelected = -1;
    private final List<Match> lsMatch = new ArrayList();

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }

    private Joueur joueur1;
    private Joueur joueur2;
    private Resultats res;

    public Match() {
    }

    public Match(Joueur j1, Joueur j2, Resultats r) {
        //if (valideMatch(j1, j2)) {
            this.joueur1 = j1;
            this.joueur2 = j2;
            this.res = r;
       // }
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public Resultats getResultats() {
        return res;
    }

    public List<Match> getList() {
        return this.lsMatch;
    }

    public int getSize() {
        return lsMatch.size();
    }

    public Match getMatch() {
        return new Match(joueur1, joueur2, res);
    }

    public void addMatch(Joueur j1, Joueur j2, Resultats r) {
        lsMatch.add(new Match(j1, j2, r));
    }
    public void addMatch(Match m) {
        lsMatch.add(new Match(m.joueur1,m.joueur2,m.res));
    }
    
    public void updMatch(Joueur j1, Joueur j2, Resultats r) {
        if (numMatchSelected >= 0 && numMatchSelected < lsMatch.size()) {
            lsMatch.set(numMatchSelected,new Match(j1, j2, r));
        }
    }

    private boolean valideMatch(Joueur j1, Joueur j2) {
        for (Match m : lsMatch) {
            if (j1.equals(m.joueur1) && j2.equals(m.joueur2)) {
                return false;
            }
        }
        return true;
    }

    public void selectMatch(int index) {
        numMatchSelected = index;
    }

    public void unselectMatch() {
        numMatchSelected = -1;
    }

    public Match getSelectedMatch() {
        if (numMatchSelected >= 0 && numMatchSelected < lsMatch.size()) {
            return lsMatch.get(numMatchSelected);
        }
        return null;
    }

    public void deleteMatch(Match m) {
        lsMatch.remove(m);
    }

}

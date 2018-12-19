package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import model.Match.Resultats;

public class Tournoi extends Observable {

    private int JOUEUR_SELECTED = -1;
    private int JOUEUR2_SELECTED = -1;
    private final List<Joueur> lsinscrits = new ArrayList<>();

    private int numMatchSelected = -1;
    private final List<Match> lsMatch = new ArrayList();

    private String name;

    /**
     *
     * TOURNAMENT FUNCTIONS
     *
     */
    public Tournoi(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Joueur> adversaire() {
        List<Joueur> res = copyLst();
        Joueur j = getSelectedMatch().getJoueur1();
        for (Match m : getAllMatch()) {
            if (j.equals(m.getJoueur1())) {
                res.remove(m.getJoueur2());
            }
            if (j.equals(m.getJoueur2())) {
                res.remove(m.getJoueur1());
            }
        }
        res.remove(j);
        return res;
    }

    public List<Joueur> adversaire2() {
        List<Joueur> res = copyLst();
        Joueur j = getSelectedMatch().getJoueur2();
        for (Match m : getAllMatch()) {
            if (j.equals(m.getJoueur1())) {
                res.remove(m.getJoueur2());
            }
            if (j.equals(m.getJoueur2())) {
                res.remove(m.getJoueur1());
            }
        }
        res.remove(j);
        return res;
    }

    public List<Joueur> advJoueurSelect() {
        List<Joueur> res = copyLst();

        Joueur j = JoueurSelected();
        for (Match m : getAllMatch()) {
            if (j.equals(m.getJoueur1())) {
                res.remove(m.getJoueur2());
            }
            if (j.equals(m.getJoueur2())) {
                res.remove(m.getJoueur1());
            }
        }
        res.remove(j);
        return res;
    }

    /**
     *
     * PLAYER LIST FUNCTIONS
     *
     */
    public void addJoueur(String nom) {
        lsinscrits.add(new Joueur(nom));
    }

    public void selectJoueur(int joueur) {
        JOUEUR_SELECTED = joueur;
    }

    public void selectJoueur2(int joueur) {
        JOUEUR2_SELECTED = joueur;
    }

    public void unselectJoueur() {
        JOUEUR_SELECTED = -1;
    }

    public Joueur getJoueur(int pos) {
        if (pos >= 0 && pos < lsinscrits.size()) {
            return lsinscrits.get(pos);
        }
        return null;
    }

    public int joueurSize() {
        return lsinscrits.size();
    }

    public List<Joueur> copyLst() {
        List<Joueur> res = new ArrayList();
        for (Joueur j : lsinscrits) {
            res.add(j.copy());
        }
        return res;
    }

    public Joueur JoueurSelected() {
        if (JOUEUR_SELECTED >= 0 && JOUEUR_SELECTED < lsinscrits.size()) {
            return lsinscrits.get(JOUEUR_SELECTED);
        }
        return null;
    }

    public Joueur Joueur2Selected() {
        if (JOUEUR2_SELECTED >= 0 && JOUEUR2_SELECTED < lsinscrits.size()) {
            return lsinscrits.get(JOUEUR2_SELECTED);
        }
        return null;
    }

    public List<Joueur> getAllInscrit() {
        return lsinscrits;
    }

    /**
     *
     * MATCH LIST FUNCTIONS
     *
     */
    public List<Match> getAllMatch() {
        return lsMatch;
    }

    public int matchSize() {
        return lsMatch.size();
    }

    public boolean addMatch(Joueur j1, Joueur j2, Resultats r) {
        if (validate(j1, j2)) {
            return lsMatch.add(new Match(j1, j2, r));
        }
        return false;
    }

    public boolean updMatch(Joueur j1, Joueur j2, Resultats r) {
        if (numMatchSelected >= 0 && numMatchSelected < lsMatch.size()) {
            lsMatch.set(numMatchSelected, new Match(j1, j2, r));
            return true;
        }
        return false;
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

    public boolean deleteMatch(Match m) {
        return lsMatch.remove(m);
    }

    public boolean validate(Joueur j1, Joueur j2) {
        Match tmp = new Match(j1, j2, null);
        for (Match m : lsMatch) {
            if (m.equals(tmp)) {
                return false;
            }
        }
        return true;
    }

}

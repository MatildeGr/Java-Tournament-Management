package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.scene.control.Button;

public class Match extends Observable {

    private final List<Match> lsMatch = new ArrayList();

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }

    private Joueur joueur1;
    private Joueur joueur2;
    private Resultats res;
    private Button button;

    //Constructeur de} Match. 
    public Match() {
    }

    //Constructeur de Match avec deux joueurs et un résultat. 
    public Match(Joueur j1, Joueur j2, Resultats r) {
        if (valideMatch(j1, j2)) {
            this.joueur1 = j1;
            this.joueur2 = j2;
            this.res = r;
            this.button = new Button("Supprimer");
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

    public void addMatch(Joueur j1, Joueur j2, Resultats r) {
        lsMatch.add(new Match(j1, j2, r));
    }

    public Button getButton() {
        return button;
    }

    public void deleteMatch(Match m) {
        lsMatch.remove(m);
    }

    public Match getMatch() {
        return new Match(joueur1, joueur2, res);
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

}

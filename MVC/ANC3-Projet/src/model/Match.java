package model;

import java.util.Objects;
import java.util.Observable;

public class Match extends Observable {

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }

    private Joueur joueur1;
    private Joueur joueur2;
    private Resultats res;

    public Match() {
    }

    public Match(Joueur j1, Joueur j2, Resultats r) {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.res = r;

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Match) {
            Match m = (Match) o;
            return ( (m.joueur1.equals(joueur1) && m.joueur2.equals(joueur2))
                    || (m.joueur1.equals(joueur2) && m.joueur2.equals(joueur1)) );
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.joueur1);
        hash = 83 * hash + Objects.hashCode(this.joueur2);
        return hash;
    }

}

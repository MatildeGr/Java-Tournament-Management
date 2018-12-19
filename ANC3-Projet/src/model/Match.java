package model;

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



}

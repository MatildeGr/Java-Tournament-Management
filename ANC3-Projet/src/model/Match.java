/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;

/**
 *
 * @author Matilde
 */
public class Match extends Observable {

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }
    private Inscrit joueur1;
    private Inscrit joueur2;
    private Resultats res;

    public Match(Inscrit s, Inscrit s1, Resultats r) {
        this.joueur1 = s;
        this.joueur2 = s1;
        this.res = r;
    }

}

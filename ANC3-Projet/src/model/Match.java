package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Match extends Observable {

    private final List<Match> lsMatch = new ArrayList();

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }

    public List<Match> getList() {
        return this.lsMatch;
    }

    private Tournoi tournois;
    private Joueur joueur1;
    private Joueur joueur2;
    private Resultats res;

    //Constructeur de Match. 
    public Match() {

    }

    //Constructeur de Match avec deux joueurs et un résultat. 
    public Match(Joueur s, Joueur s1, Resultats r) {
        this.joueur1 = s;
        this.joueur2 = s1;
        this.res = r;
    }

//    public List<Inscrit> listOfOpponents(Inscrit inscrit) {
//        List<Inscrit> res = inscrit.getList();
//        for (Match m : lsMatch) {
//            if (joueur1.equals(inscrit)) {
//                res.remove(joueur2);
//            } else if (joueur2.equals(inscrit)) {
//                res.remove(joueur1);
//            }
//        }
//        return res;
//    }
    //Renvoie la liste de tous les inscrits du tournois. 
    public List<Joueur> listAllInscritMatch() {
        return tournois.getAllInscrit();
    }

}

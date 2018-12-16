package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Match extends Observable {

    private final List<Match> lsMatch = new ArrayList();

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }
    
    
    private Joueur joueur1;
    private Joueur joueur2;
    private Resultats res;

    //Constructeur de} Match. 
    public Match() {
        initData();
    }

    //Constructeur de Match avec deux joueurs et un résultat. 
    public Match(Joueur j1, Joueur j2, Resultats r) {
      // if (valideMatch(j1, j2)) {
            this.joueur1 = j1;
            this.joueur2 = j2;
            this.res = r;
       // }
    }

    private boolean valideMatch(Joueur j1, Joueur j2) {
        for(Match m : lsMatch){
            if(j1.equals(m.joueur1) && j2.equals(m.joueur2)){
                return false;
            }
        }
        return true;
    }

    public void addMatch(Joueur j1, Joueur j2, Resultats r) {
            lsMatch.add(new Match(j1, j2,r));
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
    public int getSize(){
        return lsMatch.size();
    }

    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

    public void initData() {
        lsMatch.add(new Match(new Joueur("joueur1"), new Joueur("joueur2"), Resultats.GAIN_JOUEUR1));
        lsMatch.add(new Match(new Joueur("joueur3"), new Joueur("joueur4"), Resultats.GAIN_JOUEUR2));
        lsMatch.add(new Match(new Joueur("joueur5"), new Joueur("joueur6"), Resultats.MATCH_NULL));
        lsMatch.add(new Match(new Joueur("joueur1"), new Joueur("joueur2"), Resultats.GAIN_JOUEUR1));
        lsMatch.add(new Match(new Joueur("Remy"), new Joueur("Matilde"), Resultats.GAIN_JOUEUR1));
        lsMatch.add(new Match(new Joueur("Papy Denis"), new Joueur("Remy"), Resultats.GAIN_JOUEUR1));

    }
}

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
        return lsMatch.stream().anyMatch((m) -> ((m.joueur1.equals(j1) && m.joueur2.equals(j2)) || (m.joueur1.equals(j2) && m.joueur2.equals(j1))));
    }

    
    public boolean addMatch(Joueur j1, Joueur j2, Resultats r){
        if(valideMatch(j1, j2)){
            Match m = new Match(j1,j2,r);
            notif(TypeNotif.LINE_ADDED);
            return true;
        }

        return false;
    }
    
    public Joueur getJoueur1(){
        return joueur1;
    }
    
    public Joueur getJoueur2(){
        return joueur2;
    }
    
    public Resultats getResultats(){
        return res;
    }
    
     public List<Match> getList() {
        return this.lsMatch;
    }
     

    

      public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }
      public void initData(){
          lsMatch.add(new Match(new Joueur("joueur1"),new Joueur("joueur2"),Resultats.GAIN_JOUEUR1));
          lsMatch.add(new Match(new Joueur("joueur3"),new Joueur("joueur4"),Resultats.GAIN_JOUEUR1));
          lsMatch.add(new Match(new Joueur("joueur5"),new Joueur("joueur6"),Resultats.GAIN_JOUEUR1));
          
      }
}

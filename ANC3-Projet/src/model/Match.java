package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class Match extends Observable {

    public enum Resultats {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }
    private final List<Match> lsMatch = new ArrayList();
 
    
    private Inscrit joueur1;
    private Inscrit joueur2;
    private Resultats res;

    
    
    public Match(){

    }

    public Match(Inscrit s, Inscrit s1, Resultats r) {
        this.joueur1 = s;
        this.joueur2 = s1;
        this.res = r;
    }
    
    public List<Inscrit> listOfOpponents(Inscrit inscrit){
        List<Inscrit> res = inscrit.getList();
        for(Match m : lsMatch){
            if(joueur1.equals(inscrit)){
                res.remove(joueur2);
            }else if(joueur2.equals(inscrit)){
                res.remove(joueur1);
            }         
        }
       return res; 
    }

}

package model;

import java.util.List;
import java.util.Observable;
import model.Match.Resultats;

public class Tournoi extends Observable {

    private Joueur lsinscrits = new Joueur();
    private Match lsmatchs = new Match();
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
        List<Joueur> res = lsinscrits.copyLst();
        Joueur j = lsinscrits.JoueurSelected();
        for (Match m : lsmatchs.getList()) {
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
    public boolean addJoueur(String nom) {
        lsinscrits.addJoueur(nom);
        return true;
    }

    public Joueur getJoueur() {
        return lsinscrits;
    }

    public int joueurSize() {
        return lsinscrits.getSize();
    }

    public List<Joueur> getAllInscrit() {
        return this.lsinscrits.getList();
    }

    public void selectJoueur(int joueur) {
        lsinscrits.selectJoueur(joueur);
    }

    /**
     *
     * MATCH LIST FUNCTIONS
     *
     */
    public List<Match> getAllMatch() {
        return this.lsmatchs.getList();
    }

    public int matchSize() {
        return this.lsmatchs.getSize();
    }

    public boolean addMatch(Joueur j1, Joueur j2, Resultats r) {
        lsmatchs.addMatch(j1, j2, r);
        return true;
    }
    public void updMatch(Joueur j1, Joueur j2, Resultats r) {
        lsmatchs.updMatch(j1,j2,r);
    }

    public boolean deleteMatch(Match m) {
        lsmatchs.deleteMatch(m);
        return true;
    }
    public void selectMatch(int index){
        lsmatchs.selectMatch(index);
    }
    public Match getSelectedMatch(){
       return lsmatchs.getSelectedMatch();
    }
    public void unselectMatch(){
        lsmatchs.unselectMatch();
    }

}

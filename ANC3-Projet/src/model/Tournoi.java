package model;

import java.util.List;
import java.util.Observable;
import model.Match.Resultats;

public class Tournoi extends Observable {

    private static final int MAX_WORD_LENGTH = 10;
    private int tournois;
    private Joueur lsinscrits = new Joueur();
    private Match lsmatchs = new Match();
    private String name;

    //Constructeur de Tournoi. 
    public Tournoi(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    //Getteur qui renvoie le nom d'un tournoi.
    public String getName() {
        return this.name;
    }

    //Fonction qui renvoie la liste des joueurs inscrit au tournoi. 
    public List<Joueur> getAllInscrit() {
        return this.lsinscrits.getList();
    }

    public int joueurSize() {
        return lsinscrits.getSize();
    }

    //Fonction qui renvoie la liste des matchs joués au tournoi. 
    public List<Match> getAllMatch() {
        return this.lsmatchs.getList();
    }

    public int matchSize() {
        return this.lsmatchs.getSize();
    }

    public Joueur getJoueur() {
        return lsinscrits;
    }

    public void selectJoueur(int joueur) {
        lsinscrits.selectJoueur(joueur);
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

    public boolean addMatch(Joueur j1, Joueur j2, Resultats r) {
        lsmatchs.addMatch(j1, j2, r);
        return true;
    }

    public boolean addJoueur(String nom) {
        lsinscrits.addJoueur(nom);
        return true;
    }

    public boolean deleteMatch(Match m) {
        lsmatchs.deleteMatch(m);
        return true;
    }

}

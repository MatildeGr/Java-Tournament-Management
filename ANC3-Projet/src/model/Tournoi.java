package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

    //Getteur qui renvoie le nom d'un tournoi.
    public String getName() {
        return this.name;
    }

    //Fonction qui renvoie la liste des joueurs inscrit au tournoi. 
    public List<Joueur> getAllInscrit() {
        return this.lsinscrits.getList();
    }

    //Fonction qui renvoie la liste des matchs joués au tournoi. 
    public List<Match> getAllMatch() {
        return this.lsmatchs.getList();
    }

    //Fonction qui change le type de notif. 
    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

}

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

    public Tournoi(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Joueur> getAllInscrit() {
        return this.lsinscrits.getList();
    }

    public List<Match> getAllMatch() {
        return this.lsmatchs.getList();
    }

    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

}

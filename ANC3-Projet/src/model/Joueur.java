package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class Joueur extends Observable {

    private int JOUEUR_SELECTED = -1;
    private final List<Joueur> lsinscrits = new ArrayList<>();

    private String name;

    public Joueur() {
        initData();
    }

    public Joueur(String name) {
        this.name = name;
    }

    //Fonction qui renvoie le nom d'un joueur. 
    public String getName() {
        return this.name;
    }

    public Joueur getJoueur(int pos) {
        return lsinscrits.get(pos);
    }

    public void addJoueur(String nom) {
        lsinscrits.add(new Joueur(nom));
    }

    public void selectJoueur(int joueur) {
        JOUEUR_SELECTED = joueur;
    }

    public int getSize() {
        return lsinscrits.size();
    }

    //Fonction qui renvoie la liste des joueurs. 
    public List<Joueur> getList() {
        return this.lsinscrits;
    }

    //retourne joueur selected si hors borne return null
    public Joueur JoueurSelected() {
        if(JOUEUR_SELECTED >=0 && JOUEUR_SELECTED < lsinscrits.size()){
            return lsinscrits.get(JOUEUR_SELECTED);
        }
        return null;
    }

    public List<Joueur> listAdversaire(int joueur_selected) {
        return lsinscrits;
    }

    public Joueur copy() {
        return new Joueur(name);
    }

    public List<Joueur> copyLst() {
        List<Joueur> res = new ArrayList();
        for (Joueur j : lsinscrits) {
            res.add(j.copy());
        }
        return res;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Joueur) {
            Joueur i = (Joueur) o;
            return (i.name.equals(this.name));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    
    //Fonction qui initialiste les données des joueurs. 

    private void initData() {
        lsinscrits.add(new Joueur("Matilde"));
        lsinscrits.add(new Joueur("Remy"));
        lsinscrits.add(new Joueur("Papy Denis"));
        lsinscrits.add(new Joueur("Jean"));
        lsinscrits.add(new Joueur("Heude"));
    }

}

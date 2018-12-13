package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class Joueur extends Observable {

    private static final int MAX_WORD_LENGTH = 10;
    private final List<Joueur> lsinscrits = new ArrayList<>();

    private String name;

    public Joueur() {
        initData();
    }

    //Fonction qui renvoie une liste de string de la liste des joueurs inscrits. 
    public static List<String> getListToString(List<Joueur> lsinscrits) {
        List<String> res = new ArrayList<>();
        lsinscrits.forEach((j) -> {
            res.add(j.name);
        });
        return res;
    }

    //Fonction qui renvoie la liste des joueurs. 
    public List<Joueur> getList() {
        return this.lsinscrits;
    }
    
    //Fonction qui initialiste les données des joueurs. 
    private void initData() {
        lsinscrits.add(new Joueur("Matilde"));
        lsinscrits.add(new Joueur("Remy"));
        lsinscrits.add(new Joueur("Papy Denis"));
        lsinscrits.add(new Joueur("Jean"));
        lsinscrits.add(new Joueur("Heude"));
    }

    //Constructeur d'un joueur grâce à un nom. 
    public Joueur(String name) {
        this.name = name;
    }

    //Fonction qui renvoie le nom d'un joueur. 
    public String getName() {
        return this.name;
    }

    //Fonction qui change le type de notif. 
    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

    //Fonction de comparaison entre un Joueur et un object o. 
    @Override
    public boolean equals(Object o) {
        if (o instanceof Joueur) {
            Joueur i = (Joueur) o;
            return (i.name.equals(this.name));
        }
        return false;
    }

    //Fonction qui renvoie le hashCode du nom d'un joueur.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

}

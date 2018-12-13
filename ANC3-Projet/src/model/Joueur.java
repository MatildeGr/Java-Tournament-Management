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

    public static List<String> getListToString(List<Joueur> lsinscrits) {
        List<String> res = new ArrayList<>();
        lsinscrits.forEach((j) -> {
            res.add(j.name);
        });
        return res;
    }

    public List<Joueur> getList() {
        return this.lsinscrits;
    }

    private void initData() {
        lsinscrits.add(new Joueur("Matilde"));
        lsinscrits.add(new Joueur("Remy"));
        lsinscrits.add(new Joueur("Papy Denis"));
        lsinscrits.add(new Joueur("Jean"));
        lsinscrits.add(new Joueur("Heude"));
    }

    public Joueur(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
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

}

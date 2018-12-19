package model;

import java.util.Objects;
import java.util.Observable;

public class Joueur extends Observable {

    private final String name;


    public Joueur(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Joueur copy() {
        return new Joueur(name);
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


}

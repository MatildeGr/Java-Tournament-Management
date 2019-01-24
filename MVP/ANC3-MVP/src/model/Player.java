package model;

import java.util.Objects;

/**
 *
 * @author remy
 */
public class Player {
    
    
     private final String name;

     public Player(String name){
         this.name = name;
     }

     //retourne le nom du joueur
    public String getName() {
        return this.name;
    }

    //retourne une copy d'un joueur
    public Player copy() {
        return new Player(name);
    }

    //representation textuel d'un joueur
    @Override
    public String toString() {
        return name;
    }

    //redifinition de la méthode de comparaison 
    //revoie true si deux personnes sont les mêmes (noms identiques)
    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player p = (Player) o;
            return (p.name.equals(this.name));
        }
        return false;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    
}

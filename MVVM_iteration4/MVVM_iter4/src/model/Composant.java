/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author remy
 */
public abstract class Composant {

    private final String name;
    
    public Composant(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Composant){
            Composant c  = (Composant)o;
            if(name.equals(c.name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }
    

}

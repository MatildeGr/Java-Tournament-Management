/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;

/**
 *
 * @author Matilde
 */
public class Inscrit extends Observable {

    private String nom;

    public Inscrit(String nom) {
        this.nom = nom;
    }
}

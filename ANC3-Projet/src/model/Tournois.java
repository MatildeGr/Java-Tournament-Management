/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Matilde
 */
public class Tournois extends Observable {

    private String nom;
    private List<Match> listematch = new ArrayList<>();
    private List<Inscrit> listeinscrit = new ArrayList<>();

    public Tournois(String n) {
        this.nom = n;
    }

    public Tournois(String n, List<Match> listem, List<Inscrit> listei) {
        this.nom = n;
        this.listematch = listem;
        this.listeinscrit = listei;
    }
}

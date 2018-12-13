/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import static model.Joueur.getListToString;

/**
 *
 * @author 1501magravano
 */
public class ListeTournois extends Observable {

    private final List<Tournoi> lsTournois = new ArrayList();
    private int numLineSelected = -1;

    public ListeTournois() {
        initData();
    }

    public List<String> getLines() {
        List<String> res = new ArrayList<>();
        lsTournois.forEach((t) -> {
            res.add(t.getName());
        });
        return res;
    }

    public List<Joueur> getAllInscrit(int numLineSelected) {
        Tournoi t = lsTournois.get(numLineSelected);
        return t.getAllInscrit();
    }

    public List<String> getAllJoueurToString(int numLineSelected) {
        return getListToString(getAllInscrit(numLineSelected));
    }

    public List<Tournoi> getTournament() {
        return this.lsTournois;
    }

    public int getNumLineSelected() {
        return this.numLineSelected;
    }

    public int nbLines() {
        return lsTournois.size();
    }

    public void select(int index) {
        numLineSelected = index;
        notif(TypeNotif.LINE_TOURNOI_SELECTED);
    }

    public void unselect() {
        numLineSelected = -1;
        notif(TypeNotif.LINE_UNSELECTED);
    }

    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

    private void initData() {
        lsTournois.add(new Tournoi("Tournoi 1"));
        lsTournois.add(new Tournoi("Tournoi 2"));
        lsTournois.add(new Tournoi("Tournoi 3"));
    }
}

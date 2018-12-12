package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Tournois extends Observable {

    
    private static final int MAX_WORD_LENGTH = 10;
    private final List<Tournois> lsTournoi = new ArrayList();
    private Inscrit inscrits = new Inscrit();
    private int numLineSelected = -1;

    private String name;

    public Tournois() {
        initData();
    }

    public Tournois(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Tournois> getTournament() {
        return this.lsTournoi;
    }

    public List<Inscrit> getAllInscrit() {
        return this.inscrits.getList();
    }

    public int getNumLineSelected() {
        return this.numLineSelected;
    }

    private boolean unicity(Tournois tournoi) {
        return lsTournoi.stream().noneMatch((t) -> (t.getName().equals(tournoi.getName())));
    }

    public boolean addLine(Tournois tournoi) {
        if (tournoi.getName().length() <= MAX_WORD_LENGTH && unicity(tournoi)) {
            lsTournoi.add(tournoi);
            numLineSelected = lsTournoi.size() - 1;
            notif(TypeNotif.LINE_ADDED);
            return true;
        }
        return false;
    }

    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

    private void initData() {
        addLine(new Tournois("Tournoi 1"));
        addLine(new Tournois("Tournoi 2"));
        addLine(new Tournois("Tournoi 3"));
    }

}

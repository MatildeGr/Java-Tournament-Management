package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author remy
 */
public class Tournament {
    
    private final ObservableList<Player> lsPlayers = FXCollections.observableArrayList();
    private final ObservableList<Match> lsMatch = FXCollections.observableArrayList();

    private final String name;

    public Tournament(String name) {
        this.name = name;
    }

    //retourne le nom d'un tournoi
    public String getName() {
        return this.name;
    }

    //ajout d'un joueur
    public boolean addPlayer(String name) {
        return lsPlayers.add(new Player(name));
    }

    //representation textuel d'un tournoi
    @Override
    public String toString() {
        return name;
    }

    //retourne le nombre de joueurs
    public int nbPlayers() {
        return lsPlayers.size();
    }

    //retourne tous les joueurs
    public ObservableList<Player> getPlayers() {
        return lsPlayers;
    }

    //renovoie une copie de la liste de joueurs
    private ObservableList<Player> copyLst() {
        ObservableList<Player> res = FXCollections.observableArrayList();
        for (Player j : lsPlayers) {
            res.add(j.copy());
        }
        return res;
    }

    //retourne une liste d'adversaires d'un joueur
    public ObservableList<Player> getOpponents(Player p) {
        ObservableList<Player> res = copyLst();
        for (Match m : getMatchs()) {
            if (p.equals(m.getPlayer1())) {
                res.remove(m.getPlayer2());
            }
            if (p.equals(m.getPlayer2())) {
                res.remove(m.getPlayer1());
            }
        }
        res.remove(p);
        return res;
    }

    //retourne le nombre de matchs 
    public int nbMatchs() {
        return lsMatch.size();
    }

    //retourne tous les matchs
    public ObservableList<Match> getMatchs() {
        return lsMatch;
    }

    //ajoute un match
    public boolean addMatch(Match m) {
        if (!existMatch(m)) {
            lsMatch.add(m);
            return true;
        }
        return false;
    }

    //moddifie un match
    public boolean updateMatch(int numLineMatch, Match m) {
        if ( !m.getPlayer1().equals(m.getPlayer2())) {
            lsMatch.set(numLineMatch, m);
            return true;
        }
        return false;
    }

    //supprime une match
    //revoie true si match supprimé
    public boolean deleteMatch(Match m) {
        return lsMatch.remove(m);
    }

    //renvoie true si un match exist
    private boolean existMatch(Match m) {
        return lsMatch.contains(m);
    }
}

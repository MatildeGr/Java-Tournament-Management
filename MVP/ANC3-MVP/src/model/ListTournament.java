package model;

import java.util.ArrayList;
import java.util.List;
import model.Match.Result;

/**
 *
 * @author remy
 */
public class ListTournament {

    private final List<Tournament> lsTournaments = new ArrayList();

    public ListTournament() {
        initData();
    }

    //retourne le nombre de tournois
    public int nbTournament() {
        return lsTournaments.size();
    }

    //retourne un tournoisa une position donnée
    public Tournament getTournament(int index) {
        return lsTournaments.get(index);
    }

    //retourne tous les tournois
    public List<Tournament> getTournaments() {
        return lsTournaments;
    }

    //retourne le nombre de joueurs d'un tournoi
    public int nbPlayers(int numTournament) {
        return lsTournaments.get(numTournament).nbPlayers();
    }

    //retourne tous les inscrits d'un tournoi
    public List<Player> getPlayers(int numTournament) {
        return lsTournaments.get(numTournament).getPlayers();
    }

    //retourne tous les adversaires d'un joueur
    public List<Player> getOpponents(int numTournament, Player p) {
        return lsTournaments.get(numTournament).getOpponents(p);
    }

    //retourne le nombre de matchs d'un tournoi
    public int nbMatchs(int numTournament) {
        return lsTournaments.get(numTournament).nbMatchs();
    }

    //retourne les matchs d'un tournoi
    public List<Match> getMatchs(int numTournament) {
        return lsTournaments.get(numTournament).getMatchs();
    }

    //ajoute un match dans un tournoi
    public boolean addMatch(int numTournament, Match m) {
        return lsTournaments.get(numTournament).addMatch(m);
    }

    //modifie un match dans un tournoi 
    public boolean updateMatch(int numTournament, int numLineMatch, Match m) {
        return lsTournaments.get(numTournament).updateMatch(numLineMatch, m);
    }

    //supprime un match dans un tournoi
    public boolean deleteMatch(int numTournament, Match m) {
        return lsTournaments.get(numTournament).deleteMatch(m);
    }

    private void initData() {
        String nom[] = {"Gabriel", "Louise", "Jules", "Emma", "Lucas", "Jade", "Louis", "Chloé", "Adam",
            "Manon", "Hugo", "Alice", "Léo", "Lina", "Raphaël", "Léa"};

        lsTournaments.add(new Tournament("Tournament 1"));

        for (int i = 0; i < 10; i++) {
            lsTournaments.get(0).addPlayer(nom[i]);
        }
        for (int i = 0; i < 10; i += 2) {
            Match m = new Match(new Player(nom[i]), new Player(nom[i + 1]), Result.MATCH_NULL);
            lsTournaments.get(0).addMatch(m);
        }

        lsTournaments.add(new Tournament("Tournament 2"));

        for (int i = 8; i < 16; i++) {
            lsTournaments.get(1).addPlayer(nom[i]);
        }
        lsTournaments.add(new Tournament("Tournament 3"));

    }

}

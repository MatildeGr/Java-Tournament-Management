package model;

import java.util.Objects;

/**
 *
 * @author remy
 */
public class Match {
     public enum Result {
        GAIN_JOUEUR1, GAIN_JOUEUR2, MATCH_NULL;
    }

    private final Player player1;
    private final Player player2;
    private Result res;

 

    public Match(Player p1, Player p2, Result r) {
        this.player1 = p1;
        this.player2 = p2;
        this.res = r;

    }

    //retourne joueur 1 d'un match
    public Player getPlayer1() {
        return player1;
    }

    //retourne joueur 2 d'un match
    public Player getPlayer2() {
        return player2;
    }

    //retourne resultat d'un match
    public Result getResult() {
        return res;
    }

    //redifinition de la méthode de comparaison 
    //revoie true si deux matchs sont les mêmes sur base des joueurs
    @Override
    public boolean equals(Object o) {
        if (o instanceof Match) {
            Match m = (Match) o;
            return ( (m.player1.equals(player1) && m.player2.equals(player2))
                    || (m.player1.equals(player2) && m.player2.equals(player1)) );
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.player1);
        hash = 47 * hash + Objects.hashCode(this.player2);
        return hash;
    }
    
}

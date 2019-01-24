package presenter;

import java.util.EnumSet;
import model.ListTournament;
import model.Match;
import model.Player;
import view.View;

/**
 *
 * @author remy
 */
public class Presenter {

    private int numTournament = -1;
    private int numMatch = -1;
    private View view;
    private final ListTournament lsTournament;

    public Presenter(ListTournament lsTournament) {
        this.lsTournament = lsTournament;
    }

    //initialise la vue avec les tournois
    public void setView(View view) {
        this.view = view;
        this.view.loadTournaments(lsTournament.getTournaments());
        this.view.disableButton(true);
    }

    //selection et deselection d'un tournoi
    public void lineSelectionTournament(int index) {
        if (index >= 0 && index < lsTournament.nbTournament()) {
            numTournament = index;
            this.view.loadMatchs(lsTournament.getMatchs(numTournament));
            this.view.loadPlayers(lsTournament.getPlayers(numTournament));
            this.view.loadComboboxPlayer1(lsTournament.getPlayers(numTournament));
            this.view.loadComboboxResult(EnumSet.allOf(Match.Result.class));
            this.view.editButton(true);
        } else {
            this.view.unselectTournament();
            numTournament = -1;
            numMatch = -1;
            this.view.disableButton(true);
        }
    }

    //selection et deselection d'un match
    public void lineSelectionMatch(int index) {
        if (numTournament >= 0 && numTournament < lsTournament.nbTournament()) {
            if (index >= 0 && index < lsTournament.nbMatchs(numTournament)) {
                numMatch = index;
                this.view.loadMatchSelected();
                this.view.editButton(false);
            } else {
                this.view.unselectMatch();
                numMatch = -1;
                this.view.editButton(true);
            }
        }
    }

    //ajout des adversaires dans le combobox joueur2
    public void setOpponentsPlayer1(Player p) {
        if (p != null && numTournament >= 0 && numTournament < lsTournament.nbTournament()) {
            this.view.loadComboboxPlayer2(lsTournament.getOpponents(numTournament, p));
        }
    }

    //ajout des adversaires dans le combobox joueur1
    public void setOpponentsPlayer2(Player p) {
        if (p != null && isValidTournamentSelected()) {
            this.view.loadComboboxPlayer1(lsTournament.getOpponents(numTournament, p));
        }
    }

    //ajout d'un match dans un tournoi
    public void addMatch(Match m) {
        if (isValidTournamentSelected()) {
            lsTournament.addMatch(numTournament, m);
            this.view.loadMatchs(lsTournament.getMatchs(numTournament));
        }
    }

    //suppresion d'un match
    public void deleteMatch(Match m) {
        if (isValidTournamentSelected()) {
            lsTournament.deleteMatch(numTournament, m);
            this.view.loadMatchs(lsTournament.getMatchs(numTournament));
        }
    }

    //mise à jour d'un match
    public void updatematch(Match m) {
        if (isValidTournamentSelected()) {
            if (isValidMatchSelected()) {
                lsTournament.updateMatch(numTournament, numMatch, m);
                this.view.loadMatchs(lsTournament.getMatchs(numTournament));
                this.view.unselectMatch();
            }
        }
    }

    //valide la ligne selectionnée d'un tournoi
    private boolean isValidTournamentSelected() {
        return numTournament >= 0 && numTournament < lsTournament.nbTournament();
    }

    //valide la ligne selectionnée d'un match
    private boolean isValidMatchSelected() {
        return numMatch >= 0 && numMatch < lsTournament.nbMatchs(numTournament);
    }
}

package view;

import java.util.EnumSet;
import java.util.List;
import model.Match;
import model.Match.Result;
import model.Player;
import model.Tournament;

/**
 *
 * @author remy
 */
public interface View {
    
    void loadTournaments(List<Tournament> lsTournament);
    
    void loadMatchs(List<Match> lsMatchs);
    
    void loadPlayers(List<Player> lsPlayers);
    
    void loadComboboxPlayer1(List<Player> lsPlayers);
    
    void loadComboboxPlayer2(List<Player> lsPlayers);
    
    void loadComboboxResult(EnumSet<Result> result);
    
    void loadMatchSelected();
    
    void unselectTournament();
    
    void unselectMatch();

    void selectTournament(int index);

    void selectmatch(int index);
    
    void editButton(boolean on);
    
    void disableButton(boolean on);
}

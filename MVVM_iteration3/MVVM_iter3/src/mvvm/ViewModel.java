package mvvm;

import java.util.EnumSet;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import mediator.Mediator;
import model.ListTournament;
import model.Match;
import model.Match.Result;
import model.Player;
import model.Tournament;

/**
 *
 * @author remy
 */
public class ViewModel {

    private static enum StateCombo {
        LOCKED, UNLOCKED;
    }
    private final ListTournament lsTournament;

    private final IntegerProperty numTournamentSelected = new SimpleIntegerProperty(-1);
    private final IntegerProperty numMatchSelected = new SimpleIntegerProperty(-1);
    private final ListProperty<Player> lvPlayer = new SimpleListProperty();
    private final ListProperty<Match> lvMatch = new SimpleListProperty();
    private final ListProperty<Player> combobox1 = new SimpleListProperty();
    private final ListProperty<Player> combobox2 = new SimpleListProperty();
    private final ListProperty<Result> comboboxRes = new SimpleListProperty();
    private final ObjectProperty<Player> p1 = new SimpleObjectProperty();
    private final ObjectProperty<Player> p2 = new SimpleObjectProperty();
    private final ObjectProperty<Player> p1Selected = new SimpleObjectProperty();
    private final ObjectProperty<Player> p2Selected = new SimpleObjectProperty();
    private final ObjectProperty<Result> res = new SimpleObjectProperty();
    private final BooleanProperty tournamentSelected = new SimpleBooleanProperty(false);
    private final BooleanProperty matchSelected = new SimpleBooleanProperty();
    private StateCombo state = StateCombo.UNLOCKED;

    public ViewModel(ListTournament lsTournament) {
        this.lsTournament = lsTournament;
        configApplicativeLogicTournament();
        configApplicativeLogigMatch();
        configListenerPlayer1Selected();
        configListenerPlayer2Selected();
        tournamentSelected.bind(numTournamentSelected.isEqualTo(-1));
        matchSelected.bind(numMatchSelected.isEqualTo(-1));

    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //            APPLICATION LOGIC            //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //gestion de la selection et deselection d'un tournoi
    private void configApplicativeLogicTournament() {
        numTournamentSelected.addListener((o, old, newValue) -> {
            int index = newValue.intValue();
            if (index >= 0) {
                setLvMatchPlayer(index);
                addListPlayerCombobox(index);

            } else {
                clearLvPlayerMatch();
                clearCbList();

            }
        });

    }

    //gestion de la selection et deselection d'un match
    private void configApplicativeLogigMatch() {
        numMatchSelected.addListener((o, old, newValue) -> {
            int index = newValue.intValue();
            if (validatePosMatch()) {
                setCbValues(index);

            } else {
                clearCbValues();
                if(validatePosTournament()){
                    addListPlayerCombobox(numTournamentSelected.get());
                }
                resetPlayersSelected();
            }
        });
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //                 LISTENNER               //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    private void configListenerPlayer1Selected() {
        p1Selected.addListener((o, old, newValue) -> {
            if (newValue != null) {
                switch (state) {
                    case UNLOCKED:
                        state = StateCombo.LOCKED;
                        Player p = p2Selected.get();
                        combobox2.set(lsTournament.getOpponents(numTournamentSelected.get(), newValue));
                        p2.set(p);
                        state = StateCombo.UNLOCKED;
                        break;
                }

            }
        });
    }

    private void configListenerPlayer2Selected() {
        p2Selected.addListener((o, old, newValue) -> {
            if (newValue != null) {
                switch (state) {
                    case UNLOCKED:
                        Player p = p1Selected.get();
                        state = StateCombo.LOCKED;
                        combobox1.set(lsTournament.getOpponents(numTournamentSelected.get(), newValue));
                        p1.set(p);
                        state = StateCombo.UNLOCKED;
                        break;
                }
            }
        });
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //                  GETTER                 //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //retourne la liste des tournois
    public SimpleListProperty<Tournament> linesProperty() {
        return new SimpleListProperty<>(lsTournament.getTournaments());
    }

    //retourne la liste des matchs d'un tournoi
    public SimpleListProperty<Match> matchProperty() {
        return new SimpleListProperty<>(lvMatch);
    }

    //retourne la liste des joueurs d'un tournoi
    public SimpleListProperty<Player> playerProperty() {
        return new SimpleListProperty<>(lvPlayer);
    }

    //retourne la liste des joueurs dans le combobox joueur 1
    public SimpleListProperty<Player> combobox1Property() {
        return new SimpleListProperty<>(combobox1);
    }

    //retourne la liste des joueurs dans le combobox joueur 2
    public SimpleListProperty<Player> combobox2Property() {
        return new SimpleListProperty<>(combobox2);
    }

    //retourne la liste des enums pour les resultats
    public SimpleListProperty<Result> comboboxResProperty() {
        return new SimpleListProperty<>(comboboxRes);
    }

    //retourne le numéro du tournoi selectionné
    public IntegerProperty numTournamentSelectedProperty() {
        return numTournamentSelected;
    }

    //retourne le joueur 1 d'un match selectionné
    public ObjectProperty<Player> Player1Selected() {
        return p1;
    }

    //retourne le joueur 2 d'un match selectionné
    public ObjectProperty<Player> Player2Selected() {
        return p2;
    }

    //retourne le resultat d'un match selectionné
    public ObjectProperty<Result> resultSelected() {
        return res;
    }

    //retourne le joueur 1 d'un match selectionné
    public ObjectProperty<Player> Player1CbValue() {
        return p1Selected;
    }

    //retourne le joueur 2 d'un match selectionné
    public ObjectProperty<Player> Player2CbValue() {
        return p2Selected;
    }

    //retourne vrai ou faux si un tournoi est selectionné
    public BooleanProperty tournamentSelectedProperty() {
        return tournamentSelected;
    }

    //retourne vrai ou faux si un tournoi est selectionné
    public BooleanProperty matchSelectedProperty() {
        return matchSelected;
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //                 BINDING                 //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //binding avec le numéro de tournoi selectionné
    public void bindNumTournamentSlectedPropTo(ReadOnlyIntegerProperty prop) {
        numTournamentSelected.bind(prop);
    }

    //binding avec le numéro de match selectionné
    public void bindNumMatchSlectedPropTo(ReadOnlyIntegerProperty prop) {
        numMatchSelected.bind(prop);
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //           APPLICATION METHOD            //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //suppresion d'un match
    public void deleteMatch(Match m) {
        if (validatePosTournament() && validatePosMatch()) {
            lsTournament.deleteMatch(numTournamentSelected.get(), m);
        }
    }

    //mise à jour d'un match
    public void updatematch(Match m) {
        if (validatePosTournament() && validatePosMatch()) {
            lsTournament.updateMatch(numTournamentSelected.get(), numMatchSelected.get(), m);
        }
    }

    //ajout d'un match dans un tournoi
    public void addMatch(Match m) {
        if (validatePosTournament()) {
            lsTournament.addMatch(numTournamentSelected.get(), m);
            addListPlayerCombobox(numTournamentSelected.get());
            clearCbValues();
            resetPlayersSelected();
        }
    }
    //
    //
    /////////////////////////////////////////////
    //                                         //
    //       USEFUL METHOD FOR VIEWMODEL       //
    //                                         //
    /////////////////////////////////////////////
    //
    //

    //validation d'une position d'un tournoi
    //renvoie true si la position est comprise entre 0 et lsTournament -1
    private boolean validatePosTournament() {
        return numTournamentSelected.get() >= 0 && numTournamentSelected.get() < lsTournament.nbTournament();
    }

    //validation d'une position d'un match
    //revoie true si la position est comprise entre 0 et lvMatch -1
    private boolean validatePosMatch() {
        return numMatchSelected.get() >= 0 && numMatchSelected.get() < lsTournament.nbMatchs(numTournamentSelected.get());
    }

    //ajoute les valeurs d'un match selectionné dans les combobox
    private void setCbValues(int index) {
        p1.set(lvMatch.get(index).getPlayer1());
        p2.set(lvMatch.get(index).getPlayer2());
        res.set(lvMatch.get(index).getResult());
    }

    //vide les valeurs des combobox lors de la deselection
    private void clearCbValues() {
        p1.set(null);
        p2.set(null);
        res.set(null);
    }

    private void cb1SetValue(Player p) {
        p1.set(p);
    }

    //vide les listes des combobox
    private void clearCbList() {
        combobox1.set(null);
        combobox2.set(null);
        comboboxRes.set(null);
    }

    //ajout les matchs dans la liste des matchs
    private void setLvMatch(int index) {
        lvMatch.set(lsTournament.getMatchs(index));
    }

    //ajout les joueurs dans la liste des joueurs
    private void setLvPlayer(int index) {
        lvPlayer.set(lsTournament.getPlayers(index));
    }

    //ajout les matchs et les joueur dans leur liste
    private void setLvMatchPlayer(int index) {
        setLvMatch(index);
        setLvPlayer(index);
    }

    //supprime les matchs lors de la deselection d'un tournoi
    private void clearLvMatch() {
        lvMatch.set(null);
    }

    //supprime les joueurs lors de la deselection d'un tournoi
    private void clearLvPlayer() {
        lvPlayer.set(null);
    }

    //supprime les matchs et les joueurs lors de la deselection d'un tournoi
    private void clearLvPlayerMatch() {
        clearLvMatch();
        clearLvPlayer();
    }

    //ajoute tous les joueurs d'un tournois dans les combobox
    private void addListPlayerCombobox(int index) {
        combobox1.set(lsTournament.getPlayers(index));
        combobox2.set(lsTournament.getPlayers(index));
        comboboxRes.set(FXCollections.observableArrayList(EnumSet.allOf(Match.Result.class)));
    }

    //met les joueurs selectionnés a null
    private void resetPlayersSelected() {
        p1Selected.set(null);
        p2Selected.set(null);
    }
    
    public void constructionPartie(){
        Mediator.getInstance().constructionPartie();
    }
}

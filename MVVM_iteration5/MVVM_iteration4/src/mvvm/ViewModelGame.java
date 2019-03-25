package mvvm;

import java.util.Optional;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Game;
import model.Match.Result;
import model.Player;

/**
 *
 * @author remy
 */
public class ViewModelGame {

    private final Game game;
    private final IntegerProperty numQuest = new SimpleIntegerProperty(0);
    private final StringProperty quest = new SimpleStringProperty();
    private final IntegerProperty pointQuest = new SimpleIntegerProperty();
    private final StringProperty reponse1 = new SimpleStringProperty();
    private final StringProperty reponse2 = new SimpleStringProperty();
    private final StringProperty reponse3 = new SimpleStringProperty();
    private final StringProperty reponse4 = new SimpleStringProperty();
    private final IntegerProperty pointsgagnes = new SimpleIntegerProperty();
    private final IntegerProperty numRepSelected = new SimpleIntegerProperty();
    private final BooleanProperty close = new SimpleBooleanProperty(false);
    private final BooleanProperty cancel = new SimpleBooleanProperty(false);
    private final BooleanProperty setVisibleHint = new SimpleBooleanProperty();
    private final StringProperty hintText = new SimpleStringProperty();

    public ViewModelGame(Game game) {
        this.game = game;
        setQuestion(numQuest.get());
        setHint(numQuest.get());
        configApplicativeLogic();
    }

    //listener sur le numero de question
    private void configApplicativeLogic() {
        numQuest.addListener((o, old, newValue) -> {
            if (stateForEndGame()) {
                endGame();
            } else {
                setQuestion(newValue.intValue());
                setHint(newValue.intValue());
            }
        }
        );
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
    //retourne le numero de question
    public IntegerProperty numQuestProperty() {
        return numQuest;
    }

    //retourne le nom de la question 
    public StringProperty questProperty() {
        return quest;
    }

    //retourne les points d'une question
    public IntegerProperty pointQuestProperty() {
        return pointQuest;
    }

    //retourne la reponse 1
    public StringProperty reponse1Property() {
        return reponse1;
    }

    //retourne la reponse2
    public StringProperty reponse2Property() {
        return reponse2;
    }

    //retourne la reponse 3
    public StringProperty reponse3Property() {
        return reponse3;
    }

    //retourne la reponse 4
    public StringProperty reponse4Property() {
        return reponse4;
    }

    //retourne les points gagnés
    public IntegerProperty pointsGagnesProperty() {
        return pointsgagnes;
    }

    //retourne le numero de la reponse selectionnée
    public IntegerProperty numRepSelectedProperty() {
        return numRepSelected;
    }

    //retourne si la scene doit etre fermée
    public BooleanProperty closeProperty() {
        return close;
    }

    //retourne si une partie est annulée
    public BooleanProperty cancelProperty() {
        return cancel;
    }

    //retourne si un joker peut être visible
    public BooleanProperty setVisibleHintProperty() {
        return setVisibleHint;
    }
    
    public StringProperty hintTextProperty(){
        return hintText;
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
    //retourne le joueur1
    public Player getPlayer1() {
        return game.getPlayer1();
    }

    //retourne le joueur 2
    public Player getPlayer2() {
        return game.getPlayer2();
    }

    //retourne le nombre de question 
    public int getNbQuest() {
        return game.getNbQuest();
    }

    //retourne le nombre de points du max du questionnaire
    public int getMaxPoint() {
        return game.getMaxPoint();
    }

    //set une question 
    public void setQuestion(int index) {
        quest.set(game.getQuestion(index).getNom());
        pointQuest.set(game.getQuestion(index).getPoints());
        reponse1.set(game.getQuestion(index).getReponseToString(0));
        reponse2.set(game.getQuestion(index).getReponseToString(1));
        reponse3.set(game.getQuestion(index).getReponseToString(2));
        reponse4.set(game.getQuestion(index).getReponseToString(3));

    }

    //confirme une reponse, ajoute les points si reponse juste 
    //ferme la page si confirm et fin de qestionnaire
    public void confirm() {
        if (numRepSelected.get() == game.getQuestion(numQuest.get()).getNumRep()) {
            pointsgagnes.set(pointsgagnes.get() + pointQuest.get());
            nextQuestionOrEndGame();
        } else {
            nextQuestionOrEndGame();
        }
    }

    //increment le numero de question ou termine le jeux
    private void nextQuestionOrEndGame() {
        if (numQuest.get() < getNbQuest() - 1) {
            numQuest.set(numQuest.get() + 1);
        } else {
            endGame();
        }
    }

    //alert de fin de jeux, ajoute le resultat dans la liste des matchs
    private void endGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText("Fin du match entre " + getPlayer1() + " et " + getPlayer2());
        if (pointsgagnes.get() > ((double) getMaxPoint()) / 2) {
            game.result(Result.GAIN_JOUEUR2);
            alert.setContentText("Vous avez gagné");
        } else if (pointsgagnes.get() < ((double) getMaxPoint()) / 2) {
            game.result(Result.GAIN_JOUEUR1);
            alert.setContentText(getPlayer1() + " à gagné");
        } else {
            game.result(Result.MATCH_NULL);
            alert.setContentText("Vous avez fait match null");
        }
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK || ButtonType.CANCEL == action.get()) {
            close.set(true);
        }
    }

    private boolean stateForEndGame() {
        return (pointsgagnes.get() + game.leftPoint(numQuest.get()) < getMaxPoint() / 2
                || pointsgagnes.get() > getMaxPoint() / 2);
    }

    private void setHint(int index) {
        if (pointQuest.get() == 3) {
            setVisibleHint.set(false);
            setVisibleHint.set(true);
            hintText.set(game.getQuestion(index).getHint());
        } else {
            setVisibleHint.set(true);
            setVisibleHint.set(false);
            hintText.set(" ");
        }
    }

    public void cancel() {
        game.cancel();
    }

}

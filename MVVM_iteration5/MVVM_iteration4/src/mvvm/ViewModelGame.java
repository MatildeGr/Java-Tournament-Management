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
import model.Match.Result;
import model.Player;
import model.Question;
import undoableBuilding.GameMemento;

/**
 *
 * @author remy
 */
public class ViewModelGame {

    private final GameMemento game;
    private final IntegerProperty numQuest = new SimpleIntegerProperty(-1);
    private final StringProperty textPlayersMatch = new SimpleStringProperty();
    private final StringProperty quest = new SimpleStringProperty();
    private final StringProperty textNumQuest = new SimpleStringProperty();
    private final StringProperty pointQuest = new SimpleStringProperty();
    private final StringProperty reponse1 = new SimpleStringProperty();
    private final StringProperty reponse2 = new SimpleStringProperty();
    private final StringProperty reponse3 = new SimpleStringProperty();
    private final StringProperty reponse4 = new SimpleStringProperty();
    private final StringProperty textPointsgagnes = new SimpleStringProperty();
    private final IntegerProperty pointsgagnes = new SimpleIntegerProperty();
    private final IntegerProperty numRepSelected = new SimpleIntegerProperty();
    private final BooleanProperty close = new SimpleBooleanProperty(false);
    private final BooleanProperty cancel = new SimpleBooleanProperty(false);
    private final BooleanProperty setVisibleHint = new SimpleBooleanProperty();
    private final BooleanProperty setVisibleGoBack = new SimpleBooleanProperty();
    private final StringProperty hintText = new SimpleStringProperty();
    private final IntegerProperty numRepUndo = new SimpleIntegerProperty(-1);

    private boolean hintselected = false;
    private boolean firstBadAnswer = true;
    private boolean isUndo = false;
    private int numQuestBeforeUndo = -1;

    private final static int POINT_TO_REMOVE_IF_HINT = 2;
    private final static int POINT_TO_REMOVE_IF_FAKEHINT = 1;
    private final static int POINT_TO_REMOVE_IF_NOT_HINT = 0;
    private final static int POINTTOHIND = 3;

    public ViewModelGame(GameMemento game) {
        this.game = game;
        setTextPlayersMatch();
        nextQuestionOrEndGame();
        setTextEarnedPoints();
        configApplicativeLogic();
    }

    //listener sur le numero de question
    private void configApplicativeLogic() {
        numQuest.addListener((o, old, newValue) -> dynamicEndGame());
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

    //retourne le texte des joueurs participant au match
    public StringProperty textPlayersMatchProperty() {
        return textPlayersMatch;
    }

    //retourne le nom de la question 
    public StringProperty questProperty() {
        return quest;
    }

    //retourne les points d'une question
    public StringProperty pointQuestProperty() {
        return pointQuest;
    }

    //retourne le texte du numero de la question en cours 
    public StringProperty TextNumQuestProperty() {
        return textNumQuest;
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

    //retourne le texte des points gagnés
    public StringProperty textPointsgagnesProperty() {
        return textPointsgagnes;
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

    //renvoie le texte de l'indice. 
    public StringProperty hintTextProperty() {
        return hintText;
    }

    //renvoie si le boutton revenir en arrière peut etre visible
    public BooleanProperty setGoBackProperty() {
        return setVisibleGoBack;
    }

    //renvoie le numéro de la réponse donnée quand undo
    public IntegerProperty numRepUndoProperty() {
        return numRepUndo;
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

    //retourne le nombre de points du max du questionnaire
    public int getMaxPoint() {
        return game.getMaxPoint();
    }

    //confirme une reponse, ajoute les points si reponse juste 
    //Ajoute numéro question et numéro reponse au memento lorsque réponse fausse.
    //ferme la page si confirm et fin de qestionnaire
    public void confirm() {
        if (matchQuestionAnswer()) {
            addEarnedPoints();
        } else {
            if (firstBadAnswer) {
                firstBadAnswer = false;
            }
            game.addQuestion(numQuest.get(), numRepSelected.get());
        }
        nextQuestionOrEndGame();
    }

    public void goBack() {
        if (!firstBadAnswer) {
            game.undo();
            isUndo = true;
            numQuestBeforeUndo = numQuest.get();
            nextQuestionWhenUndo();
        }

    }

    //increment le numero de question ou termine le jeux
    private void nextQuestionOrEndGame() {
        if (canNextQuestion()) {
            if (isUndo) {
                isUndo = false;
                nextQuestionAfterUndo();
            } else {
                nextQuestion();
            }
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

    //set une question 
    private void setQuestion(int index) {
        Question q = game.getQuestion(index);
        quest.set(q.getNom());
        pointQuest.set(q.getPoints() + "points");
        reponse1.set(q.getReponseToString(0));
        reponse2.set(q.getReponseToString(1));
        reponse3.set(q.getReponseToString(2));
        reponse4.set(q.getReponseToString(3));
        setHint();
        setGoBack();
    }

    private boolean stateForEndGame() {
        return (pointsgagnes.get() + game.leftPoint(numQuest.get()) < getMaxPoint() / 2
                || pointsgagnes.get() > getMaxPoint() / 2);
    }

    private void setHint() {
        if (canHadHind()) {
            setVisibleHint(true);

        } else {
            setVisibleHint(false);
        }
    }

    public void setTextHint() {
        setVisibleHint(false);
        hintselected = true;
        hintText.set(currentQuestion().getHint());
    }

    private boolean canHadHind() {
        return currentQuestion().getPoints() == POINTTOHIND;
    }

    public void cancel() {
        game.cancel();
    }

    private boolean canNextQuestion() {
        return numQuest.get() < game.getNbQuest() - 1;
    }

    private void nextQuestion() {
        clearHint();
        numQuest.set(numQuest.get() + 1);
        setTextNumCurrentQuestion();
        setQuestion(numQuest.get());
    }

    private void nextQuestionAfterUndo() {
        clearHint();
        numQuest.set(numQuestBeforeUndo);
        setTextNumCurrentQuestion();
        setQuestion(numQuestBeforeUndo);
    }

    private void nextQuestionWhenUndo() {
        clearHint();
        numQuest.set(game.getNumCurrentQuest());
        numRepUndo.set(game.getNumRepDonner());
        System.out.println(game.getNumRepDonner());
        setTextNumCurrentQuestion();
        setQuestion(numQuest.get());
    }

    private void setTextNumCurrentQuestion() {
        textNumQuest.set((numQuest.get() + 1) + " / " + game.getNbQuest());
    }

    private boolean matchQuestionAnswer() {
        return numRepSelected.get() == currentQuestion().getNumRep();
    }

    private void addEarnedPoints() {
        if (hintselected) {
            if (!isFakeHint()) {
                minusPoint(POINT_TO_REMOVE_IF_HINT);
            } else {
                minusPoint(POINT_TO_REMOVE_IF_FAKEHINT);
            }
        } else {
            minusPoint(POINT_TO_REMOVE_IF_NOT_HINT);
        }
        setTextEarnedPoints();
        hintselected = false;
    }

    private void minusPoint(int point) {
        pointsgagnes.set(pointsgagnes.get() + currentQuestion().getPoints() - point);
    }

    private void setTextEarnedPoints() {
        textPointsgagnes.set("Points gagnés " + pointsgagnes.get() + "/" + game.getMaxPoint());
    }

    private void dynamicEndGame() {
        if (stateForEndGame()) {
            endGame();
        }
    }

    private void setTextPlayersMatch() {
        textPlayersMatch.set("Match : " + getPlayer1() + " - " + getPlayer2());
    }

    private void setVisibleHint(boolean b) {
        setVisibleHint.set(!b);
        setVisibleHint.set(b);
    }

    private void clearHint() {
        hintText.set(null);
    }

    //renvoie true si un l'indice est faux. 
    private boolean isFakeHint() {
        return currentQuestion().getIsFake();
    }

    //Renvoie la question courante.
    private Question currentQuestion() {
        return game.getQuestion(numQuest.get());
    }

    //set la visibilité du bouton retour en arrière.
    private void setVisibleGoBack(boolean b) {
        setVisibleGoBack.set(!b);
        setVisibleGoBack.set(b);
    }

    //set la visibilité du bouton retour en arrière.
    private void setGoBack() {
        if (canHadGoBack()) {
            setVisibleGoBack(true);
        } else {
            setVisibleGoBack(false);
        }
    }

    //Renvoie true si on peut avoir un retour en arriere.
    private boolean canHadGoBack() {
        return !game.isEmptyMemento();
    }

}

package mvvm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ConstructGame;
import model.Question;

/**
 *
 * @author remy
 */
public class ViewModelConstructQuest {

    private final ConstructGame game;

    private final ListProperty<Question> lvQuestions = new SimpleListProperty();
    private final ListProperty<Question> lvQuestionsChoix = new SimpleListProperty();

    private final IntegerProperty numQuestSelected = new SimpleIntegerProperty(-1);
    private final IntegerProperty numQuestChoixSelected = new SimpleIntegerProperty(-1);
    private final IntegerProperty nbQuestChoix = new SimpleIntegerProperty(0);
    private final IntegerProperty pointQuest = new SimpleIntegerProperty();
    private final IntegerProperty nbPointsDispo = new SimpleIntegerProperty(55);

    private final StringProperty quest = new SimpleStringProperty();

    public ViewModelConstructQuest(ConstructGame newGame) {
        this.game = newGame;
        initData();
    }

    private void configApplicativeLogicQuest() {
        numQuestSelected.addListener((o, old, newValue) -> {
            int index = newValue.intValue();
            if (index >= 0) {
                quest.set(lvQuestions.get(numQuestSelected.get()).getNom());
                pointQuest.set(lvQuestions.get(numQuestSelected.get()).getPoints());

            } else {

            }
        });

    }

    private void configApplicativeLogicQuestChoix() {
        numQuestChoixSelected.addListener((o, old, newValue) -> {
            int index = newValue.intValue();
            if (index >= 0) {
                quest.set(lvQuestionsChoix.get(numQuestChoixSelected.get()).getNom());
                pointQuest.set(lvQuestionsChoix.get(numQuestChoixSelected.get()).getPoints());

            } else {

            }
        });

    }

    public void initData() {
        lvQuestions.set(game.getListQuestions());
        lvQuestionsChoix.set(game.getListQuestionsChoix());
        nbQuestChoix.bind(lvQuestionsChoix.sizeProperty());
        configApplicativeLogicQuest();
        configApplicativeLogicQuestChoix();
    }

    //retourne la liste des questions 
    public SimpleListProperty<Question> questionsProperty() {
        return new SimpleListProperty<>(lvQuestions);
    }

    //retourne la liste des questions choisis
    public SimpleListProperty<Question> questionsChoixProperty() {
        return new SimpleListProperty<>(lvQuestionsChoix);
    }

    //retourne le numéro de la question, dans la liste de questions, selectionné
    public IntegerProperty numQuestSelectedProperty() {
        return numQuestSelected;
    }

    //retourne le numéro de la question"dans la liste de questions choisit" selectionné
    public IntegerProperty numQuestChoixSelectedProperty() {
        return numQuestChoixSelected;
    }

    //retourne le nombre de question choisit
    public IntegerProperty nbQuestChoix() {
        return nbQuestChoix;
    }

    //retourne le nom d'une question
    public StringProperty nameQuestSelected() {
        return quest;
    }

    //retourne le nombre de points d'une question
    public IntegerProperty nbPointProperty() {
        return pointQuest;
    }
    //retourne le nombre de points total disponible dans la liste de questions
    public IntegerProperty nbPointsDispoProperty(){
        return nbPointsDispo;
    }

    //binding avec le numéro de question (liste questions) selectionné
    public void bindNumQuestSlectedPropTo(ReadOnlyIntegerProperty prop) {
        numQuestSelected.bind(prop);
    }

    //binding avec le numéro de question (liste questions choisit) selectionné
    public void bindNumQuestChoixSlectedPropTo(ReadOnlyIntegerProperty prop) {
        numQuestChoixSelected.bind(prop);
    }

    public boolean addQuest() {
        if (numQuestSelected.get() > -1) {
            nbPointsDispo.set(nbPointsDispo.get() - lvQuestions.get(numQuestSelected.get()).getPoints());
            return game.add(lvQuestions.get(numQuestSelected.get()));
        }
        return false;
    }

    public boolean removeQuest() {
        if (numQuestChoixSelected.get() > -1) {
            nbPointsDispo.set(nbPointsDispo.get() + lvQuestionsChoix.get(numQuestChoixSelected.get()).getPoints());
            return game.remove(lvQuestionsChoix.get(numQuestChoixSelected.get()));
        }
        return false;
    }
}

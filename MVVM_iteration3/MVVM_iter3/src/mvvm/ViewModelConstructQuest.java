package mvvm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ConstructGame;
import model.Player;
import model.Question;

/**
 *
 * @author remy
 */
public class ViewModelConstructQuest {

    private final ConstructGame game;

    private final ListProperty<Question> lvQuestions = new SimpleListProperty();
    private final ListProperty<Question> lvQuestionsChoix = new SimpleListProperty();

    private final IntegerProperty numQuestSelected = new SimpleIntegerProperty();
    private final IntegerProperty numQuestChoixSelected = new SimpleIntegerProperty();
    private final IntegerProperty nbQuestChoix = new SimpleIntegerProperty(0);
    private final IntegerProperty pointQuest = new SimpleIntegerProperty();
    private final IntegerProperty nbPointsDispo = new SimpleIntegerProperty();
    private final IntegerProperty nbPointsQuestSelect = new SimpleIntegerProperty(0);
    private final IntegerProperty numReponse = new SimpleIntegerProperty();

    private final StringProperty quest = new SimpleStringProperty();
    private final StringProperty reponse1 = new SimpleStringProperty();
    private final StringProperty reponse2 = new SimpleStringProperty();
    private final StringProperty reponse3 = new SimpleStringProperty();
    private final StringProperty reponse4 = new SimpleStringProperty();   

    

    public ViewModelConstructQuest(ConstructGame newGame) {
        this.game = newGame;
        initData();
    }

    public void initData() {
        lvQuestions.set(game.getListQuestions());
        lvQuestionsChoix.set(game.getListQuestionsChoix());
        nbPointsDispo.set(game.getPoints());
        nbQuestChoix.bind(lvQuestionsChoix.sizeProperty());
        configApplicativeLogicQuest();
        configApplicativeLogicQuestChoix();
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
    //gestion de la selection et deselection d'un question dans la liste de questions
    private void configApplicativeLogicQuest() {
        numQuestSelected.addListener((o, old, newValue) -> {
            int index = newValue.intValue();
            if (index >= 0) {
                setAffichageQuestion(lvQuestions.get(numQuestSelected.get()));
            } else {
                defaultAffichageQuestion();
            }
        });

    }

    //gestion de la selection et deselection d'un question dans la liste de questions choisi
    private void configApplicativeLogicQuestChoix() {
        numQuestChoixSelected.addListener((o, old, newValue) -> {
            int index = newValue.intValue();
            if (index >= 0) {
                setAffichageQuestion(lvQuestionsChoix.get(numQuestChoixSelected.get()));

            } else {
                defaultAffichageQuestion();
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

    //retourne la reponse 1 de la question selectionné
    public StringProperty reponse1SelectedProperty() {
        return reponse1;
    }

    //retourne la reponse 2 de la question selectionné
    public StringProperty reponse2SelectedProperty() {
        return reponse2;
    }

    //retourne la reponse 4 de la question selectionné
    public StringProperty reponse3SelectedProperty() {
        return reponse3;
    }

    //retourne la reponse 4 de la question selectionné
    public StringProperty reponse4SelectedProperty() {
        return reponse4;
    }

    //retourne le numero de la response corecte
    public IntegerProperty numReponseProperty() {
        return numReponse;
    }

    //retourne le nombre de points total disponible dans la liste de questions
    public IntegerProperty nbPointsDispoProperty() {
        return nbPointsDispo;
    }

    //retourne le nombre de points total des questions chosis
    public IntegerProperty nbPointsQuestSelectProperty() {
        return nbPointsQuestSelect;
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
    //binding avec le numéro de question (liste questions) selectionné
    public void bindNumQuestSlectedPropTo(ReadOnlyIntegerProperty prop) {
        numQuestSelected.bind(prop);
    }

    //binding avec le numéro de question (liste questions choisit) selectionné
    public void bindNumQuestChoixSlectedPropTo(ReadOnlyIntegerProperty prop) {
        numQuestChoixSelected.bind(prop);
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
    //ajoute une question dans la liste des questions selectionnées et la supprime de la liste des questions
    public boolean addQuest() {
        if (numQuestSelected.get() > -1 && nbPointsQuestSelect.get() + lvQuestions.get(numQuestSelected.get()).getPoints() <= game.getMaxPoint()) {
            nbPointsDispo.set(nbPointsDispo.get() - lvQuestions.get(numQuestSelected.get()).getPoints());
            nbPointsQuestSelect.set(nbPointsQuestSelect.get() + lvQuestions.get(numQuestSelected.get()).getPoints());
            return game.add(lvQuestions.get(numQuestSelected.get()));
        }
        return false;
    }

    //retire une question dans la liste des questions choix selectionnées et l'ajoute dans la liste de question
    public boolean removeQuest() {
        if (numQuestChoixSelected.get() > -1) {
            nbPointsDispo.set(nbPointsDispo.get() + lvQuestionsChoix.get(numQuestChoixSelected.get()).getPoints());
            nbPointsQuestSelect.set(nbPointsQuestSelect.get() - lvQuestionsChoix.get(numQuestChoixSelected.get()).getPoints());
            return game.remove(lvQuestionsChoix.get(numQuestChoixSelected.get()));
        }
        return false;
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
    //retourne le nombre de points maximum pour le questionnaire
    public int getMaxPoints() {
        return game.getMaxPoint();
    }
    
    //retourne joueur 1
    public Player getPlayer1(){
        return game.getPlayer1();
    }
    
    //retourne joueur 2
    public Player getPlayer2(){
        return game.getPlayer2();
    }
    
    //set tous les attributs d'une question
    private void setAffichageQuestion(Question q) {
        quest.set(q.getNom());
        pointQuest.set(q.getPoints());
        reponse1.set(q.getReponseToString(0));
        reponse2.set(q.getReponseToString(1));
        reponse3.set(q.getReponseToString(2));
        reponse4.set(q.getReponseToString(3));
        numReponse.set(q.getNumRep());
    }

    //set tous les attributs d'une question par defaut
    private void defaultAffichageQuestion() {
        quest.set("Question");
        pointQuest.set(0);
        reponse1.set("Reponse 1");
        reponse2.set("Reponse 2");
        reponse3.set("Reponse 3");
        reponse4.set("Reponse 4");
        numReponse.set(0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static model.BuilderElemToQuest.questions;
import model.ListQuestions;
import model.ListTournament;
import mvvm.ViewModel;
import view.View;
import view.ViewContructQuestionGame;

/**
 *
 * @author 3009rerys
 */
public class Mediator {

    private static Mediator INSTANCE = null;

    private final ListTournament lsTournament = new ListTournament();
    private final ObservableList lsQuestion = questions();

    private Mediator() {
    }

    ;
    
        public static Mediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Mediator();
        }
        return INSTANCE;
    }

    public void managerView(Stage primaryStage) {
        ViewModel viewModel = new ViewModel(lsTournament);
        View view = new View(primaryStage, viewModel);
        primaryStage.show();
    }

    public void constructionPartie() {
        Stage stage = new Stage();
        ViewContructQuestionGame view = new ViewContructQuestionGame(stage,lsQuestion);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void viewAnswer() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}

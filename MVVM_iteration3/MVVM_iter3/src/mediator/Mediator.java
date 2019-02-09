/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ConstructGame;
import model.ListTournament;
import model.Player;
import mvvm.ViewModel;
import mvvm.ViewModelConstructQuest;
import view.View;
import view.ViewContructQuestionGame;

/**
 *
 * @author 3009rerys
 */
public class Mediator {

    private static Mediator INSTANCE = null;

    private final ListTournament lsTournament = new ListTournament();

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

    public void constructionPartie(Player p1, Player p2) {
        if (p1 != null && p2 != null) {
            Stage stage = new Stage();
            ConstructGame newGame = new ConstructGame(p1,p2);
            ViewModelConstructQuest viewConstruct = new ViewModelConstructQuest(newGame);
            ViewContructQuestionGame view = new ViewContructQuestionGame(stage, viewConstruct);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
    }

    public void viewAnswer() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}

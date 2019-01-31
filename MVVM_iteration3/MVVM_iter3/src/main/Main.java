/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import model.ListTournament;
import mvvm.ViewModel;
import view.View;

/**
 *
 * @author remy
 */
public class Main extends Application {
    
        @Override
    public void start(Stage primaryStage) throws Exception {
        ListTournament lsTournament = new ListTournament();
        ViewModel viewModel = new ViewModel(lsTournament);
        View view = new View(primaryStage, viewModel);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import mediator.Mediator;

/**
 *
 * @author remy
 */
public class Main extends Application {
    
        @Override
    public void start(Stage primaryStage) throws Exception {
        Mediator.getInstance().managerView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

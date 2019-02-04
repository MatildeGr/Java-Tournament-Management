/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ViewContructQuestionGame;

/**
 *
 * @author 3009rerys
 */
public class Mediator {
    
    
    public static void constructionPartie(){
        Stage stage = new Stage();
        ViewContructQuestionGame view = new ViewContructQuestionGame(stage);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        stage.setTitle("Question game");  
        stage.show(); 
        }
}

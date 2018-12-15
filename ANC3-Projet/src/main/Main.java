package main;

import ctrl.Ctrl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ListeTournois;
import model.TypeNotif;
import view.View;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListeTournois lstournois = new ListeTournois();
        Ctrl ctrl = new Ctrl(lstournois);
        View view = new View(primaryStage,ctrl);
        lstournois.addObserver(view);
        lstournois.notif(TypeNotif.INIT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

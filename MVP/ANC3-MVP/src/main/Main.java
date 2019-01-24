package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import model.ListTournament;
import presenter.Presenter;
import view.ViewImpl;

/**
 *
 * @author remy
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListTournament lsTournament = new ListTournament();
        Presenter presenter = new Presenter(lsTournament);
        ViewImpl view = new ViewImpl(primaryStage);
        view.setPresenter(presenter);
        presenter.setView(view);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

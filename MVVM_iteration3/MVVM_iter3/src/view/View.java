package view;

import java.net.URL;
import java.util.Optional;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Match;
import model.Match.Result;
import model.Player;
import model.Tournament;
import mvvm.ViewModel;

/**
 *
 * @author remy
 */
public class View {

    private static final int TEXTSIZE = 900, SPACING = 10, TEXTSIZEINSCRIT = 400, HTOURNOI = 300, CB = 100, TABLEWIDTH = 150;

    private final VBox tournoi = new VBox(), inscrit = new VBox(), root = new VBox(), match = new VBox();
    private final HBox bas = new HBox();
    private final GridPane upd = new GridPane();
    private final ListView<Tournament> lvTournoi = new ListView<>();
    private final ListView<Player> lvInscrit = new ListView<>();
    private final TableView<Match> lvMatch = new TableView<>();
    private final Label lbTournoi = new Label();
    private final Label lbInscrit = new Label();
    private final Label lbMatch = new Label();
    private final Label j1 = new Label();
    private final Label j2 = new Label();
    private final Label res = new Label();
    private final ComboBox<Player> cbJ1 = new ComboBox();
    private final ComboBox<Player> cbJ2 = new ComboBox();
    private final ComboBox<Result> cbRes = new ComboBox();
    private final Button add = new Button();
    private final Button delete = new Button();
    private final Button update = new Button();
    private final Button play = new Button();

    private final ViewModel viewModel;

    private final ObjectProperty<Player> p1 = new SimpleObjectProperty();
    private final ObjectProperty<Player> p2 = new SimpleObjectProperty();
    private final ObjectProperty<Result> res1 = new SimpleObjectProperty();
    private final ObjectProperty<Player> p1Selected = new SimpleObjectProperty();
    private final ObjectProperty<Player> p2Selected = new SimpleObjectProperty();
    private final ObjectProperty<Result> resultSelected = new SimpleObjectProperty();
    private final BooleanProperty tournamentSelected = new SimpleBooleanProperty();
    private final BooleanProperty matchSelected = new SimpleBooleanProperty();

    public View(Stage primaryStage, ViewModel viewModel) {
        this.viewModel = viewModel;
        configScene();
        config();
        Scene scene = new Scene(root, 1100, 700);
        final URL buttonCSSURL = getClass().getResource("style.css");
        scene.getStylesheets().add(buttonCSSURL.toExternalForm());
        primaryStage.setTitle("Gestion des tournois (mvvm)");
        primaryStage.setScene(scene);
    }

    //Configuration de la partie Tournoi de la vue.
    private void configTournoi() {
        tournoi.setSpacing(SPACING);
        lbTournoi.setText("Tournois");
        lvTournoi.setPrefWidth(TEXTSIZE);
        lvTournoi.setPrefHeight(HTOURNOI);
        tournoi.getChildren().addAll(lbTournoi, lvTournoi);
    }

    //Configuration de la partie Inscrit de la vue.
    private void configInscrit() {
        inscrit.setSpacing(SPACING);
        lbInscrit.setText("Inscrits");
        lvInscrit.setPrefWidth(TEXTSIZEINSCRIT);
        inscrit.getChildren().addAll(lbInscrit, lvInscrit);
    }

    //Configuration de la partie Match de la vue. 
    private void configMatch() {
        configUpd();
        final TableColumn<Match, Player> joueur1 = new TableColumn<>("Joueur 1");
        joueur1.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getPlayer1());
        });
        final TableColumn<Match, Player> joueur2 = new TableColumn<>("Joueur 2");
        joueur2.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getPlayer2());
        });
        final TableColumn<Match, Match.Result> resultat = new TableColumn<>("Résultat");
        resultat.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getResult());
        });

        joueur1.setSortable(false);
        joueur2.setSortable(false);
        resultat.setSortable(false);
        joueur1.setPrefWidth(TABLEWIDTH);
        joueur2.setPrefWidth(TABLEWIDTH);
        resultat.setPrefWidth(TABLEWIDTH * 3.2);
        lvMatch.getColumns().addAll(joueur1, joueur2, resultat);
        match.setSpacing(SPACING);
        lbMatch.setText("Matchs");
        lvMatch.setPrefWidth(TEXTSIZE);
        delete.setText("Delete");
        match.getChildren().addAll(lbMatch, lvMatch, delete, upd);

    }

    //Configuration de la partie de modification ou d'ajout d'un match. 
    private void configUpd() {
        upd.setHgap(30);
        upd.setVgap(10);
        upd.setPadding(new Insets(20, 20, 20, 20));
        j1.setText("Joueur 1");
        j2.setText("Joueur 2");
        res.setText("Résultat");
        add.setText("Ajouter");
        update.setText("Modifier");
        play.setText("Jouer");
        cbJ1.setPrefWidth(CB);
        cbJ2.setPrefWidth(CB);
        cbRes.setPrefWidth(CB * 2);
        upd.add(j1, 0, 0);
        upd.add(j2, 1, 0);
        upd.add(res, 2, 0);
        upd.add(cbJ1, 0, 1);
        upd.add(cbJ2, 1, 1);
        upd.add(cbRes, 2, 1);
        upd.add(add, 3, 1);
        upd.add(update, 4, 1);
        upd.add(play, 5, 1);

    }

    private void configBas() {
        configInscrit();
        configMatch();
        bas.setSpacing(SPACING);
        bas.getChildren().addAll(inscrit, match);
    }

    private void configRoot() {
        configTournoi();
        configBas();
        root.setPadding(new Insets(SPACING));
        root.setSpacing(10);
        root.getChildren().addAll(tournoi, bas);
    }

    public void configScene() {
        configRoot();
    }

    public void config() {
        congigAllListener();
        congAllBinding();
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //               CONFIG BINDING            //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //configuration de tous les binding
    private void congAllBinding() {
        bindingListsViews();
        bindToProperty();
        bindCbList();
        bindCbValues();
        bindSelectionView();
    }

    //binding listView
    private void bindingListsViews() {
        lvTournoi.itemsProperty().bind(viewModel.linesProperty());
        lvMatch.itemsProperty().bind(viewModel.matchProperty());
        lvInscrit.itemsProperty().bind(viewModel.playerProperty());
    }

    //binding to property
    private void bindToProperty() {
        viewModel.bindNumTournamentSlectedPropTo(getListTournament().selectedIndexProperty());
        viewModel.bindNumMatchSlectedPropTo(getListMatch().selectedIndexProperty());
    }

    //Binding list combobox
    private void bindCbList() {
        cbJ1.itemsProperty().bind(viewModel.combobox1Property());
        cbJ2.itemsProperty().bind(viewModel.combobox2Property());
        cbRes.itemsProperty().bind(viewModel.comboboxResProperty());
    }

    //binding combobox values
    private void bindCbValues() {
        p1.bind(viewModel.Player1Selected());
        p2.bind(viewModel.Player2Selected());
        res1.bind(viewModel.resultSelected());
    }

    //binding des selections dans la vue 
    private void bindSelectionView() {
        tournamentSelected.bind(viewModel.tournamentSelectedProperty());
        matchSelected.bind(viewModel.matchSelectedProperty());
        p1Selected.bindBidirectional(viewModel.Player1CbValue());
        p2Selected.bindBidirectional(viewModel.Player2CbValue());
        resultSelected.bindBidirectional(viewModel.ResultCbValue());
    }
    //
    //
    /////////////////////////////////////////////
    //                                         //
    //             SELECTION MODEL             //
    //                                         //
    /////////////////////////////////////////////
    //
    //

    //retourne la liste du listView tournoi
    private SelectionModel<Tournament> getListTournament() {
        return lvTournoi.getSelectionModel();
    }

    //retourne la liste du tableView match
    private SelectionModel<Match> getListMatch() {
        return lvMatch.getSelectionModel();
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //           CONFIG ALL LISTENERS          //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //méthode de configuration de tous les groupes de listener et setOnAction
    private void congigAllListener() {
        configListener();
        configSetOnAction();
        configLogicView();
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //                 LISTENNER               //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //configuration de tous les listener
    private void configListener() {
        configListenerP1();
        configListenerP2();
        configListenerRes1();
        configListenerComboboxPlayer1();
        configListenerComboboxPlayer2();
        configListenerComboboxResultat();
    }

    //listener lors de la selection d'un match (ajout joueur 1 dans la valeur du combobox 1)
    public void configListenerP1() {
        p1.addListener((observable, oldValue, newValue) -> {
            cbJ1.setValue(newValue);
        });
    }

    //listener lors de la selection d'un match (ajout joueur 2 dans la valeur du combobox 2)
    public void configListenerP2() {
        p2.addListener((observable, oldValue, newValue) -> {
            cbJ2.setValue(newValue);
        });
    }

    //listener lors de la selection d'un match (ajout resultat dans la valeur du combobox)
    public void configListenerRes1() {
        res1.addListener((observable, oldValue, newValue) -> {
            cbRes.setValue(newValue);
        });
    }

    //listener selection du joueur 1
    private void configListenerComboboxPlayer1() {
        cbJ1.getSelectionModel().selectedIndexProperty().addListener(
                observable -> p1Selected.set(cbJ1.getValue()));
    }

    //listener selection du joueur 2
    private void configListenerComboboxPlayer2() {
        cbJ2.getSelectionModel().selectedIndexProperty().addListener(
                observable -> p2Selected.set(cbJ2.getValue()));
    }

    //listener selection du resultat
    private void configListenerComboboxResultat() {
        cbRes.getSelectionModel().selectedIndexProperty().addListener(
                observable -> resultSelected.set(cbRes.getValue()));
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
    //configuration de tous les setOnAction
    private void configSetOnAction() {
        setOnActionAddDelete();
        setOnActionUpdate();
        setOnActionAddMatch();
    }

    // supprime un match avec demande de confirmation
    private void setOnActionAddDelete() {
        delete.setOnAction(e -> viewModel.deleteMatch());
    }

    //modifie un match 
    private void setOnActionUpdate() {
        update.setOnAction(e -> viewModel.updatematch());
    }

    //ajoute un match 
    private void setOnActionAddMatch() {
        add.setOnAction(e -> viewModel.addMatch());
        play.setOnAction(e -> viewModel.constructionPartie());

    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //          APPLICATION LOGIC VIEW         //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //configuration de toutes les methodes de la logique de la vue
    private void configLogicView() {
        configTournamentSelectedListener();
        configMatchSelectedListener();

    }

    //active ou desactive tous les boutons de la vue
    private void configTournamentSelectedListener() {
        tournamentSelected.addListener((observable, oldValue, newValue) -> {
            boolean b = tournamentSelected.get();
            disableButton(b);
            if (!tournamentSelected.get()) {
                editButton(!b);
            }
        });
    }

    //active ou desactive tous les boutons en fonction d'un match selection ou deselectionné
    private void configMatchSelectedListener() {
        matchSelected.addListener((observable, oldValue, newValue) -> {
            boolean b = matchSelected.get();
            if (!tournamentSelected.get()) {
                editButton(b);
            }

        });
    }

    //
    //
    /////////////////////////////////////////////
    //                                         //
    //          USEFUL METHOD FOR VIEW         //
    //                                         //
    /////////////////////////////////////////////
    //
    //
    //active ou desactive tous les boutons de la vue 
    private void disableButton(boolean on) {
        add.setDisable(on);
        delete.setDisable(on);
        update.setDisable(on);
    }

    //active add && desactive (delete && update) si false
    //desactive add && active (delete && update) si true
    private void editButton(boolean on) {
        add.setDisable(!on);
        delete.setDisable(on);
        update.setDisable(on);
    }
}

package view;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
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
import presenter.Presenter;

/**
 *
 * @author remy
 */
public class ViewImpl implements View {

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

    private Presenter presenter;

    //Constructeur de View.
    public ViewImpl(Stage primaryStage) {
        configScene();
        configListenerSelectionLine();
        configListenerComboboxPlayer1();
        setOnActionAddMatch();
        configListenerSelectionMatch();
        setOnActionAddDelete();
        setOnActionUpdate();
        //configListenerComboboxPlayer2();
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Gestion des tournois (mvp)");
        primaryStage.setScene(scene);
    }

    //configuration du presenter
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
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
        final TableColumn<Match, Result> resultat = new TableColumn<>("Résultat");
        resultat.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getResult());
        });

        joueur1.setSortable(false);
        joueur2.setSortable(false);
        resultat.setSortable(false);
        joueur1.setPrefWidth(TABLEWIDTH);
        joueur2.setPrefWidth(TABLEWIDTH);
        resultat.setPrefWidth(TABLEWIDTH * 1.25);
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

    private SelectionModel<Tournament> getListViewTournament() {
        return lvTournoi.getSelectionModel();
    }

    private SelectionModel<Match> getTableViewMatch() {
        return lvMatch.getSelectionModel();
    }

    @Override
    public void loadTournaments(List<Tournament> lsTournament) {
        lvTournoi.getItems().setAll(lsTournament);
    }

    @Override
    public void loadMatchs(List<Match> lsMatchs) {
        lvMatch.getItems().setAll(lsMatchs);
    }

    @Override
    public void loadPlayers(List<Player> lsPlayers) {
        lvInscrit.getItems().setAll(lsPlayers);
    }

    @Override
    public void loadComboboxPlayer1(List<Player> lsPlayers) {
        cbJ1.getItems().setAll(lsPlayers);
    }

    @Override
    public void loadComboboxPlayer2(List<Player> lsPlayers) {
        cbJ2.getItems().setAll(lsPlayers);
    }

    @Override
    public void loadComboboxResult(EnumSet<Result> result) {
        cbRes.getItems().setAll(result);
    }

    @Override
    public void loadMatchSelected() {
        Match m = lvMatch.getSelectionModel().getSelectedItem();
        cbJ1.setValue(m.getPlayer1());
        cbJ2.setValue(m.getPlayer2());
        cbRes.setValue(m.getResult());
    }

    @Override
    public void selectTournament(int index) {
        getListViewTournament().select(index);
    }

    @Override
    public void selectmatch(int index) {
        getTableViewMatch().select(index);
    }

    private void configListenerSelectionLine() {
        getListViewTournament().selectedIndexProperty()
                .addListener(o -> {
                    presenter.lineSelectionTournament(getListViewTournament().getSelectedIndex());
                });
    }

    private void configListenerSelectionMatch() {
        getTableViewMatch().selectedIndexProperty()
                .addListener(o -> {
                    presenter.lineSelectionMatch(getTableViewMatch().getSelectedIndex());
                });
    }

    private void configListenerComboboxPlayer1() {
        cbJ1.getSelectionModel().selectedIndexProperty().addListener(
                observable -> presenter.setOpponentsPlayer1(cbJ1.getValue()));
    }

    //changement de la liste combobox joueur 1 !!probleme
    private void configListenerComboboxPlayer2() {
        cbJ2.getSelectionModel().selectedIndexProperty().addListener(
                observable -> presenter.setOpponentsPlayer2(cbJ2.getValue()));
    }

    private void setOnActionAddMatch() {
        add.setOnAction(e -> {
            if (cbJ1.getValue() != null && cbJ2.getValue() != null && cbRes.getValue() != null) {
                Match m = new Match(cbJ1.getValue(), cbJ2.getValue(), cbRes.getValue());
                presenter.addMatch(m);
                clearValuesCombobox();
                clearCbj2();
            }
        });
    }

    private void setOnActionUpdate() {
        update.setOnAction(e -> {
            if (cbJ1.getValue() != null && cbJ2.getValue() != null && cbRes.getValue() != null) {
                Match m = new Match(cbJ1.getValue(), cbJ2.getValue(), cbRes.getValue());
                presenter.updatematch(m);
            }
        }
        );
    }

    private void setOnActionAddDelete() {
        delete.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            Match m = lvMatch.getSelectionModel().getSelectedItem();
            alert.setHeaderText("Suppression du match entre " + m.getPlayer1() + " et " + m.getPlayer2());
            alert.setContentText("Souhaitez-vous supprimer ce match ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                presenter.deleteMatch(m);
            }
        });
    }

    @Override
    public void unselectTournament() {
        unselectLvMatch();
        unselectLvPlayers();
        clearListCombobox();
    }

    @Override
    public void unselectMatch() {
        clearValuesCombobox();
        clearCbj2();
    }

    private void unselectLvMatch() {
        lvMatch.getItems().clear();
    }

    private void unselectLvPlayers() {
        lvInscrit.getItems().clear();
    }

    private void clearListCombobox() {
        clearCbj1();
        clearCbj2();
        clearCbRes();
    }

    private void clearCbj1() {
        cbJ1.getItems().clear();
    }

    private void clearCbj2() {
        cbJ2.getItems().clear();
    }

    private void clearCbRes() {
        cbRes.getItems().clear();
    }

    private void clearValuesCombobox() {
        cbJ1.setValue(null);
        cbJ2.setValue(null);
        cbRes.setValue(null);
    }

    @Override
    public void editButton(boolean on) {
        add.setDisable(!on);
        delete.setDisable(on);
        update.setDisable(on);
    }

    @Override
    public void disableButton(boolean on) {
        add.setDisable(on);
        delete.setDisable(on);
        update.setDisable(on);
    }

}

package view;

import ctrl.Ctrl;
import java.util.EnumSet;
import java.util.Observable;
import java.util.Observer;
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
import model.Joueur;
import model.ListeTournois;
import model.Match;
import model.Match.Resultats;
import model.Tournoi;
import model.TypeNotif;

/**
 *
 * @author remy
 */
public class View implements Observer {

    private static final int TEXTSIZE = 900, SPACING = 10, TEXTSIZEINSCRIT = 400, HTOURNOI = 300, CB = 100, TABLEWIDTH = 150;

    private final VBox tournoi = new VBox(), inscrit = new VBox(), root = new VBox(), match = new VBox();
    private final HBox bas = new HBox();
    private final GridPane upd = new GridPane();
    private final ListView<Tournoi> lvTournoi = new ListView<>();
    private final ListView<Joueur> lvInscrit = new ListView<>();
    private final TableView<Match> lvMatch = new TableView<>();
    private final Label lbTournoi = new Label();
    private final Label lbInscrit = new Label();
    private final Label lbMatch = new Label();
    private final Label j1 = new Label();
    private final Label j2 = new Label();
    private final Label res = new Label();
    private final ComboBox<Joueur> cbJ1 = new ComboBox();
    private final ComboBox<Joueur> cbJ2 = new ComboBox();
    private final ComboBox<Resultats> cbRes = new ComboBox();
    private final Button add = new Button();
    private final Button delete = new Button();
    private final Button update = new Button();

    private final Ctrl ctrl;

    //Constructeur de View.
    public View(Stage primaryStage, Ctrl ctrl) {
        this.ctrl = ctrl;
        configScene();
        action();
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Gestion des tournois");
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
        final TableColumn<Match, Joueur> joueur1 = new TableColumn<>("Joueur 1");
        joueur1.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getJoueur1());
        });
        final TableColumn<Match, Joueur> joueur2 = new TableColumn<>("Joueur 2");
        joueur2.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getJoueur2());
        });
        final TableColumn<Match, Resultats> resultat = new TableColumn<>("Résultat");
        resultat.setCellValueFactory(param -> {
            final Match m = param.getValue();
            return new SimpleObjectProperty<>(m.getResultats());
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

    private SelectionModel<Tournoi> getListViewTournoi() {
        return lvTournoi.getSelectionModel();
    }

    private SelectionModel<Match> getListViewMatch() {
        return lvMatch.getSelectionModel();
    }

    private void action() {
        configSelectionLine();
        configSelectionMatch();
        configSelectionComboBox1();
        configSelectionComboBox2();
        configSelectionComboBoxRes();
        configListenerEditLine();
        delete();
        configUpdate();
    }

    private void configSelectionLine() {
        getListViewTournoi().selectedIndexProperty().addListener(o -> {
            ctrl.lineSelection(getListViewTournoi().getSelectedIndex());
        });
    }

    private void configSelectionMatch() {
        getListViewMatch().selectedIndexProperty().addListener(o -> {
            ctrl.selectMatch(getListViewMatch().getSelectedIndex());
        });
    }

    private void configSelectionComboBox1() {
        cbJ1.getSelectionModel().selectedIndexProperty().addListener(
                observable -> ctrl.cb1Selection(cbJ1.getSelectionModel().getSelectedIndex()));
    }

    private void configSelectionComboBox2() {
        cbJ2.getSelectionModel().selectedIndexProperty().addListener(
                observable -> ctrl.cb2Selection());
    }
    private void configSelectionComboBoxRes() {
        cbRes.getSelectionModel().selectedIndexProperty().addListener(
                observable -> add.setDisable(false) );
    }

    private void configListenerEditLine() {
        add.setOnAction(e -> {
            if (cbJ1.getValue() != null && cbJ2.getValue() != null && cbRes.getValue() != null) {
                ctrl.addMatch(cbJ1.getValue(), cbJ2.getValue(), cbRes.getValue());
            }
        });
    }
    private void configUpdate(){
        update.setOnAction(e -> {
            if (cbJ1.getValue() != null && cbJ2.getValue() != null && cbRes.getValue() != null) {
                ctrl.updMatch(cbJ1.getValue(), cbJ2.getValue(), cbRes.getValue());
            }
        }
        );
    }

    private void delete() {
        delete.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            Match m = lvMatch.getSelectionModel().getSelectedItem();
            alert.setHeaderText("Suppression du match entre " + m.getJoueur1() + " et " + m.getJoueur2());
            alert.setContentText("Souhaitez-vous supprimer ce match ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ctrl.deleteMatch(m);

            }
        });
    }

    private void reset_combobox() {
        cbJ1.getItems().setAll();
        cbJ2.getItems().setAll();
        cbRes.getItems().setAll();
        cbJ1.getItems().setAll(ctrl.getAllInscrit(ctrl.getNumLineSelected()));
    }
    private void reset_value_combobox(){
        cbJ1.setValue(null);
        cbJ2.setValue(null);
        cbRes.setValue(null);
    }

    private void setButtonEditable(boolean b) {
        add.setDisable(b);
        delete.setDisable(b);
        update.setDisable(b);
    }
    
    private void setButonDelUpd(boolean b){
        delete.setDisable(b);
        update.setDisable(b);
    }

    private void hiddenButton(boolean b) {
        add.setVisible(b);
        delete.setVisible(b);
    }

    @Override
    public void update(Observable o, Object o1) {
        ListeTournois lstournois = (ListeTournois) o;
        TypeNotif typeNotif = (TypeNotif) o1;
        switch (typeNotif) {
            case INIT:
                setButtonEditable(true);
                lvTournoi.getItems().setAll(ctrl.getLines());
                break;
            case LINE_TOURNOI_SELECTED:
                lvInscrit.getItems().setAll(ctrl.getAllInscrit(lstournois.getNumLineSelected()));
                lvMatch.getItems().setAll(ctrl.getAllMatch(lstournois.getNumLineSelected()));
                cbJ1.getItems().setAll(ctrl.getAllInscrit(lstournois.getNumLineSelected()));
                add.setDisable(false);
                break;
            case CB1_SELECTED:
                cbJ2.getItems().setAll();
                cbJ2.getItems().setAll(ctrl.getAdversaire());
                break;
            case CB2_SELECTED:
                cbRes.getItems().setAll();
                cbRes.getItems().setAll(EnumSet.allOf(Match.Resultats.class));
                break;
            case ADD_MATCH:
                lvMatch.getItems().setAll(ctrl.getAllMatch(ctrl.getNumLineSelected()));
                reset_combobox();
                break;
            case DELETE_MATCH:
                reset_value_combobox();
                lvMatch.getItems().setAll(ctrl.getAllMatch(ctrl.getNumLineSelected()));
                ctrl.unselectMatch();
                setButonDelUpd(true);
                break;
            case LINE_MATCH_SELECTED:         
                cbJ1.setValue(ctrl.getSelectMatch().getJoueur1());
                cbJ2.setValue(ctrl.getSelectMatch().getJoueur2());
                cbRes.setValue(ctrl.getSelectMatch().getResultats());
                cbRes.getItems().setAll(EnumSet.allOf(Match.Resultats.class));
                setButonDelUpd(false);
                add.setDisable(true);
                break;
            case LINE_UPDATED:
                reset_value_combobox();
                reset_combobox();
                add.setDisable(false);
                setButonDelUpd(true);
                lvMatch.getItems().setAll(ctrl.getAllMatch(ctrl.getNumLineSelected()));
                break;
        }

    }

}

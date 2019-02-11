package view;

import java.net.URL;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvvm.ViewModelGame;

/**
 *
 * @author remy
 */
public class ViewGame {

    private final ViewModelGame viewModel;
    private final Stage stage;
    private final Label title = new Label();
    private final Label match = new Label();
    private final Label nbQuest = new Label();
    private final Label quest = new Label();
    private final Label questPoint = new Label();
    private final Label reponse = new Label();
    private final RadioButton r1 = new RadioButton();
    private final RadioButton r2 = new RadioButton();
    private final RadioButton r3 = new RadioButton();
    private final RadioButton r4 = new RadioButton();
    private final ToggleGroup group = new ToggleGroup();
    private final Label pointGagne = new Label();
    private final Button confirm = new Button();
    private final Button cancel = new Button();
    private final Separator s1 = new Separator();
    private final Separator s2 = new Separator();

    private final VBox root = new VBox(), radioButton = new VBox(), vbQuest = new VBox();
    private final HBox btn = new HBox(), hbMatch = new HBox(), hbTitle = new HBox(), hbPoint = new HBox();

    private final int nbQuestion;
    private final int maxPoint;

    private final IntegerProperty numQuest = new SimpleIntegerProperty();
    private final StringProperty question = new SimpleStringProperty();
    private final IntegerProperty pointQuest = new SimpleIntegerProperty();
    private final StringProperty reponse1 = new SimpleStringProperty();
    private final StringProperty reponse2 = new SimpleStringProperty();
    private final StringProperty reponse3 = new SimpleStringProperty();
    private final StringProperty reponse4 = new SimpleStringProperty();
    private final IntegerProperty pointsgagnes = new SimpleIntegerProperty();
    private final IntegerProperty numRepSelected = new SimpleIntegerProperty();
    private final BooleanProperty close = new SimpleBooleanProperty();

    public ViewGame(Stage stage, ViewModelGame viewModel) {
        this.viewModel = viewModel;
        this.stage = stage;
        nbQuestion = viewModel.getNbQuest();
        maxPoint = viewModel.getMaxPoint();
        binding();
        initView();
        congigAllListener();
        Scene scene = new Scene(root, 350, 550);
        final URL buttonCSSURL = getClass().getResource("style.css");
        scene.getStylesheets().add(buttonCSSURL.toExternalForm());
        stage.setTitle("Réponses au questionnaire");
        stage.setScene(scene);
    }

    private void initView() {
        configTitle();
        configMatch();
        configQuest();
        configReponses();
        configPointsGagne();
        configButton();
        configPaddingSpacing();
        configAddAllBox();
    }

    private void configTitle() {
        title.setText("Réponse au questionnaire");
        title.setId("title");
        title.setAlignment(Pos.CENTER);
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.getChildren().addAll(title);
    }

    private void configMatch() {
        match.setText("Match : " + viewModel.getPlayer1() + " - " + viewModel.getPlayer2());
        nbQuest.setText("1/" + nbQuestion);
        hbMatch.setAlignment(Pos.CENTER);
        hbMatch.getChildren().addAll(match, nbQuest);
    }

    private void configQuest() {
        quest.setText(question.get());
        questPoint.setText(pointQuest.get() + " points");
        quest.setAlignment(Pos.CENTER);
        questPoint.setAlignment(Pos.CENTER);
        vbQuest.setAlignment(Pos.CENTER);
        vbQuest.getChildren().addAll(quest, questPoint);
    }

    private void configReponses() {
        reponse.setText("Réponses");
        reponse.setAlignment(Pos.CENTER);
        r1.setText(reponse1.get());
        r1.setToggleGroup(group);
        r1.setUserData(1);
        r2.setText(reponse2.get());
        r2.setToggleGroup(group);
        r2.setUserData(2);
        r3.setText(reponse3.get());
        r3.setToggleGroup(group);
        r3.setUserData(3);
        r4.setText(reponse4.get());
        r4.setToggleGroup(group);
        r4.setUserData(4);
        radioButton.setSpacing(10);
        radioButton.setPadding(new Insets(0, 0, 0, 0));
        radioButton.setAlignment(Pos.CENTER);
        s1.setMaxWidth(180);
        s2.setMaxWidth(180);
        radioButton.getChildren().addAll(reponse, s1, r1, r2, r3, r4, s2);
    }

    private void configPointsGagne() {
        pointGagne.setText("Points gagnés 0/" + maxPoint);
        pointGagne.setAlignment(Pos.CENTER);
        hbPoint.setAlignment(Pos.CENTER);
        hbPoint.getChildren().addAll(pointGagne);
    }

    private void configButton() {
        confirm.setText("Validé");
        cancel.setText("Annuler");
        btn.setAlignment(Pos.CENTER);
        btn.getChildren().addAll(confirm, cancel);
    }

    private void configPaddingSpacing() {
        hbTitle.setPadding(new Insets(0, 0, 30, 0));
        hbMatch.setPadding(new Insets(0, 0, 30, 0));
        hbMatch.setSpacing(60);
        vbQuest.setPadding(new Insets(0, 0, 30, 0));
        vbQuest.setSpacing(40);
        radioButton.setPadding(new Insets(0, 0, 30, 0));
        radioButton.setSpacing(20);
        hbPoint.setPadding(new Insets(0, 0, 30, 0));
        btn.setPadding(new Insets(0, 0, 30, 0));
        btn.setSpacing(20);
        root.setPadding(new Insets(20));
    }

    private void configAddAllBox() {
        root.getChildren().addAll(hbTitle, hbMatch, vbQuest, radioButton, hbPoint, btn);
    }

    private void binding() {
        numQuest.bind(viewModel.numQuestProperty());
        question.bind(viewModel.questProperty());
        pointQuest.bind(viewModel.pointQuestProperty());
        reponse1.bind(viewModel.reponse1Property());
        reponse2.bind(viewModel.reponse2Property());
        reponse3.bind(viewModel.reponse3Property());
        reponse4.bind(viewModel.reponse4Property());
        pointsgagnes.bind(viewModel.pointsGagnesProperty());
        numRepSelected.bindBidirectional(viewModel.numRepSelectedProperty());
        close.bind(viewModel.closeProperty());
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
        configListenernumQuest();
        configListenerQuest();
        configListenerPointQuest();
        configListenerReponse1();
        configListenerReponse2();
        configListenerReponse3();
        configListenerReponse4();
        configListenerPointsGagnes();
        configListenerRepSelected();
        setOnActionconfirm();
        closeWindows();

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
    //listener sur le numero de question
    public void configListenernumQuest() {
        numQuest.addListener((observable, oldValue, newValue) -> {
            int val = newValue.intValue() + 1;
            nbQuest.setText(val + "/" + nbQuestion);
        });
    }

    //listener nom de question
    public void configListenerQuest() {
        question.addListener((observable, oldValue, newValue) -> {
            quest.setText(newValue);
        });
    }

    //listener point question
    public void configListenerPointQuest() {
        pointQuest.addListener((observable, oldValue, newValue) -> {
            questPoint.setText(newValue + " Points");
        });
    }

    //listener reponse1
    public void configListenerReponse1() {
        reponse1.addListener((observable, oldValue, newValue) -> {
            r1.setText(newValue);
        });
    }

    //listener reponse2
    public void configListenerReponse2() {
        reponse2.addListener((observable, oldValue, newValue) -> {
            r2.setText(newValue);
        });
    }

    //listener reponse3
    public void configListenerReponse3() {
        reponse3.addListener((observable, oldValue, newValue) -> {
            r3.setText(newValue);
        });
    }

    //listener reponse4
    public void configListenerReponse4() {
        reponse4.addListener((observable, oldValue, newValue) -> {
            r4.setText(newValue);
        });
    }

    //listener des points gagnés
    public void configListenerPointsGagnes() {
        pointsgagnes.addListener((observable, oldValue, newValue) -> {
            pointGagne.setText("Points gagnés " + newValue + "/" + maxPoint);
        });
    }

    public void configListenerRepSelected() {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) group.getSelectedToggle();
                    numRepSelected.set(new Integer(button.getUserData().toString()));
                }
            }
        });
    }

    private void setOnActionconfirm() {
        confirm.setOnAction(e -> {
            viewModel.confirm();
        });
        cancel.setOnAction(e -> {
            viewModel.cancel();
            stage.close();
        });

    }

    //listener sur la fermeture de la stage
    public void closeWindows() {
        close.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stage.close();
            }
        });
    }
}

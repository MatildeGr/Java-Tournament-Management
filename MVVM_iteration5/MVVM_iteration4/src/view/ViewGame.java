package view;

import java.net.URL;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvvm.ViewModelGame;

/**
 *
 * @author remy
 */
public class ViewGame {

    private static final Insets INSETS = new Insets(0, 0, 30, 0);
    private final ViewModelGame viewModel;
    private final Stage stage;
    private final Label title = new Label();
    private final Label match = new Label();
    private final Label nbQuest = new Label();
    private final Text quest = new Text();
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
    private final Button hint = new Button();
    private final Text hintText = new Text();

    private final VBox root = new VBox(), radioButton = new VBox(), vbQuest = new VBox();
    private final HBox btn = new HBox(), hbMatch = new HBox(), hbTitle = new HBox(), hbPoint = new HBox();

    private final IntegerProperty numRepSelected = new SimpleIntegerProperty();
    private final BooleanProperty close = new SimpleBooleanProperty();

    public ViewGame(Stage stage, ViewModelGame viewModel) {
        this.viewModel = viewModel;
        this.stage = stage;
        binding();
        congigAllListener();
        initView();
        Scene scene = new Scene(root, 350, 750);
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
        hbMatch.setAlignment(Pos.CENTER);
        hbMatch.getChildren().addAll(match, nbQuest);
    }

    private void configQuest() {
        quest.setWrappingWidth(200);
        quest.setFill(Color.WHITE);
        questPoint.setAlignment(Pos.CENTER);
        hint.setText("Hint");
        hint.setAlignment(Pos.CENTER);
        hintText.setFill(Color.WHITE);
        vbQuest.setAlignment(Pos.CENTER);
        vbQuest.getChildren().addAll(quest, hint, hintText, questPoint);
    }

    private void configReponses() {
        reponse.setText("Réponses");
        reponse.setAlignment(Pos.CENTER);
        r1.setToggleGroup(group);
        r1.setUserData(1);
        r2.setToggleGroup(group);
        r2.setUserData(2);
        r3.setToggleGroup(group);
        r3.setUserData(3);
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
        hbTitle.setPadding(INSETS);
        hbMatch.setPadding(INSETS);
        hbMatch.setSpacing(60);
        vbQuest.setPadding(INSETS);
        vbQuest.setSpacing(40);
        radioButton.setPadding(INSETS);
        radioButton.setSpacing(20);
        hbPoint.setPadding(INSETS);
        btn.setPadding(INSETS
        );
        btn.setSpacing(20);
        root.setPadding(new Insets(20));
    }

    private void configAddAllBox() {
        root.getChildren().addAll(hbTitle, hbMatch, vbQuest, radioButton, hbPoint, btn);
    }

    private void binding() {
        match.textProperty().bind(viewModel.textPlayersMatchProperty());
        quest.textProperty().bind(viewModel.questProperty());
        nbQuest.textProperty().bind(viewModel.TextNumQuestProperty());
        questPoint.textProperty().bind(viewModel.pointQuestProperty());
        pointGagne.textProperty().bind(viewModel.textPointsgagnesProperty());
        r1.textProperty().bind(viewModel.reponse1Property());
        r2.textProperty().bind(viewModel.reponse2Property());
        r3.textProperty().bind(viewModel.reponse3Property());
        r4.textProperty().bind(viewModel.reponse4Property());
        numRepSelected.bindBidirectional(viewModel.numRepSelectedProperty());
        close.bind(viewModel.closeProperty());
        hint.visibleProperty().bind(viewModel.setVisibleHintProperty());
        hintText.textProperty().bind(viewModel.hintTextProperty());
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
        configListenerRepSelected();
        setOnActionHint();
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

    private void setOnActionHint() {
        hint.setOnAction(e -> {
            viewModel.setTextHint();
        });

    }

    private void setOnActionconfirm() {
        confirm.setOnAction(e -> viewModel.confirm());

        cancel.setOnAction(e -> {
            viewModel.cancel();
            stage.close();
        });

        stage.setOnCloseRequest(e -> viewModel.cancel());
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

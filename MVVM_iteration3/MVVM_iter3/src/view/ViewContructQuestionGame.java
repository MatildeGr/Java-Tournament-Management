/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import mvvm.*;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 3009rerys
 */
public class ViewContructQuestionGame {

    private static final int WIDTHLIST = 300, HEIGHTLIST = 800, SPACING60 = 60, SPACING80 = 80, SPACINGBUTTON = 10, PADDING = 20;

    private final HBox root = new HBox(), btt = new HBox();
    private final VBox list = new VBox(), viewer = new VBox(), selected = new VBox(), btb = new VBox(), question = new VBox(), radioButton = new VBox();
    private final BorderPane border = new BorderPane();

    private final Label lbMatch = new Label();
    private final Label lbPointsDisp = new Label();
    private final Label lbTitle = new Label();
    private final Label lbNbQuest = new Label();
    private final Label lbPointsQuest = new Label();
    private final Label lbquestion = new Label();
    private final Label points = new Label();
    private final Label lbReponse = new Label();

    private final Button addQuest = new Button();
    private final Button removeQuest = new Button();
    private final Button validate = new Button();
    private final Button cancel = new Button();

    private final ListView<String> listQuest = new ListView();
    private final ListView<String> listQuestChoix = new ListView();

    private final RadioButton rq1 = new RadioButton();
    private final RadioButton rq2 = new RadioButton();
    private final RadioButton rq3 = new RadioButton();
    private final RadioButton rq4 = new RadioButton();
    final ToggleGroup group = new ToggleGroup();

    public ViewContructQuestionGame(Stage stage) {
        initView();
        Scene scene = new Scene(root, 900, 600);
        final URL buttonCSSURL = getClass().getResource("style.css");
        scene.getStylesheets().add(buttonCSSURL.toExternalForm());
        stage.setTitle("Construction quéstionnaire");
        stage.setScene(scene);
    }

    private void initView() {
        configQestion();
        configVueQuestion();
        configViewQuestChoix();
        configRoot();
    }

    ///////////////////////////////////////
    //                                   //
    //       CONFIG VIEW QUESTIONS       //
    //                                   //
    ///////////////////////////////////////
    private void configQestion() {
        configLabelTopQuestion();
        configListViewQuestion();
        configLabelBottomQuestion();
        configAddChildrenQuestion();
    }

    private void configLabelTopQuestion() {
        lbMatch.setPrefWidth(WIDTHLIST);
        lbMatch.setText("Match joueur 1 joueur2");
        lbMatch.setAlignment(Pos.CENTER);
    }

    private void configListViewQuestion() {
        listQuest.setPrefWidth(WIDTHLIST);
        listQuest.setPrefHeight(HEIGHTLIST);
    }

    private void configLabelBottomQuestion() {
        lbPointsDisp.setPrefWidth(WIDTHLIST);
        lbPointsDisp.setText("Points disponibles: 55");
        lbPointsDisp.setAlignment(Pos.CENTER);
    }

    private void configAddChildrenQuestion() {
        list.setSpacing(PADDING);
        list.setPadding(new Insets(PADDING, 0, PADDING, 0));
        list.getChildren().addAll(lbMatch, listQuest, lbPointsDisp);
    }

    ///////////////////////////////////////
    //                                   //
    //  CONFIG VIEW QUESTIONS SELECTED   //
    //                                   //
    ///////////////////////////////////////
    private void configVueQuestion() {
        configLabelTitle();
        configLabelQuestion();
        configLabelPoints();
        configAddVBoxQuestionPoints();
        configLabelReponse();
        configRadioButton();
        configAddChildrenVBoxRadioButton();
        configButtonsQuestion();
        configAddButtonsVBobHBox();
        configBorderPaneQuestion();
        configAddChildrenVBoxQuestion();
    }

    private void configLabelTitle() {
        lbTitle.setPrefWidth(WIDTHLIST);
        lbTitle.setText("Construction questionnaire");
        lbTitle.setId("title");
        lbTitle.setAlignment(Pos.CENTER);
    }

    private void configLabelQuestion() {
        lbquestion.setMaxWidth(200);
        lbquestion.setWrapText(true);
        lbquestion.setText("Une question");
        lbquestion.setAlignment(Pos.CENTER);
        lbquestion.setId("quest");
        //lbquestion.setTextAlignment(TextAlignment.CENTER);
    }

    private void configLabelPoints() {
        points.setText("3 points");
    }

    private void configAddVBoxQuestionPoints() {
        question.setSpacing(SPACING60);
        question.setPadding(new Insets(SPACING80, 0, 0, 0));
        question.setAlignment(Pos.CENTER);
        question.getChildren().addAll(lbquestion, points);
    }

    private void configLabelReponse() {
        lbReponse.setText("Réponses");
    }

    private void configRadioButton() {
        rq1.setToggleGroup(group);
        rq1.setText("reponse 1");
        rq1.setUserData("Reponse 1");
        rq1.setSelected(true);
        rq1.setDisable(true);
        rq2.setToggleGroup(group);
        rq2.setText("reponse 2");
        rq2.setUserData("Reponse 2");
        rq2.setDisable(true);
        rq3.setToggleGroup(group);
        rq3.setText("reponse 3");
        rq3.setUserData("Reponse 3");
        rq3.setDisable(true);
        rq4.setToggleGroup(group);
        rq4.setText("reponse 4");
        rq4.setUserData("Reponse 4");
        rq4.setDisable(true);
    }

    private void configAddChildrenVBoxRadioButton() {
        radioButton.setSpacing(SPACINGBUTTON);
        radioButton.setPadding(new Insets(SPACING60, 0, SPACING60, 0));
        radioButton.setAlignment(Pos.CENTER);
        radioButton.getChildren().addAll(lbReponse, rq1, rq2, rq3, rq4);
    }

    private void configButtonsQuestion() {
        addQuest.setText("->");
        addQuest.setAlignment(Pos.CENTER);
        removeQuest.setText("<-");
        removeQuest.setAlignment(Pos.CENTER);
        validate.setText("Confirm");
        cancel.setText("Cancel");
    }

    private void configAddButtonsVBobHBox() {
        btb.setSpacing(SPACINGBUTTON / 2);
        btb.getChildren().addAll(addQuest, removeQuest);
        btb.setAlignment(Pos.CENTER);
        btt.setSpacing(PADDING / 2);
        btt.setAlignment(Pos.CENTER);
        btt.setPadding(new Insets(PADDING, 0, 0, 0));
        btt.getChildren().addAll(validate, cancel);
    }

    private void configBorderPaneQuestion() {
        border.setTop(btb);
        border.setCenter(btt);
    }

    private void configAddChildrenVBoxQuestion() {
        viewer.getChildren().addAll(lbTitle, question, radioButton, border);
    }

    ///////////////////////////////////////
    //                                   //
    //     CONFIG VIEW QUESTIONS ADD     //
    //                                   //
    ///////////////////////////////////////
    private void configViewQuestChoix() {
        configLabelTopQuestChoix();
        configListViewQuestionChoix();
        configLabelBottomQuestChoix();
        configAddChildrenQuestionChoix();
    }

    private void configLabelTopQuestChoix() {
        lbNbQuest.setPrefWidth(WIDTHLIST);
        lbNbQuest.setText("Nombre de questions: 2");
        lbNbQuest.setAlignment(Pos.CENTER);
    }

    private void configListViewQuestionChoix() {
        listQuestChoix.setPrefWidth(WIDTHLIST);
        listQuestChoix.setPrefHeight(HEIGHTLIST);
    }

    private void configLabelBottomQuestChoix() {
        lbPointsQuest.setPrefWidth(WIDTHLIST);
        lbPointsQuest.setText("Points questionnaire: 3/10");
        lbPointsQuest.setAlignment(Pos.CENTER);
    }

    private void configAddChildrenQuestionChoix() {
        selected.setSpacing(PADDING);
        selected.setPadding(new Insets(PADDING, 0, PADDING, 0));
        selected.getChildren().addAll(lbNbQuest, listQuestChoix, lbPointsQuest);
    }

    private void configRoot() {
        root.setPadding(new Insets(30)); // changer valeur par une constante
        root.getChildren().addAll(list, viewer, selected);
    }

}

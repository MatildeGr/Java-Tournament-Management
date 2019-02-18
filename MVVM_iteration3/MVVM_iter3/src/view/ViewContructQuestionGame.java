package view;

import java.net.URL;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Question;
import mvvm.ViewModelConstructQuest;

/**
 *
 * @author 3009rerys
 */
public class ViewContructQuestionGame {

    private final ViewModelConstructQuest viewModel;
    private final Stage stage;

    private static final int WIDTHLIST = 300, HEIGHTLIST = 800, SPACING60 = 60, SPACING80 = 80, SPACINGBUTTON = 10, PADDING = 20;
    private final int maxPoints;

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

    private final ListView<Question> listQuest = new ListView();
    private final ListView<Question> listQuestChoix = new ListView();

    private final RadioButton rq1 = new RadioButton();
    private final RadioButton rq2 = new RadioButton();
    private final RadioButton rq3 = new RadioButton();
    private final RadioButton rq4 = new RadioButton();
    private final ToggleGroup group = new ToggleGroup();

    private final IntegerProperty nbQuestChoix = new SimpleIntegerProperty();
    private final IntegerProperty nbPoints = new SimpleIntegerProperty();
    private final IntegerProperty nbPointsDispo = new SimpleIntegerProperty();
    private final IntegerProperty nbPointsQuestSelect = new SimpleIntegerProperty();
    private final IntegerProperty numReponse = new SimpleIntegerProperty();

    private final StringProperty quest = new SimpleStringProperty();
    private final StringProperty reponse1 = new SimpleStringProperty();
    private final StringProperty reponse2 = new SimpleStringProperty();
    private final StringProperty reponse3 = new SimpleStringProperty();
    private final StringProperty reponse4 = new SimpleStringProperty();

    private final BooleanProperty close = new SimpleBooleanProperty();

    public ViewContructQuestionGame(Stage stage, ViewModelConstructQuest viewModel) {
        this.viewModel = viewModel;
        maxPoints = viewModel.getMaxPoints();
        this.stage = stage;
        initView();
        config();
        Scene scene = new Scene(root, 900, 600);
        final URL buttonCSSURL = getClass().getResource("style.css");
        scene.getStylesheets().add(buttonCSSURL.toExternalForm());
        stage.setTitle("Questions Game");
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
        lbMatch.setText("Match : " + viewModel.getPlayer1() + " - " + viewModel.getPlayer2());
        lbMatch.setAlignment(Pos.CENTER);
    }

    private void configListViewQuestion() {
        listQuest.setPrefWidth(WIDTHLIST);
        listQuest.setPrefHeight(HEIGHTLIST);
    }

    private void configLabelBottomQuestion() {
        lbPointsDisp.setPrefWidth(WIDTHLIST);
        lbPointsDisp.setText("Points disponibles: " + nbPointsDispo.getValue());
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
        lbquestion.setWrapText(true);
        lbquestion.setMaxWidth(200);
        lbquestion.setAlignment(Pos.CENTER);
        lbquestion.setId("quest");
        lbquestion.setTextAlignment(TextAlignment.CENTER);
    }

    private void configLabelPoints() {
        points.setText("Points");
    }

    private void configAddVBoxQuestionPoints() {
        question.setSpacing(SPACING60);
        question.setPadding(new Insets(20, 0, 0, 0));
        question.setAlignment(Pos.CENTER);
        question.getChildren().addAll(lbquestion, points);
    }

    private void configLabelReponse() {
        lbReponse.setText("Réponses");
    }

    private void configRadioButton() {
        rq1.setToggleGroup(group);
        rq1.setText("reponse 1");
        rq1.setDisable(true);
        rq2.setToggleGroup(group);
        rq2.setText("reponse 2");
        rq2.setDisable(true);
        rq3.setToggleGroup(group);
        rq3.setText("reponse 3");
        rq3.setDisable(true);
        rq4.setToggleGroup(group);
        rq4.setText("reponse 4");
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
        lbNbQuest.setText("Nombre de questions: " + nbQuestChoix.get());
        lbNbQuest.setAlignment(Pos.CENTER);
    }

    private void configListViewQuestionChoix() {
        listQuestChoix.setPrefWidth(WIDTHLIST);
        listQuestChoix.setPrefHeight(HEIGHTLIST);
    }

    private void configLabelBottomQuestChoix() {
        lbPointsQuest.setPrefWidth(WIDTHLIST);
        lbPointsQuest.setText("Points questionnaire: 0/" + maxPoints);
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
        bindQuestSelected();
        bindPointsListView();
    }

    //binding listView
    private void bindingListsViews() {
        listQuest.itemsProperty().bind(viewModel.questionsProperty());
        listQuestChoix.itemsProperty().bind(viewModel.questionsChoixProperty());
    }

    //binding to property
    private void bindToProperty() {
        viewModel.bindNumQuestSlectedPropTo(getListQuestions().selectedIndexProperty());
        viewModel.bindNumQuestChoixSlectedPropTo(getListQuestionsChoix().selectedIndexProperty());
    }

    //binding question selectionné
    private void bindQuestSelected() {
        quest.bind(viewModel.nameQuestSelected());
        nbPoints.bind(viewModel.nbPointProperty());
        reponse1.bind(viewModel.reponse1SelectedProperty());
        reponse2.bind(viewModel.reponse2SelectedProperty());
        reponse3.bind(viewModel.reponse3SelectedProperty());
        reponse4.bind(viewModel.reponse4SelectedProperty());
        numReponse.bind(viewModel.numReponseProperty());
    }

    //binding points des listes
    private void bindPointsListView() {
        close.bind(viewModel.closeProperty());
        nbPointsDispo.bind(viewModel.nbPointsDispoProperty());
        nbPointsQuestSelect.bind(viewModel.nbPointsQuestSelectProperty());
        nbQuestChoix.bind(viewModel.nbQuestChoix());
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
    //retourne la liste du listView question
    private SelectionModel<Question> getListQuestions() {
        return listQuest.getSelectionModel();
    }

    //retourne la liste du listView question choisit
    private SelectionModel<Question> getListQuestionsChoix() {
        return listQuestChoix.getSelectionModel();
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
        configListenerNbQuestChoix();
        configLIstenerNomQuest();
        configLIstenerNbPoints();
        configLIstenerNbPointsDispo();
        configListenernbPointsQuestSelect();
        configListenerReponse1();
        configListenerReponse2();
        configListenerReponse3();
        configListenerReponse4();
        configListenerNumReponse();
        closeWindows();

    }

    //listener nombre de question dans la liste de questions choisi
    public void configListenerNbQuestChoix() {
        nbQuestChoix.addListener((observable, oldValue, newValue) -> {
            lbNbQuest.setText("Nombre de questions: " + newValue);
        });
    }

    //listener nom de la question choisi
    public void configLIstenerNomQuest() {
        quest.addListener((observable, oldValue, newValue) -> {
            lbquestion.setText(newValue);
        });
    }

    //listener nombre de points de la question choisi
    public void configLIstenerNbPoints() {
        nbPoints.addListener((observable, oldValue, newValue) -> {
            points.setText(newValue.toString() + " points");
        });
    }

    //listener reponse 1 de la question choisi
    public void configListenerReponse1() {
        reponse1.addListener((observable, oldValue, newValue) -> {
            rq1.setText(newValue);
        });
    }

    //listener reponse 2 de la question choisi
    public void configListenerReponse2() {
        reponse2.addListener((observable, oldValue, newValue) -> {
            rq2.setText(newValue);
        });
    }

    //listener reponse 3 de la question choisi
    public void configListenerReponse3() {
        reponse3.addListener((observable, oldValue, newValue) -> {
            rq3.setText(newValue);
        });
    }

    //listener reponse 4 de la question choisi
    public void configListenerReponse4() {
        reponse4.addListener((observable, oldValue, newValue) -> {
            rq4.setText(newValue);
        });
    }

    //listener de la reponse correcte de la question choisi
    public void configListenerNumReponse() {
        numReponse.addListener((observable, oldValue, newValue) -> {
            disableRadioButton();
            unselectRadioButton();
            switch (newValue.intValue()) {
                case 1:
                    rq1.setDisable(false);
                    rq1.setSelected(true);
                    break;
                case 2:
                    rq2.setDisable(false);
                    rq2.setSelected(true);
                    break;
                case 3:
                    rq3.setDisable(false);
                    rq3.setSelected(true);
                    break;
                case 4:
                    rq4.setDisable(false);
                    rq4.setSelected(true);
                    break;
                default:
                    break;
            }
        });
    }

    //listener nombre de points disponible dans la liste des questions
    public void configLIstenerNbPointsDispo() {
        nbPointsDispo.addListener((observable, oldValue, newValue) -> {
            lbPointsDisp.setText("Points disponibles: " + newValue);
        });
    }

    //listener du nombre de points dans la liste des questions choisi
    public void configListenernbPointsQuestSelect() {
        nbPointsQuestSelect.addListener((observable, oldValue, newValue) -> {
            lbPointsQuest.setText("Points questionnaire: " + newValue + "/" + maxPoints);
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
        setOnActionRemoveQuest();
        cancelOnActionAddQuest();
        setOnActionAddQuest();
    }

    //ajoute une question dans la liste des questions selectionnées et la supprime de la liste des questions
    private void setOnActionAddQuest() {
        addQuest.setOnAction(e -> {
            viewModel.addQuest();
        });

    }

    //retire une question dans la liste des questions choix selectionnées et l'ajoute dans la liste de question
    private void setOnActionRemoveQuest() {
        removeQuest.setOnAction(e -> {
            viewModel.removeQuest();
        });
    }

    //ferme la scene
    private void cancelOnActionAddQuest() {
        cancel.setOnAction(e -> {
            viewModel.cancel();
            stage.close();
        });
        validate.setOnAction(e -> viewModel.endGame());

        stage.setOnCloseRequest(e -> viewModel.cancel());

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
    //desactive les reponses 
    private void disableRadioButton() {
        rq1.setDisable(true);
        rq2.setDisable(true);
        rq3.setDisable(true);
        rq4.setDisable(true);

    }

    //deselectionne les reponses
    private void unselectRadioButton() {
        rq1.setSelected(false);
        rq2.setSelected(false);
        rq3.setSelected(false);
        rq4.setSelected(false);
    }
}

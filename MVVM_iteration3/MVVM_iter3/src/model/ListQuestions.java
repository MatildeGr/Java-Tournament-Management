package model;

import javafx.collections.ObservableList;
import static model.BuilderElemToQuest.questions;

/**
 *
 * @author 3009rerys
 */
public class ListQuestions {
    private final ObservableList<Question> questions;
    
    public ListQuestions(){
        questions = questions();
    }
    
    public ObservableList<Question> getQuestions(){
        return questions;
    }
    
}

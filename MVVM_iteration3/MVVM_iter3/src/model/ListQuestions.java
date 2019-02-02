package model;

import java.util.List;
import static model.BuilderElemToQuest.questions;

/**
 *
 * @author 3009rerys
 */
public class ListQuestions {
    private final List<Question> questions;
    
    public ListQuestions(){
        questions = questions();
    }
    
    public List<Question> getQuestions(){
        return questions;
    }
    
}

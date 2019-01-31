package model;
import java.util.List;
import element.*;
import java.util.ArrayList;
/**
 *
 * @author 3009rerys
 */
public class BuilderElemToQuest {
    private static List<Elem> loadQuestions(){
        return Elements.loadElemsFromFile("Questions.json");
    } 
    
    public static List<Question> questions(){
        List<Elem> lsElem = loadQuestions();
        List<Question> result = new ArrayList();
                lsElem.forEach((e) -> {
            result.add(new Question(e.name,e.points,e.responses,e.numCorrectResponse));
        });
        return result;
    }
    
}

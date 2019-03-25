package model;
import java.util.List;
import elementSub.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author 3009rerys
 */
public class BuilderElemToQuest {
    private static List<ElemWithSubElems> loadQuestions(){
        return ElemsWithSub.loadElemsFromFile("Questions.json");
    } 
    
    public static ObservableList<Question> questions(){
        List<ElemWithSubElems> lsElem = loadQuestions();
        ObservableList<Question> result = FXCollections.observableArrayList();
                lsElem.forEach((e) -> {
                    List<Reponse> reponses = new ArrayList();
                    e.responses.forEach((s) -> {
                        reponses.add(new Reponse(s));
            });
            //result.add(new Question(e.name,e.points,reponses,e.numCorrectResponse));
        });
        return result;
    }
    
}

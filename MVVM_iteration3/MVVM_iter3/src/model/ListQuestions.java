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
    
    //retourne la liste des questions
    public ObservableList<Question> getQuestions(){
        return questions;
    }
    
    //retourne une question à une position donnée
    public Question get(int index){
        return questions.get(index);
    }
    
    //ajoute une question 
    //retourne vrai si ajouté
    public boolean add(Question q){
        return questions.add(q);
    }
    
    //enleve une question
    //retourne vrai si enlevé
    public boolean remove(Question q){
        return questions.remove(q);
    }
    
    //retourne le nombre de points de la liste
    public int getPoints(){
        int res = 0;
        res = questions.stream().map((q) -> q.getPoints()).reduce(res, Integer::sum);
        return res;
    }
    
}

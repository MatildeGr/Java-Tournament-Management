package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author remy
 */
public class ConstructGame {
    
    private final ListQuestions lsQuestions = new ListQuestions();
    private final ObservableList<Question> lsQuestionsChoix = FXCollections.observableArrayList();
    
    
    public ConstructGame(){}
    
    
    //retourne une question de la liste des questions, à une position donnée 
    public Question getQuestionList(int index){
        return lsQuestions.get(index);
    }
    
    //retourne une question de la liste des questions choisis, à une position donnée
    public Question getQuestionChoix(int index){
        return lsQuestionsChoix.get(index);
    }
    
    //retourne une observable liste de la liste des questions
    public ObservableList<Question> getListQuestions(){
        return lsQuestions.getQuestions();
    }
    
    //retourn une observable liste des questions chosis
    public ObservableList<Question> getListQuestionsChoix(){
        return lsQuestionsChoix;
    }
    
    
    //ajout une question de la liste des questions à la liste des questions choisis
    //et supprime la question choisit de la liste des questions
    public boolean add(Question q){
        return lsQuestionsChoix.add(q) && lsQuestions.remove(q);        
    }
    
    //enleve une question de la liste des questions choisit
    //et l'ajoute dans la liste des questions
    public boolean remove(Question q){
        return lsQuestions.add(q) && lsQuestionsChoix.remove(q);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

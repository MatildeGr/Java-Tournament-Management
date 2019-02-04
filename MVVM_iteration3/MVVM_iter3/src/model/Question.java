package model;

import java.util.List;

/**
 *
 * @author 3009rerys
 */
public class Question {

    private final String nom;
    private final int points;   //point de la question
    private final List<Reponse> reponses; //4 reponses possible
    private final int numCorrectReponse;

    public Question(String nom, int points, List<Reponse> reponses, int numRep) {
        this.nom = nom;
        this.points = points;
        this.reponses = reponses;
        this.numCorrectReponse = numRep;
    }
    
    public String getNom(){
        return nom;
    }
    
    public int getPoints(){
        return points;
    }
    
    public List<Reponse> getReponses(){
        return reponses;
    }
    
    public int getNumRep(){
        return numCorrectReponse;
    }
    
    @Override
    public String toString(){
        return nom;
    }
}

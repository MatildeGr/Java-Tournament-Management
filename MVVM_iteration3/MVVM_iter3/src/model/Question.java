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
    
    //retourne le nom d'une reponse
    public String getNom(){
        return nom;
    }
    
    //retourne les points d'une reponse
    public int getPoints(){
        return points;
    }
    
    //retourne la liste des reponses d'une question
    public List<Reponse> getReponses(){
        return reponses;
    }
    
    //retourne une reponse d'une liste de reponses d'une question
    public Reponse getReponse(int index){
        return reponses.get(index);
    }
    
    //retourn une reponse d'une liste de reponses d'une question sous forme de string
    public String getReponseToString(int index){
        return reponses.get(index).getReponse();
    }
    
    //retourne le numero de la reponse juste
    public int getNumRep(){
        return numCorrectReponse;
    }
    
    @Override
    public String toString(){
        return nom;
    }
}

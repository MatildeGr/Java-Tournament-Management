package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3009rerys
 */
public class Question extends Composant {

    private final int points;
    private final List<Reponse> reponses;
    private final int numCorrectReponse;
    private final Composant category;
    private final String hint;
    private final boolean isFake;

    public Question(String nom, int points, List<Reponse> reponses, int numRep, Composant category,
            String hint, boolean isfake) {
        super(nom);
        this.points = points;
        this.reponses = reponses;
        this.numCorrectReponse = numRep;
        this.category = category;
        this.hint = hint;
        this.isFake = isfake;

    }
    
    public Question(Question quest){
        this(quest.getNom(),quest.points,quest.reponses,quest.numCorrectReponse,quest.category,quest.hint,quest.isFake);
    }

    //retourne le nom d'une reponse
    public String getNom() {
        return getName();
    }

    //retourne les points d'une reponse
    public int getPoints() {
        return points;
    }

    //retourne la liste des reponses d'une question
    public List<Reponse> getReponses() {
        return reponses;
    }

    //retourne une reponse d'une liste de reponses d'une question
    public Reponse getReponse(int index) {
        return reponses.get(index);
    }

    //retourn une reponse d'une liste de reponses d'une question sous forme de string
    public String getReponseToString(int index) {
        return reponses.get(index).getReponse();
    }

    //retourne le numero de la reponse juste
    public int getNumRep() {
        return numCorrectReponse;
    }

    //Renvoie vrai si l'indice est un faux.
    public boolean getIsFake() {
        return isFake;
    }

    //ajoute la question de sa category
    public void add() {
        ((Composite) category).add(this);
    }

    //enleve la question a sa category
    public void remove() {
        ((Composite) category).remove(this);
    }

    //retourne la categorie d'une question
    public Composant getCategory() {
        return category;
    }

    //renvoie le hint d'une question
    public String getHint() {
        return hint;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> res = new ArrayList();
        res.add(this);
        return res;
    }

}

package model;

import javafx.collections.ObservableList;
import model.Match.Result;

/**
 *
 * @author remy
 */
public class Game {

    private final Player p1;
    private final Player p2;
    private final ObservableList<Question> ls;
    private boolean canceled = false;
    private Result res;
    private Question currentQuestion;
    protected int numCurrentQuest;
    protected int numRepDonner;
    private ConstructGame constructGame;

    public Game(ConstructGame game) {
        this.p1 = game.getPlayer1();
        this.p2 = game.getPlayer2();
        this.ls = game.getListQuestionsChoix();
    }

    public ConstructGame getConstrucGame() {
        return constructGame;
    }

    //retourne le joueur 1
    public Player getPlayer1() {
        return p1;
    }

    //retourne le joueur 2
    public Player getPlayer2() {
        return p2;
    }

    //retourne la liste des questions
    public ObservableList<Question> getListQuest() {
        return ls;
    }

    //retourne une question a une position donnéé
    public Question getQuestion(int index) {
        currentQuestion = ls.get(index);
        numCurrentQuest = index;
        return currentQuestion;
    }

    //retourne le nombre de points maximum de la liste des questions
    public int getMaxPoint() {
        int res = 0;
        for (Question q : ls) {
            res += q.getPoints();
        }
        return res;
    }

    //retourne le nombre de questions dans la liste 
    public int getNbQuest() {
        return ls.size();
    }

    //change l'état de la partie
    public void cancel() {
        canceled = true;
    }

    //retourne si la partie est annulée
    public boolean canceled() {
        return canceled;
    }

    public String getHint(int index) {
        return ls.get(index).getHint();
    }

    public int leftPoint(int pos) {
        int res = 0;
        for (int i = pos; i < ls.size(); i++) {
            res += ls.get(i).getPoints();
        }
        return res;
    }

    //set le res
    public void result(Result r) {
        res = r;
    }

    public Result result() {
        return res;
    }

    //renvoie la question courante
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    //set la question courante
    public void setNumCurrentQuestion(int numQuest) {
        numCurrentQuest = numQuest;
    }

    //renvoie le numéro de la question courante
    public int getNumCurrentQuest() {
        return numCurrentQuest;
    }

    //set le numéro de la réponse donnée.
    public void setNumRepDonner(int numCurrentRep) {
        numRepDonner = numCurrentRep;
    }

    //renvoie le numéro de la réponse donnée.
    public int getNumRepDonner() {
        return numRepDonner;
    }

}

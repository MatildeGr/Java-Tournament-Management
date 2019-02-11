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

    public Game(ConstructGame game) {
        this.p1 = game.getPlayer1();
        this.p2 = game.getPlayer2();
        this.ls = game.getListQuestionsChoix();
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
        return ls.get(index);
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
    
    //set le res
    public void result(Result r){
        res = r;
    }
    public Result result(){
        return res;
    }

}

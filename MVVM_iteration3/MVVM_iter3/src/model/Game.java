package model;

import javafx.collections.ObservableList;

/**
 *
 * @author remy
 */
public class Game {

    private final Player p1;
    private final Player p2;
    private final ObservableList<Question> ls;

    public Game(Player p1, Player p2, ObservableList<Question> ls) {
        this.p1 = p1;
        this.p2 = p2;
        this.ls = ls;
    }
    
    //retourne le joueur 1
    public Player getPlayer1(){
        return p1;
    }

    //retourne le joueur 2
    public Player getPlayer2(){
        return p2;
    }
    
    //retourne la liste des questions
    public ObservableList<Question> getListQuest(){
        return ls;
    }
    
    //retourne une question a une position donnéé
    public Question getQuestion(int index){
        return ls.get(index);
    }
    
    //retourne le nombre de points maximum de la liste des questions
    public int getMaxPoint(){
        int res = 0;
        for(Question q: ls){
            res += q.getPoints();
        }
        return res;
    }
    
    //retourne le nombre de questions dans la liste 
    public int getNbQuest(){
        return ls.size();
    }
            
        
}

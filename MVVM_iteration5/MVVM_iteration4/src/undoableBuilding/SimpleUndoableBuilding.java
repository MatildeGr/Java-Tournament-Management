/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoableBuilding;

import java.util.LinkedList;
import java.util.Queue;
import model.ConstructGame;
import model.Question;

/**
 *
 * @author 1501magravano
 */
public class SimpleUndoableBuilding extends UndoableBuilding {

    class State {

        private final Question quest;

        public State(Question quest) {
            // Il faut faire une copie de la date reçue
            // et pas simplement prendre une référence
            this.quest = new Question(quest);
        }

    }

    private final Queue<State> states = new LinkedList<>();

    public SimpleUndoableBuilding(ConstructGame game) {
        super(game);
    }

//    @Override
//    public void readQuestion(Question quest) {
//        states.offer(new State(quest));
//        super.readQuestion(quest);
//    }
    @Override
    public void undo() {
        System.out.println("Retour en arrière depuis Simple :");
        if (!states.isEmpty()) {
            State state = states.poll();
            setCurrentQuestion(state.quest);
        }
    }

}

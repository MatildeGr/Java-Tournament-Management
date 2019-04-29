/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoableBuilding;

import memento.CareTaker;
import memento.Memento;
import model.ConstructGame;
import model.Question;

/**
 *
 * @author Matilde
 */
public class GameMemento extends UndoableBuilding {

    private final CareTaker careTaker;

    public GameMemento(ConstructGame game) {
        super(game);
        careTaker = new CareTaker();
    }

    public void addQuestion(Question question){
        careTaker.gardeMemento(createMemento());//ajoute au queue
        super.setCurrentQuestion(question);//set dans le model.
    }
    
    @Override
    public void undo() {
        System.out.println("Retour en arrière :");
        setMemento(careTaker.getMemento());
    }

    private Memento createMemento() {
        return new MementoImpl(getCurrentQuestion());
    }

    private void setMemento(Memento m) {
        MementoImpl memento = (MementoImpl) m;
        setCurrentQuestion(memento.getQuestion());
    }

    private class MementoImpl implements Memento {

        private final Question quest;

        private MementoImpl(Question quest) {
            this.quest = new Question(quest);
        }

        private Question getQuestion() {
            return getCurrentQuestion();
        }
    }
}

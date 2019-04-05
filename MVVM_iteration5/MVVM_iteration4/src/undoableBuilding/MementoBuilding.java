/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoableBuilding;

import memento.CareTaker;
import memento.Memento;
import model.Question;

/**
 *
 * @author Matilde
 */
public class MementoBuilding extends UndoableBuilding {

    private final CareTaker careTaker;

    public MementoBuilding(Question quest) {
        super(quest);
        careTaker = new CareTaker(createMemento());
    }

    @Override
    public void undo() {
       // System.out.println("Retour en arrière :");
        setMemento(careTaker.getMemento());
    }

    private Memento createMemento() {
        return new MementoImpl(question);
    }

    private void setMemento(Memento m) {
        MementoImpl memento = (MementoImpl) m;
        question = memento.getQuestion();
    }

    private static class MementoImpl implements Memento {

        private final Question quest;

        private MementoImpl(Question quest) {
            this.quest = new Question(quest);
        }

        private Question getQuestion() {
            return question;
        }
    }
}

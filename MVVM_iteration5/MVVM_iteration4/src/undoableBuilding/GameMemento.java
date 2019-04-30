/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoableBuilding;

import memento.CareTaker;
import memento.Memento;
import model.ConstructGame;

/**
 *
 * @author Matilde
 */
public class GameMemento extends UndoableBuilding {

    private final CareTaker careTaker;

    public GameMemento(ConstructGame game, int numQuest) {
        super(game);
        careTaker = new CareTaker();
    }

    public void addQuestion(int numQuest) {
        careTaker.gardeMemento(createMemento());
        super.setNumCurrentQuestion(numQuest);//set dans le model.
    }

    @Override
    public void undo() {
        //System.out.println("Retour en arrière :");
        setMemento(careTaker.getMemento());

    }

    private Memento createMemento() {
        return new MementoImpl(getNumCurrentQuest());
    }

    private void setMemento(Memento m) {
        MementoImpl memento = (MementoImpl) m;
        setNumCurrentQuestion(memento.getNumQuest());

    }

    private class MementoImpl implements Memento {

        //private final Question quest;
        private final int numQuest;
        //private final int reponsedonner;

        private MementoImpl(int numQuest) {
            this.numQuest = numQuest;
            //this.reponsedonner = reponse;
        }

        private int getNumQuest() {
            return numQuest;
        }

//        private int getReponse(){
//            return reponsedonner;
//        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Matilde
 */
public class CareTaker {

    private final Queue<Memento> mementos = new LinkedList<>();

    public CareTaker() {

    }

    public CareTaker(Memento memento) {
        mementos.add(memento);
    }
    
    public void gardeMemento(Memento mem) {
        mementos.add(mem);
    }

    public boolean isEmptyMemento() {
        return mementos.isEmpty();
    }

    public Memento getMemento() {
        return mementos.poll();
    }

}

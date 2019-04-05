/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Matilde
 */
public class CareTaker {
    private final Queue<Memento> mementos = new ConcurrentLinkedQueue<>();

    public CareTaker(Memento memento) {
        mementos.add(memento);
    }

    public void gardeMemento(Memento mem) {
        mementos.add(mem);
    }

    public Memento getMemento() {
        if(mementos.size() > 1)
            return mementos.poll();
        return mementos.peek();
    }

}

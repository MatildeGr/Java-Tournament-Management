package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class Inscrit extends Observable {

    private static final int MAX_WORD_LENGTH = 10;
    private final List<Inscrit> lines = new ArrayList();
    private int numLineSelected = -1;

    private String name;

    public Inscrit() {
        initData();
    }

    public Inscrit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Inscrit> getList() {
        return this.lines;
    }

    public int getNumLineSelected() {
        return this.numLineSelected;
    }

    private boolean unicity(Inscrit inscrit) {
        return lines.stream().noneMatch((i) -> (i.equals(inscrit)));
    }

    public boolean addLine(Inscrit inscrit) {
        if (inscrit.getName().length() <= MAX_WORD_LENGTH && unicity(inscrit)) {
            lines.add(inscrit);
            numLineSelected = lines.size() - 1;
            notif(TypeNotif.LINE_ADDED);
            return true;
        }
        return false;
    }

    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Inscrit) {
            Inscrit i = (Inscrit) o;
            return (i.name.equals(this.name));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    private void initData() {
        addLine(new Inscrit("Inscrit 1"));
        addLine(new Inscrit("Inscrit 2"));
        addLine(new Inscrit("Inscrit 3"));
    }

}

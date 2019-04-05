package undoableBuilding;

import building.Building;
import model.Question;

public abstract class UndoableBuilding extends Building {

    public UndoableBuilding(Question quest) {
        super(quest);
    }
    
    public abstract void undo();

}

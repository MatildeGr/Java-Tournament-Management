package undoableBuilding;

import model.ConstructGame;
import model.Game;

public abstract class UndoableBuilding extends Game {

    public UndoableBuilding(ConstructGame game) {
        super(game);
    }
    
    public abstract void undo();

}

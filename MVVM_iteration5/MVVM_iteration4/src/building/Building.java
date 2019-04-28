package building;

import model.Question;

/**
 *
 * @author 1501magravano
 */
public class Building {

    protected static Question question;

    public Building(Question quest) {
        _setQuest(quest);
    }

    private void _setQuest(Question question) {
        this.question = question;
    }
    
    public void readQuestion(Question question){
        
    }

}

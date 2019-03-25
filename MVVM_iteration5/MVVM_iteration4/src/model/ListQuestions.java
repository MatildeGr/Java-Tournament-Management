package model;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static model.CategoriesQuestions.createCategoriesQuestions;

/**
 *
 * @author 3009rerys
 */
public class ListQuestions {

    private final Map<String, Composite> map;
    private final ObservableList<Question> questions = FXCollections.observableArrayList();
    private static final ObservableList<String> CATEGORIES =FXCollections.observableArrayList("All categories","Sciences"
            , "Biologie","Zoologie","Histoire","Histoire France","Histoire Belgique","Histoire Sciences"
                    , "Culture","Inventions","Chanson","Cinéma","Lecture","Littérature"
                            , "Géographie","Géographie Belgique","Géographie France");

    public ListQuestions() {
        map = createCategoriesQuestions();
    }

    //retourne la liste des questions
    public ObservableList<Question> getQuestions() {
        questions.clear();
        questions.addAll(map.get("All categories").getQuestions());
        return questions;
    }

    //retourne la liste des questions d'une category
    public ObservableList<Question> getQuestions(String category) {
        questions.clear();
        questions.addAll(map.get(category).getQuestions());
        return questions;
    }

    //retourne une question à une position donnée
    public Question get(int index) {
        return questions.get(index);
    }

    //ajoute une question 
    //retourne vrai si ajouté
    public boolean add(String category,Question q) {
        q.add();
        if(map.get(category).containCategory(q.getCategory())){
            return questions.add(q);
        }
        return true;
    }

    //enleve une question
    //retourne vrai si enlevé
    public boolean remove(Question q) {
        q.remove();
        return questions.remove(q);
    }

    //retourne le nombre de points de la liste
    public int getPoints() {
        int res = 0;
        res = questions.stream().map((q) -> q.getPoints()).reduce(res, Integer::sum);
        return res;
    }

    //retourne une liste des categories
    public ObservableList<String> getCategories() {
        return CATEGORIES;
    }
    
}

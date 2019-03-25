/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import element.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author remy
 */
public final class CategoriesQuestions {


    public static Map<String, Composite> createCategoriesQuestions() {
        Map<String, Composite> map = new HashMap<>();
        Composite c = new Composite("All categories");
        map.put(c.getName(), c);

        for (Elem e : Elements.loadElemsFromFile("Questions.json")) {
            if (e.subElems != null) {
                Composite comp = new Composite(e.name);
                map.put(e.name, comp);
                c.add(comp);
                categoryQuestion(comp, e,map);
            }
        }
        return map;
    }

    public static void categoryQuestion(Composite c, Elem subElem,Map<String, Composite> map) {

        for (Elem e : subElem.subElems) {
            if (e.subElems != null) {
                Composite comp = new Composite(e.name);
                map.put(e.name, comp);
                c.add(comp);
                categoryQuestion(comp, e,map);
            } else {
                List<Reponse> ls = new ArrayList();
                for (String s : e.responses) {
                    ls.add(new Reponse(s));
                }
                c.add(new Question(e.name, e.points, ls, e.numCorrectResponse,c,e.hint,e.fakeHint));
            }
        }
    }

}

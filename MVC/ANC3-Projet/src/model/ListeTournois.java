package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import model.Match.Resultats;

public class ListeTournois extends Observable {

    private int numLineSelected = -1;
    private final List<Tournoi> lsTournois = new ArrayList();

    /**
     *
     * TOURNAMENTS LIST FUNCTIONS
     *
     */
    public ListeTournois() {
        initData();
    }

    public List<Tournoi> getLines() {
        return lsTournois;
    }

    //Fonction qui renvoie le nombre de ligne de la liste de tournois.
    public int nbLines() {
        return lsTournois.size();
    }

    //Fonction renvoie le numéro de la ligne sélectionnée.
    public int getNumLineSelected() {
        return this.numLineSelected;
    }

    //Fonction qui modifie le numéro de la ligne sélectionnée et qui change le type de notif. 
    public void select(int index) {
        numLineSelected = index;
        notif(TypeNotif.LINE_TOURNOI_SELECTED);
    }

    public void unselectTournoi() {
        numLineSelected = -1;
        notif(TypeNotif.TOURNOI_UNSELECTED);
    }

    //Fonction qui change le type de notif. 
    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }

    /**
     *
     * PLAYER LIST FUNCTIONS
     *
     */
    public List<Joueur> getAllInscrit(int numLineSelected) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).getAllInscrit();
        }
        return null;
    }

    public int joueurSize() {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).joueurSize();
        }
        return -1;
    }

    public void selectJoueurs(int joueur) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            lsTournois.get(numLineSelected).selectJoueur(joueur);
            notif(TypeNotif.CB1_SELECTED);
        }

    }
    public void selectJoueur2(int joueur) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            lsTournois.get(numLineSelected).selectJoueur2(joueur);
            notif(TypeNotif.CB2_SELECTED);
        }

    }



    /**
     *
     * MATCH LIST FUNCTIONS
     *
     */
    public List<Match> getAllMatch(int nulLineSelected) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).getAllMatch();
        }
        return null;
    }

    public int matchSize() {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).matchSize();
        }
        return -1;
    }

    public List<Joueur> getAdversaire() {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).adversaire();
        }
        return null;
    }
    
    public List<Joueur> getAdversaire2() {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).adversaire2();
        }
        return null;
    }

    public boolean addMatch(Joueur j1, Joueur j2, Match.Resultats r) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            if (lsTournois.get(numLineSelected).addMatch(j1, j2, r)) {
                notif(TypeNotif.ADD_MATCH);
                return true;
            }

        }
        return false;
    }

    public boolean updMatch(Joueur j1, Joueur j2, Resultats r) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            if (lsTournois.get(numLineSelected).updMatch(j1, j2, r)) {
                notif(TypeNotif.LINE_UPDATED);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMatch(Match m) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            if (lsTournois.get(numLineSelected).deleteMatch(m)) {
                notif(TypeNotif.DELETE_MATCH);
                return true;
            }
        }
        return false;
    }

    public void selectMatch(int index) {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            lsTournois.get(numLineSelected).selectMatch(index);
            notif(TypeNotif.LINE_MATCH_SELECTED);
        }
    }

    public Match getSelectedMatch() {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            return lsTournois.get(numLineSelected).getSelectedMatch();
        }
        return null;
    }

    public void unselectMatch() {
        if (numLineSelected >= 0 && numLineSelected < lsTournois.size()) {
            lsTournois.get(numLineSelected).unselectMatch();
            notif(TypeNotif.MATCH_UNSELECTED);
        }
    }
    public List<Joueur> advJoueurSelect(){
        return lsTournois.get(numLineSelected).advJoueurSelect();
    }

    //Fonction qui initialise les données de la liste de tournois. 
    private void initData() {
        String nom[] = {"Gabriel", "Louise", "Jules", "Emma", "Lucas", "Jade", "Louis", "Chloé", "Adam",
            "Manon", "Hugo", "Alice", "Léo", "Lina", "Raphaël", "Léa"};

        lsTournois.add(new Tournoi("Tournoi 1"));
        for (int i = 0; i < 10; i++) {
            lsTournois.get(0).addJoueur(nom[i]);
        }
        for (int i = 0; i < 10; i += 2) {
            lsTournois.get(0).addMatch(new Joueur(nom[i]), new Joueur(nom[i + 1]), Resultats.MATCH_NULL);
        }
        lsTournois.add(new Tournoi("Tournoi 2"));
        for (int i = 8; i < 16; i++) {
            lsTournois.get(1).addJoueur(nom[i]);
        }
    }

}

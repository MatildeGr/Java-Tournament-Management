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

    public List<Tournoi> getTournament() {
        return this.lsTournois;
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
        return lsTournois.get(numLineSelected).getAllInscrit();
    }

    public int joueurSize() {
        return lsTournois.get(numLineSelected).joueurSize();
    }

    public void selectJoueurs(int joueur) {
        lsTournois.get(numLineSelected).selectJoueur(joueur);
        notif(TypeNotif.CB1_SELECTED);
    }

    public void selectJoueur2() {
        notif(TypeNotif.CB2_SELECTED);
    }

    /**
     *
     * MATCH LIST FUNCTIONS
     *
     */
    public List<Match> getAllMatch(int nulLineSelected) {
        return lsTournois.get(numLineSelected).getAllMatch();
    }

    public int matchSize() {
        return lsTournois.get(numLineSelected).matchSize();
    }

    public List<Joueur> getAdversaire() {
        return lsTournois.get(numLineSelected).adversaire();
    }

    public void addMatch(Joueur j1, Joueur j2, Match.Resultats r) {
        lsTournois.get(numLineSelected).addMatch(j1, j2, r);
        notif(TypeNotif.ADD_MATCH);
    }
    public void updMatch(Joueur j1, Joueur j2, Resultats r) {
        lsTournois.get(numLineSelected).updMatch(j1,j2,r);
        notif(TypeNotif.LINE_UPDATED);   
    }

    public void deleteMatch(Match m) {
        lsTournois.get(numLineSelected).deleteMatch(m);
        notif(TypeNotif.DELETE_MATCH);
    }
    public void selectMatch(int index){
        lsTournois.get(numLineSelected).selectMatch(index);
        notif(TypeNotif.LINE_MATCH_SELECTED);
    }
    
      public Match getSelectedMatch(){
       return lsTournois.get(numLineSelected).getSelectedMatch();
    }
      public void unselectMatch(){
          lsTournois.get(numLineSelected).unselectMatch();
          notif(TypeNotif.MATCH_UNSELECTED);
      }

    
    
    
    
    
    
    
    
    
    
    
    //Fonction qui initialise les données de la liste de tournois. 
    private void initData() {
        lsTournois.add(new Tournoi("Tournoi 1"));
        lsTournois.get(0).addMatch(new Joueur("Remy"), new Joueur("Matilde"), Resultats.GAIN_JOUEUR1);
        lsTournois.get(0).addMatch(new Joueur("Papy Denis"), new Joueur("Remy"), Resultats.GAIN_JOUEUR1);
        lsTournois.get(0).addJoueur("Albert");
        lsTournois.add(new Tournoi("Tournoi 2"));
        lsTournois.add(new Tournoi("Tournoi 3"));
    }

}

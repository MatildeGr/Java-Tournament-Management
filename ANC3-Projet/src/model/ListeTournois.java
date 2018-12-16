package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import static model.Joueur.getListToString;
import model.Match.Resultats;

public class ListeTournois extends Observable {

    private final List<Tournoi> lsTournois = new ArrayList();
    private int numLineSelected = -1;

    //Constructeur de ListeTournois.
    public ListeTournois() {
        initData();
    }

    public List<Tournoi> getLines() {
        return lsTournois;
    }

    //Fonction qui renvoie la liste des joueurs inscrits d'un tournoi à une ligne sélectionnée.
    public List<Joueur> getAllInscrit(int numLineSelected) {
        return lsTournois.get(numLineSelected).getAllInscrit();
    }

    public List<Match> getAllMatch(int nulLineSelected) {
        return lsTournois.get(numLineSelected).getAllMatch();
    }

    //Fonction qui renvoie une liste de String de tous les joueurs d'un tournoi à une ligne sélectionnée.
    public List<String> getAllJoueurToString(int numLineSelected) {
        return getListToString(getAllInscrit(numLineSelected));
    }

    //Getteur qui renvoie la liste de tournoi.
    public List<Tournoi> getTournament() {
        return this.lsTournois;
    }

    //Fonction renvoie le numéro de la ligne sélectionnée.
    public int getNumLineSelected() {
        return this.numLineSelected;
    }

    //Fonction qui renvoie le nombre de ligne de la liste de tournois.
    public int nbLines() {
        return lsTournois.size();
    }
    
    public int matchSize(){
         return lsTournois.get(numLineSelected).matchSize();
    }
    
    public int joueurSize(){
        return lsTournois.get(numLineSelected).joueurSize();
    }
    
    public void selectJoueurs(int joueur){
        lsTournois.get(numLineSelected).selectJoueur(joueur);
        notif(TypeNotif.CB1_SELECTED);
    }
    public void selectJoueur2(){
        notif(TypeNotif.CB2_SELECTED);
    }
    public List<Joueur> getAdversaire(){
        return lsTournois.get(numLineSelected).adversaire();
    }

    //Fonction qui modifie le numéro de la ligne sélectionnée et qui change le type de notif. 
    public void select(int index) {
        numLineSelected = index;
        notif(TypeNotif.LINE_TOURNOI_SELECTED);
    }

    //Fonction qui remet à -1 le numéro de la ligne et qui change le type de notif.
    public void unselect() {
        numLineSelected = -1;
        notif(TypeNotif.LINE_UNSELECTED);
    }

    //Fonction qui change le type de notif. 
    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
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
    public void addMatch(Joueur j1,Joueur j2,Match.Resultats r){
        lsTournois.get(numLineSelected).addMatch(j1,j2,r);
        notif(TypeNotif.ADD_MATCH);
    }
}

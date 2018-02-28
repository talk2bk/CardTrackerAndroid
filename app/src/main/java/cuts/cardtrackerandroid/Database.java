package cuts.cardtrackerandroid;

import java.util.ArrayList;

/**
 * Created by CuTs on 2/22/2018.
 */

public class Database {
    ArrayList<Card> cards;

    public Database(){
        this.cards = new ArrayList<Card>();
        //loadDatabase();
    }

    public void addCard(String scryfallUUID, int numCopies, String originalOwnerName, String currentOwnerName, boolean foil, String client, String date){ //mainly used for the add card screen
        //should check for cards with the same information already
        cards.add(new Card(scryfallUUID, numCopies, originalOwnerName, currentOwnerName, foil, client, date));
    }
    public void addCard(Card cardToAdd){ //addcard given an already made card
        cards.add(cardToAdd);
    }

    public ArrayList<Card> getCards(){
        return this.cards;
    }















    private void saveDatabase() {

    }
    private void loadDatabase() {
    }

    //test methods?
    public void resetDatabase(){
        cards = new ArrayList<Card>();
        saveDatabase();
    }
}

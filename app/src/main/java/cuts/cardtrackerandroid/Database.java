package cuts.cardtrackerandroid;

import java.util.ArrayList;

/**
 * Created by CuTs on 2/22/2018.
 */

public class Database {
    ArrayList<Card> cards;

    public Database(){
        loadDatabase();
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

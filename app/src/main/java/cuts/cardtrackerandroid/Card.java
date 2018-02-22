package cuts.cardtrackerandroid;

/**
 * Created by CuTs on 2/6/2018.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Card implements Comparable, Serializable {
    private String scryfallUUID;
    private int numCopies;
    private String originalOwner;
    private String currentOwner;
    private boolean foil;
    private String client;
    private String date;

    public Card(String scryfallUUID, String originalOwner, String currentOwner, String client, String date){
        this.scryfallUUID = scryfallUUID;
        numCopies = 1;
        this.originalOwner = originalOwner;//original owner is whoever created the card?
        this.currentOwner = currentOwner;//current owner is probably the original owner?
        this.client = client;
        this.date = date;
    }
    public Card(String scryfallUUID,int numCopies, String originalOwner, String currentOwner, boolean foil, String client, String date){
        this.scryfallUUID = scryfallUUID;
        this.numCopies = numCopies;
        this.originalOwner = originalOwner;//original owner is whoever created the card?
        this.currentOwner = currentOwner;//current owner is probably the original owner?
        this.foil = foil;
        this.client = client;
        this.date = date;
    }



    public String getDate() {return this.date;}
    public void setDate() {this.date = date;}
    public String getClient(){return this.client;}
    public void setClient(String clientToBe) {client = clientToBe;}
    public boolean getFoil(){
        return foil;
    }

    public void setFoil(boolean foil){
        this.foil = foil;
    }

    public int getNumCopies(){
        return numCopies;
    }

    public void setCopies(int num){
        numCopies = num;
    }

    public boolean changeOwner(String newOwner){
        try{
            //check if this user exists (should catch here)
            originalOwner = newOwner;
            return true;
        }catch(Exception e){

        }
        return false;
    }

    public String getOriginalOwner(){
        return originalOwner;
    }
    public String getCurrentOwner(){
        return currentOwner;
    }

    @Override
    public int compareTo(Object t) {
        Card temp = (Card) t;
        cuts.forohfor.scryfall.api.Card thisCard = this.getCardByUUID();
        cuts.forohfor.scryfall.api.Card thatCard = temp.getCardByUUID();
        if(thisCard.getName().equalsIgnoreCase(thatCard.getName())){
            return originalOwner.compareTo(temp.originalOwner);
        }
        return thisCard.getName().compareTo(thatCard.getName());
    }

    @Override
    public boolean equals(Object t){
        Card temp = (Card) t;
        cuts.forohfor.scryfall.api.Card thisCard = this.getCardByUUID();
        cuts.forohfor.scryfall.api.Card thatCard = temp.getCardByUUID();
        if(thisCard.getName().equalsIgnoreCase(thatCard.getName())){
            if(originalOwner.equals(temp.originalOwner)){
                return true;
            }
        }
        return false;
    }


    public cuts.forohfor.scryfall.api.Card getCardByQuery(){
        return null; //to implement
    }

    public cuts.forohfor.scryfall.api.Card getCardByUUID(){
        CardTask getCard = new CardTask();
        getCard.execute(this);
        try {
            return getCard.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    //this class returns a card that is queried from online
    private class CardTask extends AsyncTask<Card, Void, cuts.forohfor.scryfall.api.Card> {

        @Override
        protected cuts.forohfor.scryfall.api.Card doInBackground(final Card... params) {
            Card temp = params[0];
            try {
                return cuts.forohfor.scryfall.api.MTGCardQuery.getCardByScryfallId(temp.scryfallUUID);
            } catch (IOException ex) {
                Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        }
    }




}

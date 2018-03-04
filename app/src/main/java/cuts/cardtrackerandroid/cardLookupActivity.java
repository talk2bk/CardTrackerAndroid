package cuts.cardtrackerandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuts.forohfor.scryfall.api.*;

public class cardLookupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_lookup);
    }

    public void cardSearch(View view){
        ArrayList<cuts.forohfor.scryfall.api.Card> searchResults = getCardByQuery(((EditText)findViewById(R.id.cardNameToSearch)).getText().toString());
        if(searchResults != null){
            for(cuts.forohfor.scryfall.api.Card card : searchResults){
                //display card on screen to choose from
            }
        }
        else{
            //display NO CARDS FOUND text view
        }

    }

    public ArrayList<cuts.forohfor.scryfall.api.Card> getCardByQuery(String query){
        CardTask searchCard = new CardTask();
        searchCard.execute(query);
        try{
            return searchCard.get();
        } catch(InterruptedException e){
            e.printStackTrace();
        } catch(ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    //this class returns a card that is queried from online
    private class CardTask extends AsyncTask<String, Void, ArrayList<cuts.forohfor.scryfall.api.Card>> {

        @Override
        protected ArrayList<cuts.forohfor.scryfall.api.Card> doInBackground(final String... params) {
            String temp = params[0];
            try {
                return cuts.forohfor.scryfall.api.MTGCardQuery.search("++! "+ temp);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }

        }
    }
}

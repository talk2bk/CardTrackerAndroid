package cuts.cardtrackerandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainScreen extends AppCompatActivity {
    private final int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;
    private final int UPPERCARDTEXTSIZE = 35;
    private final int LOWERCARDTEXTSIZE = 30;
    private final int LEFTMARGIN = 10;
    private final int MAXROWSIZE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addCard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
        //check database
        Database cardDatabase = new Database();
        //display all cards being borrowed

        //t e s t
        Card test = new Card("28708c8c-4336-4d04-b43a-59a31471a9f6",4,"CuTs","CuTs",true,"Paper","02/06/18");
        Card secondTest = new Card("f582e3c3-c329-40bb-9ffb-c4812a7aedd5", 4, "CuTs", "CuTs", true, "Paper", "2/07/18");
        cardDatabase.addCard(test);
        cardDatabase.addCard(secondTest);
        //t e s t

        //display all cards? maybe display individual users
            //make each thing clickable to display a card info page OR display a list of cards that user owns
        displayCardSet(cardDatabase.getCards()); //currently displaying all cards
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //add options here later
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //to do (adding cards manually)
    public void addCard(View view){
        startActivity(new Intent(this,addCardActivity.class));

    }

    private void displayCardSet(ArrayList<Card> cardSet){
        //pass in a set of cards?
        //do displayCard on each of them
        LinearLayout mainScreenLayout = (LinearLayout) findViewById(R.id.mainscreen);
        int id = 1; //can't start from 0 for wahtever reason for ids
        for(Card temp : cardSet){
            RelativeLayout tempLayout = displayCard(temp);
            mainScreenLayout.addView(tempLayout);
        }
    }


    private RelativeLayout displayCard(Card cardToDisplay){//maybe put card info in params?
        RelativeLayout card = new RelativeLayout(this);
        cuts.forohfor.scryfall.api.Card cardInfo= cardToDisplay.getCardByUUID();
        int cardImageView = 1; int cardNameView = 2; int ownerNameView = 3; int clientNameView = 4; int dateView = 5; int numCopiesView = 6; int foilView = 7;
        //Imageview
        ImageView cardImage = new ImageView(this);
        RelativeLayout.LayoutParams cardImageParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardImageParams.addRule(RelativeLayout.ALIGN_LEFT);
        cardImage.setLayoutParams(cardImageParams);
        cardImage.setAdjustViewBounds(true);
        cardImage.setScaleType(ImageView.ScaleType.FIT_START);
        cardImage.setId(cardImageView);
        cardImage.setImageBitmap(getCardImage(cardInfo.getImageURI("small")));
        card.addView(cardImage);
        //endImageview

        //cardName
        TextView cardName = new TextView(this);
        cardName.setId(cardNameView);
        RelativeLayout.LayoutParams cardNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        cardNameParams.addRule(RelativeLayout.RIGHT_OF,cardImage.getId());
        cardName.setLayoutParams(cardNameParams);
        cardName.setText(cardInfo.getName()); //use param to set text here
        cardName.setTextSize(TypedValue.COMPLEX_UNIT_SP, UPPERCARDTEXTSIZE);
        card.addView(cardName);
        //endCardName

        //ownerName
        TextView ownerName = new TextView(this);
        ownerName.setId(ownerNameView);
        RelativeLayout.LayoutParams ownerNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        ownerNameParams.addRule(RelativeLayout.RIGHT_OF,cardImage.getId());
        ownerNameParams.addRule(RelativeLayout.BELOW, cardName.getId());
        ownerName.setLayoutParams(ownerNameParams);
        ownerName.setText(cardToDisplay.getOriginalOwner()); //use param to set text here
        ownerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, LOWERCARDTEXTSIZE);
        card.addView(ownerName);
        //endOwnerName

        //numCopies
        TextView numCopies = new TextView(this);
        numCopies.setId(numCopiesView);
        RelativeLayout.LayoutParams numCopiesParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        numCopiesParams.addRule(RelativeLayout.RIGHT_OF,cardName.getId());
        numCopiesParams.setMargins(LEFTMARGIN,0,0,0);
        numCopies.setLayoutParams(numCopiesParams);
        numCopies.setText("x"+cardToDisplay.getNumCopies()); //use param to set text here
        numCopies.setTextSize(TypedValue.COMPLEX_UNIT_SP, UPPERCARDTEXTSIZE);
        card.addView(numCopies);
        //endNumCopies

        //clientName
        TextView clientName = new TextView(this);
        clientName.setId(clientNameView);
        RelativeLayout.LayoutParams clientNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        clientNameParams.addRule(RelativeLayout.RIGHT_OF,ownerName.getId());
        clientNameParams.addRule(RelativeLayout.BELOW, numCopies.getId());
        clientNameParams.setMargins(LEFTMARGIN,0,0,0);
        clientName.setLayoutParams(clientNameParams);
        clientName.setText(cardToDisplay.getClient()); //use param to set text here
        clientName.setTextSize(TypedValue.COMPLEX_UNIT_SP, LOWERCARDTEXTSIZE);
        card.addView(clientName);
        //endClientName

        //foil
        TextView foil = new TextView(this);
        foil.setId(foilView);
        RelativeLayout.LayoutParams foilParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        foilParams.addRule(RelativeLayout.RIGHT_OF,numCopies.getId());
        foilParams.setMargins(LEFTMARGIN,0,0,0);
        foil.setLayoutParams(foilParams);
        if(cardToDisplay.getFoil()) {foil.setText("Foil");} else{foil.setText("Nonfoil");} //use param to set text here
        foil.setTextSize(TypedValue.COMPLEX_UNIT_SP, UPPERCARDTEXTSIZE);
        card.addView(foil);
        //endfoil

        //date
        TextView date = new TextView(this);
        date.setId(dateView);
        RelativeLayout.LayoutParams dateParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        dateParams.addRule(RelativeLayout.RIGHT_OF,clientName.getId());
        dateParams.addRule(RelativeLayout.BELOW, foil.getId());
        dateParams.setMargins(LEFTMARGIN,0,0,0);
        date.setLayoutParams(dateParams);
        date.setText(cardToDisplay.getDate()); //use param to set text here
        date.setTextSize(TypedValue.COMPLEX_UNIT_SP, LOWERCARDTEXTSIZE);
        card.addView(date);
        //enddate


        return card;
    }
    public Bitmap getCardImage(String cardURI){
        CardImageTask getCardImage = new CardImageTask();
        getCardImage.execute(cardURI);
        try{
            return getCardImage.get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class CardImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(final String... params) {
            String uri = params[0];
            try {
                URL url = new URL(uri);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException ex) {
                return null;
            }

        }
    }


}

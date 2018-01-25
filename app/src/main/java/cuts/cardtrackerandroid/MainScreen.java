package cuts.cardtrackerandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainScreen extends AppCompatActivity {
    private final int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;
    private final int CARDTEXTSIZE = 22;
    private final int LEFTMARGIN = 10;
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
        //display all cards being borrowed
        //for(Card card : database){
        // displayCard(card);
        // }


        //display all cards? maybe display individual users
            //make each thing clickable to display a card info page OR display a list of cards that user owns

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void addCard(View view){
        startActivity(new Intent(this,addCardActivity.class));

    }


    private RelativeLayout displayCard(){//maybe put card info in params?
        RelativeLayout card = new RelativeLayout(this);
        card.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        int cardImageView = 0; int cardNameView = 1; int ownerNameView = 2; int clientNameView = 3; int dateView = 4; int numCopiesView = 5; int foilView = 6;
        //Imageview
        ImageView cardImage = new ImageView(this);
        cardImage.setLayoutParams(new RelativeLayout.LayoutParams(50,60));
        cardImage.setId(cardImageView);
        //cardImage.setImageUri() //use the card URI i have from teh api
        //endImageview

        //cardName
        TextView cardName = new TextView(this);
        cardName.setId(cardNameView);
        cardName.setTextSize(CARDTEXTSIZE);
        RelativeLayout.LayoutParams cardNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        cardNameParams.addRule(RelativeLayout.RIGHT_OF,cardImage.getId());
        cardName.setLayoutParams(cardNameParams);
        //cardName.setText("fart"); //use param to set text here
        //endCardName

        //ownerName
        TextView ownerName = new TextView(this);
        ownerName.setId(ownerNameView);
        ownerName.setTextSize(CARDTEXTSIZE);
        RelativeLayout.LayoutParams ownerNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        ownerNameParams.addRule(RelativeLayout.RIGHT_OF,cardImage.getId());
        ownerNameParams.addRule(RelativeLayout.BELOW, cardName.getId());
        ownerName.setLayoutParams(ownerNameParams);
        //ownerName.setText("fart"); //use param to set text here
        //endOwnerName

        //numCopies
        TextView numCopies = new TextView(this);
        numCopies.setId(numCopiesView);
        numCopies.setTextSize(CARDTEXTSIZE);
        RelativeLayout.LayoutParams numCopiesParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        numCopiesParams.addRule(RelativeLayout.RIGHT_OF,cardName.getId());
        numCopiesParams.setMargins(LEFTMARGIN,0,0,0);
        numCopies.setLayoutParams(numCopiesParams);
        //numCopies.setText("fart"); //use param to set text here
        //endNumCopies

        //clientName
        TextView clientName = new TextView(this);
        clientName.setId(clientNameView);
        clientName.setTextSize(CARDTEXTSIZE);
        RelativeLayout.LayoutParams clientNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        clientNameParams.addRule(RelativeLayout.RIGHT_OF,ownerName.getId());
        clientNameParams.addRule(RelativeLayout.BELOW, numCopies.getId());
        clientNameParams.setMargins(LEFTMARGIN,0,0,0);
        clientName.setLayoutParams(clientNameParams);
        //clientName.setText("fart"); //use param to set text here
        //endClientName

        //foil
        TextView foil = new TextView(this);
        foil.setId(foilView);
        foil.setTextSize(CARDTEXTSIZE);
        RelativeLayout.LayoutParams foilParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        foilParams.addRule(RelativeLayout.RIGHT_OF,numCopies.getId());
        foilParams.setMargins(LEFTMARGIN,0,0,0);
        foil.setLayoutParams(foilParams);
        //foil.setText("fart"); //use param to set text here
        //endfoil

        //date
        TextView date = new TextView(this);
        date.setId(dateView);
        date.setTextSize(CARDTEXTSIZE);
        RelativeLayout.LayoutParams dateParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        dateParams.addRule(RelativeLayout.RIGHT_OF,clientName.getId());
        dateParams.addRule(RelativeLayout.BELOW, foil.getId());
        dateParams.setMargins(LEFTMARGIN,0,0,0);
        date.setLayoutParams(dateParams);
        //date.setText("fart"); //use param to set text here
        //enddate

        return card;
    }
}

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
        cardDisplayer.displayCardSet(cardDatabase.getCards(),findViewById(R.id.mainscreen), this); //currently displaying all cards
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



}

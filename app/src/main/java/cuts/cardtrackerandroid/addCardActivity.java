package cuts.cardtrackerandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class addCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        Spinner foilChoice = (Spinner) findViewById(R.id.foilChoiceToAdd);
        ArrayAdapter<CharSequence> foilAdapter = ArrayAdapter.createFromResource(this,R.array.foil_choice, android.R.layout.simple_spinner_item);
        foilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foilChoice.setAdapter(foilAdapter);

        Spinner platformChoice = (Spinner) findViewById(R.id.platformChoiceToAdd);
        ArrayAdapter<CharSequence> platformAdapter = ArrayAdapter.createFromResource(this,R.array.platform_choice, android.R.layout.simple_spinner_item);
        platformAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformChoice.setAdapter(platformAdapter);
    }

    public void addCardToDatabase(View view){
        //pull information from edittext boxes on screen and card
        //create a Card and submit it to database
        //prompt saying complete?
    }
}

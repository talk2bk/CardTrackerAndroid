package cuts.cardtrackerandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        EditText date = findViewById(R.id.dateToAdd);
        date.setText(getDate());
    }

    private String getDate(){
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return dateFormat.format(today);
    }

    public void cardLookup(View view){
        //open new window
        startActivity(new Intent(this,cardLookupActivity.class));
        //look at edittextbox for card name, search
        //display options inline
        //select an option
        //confirm
        //send information
        //close activity
    }
    public void addCardToDatabase(View view){
        //pull information from edittext boxes on screen and card
        //create a Card and submit it to database
        //prompt saying complete?
    }
}

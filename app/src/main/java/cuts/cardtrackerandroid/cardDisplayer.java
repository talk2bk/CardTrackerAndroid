package cuts.cardtrackerandroid;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class cardDisplayer {
    private static final int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;
    private static final int UPPERCARDTEXTSIZE = 35;
    private static final int LOWERCARDTEXTSIZE = 30;
    private static final int LEFTMARGIN = 10;
    private static final int MAXROWSIZE = 300;

    public static void displayCardSet(ArrayList<Card> cardSet, View view, Context context){
        //pass in a set of cards?
        //do displayCard on each of them
        LinearLayout screenLayout = (LinearLayout) view;
        int id = 1; //can't start from 0 for wahtever reason for ids
        for(Card temp : cardSet){
            RelativeLayout tempLayout = displayCard(temp, context);
            screenLayout.addView(tempLayout);
        }
    }


    public static RelativeLayout displayCard(Card cardToDisplay, Context context){//maybe put card info in params?
        RelativeLayout card = new RelativeLayout(context);
        cuts.forohfor.scryfall.api.Card cardInfo= cardToDisplay.getCardByUUID();
        int cardImageView = 1; int cardNameView = 2; int ownerNameView = 3; int clientNameView = 4; int dateView = 5; int numCopiesView = 6; int foilView = 7;
        //Imageview
        ImageView cardImage = new ImageView(context);
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
        TextView cardName = new TextView(context);
        cardName.setId(cardNameView);
        RelativeLayout.LayoutParams cardNameParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        cardNameParams.addRule(RelativeLayout.RIGHT_OF,cardImage.getId());
        cardName.setLayoutParams(cardNameParams);
        cardName.setText(cardInfo.getName()); //use param to set text here
        cardName.setTextSize(TypedValue.COMPLEX_UNIT_SP, UPPERCARDTEXTSIZE);
        card.addView(cardName);
        //endCardName

        //ownerName
        TextView ownerName = new TextView(context);
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
        TextView numCopies = new TextView(context);
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
        TextView clientName = new TextView(context);
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
        TextView foil = new TextView(context);
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
        TextView date = new TextView(context);
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
    private static Bitmap getCardImage(String cardURI){
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

    private static class CardImageTask extends AsyncTask<String, Void, Bitmap> {
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

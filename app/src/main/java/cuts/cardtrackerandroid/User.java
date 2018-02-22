package cuts.cardtrackerandroid;

/**
 * Created by CuTs on 2/6/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable, Comparable{
    private String name = "You";//default to You
    private ArrayList<Card> cards;
    private String uniqueID; //to implement

    //defaultUser
    public User(){
        this.cards = new ArrayList<Card>();
    }
    //new user
    public User(String name){
        this.name = name;
        this.cards = new ArrayList<Card>();
    }
    //for transfering user accounts?
    public User(String name, ArrayList<Card> cards){
        this.name = name;
        this.cards = cards;
    }

    String getName() {
        return name;
    }

    ArrayList<Card> getBorrowedCards(){
        ArrayList<Card> results = new ArrayList<Card>();
        for(Card card : cards){
            if(!card.getOriginalOwner().equals(this.name)){
                results.add(card);

            }
        }
        return results;
    }

    ArrayList<Card> getCards(){
        return cards;
    }

    /* card stuff */
    //figure out how to prompt them for a card name and user
    public void addACard(Card toAdd){ //definitely need to update in the future
        //check if the card is already in inventory
        if(cards.contains(toAdd)){
            Card temp = cards.get(cards.indexOf(toAdd));
            temp.setCopies(temp.getNumCopies()+toAdd.getNumCopies());
            cards.set(cards.indexOf(toAdd), temp);
            //if you already have the card, increment it instead of adding a new one.
        }
        //we'll do version cehcking when i implement the magic api
        else{cards.add(toAdd);}

    }
    /* end card stuff */

    @Override
    public int compareTo(Object t) {
        User temp = (User) t;
        return name.compareTo(temp.getName());
    }
    @Override
    public boolean equals(Object t){
        User temp = (User) t;
        return name.equalsIgnoreCase(temp.getName());
    }
}
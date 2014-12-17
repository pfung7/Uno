package com.pfung.uno;

import com.pfung.uno.compontents.*;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Client {

    public static void main(String[] args)
    {
        Card myCard;
        Player myPlayer;

        System.out.println("Welcome to Uno!");
        for(int i=1;i<=108;i++) {
            myCard = new Card(i);
            System.out.println("CardID: " + myCard.getCardID() + " " + myCard.getColor() + " " + myCard.getValue());
        }

    }

}

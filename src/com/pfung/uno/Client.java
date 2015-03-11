package com.pfung.uno;

        import com.pfung.uno.compontents.*;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Client {

    public static void main(String[] args)
    {
        int NumberPlayer = 4;
        int InitialNumCard = 7;
        int WinningScore =500;
        System.out.println("Welcome to UNO");

        Game myGame = new Game(NumberPlayer, InitialNumCard, WinningScore);

        myGame.GameOn();

    }

}

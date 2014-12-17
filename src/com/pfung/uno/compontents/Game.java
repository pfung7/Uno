package com.pfung.uno.compontents;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Game {
    private int GameID;
    private int NumPlayer;
    private Player[] Players;
    private int PlayerInAction;
    private Deck deckDraw;
    private Deck deckDiscad;
    private int topCard;
    private int lastWin;
    private direction GameDir;

    private enum direction{Clockwise, CounterClockwise};


    public Game(int lastWin) {
        GameID = 1;
        NumPlayer = 4;
        GameDir = direction.Clockwise;
        Players = new Player[NumPlayer];

        deckDiscad = new Deck();
        deckDraw = new Deck();
        this.topCard = topCard;
        this.lastWin = lastWin;
    }

    public void NextTurn(){

    }

}

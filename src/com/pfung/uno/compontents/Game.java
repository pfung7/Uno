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
    private Deck deckDiscard;
    private int topCard;
    private direction GameDir;

    private enum direction { Clockwise, CounterClockwise }

    public int getPlayerInAction() {
        return PlayerInAction;
    }

    public void setPlayerInAction(int playerInAction) {
        PlayerInAction = playerInAction;
    }

    public int getNumPlayer() {
        return NumPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        NumPlayer = numPlayer;
    }

    public void setDeckDraw(Deck deckDraw) {
        this.deckDraw = deckDraw;
    }

    public Game(int lastWin) {
        GameID = 1;
        NumPlayer = 4;
        GameDir = direction.Clockwise;
        Players = new Player[NumPlayer];

        deckDiscard = new Deck();
        deckDraw = new Deck();
        topCard = 100;
        PlayerInAction = lastWin;
    }

    public void NextTurn(){

    }

    public void ChangeDirection(){
        if (GameDir == direction.Clockwise)
                GameDir = direction.CounterClockwise;
        else GameDir = direction.Clockwise;
    }

}

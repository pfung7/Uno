package com.pfung.uno.compontents;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Game {
    private int GameID;
    private int NumPlayer;
    public Player[] GamePlayer;
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
        GamePlayer = new Player[NumPlayer];

        deckDiscard = new Deck();
        deckDraw = new Deck();

        GamePlayer[1] = new Player(true);

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

package com.pfung.uno.compontents;

import java.util.Scanner;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Player {
    private String UserName;
    private String Nickname;
    private int PlayerID;
    private int TotalScore;
    private boolean isHuman;
    private Pile hand;

    public String GetNickname(){
        return Nickname;
    }

    public int GetTotalScore(){
        return TotalScore;
    }

    public void SetTotalScore(int myTotalScore){
        TotalScore = myTotalScore;
    }

    public Player(boolean isHumanFlag){
        boolean FacingUp = false;

        Scanner in = new Scanner(System.in);

        isHuman = isHumanFlag;
        PlayerID = (1 + (int) (Math.random() * 100) );
        if (isHuman) {
            System.out.println("What's your name? ");
            Nickname = in.nextLine();
        }
        else
            Nickname = "AI#_" + PlayerID;
        hand = new Pile(FacingUp);

   }

    public void ShowPlayer(boolean ShowHand){
        System.out.print(Nickname);
        System.out.print(", Score:" + TotalScore);
        if (ShowHand)
            hand.ShowPile(true);
        else
            hand.ShowPile(false);
    }


    public boolean isHuman(){
        return isHuman;
    }

    public Pile GetHand(){
        return hand;
    }

}

package com.pfung.uno.compontents;

import sun.rmi.log.LogInputStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Player {
    private String userName;
    private String nickname;
    private int playerID;
    private int score;
    private int totalScore;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

     public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private List<Card> hand;

    public Player(String[] args){
        System.out.print("What is your nickname?");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setNickname(nickname);
        setPlayerID(1);
        setScore(0);
        hand = new LinkedList<Card>();

   }

}

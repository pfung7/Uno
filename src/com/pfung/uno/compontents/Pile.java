package com.pfung.uno.compontents;

import java.util.LinkedList;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Pile {
    private boolean FaceDown;
    private LinkedList<Card> CardList;

    public Pile(boolean isFaceDown){
        FaceDown = isFaceDown;
        CardList = new LinkedList<Card>();
    }

    public void AddToTop(Card aCard){
        CardList.addFirst(aCard);
    }

    public void AddToBottom(Card aCard){
        CardList.addLast(aCard);
    }

    public void AddRandom(Card aCard){
        if (CardList.size() == 0)
            CardList.addFirst(aCard);
        else
            CardList.add((1 + (int) (Math.random() * CardList.size())), aCard);
    }

    public Card DrawFromTop(){
        Card CardReturn = CardList.getFirst();
        CardList.removeFirst();
        return CardReturn;
    }

    public Card DrawFromBottom(){
        Card CardReturn = CardList.getLast();
        CardList.removeLast();
        return CardReturn;
    }

    public Card DrawFromPosition(int Position){
        Card CardReturn = CardList.get(Position);
        CardList.remove(Position);
        return CardReturn;
    }

    public Card DrawFromRandom(){
        int Position =  1 + (int) (Math.random() * CardList.size());
        return DrawFromPosition(Position);
    }

    public int GetSize(){
        return CardList.size();
    }

    public void ShowPile(boolean ShowDetail){
        System.out.print(", Cards" +" ("+CardList.size()+") ");
        if (ShowDetail)
            for (int i = 0; i < this.GetSize(); i++){
                CardList.get(i).ShowCard();
            }
    }

    public Card GetTop(){
        return CardList.getFirst();
    }

    public Card GetBottom(){
        return CardList.getLast();
    }

    public Card GetFromPosition(int Position){
        return CardList.get(Position);
    }

    public Card GetRandom(){
        int Position =  1 + (int) (Math.random() * CardList.size());
        return GetFromPosition(Position);
    }

    public void EmptyPile(){
        CardList.clear();
    }

    public int GetPileScore(){
        int PileScore = 0;
        for(int i = 0; i<CardList.size(); i++) {
            PileScore += GetFromPosition(i).getScore();
        }
        return  PileScore;
     }

    public int GetScoreByColor(Card.CardColor cardColor){
        int Score = 0;
        for (int i = 0; i<CardList.size(); i++) {
            if (CardList.get(i).getColor() == cardColor)
                Score += CardList.get(i).getScore();
        }
        return Score;
    }
    public Card.CardColor GetHighestScoreColor(){
        int ScoreRed    = GetScoreByColor(Card.CardColor.Red);
        int ScoreBlue   = GetScoreByColor(Card.CardColor.Blue);
        int ScoreGreen  = GetScoreByColor(Card.CardColor.Green);
        int ScoreYellow = GetScoreByColor(Card.CardColor.Yellow);
        int HighestScore = 0;
        int Counter = 0;
        Card.CardColor[] HighestScoreColor;
        HighestScoreColor = new Card.CardColor[4];

        HighestScore = ScoreRed;

        if (ScoreBlue > HighestScore) {
            HighestScore = ScoreBlue;
        }
        if (ScoreGreen > HighestScore) {
            HighestScore = ScoreGreen;
        }
        if (ScoreYellow > HighestScore) {
            HighestScore = ScoreYellow;
        }


        if (HighestScore == ScoreRed){
            HighestScoreColor[Counter] = Card.CardColor.Red;
            Counter ++;
        }
        if (HighestScore == ScoreBlue){
            HighestScoreColor[Counter] = Card.CardColor.Blue;
            Counter ++;
        }
        if (HighestScore == ScoreGreen){
            HighestScoreColor[Counter] = Card.CardColor.Green;
            Counter ++;
        }
        if (HighestScore == ScoreYellow){
            HighestScoreColor[Counter] = Card.CardColor.Yellow;
            Counter ++;
        }

        return HighestScoreColor[(int) (Math.random() * Counter)];
    }

    public boolean CheckPlayable(Card TopCard, int CardToPlay){
        if ((CardToPlay >= 0) && (CardToPlay < CardList.size()) && (GetFromPosition(CardToPlay).getColorString().equals(TopCard.getColorString()) || GetFromPosition(CardToPlay).getNameOfCard().equals(TopCard.getNameOfCard()) || (GetFromPosition(CardToPlay).getColorString().equals(Card.CardColor.Black.toString()))))
            return true;
        else
            return false;
    }

    public void ShowPlayableCards(Card TopCard) {
        for (int i = 0; i < CardList.size(); i++) {
            if (GetFromPosition(i).getColorString().equals(TopCard.getColorString()) || GetFromPosition(i).getNameOfCard().equals(TopCard.getNameOfCard()) || (GetFromPosition(i).getColorString().equals(Card.CardColor.Black.toString()))) {
                System.out.println(i + " - " + GetFromPosition(i).getColorString() + " " + GetFromPosition(i).getNameOfCard());
            }
        }
    }


    public boolean HavePlayableCards(Card TopCard) {
        for (int i = 0; i < CardList.size(); i++) {
            if (GetFromPosition(i).getColorString().equals(TopCard.getColorString()) || GetFromPosition(i).getNameOfCard().equals(TopCard.getNameOfCard()) || (GetFromPosition(i).getColorString().equals(Card.CardColor.Black.toString())))
                return true;
        }
        return false;
    }




}


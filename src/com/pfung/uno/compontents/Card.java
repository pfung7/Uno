package com.pfung.uno.compontents;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Card {
    private int CardID;
    private CardColor Color;
    private CardName NameOfCard;
    private int Score;

    public enum CardColor{
        Red, Yellow, Blue, Green, Black;

        @Override
        public String toString(){
            String s = super.toString();
            return s.substring(0,1)+s.substring(1).toLowerCase();
        }
    };

    public enum CardName {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Skip, Reverse, Draw2, Draw4, Wild;

        @Override
        public String toString(){
            String s = super.toString();
            if (s.equals("Zero")) {
                return "0";
            } else if (s.equals("One")) {
                return "1";
            } else if (s.equals("Two")) {
                return "2";
            } else if (s.equals("Three")) {
                return "3";
            } else if (s.equals("Four")) {
                return "4";
            } else if (s.equals("Five")) {
                return "5";
            } else if (s.equals("Six")) {
                return "6";
            } else if (s.equals("Seven")) {
                return "7";
            } else if (s.equals("Eight")) {
                return "8";
            } else if (s.equals("Nine")) {
                return "9";
            } else {
                return s;
            }
        }

    }

    public CardColor getColor() {return Color;}
    public String getColorString() {
        return Color.toString();
    }
    public void SetColor(CardColor myColor){ Color = myColor; }

    public int getCardID() {
        return CardID;
    }

    public String getNameOfCard() {
        return NameOfCard.toString();
    }

    public int getScore() {
        return Score;
    }

    public Card(int cardID) {
        CardID = cardID;

        // Special card are black and there are 100 color cards
        if (CardID > 100)
            Color = CardColor.Black;
        else
            switch (CardID % 4) {
                case 1:
                    Color = CardColor.Red;
                    break;
                case 2:
                    Color = CardColor.Yellow;
                break;
                case 3:
                    Color = CardColor.Blue;
                    break;
                case 0:
                    Color = CardColor.Green;
                    break;
            }

        // 4 Zero cards
        if (CardID <= 4) {
            NameOfCard = CardName.Zero;
            Score = 0;
        }
        else
            switch ((CardID + 3) / 8) {
                case 1:
                    NameOfCard = CardName.One;
                    Score = 1;
                    break;
                case 2:
                    NameOfCard = CardName.Two;
                    Score = 2;
                    break;
                case 3:
                    NameOfCard = CardName.Three;
                    Score = 3;
                    break;
                case 4:
                    NameOfCard = CardName.Four;
                    Score = 4;
                    break;
                case 5:
                    NameOfCard = CardName.Five;
                    Score = 5;
                    break;
                case 6:
                    NameOfCard = CardName.Six;
                    Score = 6;
                    break;
                case 7:
                    NameOfCard = CardName.Seven;
                    Score =7;
                    break;
                case 8:
                    NameOfCard = CardName.Eight;
                    Score = 8;
                    break;
                case 9:
                    NameOfCard = CardName.Nine;
                    Score = 9;
                    break;
                case 10:
                    NameOfCard = CardName.Skip;
                    Score = 20;
                    break;
                case 11:
                    NameOfCard = CardName.Reverse;
                    Score = 20;
                    break;
                case 12:
                    NameOfCard = CardName.Draw2;
                    Score = 20;
                    break;
                case 13:
                    NameOfCard = (CardID % 2 == 0) ? CardName.Wild: CardName.Draw4;
                    Score =50;
                    break;
            }


    }

    public void ShowCard(){
        System.out.print(" "+ Color + " " + NameOfCard + "  ");
    }
}

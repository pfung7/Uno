package com.pfung.uno.compontents;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Card {
    private int CardID;
    private CardColor Color;
    private CardValue Value;

    private enum CardColor{
        Red, Yellow, Blue, Green, Black;

        @Override
        public String toString(){
            String s = super.toString();
            return s.substring(0,1)+s.substring(1).toLowerCase();
        }
    };
    private enum CardValue{
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

    public String getColor() {
        return Color.toString();
    }

    public int getCardID() {
        return CardID;
    }

    public String getValue() {
        return Value.toString();
    }

    public Card(int cardID) {
        CardID = cardID;

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

        if (CardID <= 4)
            Value = CardValue.Zero;
        else
            switch ((CardID + 3) / 8) {
                case 1:
                    Value = CardValue.One;
                    break;
                case 2:
                    Value = CardValue.Two;
                    break;
                case 3:
                    Value = CardValue.Three;
                    break;
                case 4:
                    Value = CardValue.Four;
                    break;
                case 5:
                    Value = CardValue.Five;
                    break;
                case 6:
                    Value = CardValue.Six;
                    break;
                case 7:
                    Value = CardValue.Seven;
                    break;
                case 8:
                    Value = CardValue.Eight;
                    break;
                case 9:
                    Value = CardValue.Nine;
                    break;
                case 10:
                    Value = CardValue.Skip;
                    break;
                case 11:
                    Value = CardValue.Reverse;
                    break;
                case 12:
                    Value = CardValue.Draw2;
                    break;
                case 13:
                    Value = (CardID % 2 == 0) ? CardValue.Wild: CardValue.Draw4; break;
            }
    }
}

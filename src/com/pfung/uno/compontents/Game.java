package com.pfung.uno.compontents;
import java.util.Scanner;

/**
 * Created by Patrick on 12/12/2014.
 */
public class Game {
    private int NumPlayer;
    private int InitNumCard;
    private int NumRound;
    private int WinScore;
    private int LastRoundWinner;
    private Player[] GamePlayer;
    private int PlayerInAction;
    private Pile DrawPile;
    private Pile DiscardPile;
    private boolean RoundInProgress;
    private direction GameDir;
    private Card CardInAction;
    private enum direction { Clockwise, CounterClockwise }
    private Scanner in = new Scanner(System.in);


    public boolean IsRoundInProgress(){
        return RoundInProgress;
    }

    public void HumanPlayerAct(){
        Card TopCard = DiscardPile.GetTop();
        int CardToPlay = -1;

        System.out.println(GamePlayer[PlayerInAction].GetNickname() + ", It's your turn now. Playable card(s):");
        GamePlayer[PlayerInAction].GetHand().ShowPlayableCards(TopCard);
        System.out.println("Which card do you want to play?");

        while (CardInAction == null) {
            if (in.hasNextInt()) {
                CardToPlay = in.nextInt();
                in.nextLine();
                if (GamePlayer[PlayerInAction].GetHand().CheckPlayable(TopCard, CardToPlay)) {
                    CardInAction = GamePlayer[PlayerInAction].GetHand().DrawFromPosition(CardToPlay);
                    System.out.print(GamePlayer[PlayerInAction].GetNickname() + " played: ");
                    System.out.println(CardInAction.getColorString() + " " + CardInAction.getNameOfCard());
                }
                else
                    System.out.println("Invalid choice of card. Please choose another card to play.");
            }
            else {
                in.next();
                System.out.println("Invalid input. Please choose another card to play.");
            }
        }
    }

    public void AIPlayerAct(){
        Card TopCard = DiscardPile.GetTop();

        // play highest score card
        // next player number of card
        // score by color
        int BestCard = 0;
        double BestCardScore = 0;
        double CardScore = 0;
        double SameColorFactor = 0.3;

        for (int i = 0; i < GamePlayer[PlayerInAction].GetHand().GetSize(); i++) {
            if (GamePlayer[PlayerInAction].GetHand().CheckPlayable(TopCard, i)) {
                    CardScore = GamePlayer[PlayerInAction].GetHand().GetFromPosition(i).getScore()
                            + SameColorFactor * (GamePlayer[PlayerInAction].GetHand().GetScoreByColor(GamePlayer[PlayerInAction].GetHand().GetFromPosition(i).getColor()));
                    if (CardScore > BestCardScore){
                        BestCardScore = CardScore;
                        BestCard = i;
                    }
            }
        }
        CardInAction = GamePlayer[PlayerInAction].GetHand().DrawFromPosition(BestCard);
        System.out.print(GamePlayer[PlayerInAction].GetNickname() + " played: ");
        System.out.println(CardInAction.getColorString() + " " + CardInAction.getNameOfCard());
    }



    public void PlayerAct(){
        //System.out.println("Player " + PlayerInAction +"'s turn");
        //System.out.print("Top card on discard pile: ");
        //DiscardPile.GetTop().ShowCard();
        Card TopCard = DiscardPile.GetTop();

        if (GamePlayer[PlayerInAction].GetHand().HavePlayableCards(TopCard)){
            if (GamePlayer[PlayerInAction].isHuman())
                HumanPlayerAct();
            else
                AIPlayerAct();
        }
        else  {
           PlayerDrawCard(PlayerInAction, 1);
            System.out.println(GamePlayer[PlayerInAction].GetNickname() + " has no playable cards and draws one card.");
        }

    }

    public void PlayerChooseColor(){
        int ColorChoice = -1;

        if (GamePlayer[PlayerInAction].isHuman()) {
            System.out.println(GamePlayer[PlayerInAction].GetNickname() + ", what color?");
            System.out.println("1 - Red, 2 - Yellow, 3 - Blue, 4 - Green");

            while ((ColorChoice < 1) || (ColorChoice > 4)) {
                if (in.hasNextInt()) {
                    ColorChoice = in.nextInt();
                    in.nextLine();
                    switch (ColorChoice) {
                        case 1:
                            DiscardPile.GetTop().SetColor(Card.CardColor.Red);
                            System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Red." );
                            break;
                        case 2:
                            DiscardPile.GetTop().SetColor(Card.CardColor.Yellow);
                            System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Yellow." );
                            break;
                        case 3:
                            DiscardPile.GetTop().SetColor(Card.CardColor.Blue);
                            System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Blue." );
                            break;
                        case 4:
                            DiscardPile.GetTop().SetColor(Card.CardColor.Green);
                            System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Green." );
                            break;
                        default:
                            System.out.println("Invalid input. Please choose another color.");
                            ColorChoice = -1;
                    }
                } else {
                    in.nextLine();
                    System.out.println("Invalid input. Please choose another color.");
                }
            }
        }
        else {
            switch(GamePlayer[PlayerInAction].GetHand().GetHighestScoreColor()) {
                case Red:
                    DiscardPile.GetTop().SetColor(Card.CardColor.Red);
                    System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Red." );
                    break;
                case Yellow:
                    DiscardPile.GetTop().SetColor(Card.CardColor.Yellow);
                    System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Yellow." );
                    break;
                case Blue:
                    DiscardPile.GetTop().SetColor(Card.CardColor.Blue);
                    System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Blue." );
                    break;
                case Green:
                    DiscardPile.GetTop().SetColor(Card.CardColor.Green);
                    System.out.println(GamePlayer[PlayerInAction].GetNickname() +" choose to continue with Green." );
                    break;
            }
        }

    }

    public void UpdateRound(){
        if (CardInAction != null) {
            DiscardPile.AddToTop(CardInAction);

            switch (CardInAction.getNameOfCard()) {
                case "Skip":
                    if (GameDir == direction.Clockwise)
                        PlayerInAction++;
                    else
                        PlayerInAction--;
                    NextPlayerInAction();
                    break;
                case "Reverse":
                    GameDir = (GameDir == direction.Clockwise) ? direction.CounterClockwise : direction.Clockwise;
                    NextPlayerInAction();
                    break;
                case "Wild":
                    PlayerChooseColor();
                    NextPlayerInAction();
                    break;
                case "Draw4":
                    PlayerChooseColor();
                    NextPlayerInAction();
                    PlayerDrawCard(PlayerInAction, 4);
                    break;
                case "Draw2":
                    NextPlayerInAction();
                    PlayerDrawCard(PlayerInAction, 2);
                    break;
                default:
                    NextPlayerInAction();

            }
        }
        else
            NextPlayerInAction();
        // Free up card in action
        CardInAction = null;

        // Check if any player played all his card
        for (int i = 0; i < NumPlayer; i++){
            if (GamePlayer[i].GetHand().GetSize() == 0)
                RoundInProgress = false;
        }

    }

    public void PlayerDrawCard(int Someone, int NumDraw){
        for (int i = 0; i < NumDraw; i++){
            if (DrawPile.GetSize() == 0)
                PutDiscardBackToDraw();
            GamePlayer[Someone].GetHand().AddToTop(DrawPile.DrawFromTop());
        }
    }

    public void PutDiscardBackToDraw(){
        Pile TempPile;
        boolean FaceDown = true;
        TempPile = new Pile(FaceDown);

        System.out.println("Insufficient cards in draw pile. Putting cards from discard pile back to draw pile.");
        while (DiscardPile.GetSize() > 1){
            TempPile.AddRandom(DiscardPile.DrawFromBottom());
        }
        while (TempPile.GetSize() > 0){
            DrawPile.AddToBottom(TempPile.DrawFromTop());
        }
    }

     public void NextPlayerInAction(){
        if (GameDir == direction.Clockwise) {
            PlayerInAction ++;
            if (PlayerInAction >= NumPlayer) {
                PlayerInAction = PlayerInAction % NumPlayer;
            }
        }
        else {
            PlayerInAction --;
            if (PlayerInAction < 0 ) {
                PlayerInAction += NumPlayer;
            }
        }
    }

    public void InitRound(){
        RoundInProgress = true;
        // Increment num of round
        NumRound ++;

        // Reset the piles
        DiscardPile.EmptyPile();
        DrawPile.EmptyPile();

        //Shuffle the Draw pile
        for (int i = 1; i <= 108; i++) {
            DrawPile.AddRandom(new Card(i));
        }

        // Draw cards to players
        for (int i = 0; i < NumPlayer; i++){
            GamePlayer[i].GetHand().EmptyPile();
            for (int j = 0; j< InitNumCard; j++){
                GamePlayer[i].GetHand().AddToTop(DrawPile.DrawFromTop());
            }
        }

        // Draw the first card
        DiscardPile.AddToTop(DrawPile.DrawFromTop());

        GameDir = direction.Clockwise;
    }

    public void EndRound(){
        int RoundScore = 0;
        Player Winner;

        System.out.println();
        System.out.println("End of Round");
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < NumPlayer; i++) {
            RoundScore += GamePlayer[i].GetHand().GetPileScore();
        }

        // Give points to the winner of the round
        Winner = GetRoundWinner();
        System.out.println(Winner.GetNickname() +" gained " + RoundScore + " points in this round.");
        Winner.SetTotalScore(Winner.GetTotalScore() + RoundScore);
        System.out.println();
        System.out.println("----------------------------------------------------------------------");
    }

    public Game(int myNumPlayer, int myInitNumCard, int myWinScore){
        NumPlayer = myNumPlayer;
        InitNumCard = myInitNumCard;
        WinScore = myWinScore;

        GamePlayer = new Player[NumPlayer];

        boolean FacingDown = true;
        boolean isHuman = true;
        boolean isComputer = false;

        // Create piles
        DiscardPile = new Pile(!FacingDown);
        DrawPile = new Pile(FacingDown);

       // GamePlayer[0] = new Player(isHuman);

        for (int i = 0 ; i < NumPlayer; i++){
            GamePlayer[i] = new Player(isComputer);
        }
    }

    public int GetHighestScore() {
          int HighestScore = 0;

          for (int i = 0; i < NumPlayer; i++) {
              if (GamePlayer[i].GetTotalScore() > HighestScore){
                  HighestScore = GamePlayer[i].GetTotalScore();
              }
          }

          return HighestScore;
    }

    public void GameOn(){
        while (GetHighestScore() < WinScore) {
            InitRound();
             while (RoundInProgress) {
                 ShowGame(PlayerInAction);
                 PlayerAct();
                 UpdateRound();
            }
            EndRound();
        }
        EndGame();
    }

    public void ShowGame(int PlayerID){
        Card myTopCard;
        myTopCard = DiscardPile.GetTop();
        boolean myself = true;

        System.out.print("Round: "+ NumRound);
        System.out.print(",  DrawPile: " + DrawPile.GetSize());
        System.out.print(",  DiscardPile: " + DiscardPile.GetSize());
        System.out.println(",  TopCard: " + myTopCard.getColorString() + " " +myTopCard.getNameOfCard());

        for (int i = 0; i < NumPlayer; i++){
            if (i == PlayerID)
                GamePlayer[i].ShowPlayer(myself);
            else
                GamePlayer[i].ShowPlayer(!myself);
            System.out.println();
        }
        System.out.println();
    }

    public Player GetRoundWinner(){
        int Winner = 0;

        for (int i = 0; i < NumPlayer; i++) {
            if (GamePlayer[i].GetHand().GetSize() == 0) {
                Winner = i;
            }
        }
        return GamePlayer[Winner];
    }



    public void EndGame(){
        int Winner = NumPlayer;

        System.out.println();
        System.out.println("End of Game");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("After " + NumRound + " round(s) of game");
        for (int i = 0; i < NumPlayer; i++){
            System.out.println(GamePlayer[i].GetNickname() + "'s total score " + GamePlayer[i].GetTotalScore());
            if (GamePlayer[i].GetTotalScore() >= WinScore)
                Winner = i;
        }
        System.out.println();
        System.out.println("The Winner is " + GamePlayer[Winner].GetNickname() );
        System.out.println("----------------------------------------------------------------------");
    }

}

/* =====================================================================================
 *       Filename:  PokerAgainstComputer.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - class of poker game human vs computer 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.io.PrintStream;

import standardInputDataPackage.GettingDataFromStandardInput;

public class PokerAgainstComputer {
   private static final String START_INFO = "This program is poker game's simulation for 2 players: human and computer.";
   
   private Card[] computerPlayerCards;
   private Card[] playerCards;
   
   private Card[] computerPlayerReturnedCards = null;
   private Card[] playerReturnedCards = null;
   
   private DeckOfCards deckOfCards;
   
   private Player computerPlayer;
   private Player player;
   
   private PrintStream printStream = System.out;
   private Score gameScore;
   private boolean computerPlayerCardsPrintingBeforeEndGame = false;
   
   private PokerAgainstComputer() {
      deckOfCards = new DeckOfCards();
      computerPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      playerCards = new Card[CardsConfiguration.POKER_CARDS];
      computerPlayer = new Player();
      player = new Player();
   }
   
   public PokerAgainstComputer(PrintStream printStream, boolean computerPlayerCardsPrintingBeforeEndGame) {
      this();
      this.printStream = printStream;
      this.computerPlayerCardsPrintingBeforeEndGame = computerPlayerCardsPrintingBeforeEndGame;
   }
   
   public Score getGameScore() {
      return gameScore;
   }
   
   private void firstHandOfCards() throws Exception {
      deckOfCards.shuffle(); // place Cards in random order
      
      dealCards(computerPlayerCards);
      computerPlayer.setCardsToConfiguration(computerPlayerCards);
      if (true == computerPlayerCardsPrintingBeforeEndGame) {
         printPockerHands(computerPlayer, "*******  Computer player cards before replace:");
      }
      
      dealCards(playerCards);
      player.setCardsToConfiguration(playerCards);
      printPockerHands(player, "*******  Player cards before replace:");
   }
   
   public void run() throws Exception {
      initializeReturnedCards();
      
      printStream.println(START_INFO);
      //printRemainedCards("---------- Remained cards in deck:");
      
      firstHandOfCards();
      replaceCardsForComputerPlayer();
      replaceCardsForPlayer();
      
      printPockerHands(computerPlayer, "*******  Computer player cards after replace:");
      printPockerHands(player, "*******  Player cards after replace:");
      printScore();
      
      checkCardsInDeck();
      returnCardsToDeck();
      //printRemainedCards("---------- Remained cards in deck:");
   }
   
   private void initializeReturnedCards() {
      computerPlayerReturnedCards = null;
      playerReturnedCards = null;
   }
   
   private void replaceCardsForComputerPlayer() throws Exception {
      int counter = computerPlayer.getNumberOfCardsToReplace();
      Card card;
      while (counter > 0) {
         card = deckOfCards.dealCard();
         computerPlayer.receiveCard(card);
         counter--;
      } 
      
      if (true == computerPlayer.areCardsToReturn()) {
         computerPlayerReturnedCards = computerPlayer.getReturnedCards();
         
         if (true == computerPlayerCardsPrintingBeforeEndGame) {
            printCards(computerPlayerReturnedCards, "---------- Computer player's cards to return:");
         }
      }
   }
   
   private int getCardsIndexesFromUser(int[] cardsIndexes) {
      final String PROMPT = String.format("C. Max %d cards can be replaced. Enter integer as index of card to replace: ",
                                                            Player.MAX_OF_REPLACED_CARDS);
      final String QUIT_INFO = "A. To quit - enter sequence other than integer";
      final String END_INFO = "B. To end of enter indexes - enter number less than zero";
      final String QUIT_END_PROMPT = String.format(" %s %n %s%n %s", QUIT_INFO, END_INFO, PROMPT);
      
      int indexCounter = 0;
      do {
         printIndexedCards(playerCards, "#######  Current player's cards:", cardsIndexes);
         cardsIndexes[indexCounter] = GettingDataFromStandardInput.getInteger(QUIT_END_PROMPT);
         
         if (cardsIndexes[indexCounter] < 0) {
            break;
         }
         else if (false == isCorrectIndex(cardsIndexes[indexCounter], cardsIndexes)) {
            continue;
         }
         
         indexCounter++;
         
      } while (indexCounter < Player.MAX_OF_REPLACED_CARDS);
      
      return indexCounter;
   }
   
   private void replaceCardsForPlayer() throws Exception {
      int[] cardsIndexes = new int[Player.MAX_OF_REPLACED_CARDS];
      assignValue(cardsIndexes, CardsConfiguration.POKER_CARDS);  // set initial incorrect values
      
      int indexCounter = getCardsIndexesFromUser(cardsIndexes);
      Card[] newCards = new Card[indexCounter];
      
      dealCards(newCards, indexCounter);
      player.receiveCards(newCards, cardsIndexes);
      
      if (true == player.areCardsToReturn()) {
         playerReturnedCards = player.getReturnedCards();
         printCards(playerReturnedCards, "---------- Player's cards to return:");
      }
   }
   
   private void printCards(Card[] cards, String title) {
      printStream.printf("%n%s%n", title);
      for (int counter = 0; counter < cards.length; counter++) {
         printStream.printf("%s %n", cards[counter]);
      } 
   }
   
   private void printIndexedCards(Card[] cards, String title, int[] cardsIndexes) {
      printStream.printf("%s%n", title);
      
      for (int index = 0; index < cards.length; index++) {
         if (0 == getFrequency(cardsIndexes, index)) {
            printStream.printf("%d. %s %n", index, cards[index]);
         }
      } 
   }
   
   private boolean isCorrectIndex(int index, int[] cardsIndexes) {
      if (index >= CardsConfiguration.POKER_CARDS) {
         printStream.printf("!!! index of %d is out of range. Try again %n%n", index);
         return false;
      }
      
      for (int counter = 0; counter < cardsIndexes.length; counter++) {
         
         if (1 < getFrequency(cardsIndexes, index)) {
            printStream.printf("!!! index of %d was entered previously. Try again%n%n", index);
            return false;
         }
      } 
      
      return true;
   }
   
   public static int getFrequency(int [] array, int value) {
      int frequency = 0;
      for (int element : array) {
         if (element == value) {
            frequency++;
         }
      }
      
      return frequency;
   }
   
   public static void assignValue(int[] array, int value) {
      for (int index = 0; index < array.length; index++) {
         array[index] = value;
      }
   }
   
   private void checkCardsInDeck() throws Exception {
      checkCardsInDeck(player);
      if (null != playerReturnedCards) {
         checkCardsInDeck(playerReturnedCards);
      }
      
      checkCardsInDeck(computerPlayer);
      if (null != computerPlayerReturnedCards) {
         checkCardsInDeck(computerPlayerReturnedCards);
      }
   }
   
   private void checkCardsInDeck(Player anyPlayer) throws Exception {
      CardsConfiguration configuration = anyPlayer.getCardsConfiguration();
      
      checkCardsInDeck(configuration.getDealingCards());
   }
   
   private void checkCardsInDeck(Card[] cards) {
      for (int index = 0; index < cards.length; index++) {
         deckOfCards.checkCardInDeck(cards[index]);
      }
   }
   
   private void printRemainedCards(String title) {
      printStream.printf("%n%s%n", title);
      
      int remainedCardsInDeck = deckOfCards.getCurrentNumberOfCards();
      Card[] dealingCards = new Card[remainedCardsInDeck];
      
      Card card = deckOfCards.dealCard();
      for (int counter = 0; card != null; counter++) {
         dealingCards[counter] = card;
         
         printStream.printf("%2d. %s %n", counter, dealingCards[counter]);
         card = deckOfCards.dealCard();
      } 
      
      returnCardsToDeck(dealingCards);
   }
   
   private void printPockerHands(Player player, String title) {
      CardsConfiguration configuration = player.getCardsConfiguration();
      printCards(configuration.getDealingCards(), title);
      PokerHand pokerHand = player.getPokerHand();
      printStream.printf("%s %s %n", " --- Poker hand of dealing cards:", pokerHand);
   }
   
   private void printScore() throws Exception {
      this.gameScore  = getScore();
      
      switch (gameScore) {
         case FIRST_PLAYER_WIN:
            printStream.println("   --- Score: Computer player wins");
            break;
         case SECOND_PLAYER_WIN:
            printStream.println("   --- Score: Human player wins");
            break;
         case DRAW:
            printStream.println("   --- Score: draw");
      }
   }
   
   private Score getScore() throws Exception {
      CardsConfiguration computerPlayerConfiguration = computerPlayer.getCardsConfiguration();
      CardsConfiguration playerConfiguration = player.getCardsConfiguration();
      GameResult gameResult = new GameResult(computerPlayerConfiguration, playerConfiguration);
      
      return gameResult.getScore();
   }
   
   private void dealCards(Card[] cards) {
      dealCards(cards, CardsConfiguration.POKER_CARDS);
   }
   
   private void dealCards(Card[] cards, int numberOfCards) {
      for (int index = 0; index < numberOfCards; index++) {
         cards[index] = deckOfCards.dealCard();
      } 
   }
   
   private void returnCardsToDeck() throws Exception {
      returnCardsToDeck(player);
      if (null != playerReturnedCards) {
         returnCardsToDeck(playerReturnedCards);
      }
      
      returnCardsToDeck(computerPlayer);
      if (null != computerPlayerReturnedCards) {
         returnCardsToDeck(computerPlayerReturnedCards);
      }
   }
   
   private void returnCardsToDeck(Player anyPlayer) throws Exception {
      CardsConfiguration configuration = anyPlayer.getCardsConfiguration();
      
      returnCardsToDeck(configuration.getDealingCards());
   }
   
   private void returnCardsToDeck(Card[] cards) {
      for (int index = 0; index < cards.length; index++) {
         deckOfCards.acceptCard(cards[index]);
      }
   }
}

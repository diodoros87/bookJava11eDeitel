/* =====================================================================================
 *       Filename:  PokerAgainstComputer.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - Card shuffling, dealing for 2 players (one 
                             of them is computerPlayer with option replace some cards) and
                                deciding about game's result 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.io.PrintStream;

import standardInputDataPackage.GettingDataFromStandardInput;

public class PokerAgainstComputer {
   public static final String START_INFO = "This program is poker game's simulation for 2 players: human and computer.";
   
   Card[] computerPlayerCards;
   Card[] playerCards;
   
   DeckOfCards deckOfCards;
   
   Player computerPlayer;
   Player player;
   
   PrintStream printStream;
   
   private PokerAgainstComputer() {
      deckOfCards = new DeckOfCards();
      computerPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      playerCards = new Card[CardsConfiguration.POKER_CARDS];
      computerPlayer = new Player();
      player = new Player();
   }
   
   public PokerAgainstComputer(PrintStream printStream) {
      this();
      this.printStream = printStream;
   }
   
   private void firstHandOfCards() throws Exception {
      deckOfCards.shuffle(); // place Cards in random order
      
      dealCards(computerPlayerCards);
      computerPlayer.setCardsToConfiguration(computerPlayerCards);
      printPockerHands(computerPlayer, "*******  Computer player cards before replace:");
      
      dealCards(playerCards);
      player.setCardsToConfiguration(playerCards);
      printPockerHands(player, "*******  Player cards before replace:");
   }
   
   public void run() throws Exception {
      printStream.println(START_INFO);
      
      firstHandOfCards();
      replaceCardsForComputerPlayer();
      replaceCardsForPlayer();
      
      printPockerHands(computerPlayer, "*******  Computer player cards after replace:");
      printPockerHands(player, "*******  Player cards after replace:");
      printGameResult();
      printRemainedCards("---------- Remained cards in deck:");
   }
   
   private void replaceCardsForComputerPlayer() throws Exception {
      int counter = computerPlayer.getNumberOfCardsToReplace();
      Card card;
      while (counter > 0) {
         card = deckOfCards.dealCard();
         computerPlayer.receiveCard(card);
         counter--;
      } 
   }
   
   private int getCardsIndexesFromUser(int[] cardsIndexes) {
      final String PROMPT = String.format("Enter integer as index of card to replace: ");
      final String QUIT_INFO = "To quit enter sequence other than integer";
      final String END_INFO = "To end of enter indexes enter number less than zero";
      final String QUIT_END_PROMPT = String.format(" %s%n %s%n %s", QUIT_INFO, END_INFO, PROMPT);
      
      int indexCounter = 0;
      do {
         printIndexedCards(playerCards, "Current player's cards:", cardsIndexes);
         cardsIndexes[indexCounter] = GettingDataFromStandardInput.getInteger(QUIT_END_PROMPT);
         
         if (cardsIndexes[indexCounter] < 0) {
            break;
         }
         else if (false == isCorrectIndex(cardsIndexes[indexCounter], cardsIndexes)) {
            continue;
         }
         
         indexCounter++;
         
      } while (indexCounter < CardsConfiguration.POKER_CARDS);
      
      return indexCounter;
   }
   
   private void replaceCardsForPlayer() throws Exception {
      int[] cardsIndexes = new int[CardsConfiguration.POKER_CARDS];
      assignValue(cardsIndexes, CardsConfiguration.POKER_CARDS);
      int indexCounter = getCardsIndexesFromUser(cardsIndexes);
      Card[] newCards = new Card[indexCounter];
      
      dealCards(newCards, indexCounter);
      player.receiveCards(newCards, cardsIndexes);
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
   
   private void printRemainedCards(String title) {
      printStream.printf("%n%s%n", title);
      
      Card card = deckOfCards.dealCard();
      for (int counter = 0; card != null; counter++) {
      
         for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
            deckOfCards.checkCardInDeck(computerPlayerCards[index]);
            deckOfCards.checkCardInDeck(playerCards[index]);
         }
         
         printStream.printf("%2d. %s %n", counter, card);
         card = deckOfCards.dealCard();
      } 
   }
   
   private void printPockerHands(Player player, String title) {
      CardsConfiguration configuration = player.getCardsConfiguration();
      printCards(configuration.getDealingCards(), title);
      PokerHand pokerHand = player.getPokerHand();
      printStream.printf("%s %s %n", " --- Poker hand of dealing cards:", pokerHand);
   }
   
   private void printGameResult() throws Exception {
      CardsConfiguration computerPlayerConfiguration = computerPlayer.getCardsConfiguration();
      CardsConfiguration playerConfiguration = player.getCardsConfiguration();
      GameResult gameResult = new GameResult(computerPlayerConfiguration, playerConfiguration);
      
      Score score  = gameResult.getScore();
      printStream.printf("   --- Score: %s %n", score);
   }
   
   private void dealCards(Card[] cards) {
      dealCards(cards, CardsConfiguration.POKER_CARDS);
   }
   
   private void dealCards(Card[] cards, int numberOfCards) {
      for (int index = 0; index < numberOfCards; index++) {
         cards[index] = deckOfCards.dealCard();
      } 
   }
   
   private void returnCardsToDeck(Card[] cards) {
      for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
         deckOfCards.acceptCard(cards[index]);
      }
   }
}

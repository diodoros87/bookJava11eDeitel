/* =====================================================================================
 *       Filename:  DeckOfCardsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.31 - Card shuffling, dealing for 2 players and
                                deciding about game's result
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DeckOfCardsTest {

   public static void main(String[] args) throws Exception {
      DeckOfCards deckOfCards = new DeckOfCards();
      deckOfCards.shuffle(); // place Cards in random order
      
      // print 5 Cards in the order in which they are dealt
//       for (int counter = 1; counter <= CardsConfiguration.POKER_CARDS; counter++) {
//          // deal and display a Card
//          System.out.printf("%s ", deckOfCards.dealCard());
//       } 
//       System.out.printf("%n %n ");
      
      test();
   }
   
   public static void displayCards(Card[] cards, String title) {
      System.out.printf("%n%s%n", title);
      for (int counter = 0; counter < cards.length; counter++) {
         System.out.printf("%s %n", cards[counter]);
      } 
      //System.out.printf("%n %n");
   }
   
   public static void printPockerHands(Card[] playerCards, CardsConfiguration.PokerHands expectedPokerHands)
                                                                              throws Exception {
      CardsConfiguration cardsConfiguration = new CardsConfiguration(playerCards);
      
      displayCards(playerCards, "*******  Dealing cards:");
      printPockerHands(cardsConfiguration, expectedPokerHands, " --- Poker hand of dealing cards:");
   }
   
    public static void printPockerHands(CardsConfiguration cardsConfiguration, CardsConfiguration.PokerHands expectedPokerHands, 
                                                                         String title) throws Exception {
      CardsConfiguration.PokerHands pokerHands = cardsConfiguration.classifyPokerHands();
      AssertTesting.assertPokerHands(pokerHands, expectedPokerHands);
      System.out.printf("%s %s %n", title, CardsConfiguration.getPokerHandsDescription(pokerHands));
   }
   
   public static void printGameResult(CardsConfiguration first, CardsConfiguration.PokerHands expectedFirstPokerHands,
                                      CardsConfiguration second, CardsConfiguration.PokerHands expectedSecondPokerHands,
                                      GameResult.Score expectedScore) throws Exception {
      System.out.printf(" ---------------------------------------------------------- %n");                              
      GameResult gameResult = new GameResult(first, second);                      
      displayCards(first.getDealingCards(), "*******  Dealing first cards:");
      printPockerHands(first, expectedFirstPokerHands, " --- Poker hand of first cards:");
      
      displayCards(second.getDealingCards(), "*******  Dealing second cards:");
      printPockerHands(second, expectedSecondPokerHands, " --- Poker hand of second cards:");
      
      GameResult.Score score  = gameResult.getScore();
      AssertTesting.assertScore(score, expectedScore);
      System.out.printf("   --- Score: %s %n", GameResult.getScoreDescription(score));
   }
   
   public static void test() throws Exception {
      Card[] playerCards = new Card[CardsConfiguration.POKER_CARDS];
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("Ace", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.FOUR_OF_KIND);
      CardsConfiguration first = new CardsConfiguration(playerCards);
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("King", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.THREE_OF_KIND);
      CardsConfiguration second = new CardsConfiguration(playerCards);
      
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     GameResult.Score.FIRST_PLAYER_WIN);
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("Deuce", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.FULL_HOUSE);
      CardsConfiguration third = new CardsConfiguration(playerCards);
      
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     GameResult.Score.SECOND_PLAYER_WIN);
       
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Deuce", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration fourth = new CardsConfiguration(playerCards);
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.TWO_PAIRS);
      
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     GameResult.Score.FIRST_PLAYER_WIN);
                     
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     GameResult.Score.DRAW);
       
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Queen", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration fifth = new CardsConfiguration(playerCards);
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.ONE_PAIR);
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     GameResult.Score.FIRST_PLAYER_WIN);
       
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Queen", "Diamonds");
      playerCards[2] = new Card("Five", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.HIGH_CARD);
      CardsConfiguration sixth = new CardsConfiguration(playerCards);
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     GameResult.Score.FIRST_PLAYER_WIN);
      
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[0] = new Card("Four", "Spades");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
//       printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT_FLUSH);
      CardsConfiguration seventh = new CardsConfiguration(playerCards);
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
       
      playerCards[0] = new Card("Jack", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.FLUSH);
      CardsConfiguration eighth = new CardsConfiguration(playerCards);
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     eighth, CardsConfiguration.PokerHands.FLUSH,
                     GameResult.Score.FIRST_PLAYER_WIN);
      
      playerCards[0] = new Card("Six", "Spades");
      playerCards[3] = new Card("Four", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration ninth = new CardsConfiguration(playerCards);
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(eighth, CardsConfiguration.PokerHands.FLUSH,
                     ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     GameResult.Score.SECOND_PLAYER_WIN);
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT_FLUSH);
       
      playerCards[0] = new Card("Six", "Clubs");
      playerCards[3] = new Card("Four", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT);
      CardsConfiguration tenth = new CardsConfiguration(playerCards);
      printGameResult(first, CardsConfiguration.PokerHands.FOUR_OF_KIND,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(second, CardsConfiguration.PokerHands.THREE_OF_KIND,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(third, CardsConfiguration.PokerHands.FULL_HOUSE,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, CardsConfiguration.PokerHands.TWO_PAIRS,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, CardsConfiguration.PokerHands.ONE_PAIR,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, CardsConfiguration.PokerHands.HIGH_CARD,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(eighth, CardsConfiguration.PokerHands.FLUSH,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.FIRST_PLAYER_WIN);
      printGameResult(ninth, CardsConfiguration.PokerHands.STRAIGHT_FLUSH,
                     tenth, CardsConfiguration.PokerHands.STRAIGHT,
                     GameResult.Score.FIRST_PLAYER_WIN);
       
//       playerCards[0] = new Card("Ace", "Spades");
//       playerCards[2] = new Card("King", "Spades");
//       playerCards[1] = new Card("Three", "Spades");
//       playerCards[3] = new Card("Four", "Spades");
//       playerCards[4] = new Card("Deuce", "Spades");
//       printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT_FLUSH);
       
//       playerCards[0] = new Card("King", "Spades");
//       playerCards[1] = new Card("Queen", "Spades");
//       playerCards[2] = new Card("Jack", "Spades");
//       playerCards[3] = new Card("Ten", "Spades");
//       playerCards[4] = new Card("Ace", "Spades");
//       printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT_FLUSH);
//       
//       playerCards[0] = new Card("King", "Spades");
//       playerCards[1] = new Card("Queen", "Spades");
//       playerCards[2] = new Card("Jack", "Spades");
//       playerCards[3] = new Card("Ten", "Spades");
//       playerCards[4] = new Card("Nine", "Spades");
//       printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT_FLUSH);
       
//       playerCards[2] = new Card("Exception", "Spades");
//       printPockerHands(playerCards, CardsConfiguration.PokerHands.STRAIGHT_FLUSH);
   }
} 

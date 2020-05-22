/* =====================================================================================
 *       Filename:  DeckOfCardsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - Card shuffling, dealing for 2 players (one 
                             of them is dealer with option replace some cards) and
                                deciding about game's result 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DeckOfCardsTest {

   public static void main(String[] args) throws Exception {
      //replaceCards();
   
      Card[] firstPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      Card[] secondPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      
      for (int counter = 1; counter <= 10; counter++) {
         System.out.printf("$$$$$$$$$$$$$$$$$$ %3d. TEST WITH CARDS SHUFFLING:%n %n ", counter);
         shuffleTest(firstPlayerCards, secondPlayerCards);
      }
      
      closeEqualsCardsTest();
      test();
      
   }
   
   public static void displayCards(Card[] cards, String title) {
      System.out.printf("%n%s%n", title);
      for (int counter = 0; counter < cards.length; counter++) {
         System.out.printf("%s %n", cards[counter]);
      } 
   }
   
   public static void shuffleTest(Card[] firstPlayerCards, Card[] secondPlayerCards) throws Exception  {
      DeckOfCards deckOfCards = new DeckOfCards();
      deckOfCards.shuffle(); // place Cards in random order
      
      dealCards(firstPlayerCards, deckOfCards);
      CardsConfiguration firstConfiguration = new CardsConfiguration(firstPlayerCards);
      dealCards(secondPlayerCards, deckOfCards);
      CardsConfiguration secondConfiguration = new CardsConfiguration(secondPlayerCards);
      
      displayPockerHands(firstConfiguration, firstPlayerCards, "*******  First player cards:");
      displayPockerHands(secondConfiguration, secondPlayerCards, "*******  Second player cards:");
      
      displayGameResult(firstConfiguration, secondConfiguration);
      
   }
   
   public static void displayPockerHands(CardsConfiguration configuration, Card[] playerCards, String title) throws Exception {
      displayCards(playerCards, title);
      PokerHand pokerHand = configuration.classifyPokerHand();
      System.out.printf("%s %s %n", " --- Poker hand of dealing cards:", pokerHand);
   }
   
   public static void displayGameResult(CardsConfiguration firstConfiguration, CardsConfiguration secondConfiguration) throws Exception  {
      GameResult gameResult = new GameResult(firstConfiguration, secondConfiguration);
      
      Score score  = gameResult.getScore();
      System.out.printf("   --- Score: %s %n", score);
   }
   
   public static void dealCards(Card[] cards, DeckOfCards deckOfCards) {
      for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
         // deal and display a Card
         cards[index] = deckOfCards.dealCard();
      } 
   }
   
   public static void printPockerHands(Card[] playerCards, PokerHand expectedPokerHand)
                                                                              throws Exception {
      CardsConfiguration cardsConfiguration = new CardsConfiguration(playerCards);
      
      displayCards(playerCards, "*******  Dealing cards:");
      printPockerHands(cardsConfiguration, expectedPokerHand, " --- Poker hand of dealing cards:");
   }
   
    public static void printPockerHands(CardsConfiguration cardsConfiguration, PokerHand expectedPokerHand, 
                                                                         String title) throws Exception {
      PokerHand pokerHand = cardsConfiguration.classifyPokerHand();
      AssertTesting.assertPokerHand(pokerHand, expectedPokerHand);
      System.out.printf("%s %s %n", title, pokerHand);
   }
   
   public static void printGameResult(CardsConfiguration first, PokerHand expectedFirstPokerHand,
                                      CardsConfiguration second, PokerHand expectedSecondPokerHand,
                                      Score expectedScore) throws Exception {
      System.out.printf(" ---------------------------------------------------------- %n");                              
      GameResult gameResult = new GameResult(first, second);                      
      displayCards(first.getDealingCards(), "*******  Dealing first cards:");
      printPockerHands(first, expectedFirstPokerHand, " --- Poker hand of first cards:");
      
      displayCards(second.getDealingCards(), "*******  Dealing second cards:");
      printPockerHands(second, expectedSecondPokerHand, " --- Poker hand of second cards:");
      
      Score score  = gameResult.getScore();
      AssertTesting.assertScore(score, expectedScore);
      System.out.printf("   --- Score: %s %n", score);
   }
   
   public static void closeEqualsCardsTest() throws Exception {
      Card[] playerCards = new Card[CardsConfiguration.POKER_CARDS];
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("King", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.THREE_OF_KIND);
      CardsConfiguration first = new CardsConfiguration(playerCards);
      
      playerCards[0] = new Card("Three", "Hearts");
      playerCards[1] = new Card("King", "Diamonds");
      playerCards[2] = new Card("Three", "Clubs");
      playerCards[3] = new Card("Three", "Spades");
      playerCards[4] = new Card("Four", "Spades");
      //printPockerHands(playerCards, PokerHand.THREE_OF_KIND);
      CardsConfiguration second = new CardsConfiguration(playerCards);
      
      printGameResult(first, PokerHand.THREE_OF_KIND,
                     second, PokerHand.THREE_OF_KIND,
                     Score.FIRST_PLAYER_WIN);
      
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Deuce", "Clubs");
      playerCards[2] = new Card("Ace", "Diamonds");
      playerCards[3] = new Card("Ace", "Hearts");
      playerCards[4] = new Card("Deuce", "Hearts");
      CardsConfiguration third = new CardsConfiguration(playerCards);
      
      playerCards[0] = new Card("Five", "Clubs");
      playerCards[1] = new Card("Deuce", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration fourth = new CardsConfiguration(playerCards);
      
      printGameResult(third, PokerHand.TWO_PAIRS,
                     fourth, PokerHand.TWO_PAIRS,
                     Score.SECOND_PLAYER_WIN);
                     
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Three", "Clubs");
      playerCards[2] = new Card("Ace", "Diamonds");
      playerCards[3] = new Card("Ace", "Hearts");
      playerCards[4] = new Card("Three", "Hearts");
      CardsConfiguration fifth = new CardsConfiguration(playerCards);
      
      printGameResult(fifth, PokerHand.TWO_PAIRS,
                     fourth, PokerHand.TWO_PAIRS,
                     Score.FIRST_PLAYER_WIN);
                     
      playerCards[0] = new Card("Jack", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.FLUSH);
      CardsConfiguration sixth = new CardsConfiguration(playerCards);
      
      playerCards[0] = new Card("Ten", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.FLUSH);
      CardsConfiguration seventh = new CardsConfiguration(playerCards);
      
      printGameResult(sixth, PokerHand.FLUSH,
                     seventh, PokerHand.FLUSH,
                     Score.FIRST_PLAYER_WIN);
   }
   
   public static void test() throws Exception {
      Card[] playerCards = new Card[CardsConfiguration.POKER_CARDS];
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("Ace", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.FOUR_OF_KIND);
      CardsConfiguration first = new CardsConfiguration(playerCards);
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("King", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.THREE_OF_KIND);
      CardsConfiguration second = new CardsConfiguration(playerCards);
      
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     second, PokerHand.THREE_OF_KIND,
                     Score.FIRST_PLAYER_WIN);
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("Deuce", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.FULL_HOUSE);
      CardsConfiguration third = new CardsConfiguration(playerCards);
      
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     third, PokerHand.FULL_HOUSE,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     third, PokerHand.FULL_HOUSE,
                     Score.SECOND_PLAYER_WIN);
       
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Deuce", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration fourth = new CardsConfiguration(playerCards);
      //printPockerHands(playerCards, PokerHand.TWO_PAIRS);
      
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     fourth, PokerHand.TWO_PAIRS,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     fourth, PokerHand.TWO_PAIRS,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     fourth, PokerHand.TWO_PAIRS,
                     Score.FIRST_PLAYER_WIN);
                     
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     fourth, PokerHand.TWO_PAIRS,
                     Score.DRAW);
       
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Queen", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration fifth = new CardsConfiguration(playerCards);
      //printPockerHands(playerCards, PokerHand.ONE_PAIR);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     fifth, PokerHand.ONE_PAIR,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     fifth, PokerHand.ONE_PAIR,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     fifth, PokerHand.ONE_PAIR,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     fifth, PokerHand.ONE_PAIR,
                     Score.FIRST_PLAYER_WIN);
       
      playerCards[0] = new Card("Four", "Clubs");
      playerCards[1] = new Card("Queen", "Diamonds");
      playerCards[2] = new Card("Five", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.HIGH_CARD);
      CardsConfiguration sixth = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     sixth, PokerHand.HIGH_CARD,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     sixth, PokerHand.HIGH_CARD,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     sixth, PokerHand.HIGH_CARD,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     sixth, PokerHand.HIGH_CARD,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     sixth, PokerHand.HIGH_CARD,
                     Score.FIRST_PLAYER_WIN);
      
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[0] = new Card("Four", "Spades");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
//       printPockerHands(playerCards, PokerHand.STRAIGHT_FLUSH);
      CardsConfiguration seventh = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     seventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     seventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     seventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     seventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     seventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     seventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
       
      playerCards[0] = new Card("Jack", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.FLUSH);
      CardsConfiguration eighth = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     eighth, PokerHand.FLUSH,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     eighth, PokerHand.FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     eighth, PokerHand.FLUSH,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     eighth, PokerHand.FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     eighth, PokerHand.FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     eighth, PokerHand.FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, PokerHand.STRAIGHT_FLUSH,
                     eighth, PokerHand.FLUSH,
                     Score.FIRST_PLAYER_WIN);
      
      playerCards[0] = new Card("Six", "Spades");
      playerCards[3] = new Card("Four", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      CardsConfiguration ninth = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, PokerHand.STRAIGHT_FLUSH,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eighth, PokerHand.FLUSH,
                     ninth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      //printPockerHands(playerCards, PokerHand.STRAIGHT_FLUSH);
       
      playerCards[0] = new Card("Six", "Clubs");
      playerCards[3] = new Card("Four", "Spades");
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.STRAIGHT);
      CardsConfiguration tenth = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     tenth, PokerHand.STRAIGHT,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     tenth, PokerHand.STRAIGHT,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     tenth, PokerHand.STRAIGHT,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     tenth, PokerHand.STRAIGHT,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     tenth, PokerHand.STRAIGHT,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     tenth, PokerHand.STRAIGHT,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, PokerHand.STRAIGHT_FLUSH,
                     tenth, PokerHand.STRAIGHT,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(eighth, PokerHand.FLUSH,
                     tenth, PokerHand.STRAIGHT,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(ninth, PokerHand.STRAIGHT_FLUSH,
                     tenth, PokerHand.STRAIGHT,
                     Score.FIRST_PLAYER_WIN);
       
      playerCards[0] = new Card("Ace", "Spades");
      playerCards[2] = new Card("King", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[3] = new Card("Four", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      //printPockerHands(playerCards, PokerHand.STRAIGHT_FLUSH);
      CardsConfiguration eleventh = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, PokerHand.STRAIGHT_FLUSH,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eighth, PokerHand.FLUSH,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(ninth, PokerHand.STRAIGHT_FLUSH,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(tenth, PokerHand.STRAIGHT,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eleventh, PokerHand.STRAIGHT_FLUSH,
                     eleventh, PokerHand.STRAIGHT_FLUSH,
                     Score.DRAW);
      
       
      playerCards[0] = new Card("King", "Spades");
      playerCards[1] = new Card("Queen", "Spades");
      playerCards[2] = new Card("Jack", "Spades");
      playerCards[3] = new Card("Ten", "Spades");
      playerCards[4] = new Card("Ace", "Spades");
      CardsConfiguration twelfth = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, PokerHand.STRAIGHT_FLUSH,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eighth, PokerHand.FLUSH,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(ninth, PokerHand.STRAIGHT_FLUSH,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(tenth, PokerHand.STRAIGHT,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eleventh, PokerHand.STRAIGHT_FLUSH,
                     twelfth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      //printPockerHands(playerCards, PokerHand.STRAIGHT_FLUSH);
      
      playerCards[0] = new Card("King", "Spades");
      playerCards[1] = new Card("Queen", "Spades");
      playerCards[2] = new Card("Jack", "Spades");
      playerCards[3] = new Card("Ten", "Spades");
      playerCards[4] = new Card("Nine", "Spades");
      CardsConfiguration thirteenth = new CardsConfiguration(playerCards);
      printGameResult(first, PokerHand.FOUR_OF_KIND,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(second, PokerHand.THREE_OF_KIND,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(third, PokerHand.FULL_HOUSE,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fourth, PokerHand.TWO_PAIRS,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(fifth, PokerHand.ONE_PAIR,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(sixth, PokerHand.HIGH_CARD,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(seventh, PokerHand.STRAIGHT_FLUSH,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eighth, PokerHand.FLUSH,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(ninth, PokerHand.STRAIGHT_FLUSH,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(tenth, PokerHand.STRAIGHT,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.SECOND_PLAYER_WIN);
      printGameResult(eleventh, PokerHand.STRAIGHT_FLUSH,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.FIRST_PLAYER_WIN);
      printGameResult(twelfth, PokerHand.STRAIGHT_FLUSH,
                     thirteenth, PokerHand.STRAIGHT_FLUSH,
                     Score.FIRST_PLAYER_WIN);
      //printPockerHands(playerCards, PokerHand.STRAIGHT_FLUSH);
       
      playerCards[2] = new Card("King", "Spades");
      CardsConfiguration exception = new CardsConfiguration(playerCards);
       //printPockerHands(playerCards, PokerHand.STRAIGHT_FLUSH);
   }
} 

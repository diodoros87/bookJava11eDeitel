/* =====================================================================================
 *       Filename:  GameResult.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.31 - class represents rule of game's result
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class GameResult {
   
   public static final CardsConfiguration.PokerHands [] CARDS_CONFIGURATIONS_CLASSIFICATION = 
   { 
      CardsConfiguration.PokerHands.HIGH_CARD,
      CardsConfiguration.PokerHands.ONE_PAIR,
      CardsConfiguration.PokerHands.TWO_PAIRS,
      CardsConfiguration.PokerHands.THREE_OF_KIND,
      CardsConfiguration.PokerHands.STRAIGHT,
      CardsConfiguration.PokerHands.FLUSH,
      CardsConfiguration.PokerHands.FULL_HOUSE,
      CardsConfiguration.PokerHands.FOUR_OF_KIND,
      CardsConfiguration.PokerHands.STRAIGHT_FLUSH
   };
   
   public enum Score {FIRST_PLAYER_WIN, SECOND_PLAYER_WIN, DRAW};
   
   CardsConfiguration firstPlayerCards;
   CardsConfiguration secondPlayerCards;
   
   public GameResult (CardsConfiguration firstPlayerCards, CardsConfiguration secondPlayerCards) {
      checkNullPointer(firstPlayerCards);
      checkNullPointer(secondPlayerCards);
      
      this.firstPlayerCards = firstPlayerCards;
      this.secondPlayerCards = secondPlayerCards;
   }
   
   public void setFirstPlayerCards (CardsConfiguration firstPlayerCards) {
      checkNullPointer(firstPlayerCards);
      this.firstPlayerCards = firstPlayerCards;
   }
   
   public void setSecondPlayerCards (CardsConfiguration secondPlayerCards) {
      checkNullPointer(secondPlayerCards);
      this.secondPlayerCards = secondPlayerCards;
   }
   
   public void checkNullPointer(CardsConfiguration cards) {
      if (null == cards) {
         throw new NullPointerException("CardsConfiguration can not be null");
      }
   }
   
   public Score getScore() throws Exception {
      CardsConfiguration.PokerHands firstPokerHand = firstPlayerCards.classifyPokerHands();
      CardsConfiguration.PokerHands secondPokerHand = secondPlayerCards.classifyPokerHands();
      
      int firstIndex = getPokerHandsIndex(firstPokerHand);
      int secondIndex = getPokerHandsIndex(secondPokerHand);
      
      if (firstIndex > secondIndex) {
         return Score.FIRST_PLAYER_WIN;
      }
      else if (firstIndex < secondIndex) {
         return Score.SECOND_PLAYER_WIN;
      }
      else {
         return getScore(firstPokerHand);
      }
   }
   
   private Score getScore(CardsConfiguration.PokerHands pokerHand) throws Exception {
      int[] firstFacesFrequency = DeckOfCards.calculateFacesFrequency(firstPlayerCards.getDealingCards());
      int[] secondFacesFrequency = DeckOfCards.calculateFacesFrequency(secondPlayerCards.getDealingCards());
      
      switch (pokerHand) {
         case HIGH_CARD:
            return getScoreHighCard(firstFacesFrequency, secondFacesFrequency);
         case ONE_PAIR:
            return getScoreOnePair(firstFacesFrequency, secondFacesFrequency);
         case TWO_PAIRS:
            return getScoreTwoPairs(firstFacesFrequency, secondFacesFrequency);
         case THREE_OF_KIND:
            return getScoreNumberOfKind(firstFacesFrequency, secondFacesFrequency, 3);
         case STRAIGHT:
            return getScoreStraight();
         case FLUSH:
            return getScoreHighCard(firstFacesFrequency, secondFacesFrequency);
         case FULL_HOUSE:
            return getScoreNumberOfKind(firstFacesFrequency, secondFacesFrequency, 3);
         case FOUR_OF_KIND:
            return getScoreNumberOfKind(firstFacesFrequency, secondFacesFrequency, 4);
         case STRAIGHT_FLUSH:
            return getScoreStraight();
         default:
            throw new IllegalArgumentException("Unrecognized poker hand");
      }
   }
   
   private Score getScoreStraight() {
      int[] firstFacesIndexes = CardsConfiguration.getFacesIndexes(firstPlayerCards.getDealingCards());
      int[] secondFacesIndexes = CardsConfiguration.getFacesIndexes(secondPlayerCards.getDealingCards());
      
      CardsConfiguration.sortAsc(firstFacesIndexes);
      CardsConfiguration.sortAsc(secondFacesIndexes);
      
       Score aceLowRankScore = getScoreStraightAceLowRank(firstFacesIndexes, secondFacesIndexes);
       if (Score.DRAW != aceLowRankScore) {
         return aceLowRankScore;
       }
      // assume that in two arrays are only values: 1 and 0
      for (int faceIndex = CardsConfiguration.POKER_CARDS - 1; faceIndex >= 0 ; faceIndex--) {
         int firstFacesIndex = firstFacesIndexes[faceIndex];
         int secondFacesIndex = secondFacesIndexes[faceIndex];
         
         if (firstFacesIndex > secondFacesIndex) {
            return Score.FIRST_PLAYER_WIN;
         }
         if (firstFacesIndex < secondFacesIndex) {
            return Score.SECOND_PLAYER_WIN;
         }
      }
      
      return Score.DRAW;
   }
   
   // in case of ace is low rank then this is minimum possible straight
   private Score getScoreStraightAceLowRank(int[] firstFacesIndexes, int[] secondFacesIndexes) {
      boolean firstAceLowRank = isAceLowRank(firstFacesIndexes);
      boolean secondAceLowRank = isAceLowRank(secondFacesIndexes);
      
      if (true == firstAceLowRank && true == secondAceLowRank) {
         return Score.DRAW;
      }
      else if (true == firstAceLowRank) {
         return Score.SECOND_PLAYER_WIN;
      }
      else if (true == secondAceLowRank){ // (true == secondAceLowRank) {
         return Score.FIRST_PLAYER_WIN;
      }
      else {
         return Score.DRAW;
      }
   }
   
   private boolean isAceLowRank(int[] facesIndexes) {
      if (facesIndexes[CardsConfiguration.POKER_CARDS - 1] == DeckOfCards.getFaceIndex("Ace")) {
         if (facesIndexes[CardsConfiguration.POKER_CARDS - 2] == DeckOfCards.getFaceIndex("Five")) {
         
            return true;
         }
      }
      
      return false;
   }
   
   private Score getScoreHighCard(int[] firstFacesFrequency, int[] secondFacesFrequency) {
      int firstFrequency;
      int secondFrequency;
      // assume that in two arrays are only values: 1 and 0
      for (int faceIndex = DeckOfCards.FACES.length - 1; faceIndex >= 0 ; faceIndex--) {
         firstFrequency = firstFacesFrequency[faceIndex];
         secondFrequency = secondFacesFrequency[faceIndex];
         
         if (firstFrequency > secondFrequency) {
            return Score.FIRST_PLAYER_WIN;
         }
         if (secondFrequency > firstFrequency) {
            return Score.SECOND_PLAYER_WIN;
         }
      }
      
      return Score.DRAW;
   }
   
   private Score getScoreOnePair(int[] firstFacesFrequency, int[] secondFacesFrequency) {
      int firstFrequency;
      int secondFrequency;
      // assume that in two arrays are only values: 2, 1 and 0
      for (int faceIndex = DeckOfCards.FACES.length - 1; faceIndex >= 0 ; faceIndex--) {
         firstFrequency = firstFacesFrequency[faceIndex];
         secondFrequency = secondFacesFrequency[faceIndex];
         
         if (2 == firstFrequency) {
            if (firstFrequency > secondFrequency) {
               return Score.FIRST_PLAYER_WIN;
            }
            else {  // in case of firstFrequency == secondFrequency

               return getScoreHighCard(firstFacesFrequency, secondFacesFrequency);
            }
         }
         if (2 == secondFrequency) {
            return Score.SECOND_PLAYER_WIN;
         }
      }
      
      return Score.DRAW;
   }
   
   private Score getScoreTwoPairs(int[] firstFacesFrequency, int[] secondFacesFrequency) {
      int firstFrequency;
      int secondFrequency;
      boolean findingFirstPair = false;
      // assume that in two arrays are only values: 2, 1 and 0
      for (int faceIndex = DeckOfCards.FACES.length - 1; faceIndex >= 0 ; faceIndex--) {
         firstFrequency = firstFacesFrequency[faceIndex];
         secondFrequency = secondFacesFrequency[faceIndex];
         
         if (2 == firstFrequency) {
            if (firstFrequency > secondFrequency) {
               return Score.FIRST_PLAYER_WIN;
            }
            else { // in case of firstFrequency == secondFrequency
               if (true == findingFirstPair) {   // in case of 2 identical faces pairs
               
                  return getScoreHighCard(firstFacesFrequency, secondFacesFrequency);
               }
               
               findingFirstPair = true;
            }
         }
         else if (2 == secondFrequency) {  // in case of firstFrequency < secondFrequency
            return Score.SECOND_PLAYER_WIN;
         }
      }
      
      return Score.DRAW;
   }
   
   private Score getScoreNumberOfKind(int[] firstFacesFrequency, int[] secondFacesFrequency, int number)
                                                      throws Exception {
      int firstFrequency;
      int secondFrequency;
      // assume that in two arrays are only values: 4, 3, (1 for three of kind, otherwise 2 for full house) and 0
      for (int faceIndex = DeckOfCards.FACES.length - 1; faceIndex >= 0 ; faceIndex--) {
         firstFrequency = firstFacesFrequency[faceIndex];
         secondFrequency = secondFacesFrequency[faceIndex];
         
         if (number == firstFrequency) {
            return Score.FIRST_PLAYER_WIN;
         }
         
         if (number == secondFrequency) {
            return Score.SECOND_PLAYER_WIN;
         }
      }
      
      assert(false) : "Program is running in unexpected case";
      throw new Exception("Program is running in unexpected case in getScoreThreeOfKind");
   }
   
   public static int getPokerHandsIndex(CardsConfiguration.PokerHands hand) {
      int index = 0;
      for (CardsConfiguration.PokerHands value : CARDS_CONFIGURATIONS_CLASSIFICATION) {
         if (value == hand) {
            return index;
         }
         
         index++;
      }
      
      throw new IllegalArgumentException("Unrecognized poker hands");
   }
   
   public static String getScoreDescription(Score score) {
      switch (score) {
         case FIRST_PLAYER_WIN:
            return "first player wins";
         case SECOND_PLAYER_WIN:
            return "second player wins";
         case DRAW:
            return "draw";
         default:
            throw new IllegalArgumentException("Unrecognized score");
      }
         
   }
   
} 


/* =====================================================================================
 *       Filename:  CrapsStatistics.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.18 - statistics of results 
                                generated during games simulations
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class CrapsStatistics {
   
   public static final int MIN_TURN = 1;
   public static final int MAX_TURN = 20;
   public static final int TURN_OF_ROLLING_RANGES = 2 + MAX_TURN; // 2 because additional range to include turns beyond MAX_TURN
   // turns beyond MAX_TURN are included to last range
   
   private int[] winsTurnRollingFrequency  = new int[TURN_OF_ROLLING_RANGES];
   private int[] losesTurnRollingFrequency = new int[TURN_OF_ROLLING_RANGES];
   
   private long simulationsNumber;
   
   private long allTurnsNumber = 0;
   
   public void setSimulationsNumber(int simulationsNumber) {
      if (simulationsNumber <= 0) {
         throw new IllegalArgumentException("Number of simulations must be greater than zero");
      }
      
      this.simulationsNumber = simulationsNumber;
   }
   
   public void increaseWinsFrequency(int turn) {
      if (turn >= MIN_TURN && turn <= MAX_TURN) {
         ++winsTurnRollingFrequency[turn];
      }
      else if (turn > MAX_TURN) {
         ++winsTurnRollingFrequency[MAX_TURN + 1];
      }
   }
   
   public void increaseLosesFrequency(int turn) {
      if (turn >= MIN_TURN && turn <= MAX_TURN) {
         ++losesTurnRollingFrequency[turn];
      }
      else if (turn > MAX_TURN) {
         ++losesTurnRollingFrequency[MAX_TURN + 1];
      }
   }
   
   public void resetFrequency() {
      setValuesInArray(winsTurnRollingFrequency, 0);
      setValuesInArray(losesTurnRollingFrequency, 0);
   }
   
   public static void setValuesInArray(int[] array, int value) {
      final int LENGTH = array.length;
      for (int index = 0; index < LENGTH; index++) {
         array[index] = value;
      }
   }
   
   public long getWinsSum() {
      return getSumArrayValues(winsTurnRollingFrequency, MIN_TURN, MAX_TURN + 1);
   }
   
   public long getLosesSum() {
      return getSumArrayValues(losesTurnRollingFrequency, MIN_TURN, MAX_TURN + 1);
   }
   
   public double getAverageOfTurnsNumber() {
      return allTurnsNumber / (double)simulationsNumber;
   }
   
   private double getFrequencyPercentage(long frequency, long total) {
      return 100.0 * frequency / total;
   }
   
   public static long getSumArrayValues(int[] array, int begin, int last) {
      final int LENGTH = array.length;
      long sum = 0;
      
      if (last >= LENGTH) {
         throw new ArrayIndexOutOfBoundsException("last index >= array.length");
      }
      if (begin < 0) {
         throw new IllegalArgumentException("begin index < 0");
      }
      if (last < 0) {
         throw new IllegalArgumentException("last index < 0");
      }
      
      for (int index = begin; index <= last; index++) {
         sum += array[index];
      }
      
      return sum;
   }
   
   public void run(Craps game) { 
      if (null == game) {
         throw new NullPointerException("reference to game is null");
      }
      
      Craps.Status status;
      
      for (int counter = 0, turn = 0; counter < simulationsNumber; counter++) {
         game.runGame();
         
         status = game.getGameStatus();
         turn = game.getEndGameTurn();
         
         if (Craps.Status.WON == status) {
            increaseWinsFrequency(turn);
         }
         else if (Craps.Status.LOST == status){
            increaseLosesFrequency(turn);
         }
         
         allTurnsNumber += turn;
      }
         
   }
   
   public void printResultsInTurns() { 
      System.out.printf("%nFrequency results generated during %,d games simulations according to %n", simulationsNumber);
      System.out.println("number of turns needed to ending game have been printed below: ");
      System.out.printf("%20s %20s %20s%n", "Turn", "Wins", "Loses");
      
      for (int index = MIN_TURN; index <= MAX_TURN; index++) {
         System.out.printf("%20s %,20d %,20d%n", index, winsTurnRollingFrequency[index], losesTurnRollingFrequency[index]);
      }
      
      System.out.printf("%20s %,20d %,20d%n", String.format("after %d turn", MAX_TURN), 
                            winsTurnRollingFrequency[MAX_TURN + 1], losesTurnRollingFrequency[MAX_TURN + 1]);

   }
   
   public void printOverallStatistics() { 
      System.out.printf("%nResults generated during %,d games simulations have been printed below: %n", simulationsNumber);
      printGameEndingBetweenTurnsStatistics(MIN_TURN, MAX_TURN + 1, "All wins", " All loses");
      
      System.out.printf("%nAverage of number of turns needed to ending game is %.4f%n%n", getAverageOfTurnsNumber());
      
      printGameEndingBetweenTurnsStatistics(MIN_TURN, MIN_TURN, "Wins in first turn", "Loses in first turn");
      
      printGameEndingBetweenTurnsStatistics(MIN_TURN + 1, MAX_TURN + 1, "Wins after first turn", "Loses after first turn");
   }
   
   public void printStatisticsAfterTurns() { 
      int turn = MIN_TURN;
      String title = "in first turn";
      long winsBetweenTurns  = winsTurnRollingFrequency[MIN_TURN];
      long losesBetweenTurns = losesTurnRollingFrequency[MIN_TURN];;
      long gamesEndingBetweenTurns = winsBetweenTurns + losesBetweenTurns;
      double winsBetweenTurnsPercent  = getFrequencyPercentage(winsBetweenTurns, gamesEndingBetweenTurns);
      double losesBetweenTurnsPercent = getFrequencyPercentage(losesBetweenTurns, gamesEndingBetweenTurns);
      
      while (turn <= MAX_TURN) {
         printGameEndingBetweenTurnsStatistics("Wins " + title, "Loses " + title, winsBetweenTurns,
            losesBetweenTurns, winsBetweenTurnsPercent, losesBetweenTurnsPercent);
            
         turn++;
         title = String.format("in first %d turns", turn);
         winsBetweenTurns  += winsTurnRollingFrequency[turn];
         losesBetweenTurns += losesTurnRollingFrequency[turn];;
         gamesEndingBetweenTurns = winsBetweenTurns + losesBetweenTurns;
         winsBetweenTurnsPercent  = getFrequencyPercentage(winsBetweenTurns, gamesEndingBetweenTurns);
         losesBetweenTurnsPercent = getFrequencyPercentage(losesBetweenTurns, gamesEndingBetweenTurns);
      }
      
      title = String.format("in first %d turns and after", MAX_TURN + 1);
      printGameEndingBetweenTurnsStatistics("Wins " + title, "Loses " + title, winsBetweenTurns,
            losesBetweenTurns, winsBetweenTurnsPercent, losesBetweenTurnsPercent);
   }
   
   private void printGameEndingBetweenTurnsStatistics(int beginTurn, int lastTurn, final String WINS_TITLE, final String LOSES_TITLE) {
      long winsBetweenTurns = getSumArrayValues(winsTurnRollingFrequency, beginTurn, lastTurn);
      long losesBetweenTurns = getSumArrayValues(losesTurnRollingFrequency, beginTurn, lastTurn);
      long gamesEndingBetweenTurns = winsBetweenTurns + losesBetweenTurns;
      
      double winsBetweenTurnsPercent  = getFrequencyPercentage(winsBetweenTurns, gamesEndingBetweenTurns);
      double losesBetweenTurnsPercent = getFrequencyPercentage(losesBetweenTurns, gamesEndingBetweenTurns);
      
      printGameEndingBetweenTurnsStatistics(WINS_TITLE, LOSES_TITLE, winsBetweenTurns, losesBetweenTurns,
          winsBetweenTurnsPercent, losesBetweenTurnsPercent);
   }
   
   private void printGameEndingBetweenTurnsStatistics(final String WINS_TITLE, final String LOSES_TITLE,
          long winsBetweenTurns, long losesBetweenTurns,
          double winsBetweenTurnsPercent, double losesBetweenTurnsPercent) {
      
      System.out.printf("%46s %35s%n", WINS_TITLE, LOSES_TITLE);
      System.out.printf("Frequency  %,35d %,35d%n", winsBetweenTurns, losesBetweenTurns);
      System.out.printf("Percentage %35.3f %35.3f%n%n", winsBetweenTurnsPercent, losesBetweenTurnsPercent);
   }
   
} 

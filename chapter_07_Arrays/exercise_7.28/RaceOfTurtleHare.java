/* =====================================================================================
 *       Filename:  RaceOfTurtleHare.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.28 - race's simulation of turtle and hare
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;
import java.util.Arrays;
 
public class RaceOfTurtleHare {
   public static final int  DISTANCE  = 70;
   public static final long MILLISECONDS  = 1000;
   
   public static final int RANGE      = 10;
   public static final int DIFFERENCE = 1;
   public static final int START      = 1;
   
   public static final SecureRandom RANDOM_NUMBERS_GENERATOR = new SecureRandom();
   
   private int turtlePosition;
   private int harePosition;
   
   RaceOfTurtleHareDescription raceDescription;
   
   public RaceOfTurtleHare() {
      turtlePosition = START;
      harePosition   = START;
      raceDescription = new RaceOfTurtleHareDescription();
   }
   
   int getTurtlePosition() {
      return turtlePosition;
   }
   
   int getHarePosition() {
      return harePosition;
   }
   
   public boolean isTurtleFinish() {
      return turtlePosition == DISTANCE;
   }
   
   public boolean isHareFinish() {
      return harePosition == DISTANCE;
   }
   
   public static int generateRandomNumber() {
      int number = START + DIFFERENCE * RANDOM_NUMBERS_GENERATOR.nextInt(RANGE);

      return number; 
   }
   
   private void moveTurtle() {
      int move = generateRandomNumber();
      
      if (move >= START && move < START + 5 * DIFFERENCE) {
         turtlePosition = Math.min(DISTANCE, turtlePosition + 3);
      }
      else if (move < START + 7 * DIFFERENCE) {
         turtlePosition = Math.max(START, turtlePosition - 6);
      }
      else {
         turtlePosition = Math.min(DISTANCE, turtlePosition + 1);
      }
   }
   
   private void moveHare() {
      int move = generateRandomNumber();
      
      if (move >= START + 2 * DIFFERENCE && move < START + 4 * DIFFERENCE) {
         harePosition = Math.min(DISTANCE, harePosition + 9);
      }
      else if (move == START + 4 * DIFFERENCE) {
         harePosition = Math.max(START, harePosition - 12);
      }
      else if (move >= START + 5 * DIFFERENCE && move < START + 8 * DIFFERENCE) {
         harePosition = Math.min(DISTANCE, harePosition + 1);
      }
      else if (move >= START + 8 * DIFFERENCE) {
         harePosition = Math.max(START, harePosition - 2);
      }
   }
   
   public void run() {
      raceDescription.printStartInfo();
      
      while (false == isTurtleFinish() && false == isHareFinish()) {
         raceDescription.printTrack(this);
         
         try {
            Thread.sleep(MILLISECONDS);
         }
         catch(InterruptedException e) {
            e.printStackTrace();
         }

         moveTurtle();
         moveHare();
      }
      
      raceDescription.setRaceResultInfo(this);
      raceDescription.printTrack(this);
      raceDescription.printRaceResultInfo();
   }
   
} 

class RaceOfTurtleHareDescription {
   public static final String START_INFO = "****        Race's simulation of turtle and hare";
   public static final String IDENTITY_POSITIONS = "Ouch!";
   
   public static final char turtleCharacter = 'T';
   public static final char hareCharacter = 'H';
   public static final char noRacersCharacter = '_';
   private char[] track;
   
   private String raceResultInfo;
   
   RaceOfTurtleHareDescription() {
      track = new char[RaceOfTurtleHare.DISTANCE + 1];
      Arrays.fill(track, noRacersCharacter);
   }
   
   void printTrack (RaceOfTurtleHare raceOfTurtleHare) {
      int turtlePosition = raceOfTurtleHare.getTurtlePosition();  // update turtlePosition
      int harePosition = raceOfTurtleHare.getHarePosition();      // update harePosition
      
      updateTrack(turtlePosition, harePosition); // set characters on positions where turtle or hare are
   
      System.out.println();
      System.out.printf("Turtle's position: %d %10s Hare's position: %d %n", turtlePosition, " ", harePosition);
      for (int position = RaceOfTurtleHare.START; position <= RaceOfTurtleHare.DISTANCE; position++) {
         System.out.print(String.valueOf(track[position]));
      }
      System.out.println();
      
      clearTrack(turtlePosition, harePosition); // after printing clear characters before next update positions
   }
   
   private void updateTrack(int turtlePosition, int harePosition) {
      if (turtlePosition == harePosition) {
         int trackIndex = Math.min(turtlePosition, RaceOfTurtleHare.DISTANCE - IDENTITY_POSITIONS.length() + 1);
         int index = 0;
         final int limit = IDENTITY_POSITIONS.length();
         
         while (index < limit) {
            track[trackIndex] = IDENTITY_POSITIONS.charAt(index);
            trackIndex++;
            index++;
         }
      }
      else {
         track[turtlePosition] = turtleCharacter;
         track[harePosition]   = hareCharacter;
      }
   }
   
   private void clearTrack(int turtlePosition, int harePosition) {
      if (turtlePosition == harePosition) {
         int trackIndex = turtlePosition;
         final int lastIndex = trackIndex + IDENTITY_POSITIONS.length() - 1;
         
         while (trackIndex <= lastIndex) {
            track[trackIndex] = noRacersCharacter;
            trackIndex++;
         }
      }
      else {
         track[turtlePosition] = noRacersCharacter;
         track[harePosition]   = noRacersCharacter;
      }
   }
   
   void setRaceResultInfo(RaceOfTurtleHare raceOfTurtleHare) {
      boolean hareFinish = raceOfTurtleHare.isHareFinish();
      boolean turtleFinish = raceOfTurtleHare.isTurtleFinish();
      
      if (hareFinish == true && turtleFinish == true) {
         raceResultInfo = "draw";
      }
      else if (hareFinish == true) {
         raceResultInfo = "hare is winner";
      }
      else if (turtleFinish == true) {
         raceResultInfo = "turtle is winner";
      }
      else {
         raceResultInfo = "no result";
      }
   }
   
   void printStartInfo () {
      System.out.println(START_INFO);
      System.out.println("Bang! ");
   }
   
   void printRaceResultInfo () {
      System.out.println();
      System.out.println("Result of race: " + raceResultInfo);
   }
   
}

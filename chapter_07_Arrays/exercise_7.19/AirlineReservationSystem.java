/* =====================================================================================
 *       Filename:  AirlineReservationSystem.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.19 - airline reservation system class
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class AirlineReservationSystem {
   public static final String START_INFO = "Program was written in purpose of airline reservation system";
   
   public static final String FIRST_CLASS    = "first class";
   public static final int FIRST_CLASS_NUMBER = 1;
   public static final String ECONOMIC_CLASS = "economic class";
   public static final int ECONOMIC_CLASS_NUMBER = 2;
   
   public static final String CLASS_INFO = String.format("%s: %n %s and %s",
                              "The airline reservation system has 2 classes for tickets", FIRST_CLASS, ECONOMIC_CLASS);
   public static final String SELECT_CLASS_INFO = String.format("Enter %d for select %s or %d for select %s",
                              FIRST_CLASS_NUMBER, FIRST_CLASS, ECONOMIC_CLASS_NUMBER, ECONOMIC_CLASS);

   public static final int NUMBER_OF_SEATS = 10;
   private static final int SEAT_RESERVATIONS_LENGTH = 1 + NUMBER_OF_SEATS;
   
   private static final int FIRST_CLASS_FIRST_SEAT = 1;
   private static final int FIRST_CLASS_LAST_SEAT = 5;
   private static final int ECONOMIC_CLASS_FIRST_SEAT = 6;
   private static final int ECONOMIC_CLASS_LAST_SEAT = 10;
   
   public enum ReservationStatus {TICKET_PURCHASED, CLASS_CHANGE_OFFER, NEXT_FLIGHT_OFFER};
   
   private String message = String.format("%s. %s", CLASS_INFO, SELECT_CLASS_INFO);
   
   private boolean [] seatReservations = new boolean[SEAT_RESERVATIONS_LENGTH];
   
   public AirlineReservationSystem() {
      for (int index = 0; index < SEAT_RESERVATIONS_LENGTH; index++) {
         seatReservations[index] = false; // all seats are not reserved
      }
      
      this.message = String.format("%s. %s", CLASS_INFO, SELECT_CLASS_INFO);
   }
   
   private void resetSeatReservations() {
      setValuesInArray(seatReservations, false);
   }
   
   public static void setValuesInArray(boolean[] array, boolean value) {
      final int LENGTH = array.length;
      for (int index = 0; index < LENGTH; index++) {
         array[index] = value;
      }
   }
   
   private boolean areAllSeatsReserved(int begin, int last) {
      for (int index = begin; index <= last; index++) {
         if (seatReservations[index] == false) {
            return false;
         }
      }
      
      return true;
   }
   
   private boolean areAllSeatsReserved(final int CLASS_NUMBER) {
      switch (CLASS_NUMBER) {
      
         case FIRST_CLASS_NUMBER:
            return areAllSeatsReserved(FIRST_CLASS_FIRST_SEAT, FIRST_CLASS_LAST_SEAT);
         case ECONOMIC_CLASS_NUMBER:
            return areAllSeatsReserved(ECONOMIC_CLASS_FIRST_SEAT, ECONOMIC_CLASS_LAST_SEAT);
         default:
            throw new IllegalArgumentException("Available class numbers are: " + 
                              FIRST_CLASS_NUMBER + " and " + ECONOMIC_CLASS_NUMBER);
      }
   }
   
   public String getMessage() {
      return this.message;
   }
   
   public ReservationStatus reservationRequest(final int CLASS_NUMBER) {
      if (true == isSeatReserved(CLASS_NUMBER)) {
         return ReservationStatus.TICKET_PURCHASED;
      }
      
      return considerOtherClass(CLASS_NUMBER);
   }
   
   private ReservationStatus considerOtherClass(final int CLASS_NUMBER) {
      int otherClassNumber;
      
      switch (CLASS_NUMBER) {
         case FIRST_CLASS_NUMBER:
            otherClassNumber = ECONOMIC_CLASS_NUMBER;
            break;
         case ECONOMIC_CLASS_NUMBER:
            otherClassNumber = FIRST_CLASS_NUMBER;
            break;
         default:
            throw new IllegalArgumentException("Available class numbers are: " + 
                              FIRST_CLASS_NUMBER + " and " + ECONOMIC_CLASS_NUMBER);
      }
      
      if (true == areAllSeatsReserved(otherClassNumber)) {
         this.message = "Next flight will be 3 hours later";
         return ReservationStatus.NEXT_FLIGHT_OFFER;
      }
      else {
         this.message = String.format("Do you accept seat in %s? All seats are reserved in %s", 
                          getClassDescription(otherClassNumber), getClassDescription(CLASS_NUMBER));
         return ReservationStatus.CLASS_CHANGE_OFFER;
      }
   }
   
   public ReservationStatus otherClassReservationRequest(final int CLASS_NUMBER) {
      if (true == isSeatReserved(CLASS_NUMBER)) {
         return ReservationStatus.TICKET_PURCHASED;
      }
      else {
         this.message = "Next flight will be 3 hours later";
         return ReservationStatus.NEXT_FLIGHT_OFFER;
      }
   }
   
   public ReservationStatus otherClassReservationReject() {
      this.message = "Next flight will be 3 hours later";
      return ReservationStatus.NEXT_FLIGHT_OFFER;
   }
   
   private boolean isSeatReserved(final int CLASS_NUMBER) {
      int begin;
      int last;
      
      switch (CLASS_NUMBER) {
         case FIRST_CLASS_NUMBER:
            begin = FIRST_CLASS_FIRST_SEAT;
            last  = FIRST_CLASS_LAST_SEAT;
            break;
         case ECONOMIC_CLASS_NUMBER:
            begin = ECONOMIC_CLASS_FIRST_SEAT;
            last  = ECONOMIC_CLASS_LAST_SEAT;
            break;
         default:
            throw new IllegalArgumentException("Available class numbers are: " + 
                              FIRST_CLASS_NUMBER + " and " + ECONOMIC_CLASS_NUMBER);
      }
      
      for (int index = begin; index <= last; index++) {
         if (seatReservations[index] == false) {
            reservation(index, CLASS_NUMBER);
            return true;
         }
      }
      
      return false;
   }
   
   private String getClassDescription(final int CLASS_NUMBER) {
      switch (CLASS_NUMBER) {
         case FIRST_CLASS_NUMBER:
            return  FIRST_CLASS;
         case ECONOMIC_CLASS_NUMBER:
            return  ECONOMIC_CLASS;
         default:
            throw new IllegalArgumentException("Available class numbers are: " + 
                              FIRST_CLASS_NUMBER + " and " + ECONOMIC_CLASS_NUMBER);
      }
   }
   
   private void reservation(int seatNumber, final int CLASS_NUMBER) {
      this.message = String.format("Ticket for %s    seat: %d", getClassDescription(CLASS_NUMBER), seatNumber);
      seatReservations[seatNumber] = true;
   }
   
   public String generateAllSeatsStatus() {
      String allSeatsStatus = "";
      String classDescription = FIRST_CLASS;
      
      for (int seatNumber = FIRST_CLASS_FIRST_SEAT; seatNumber <= ECONOMIC_CLASS_LAST_SEAT; seatNumber++) {
         if (seatNumber > FIRST_CLASS_LAST_SEAT) {
            classDescription = ECONOMIC_CLASS;
         }
         
         allSeatsStatus += String.format("%25s seat: %2d --- ", classDescription, seatNumber);
         
         if (seatReservations[seatNumber] == false) {
            allSeatsStatus += String.format("free%n");
         }
         else {
            allSeatsStatus += String.format("reserved%n");
         }
      }
      
      return allSeatsStatus;
   }
   
} 

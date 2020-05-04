/* =====================================================================================
 *       Filename:  AirlineReservationSystemTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.19 - test of airline reservation system class
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class AirlineReservationSystemTest {

   public static final String QUIT_INFO = " for quit enter other sequences";
   
   public static int getClassFromUser() {
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s, %s%n", AirlineReservationSystem.CLASS_INFO,
                    AirlineReservationSystem.SELECT_CLASS_INFO, QUIT_INFO);
      
      return GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT);
   }
   
   private static boolean reservationRequest(AirlineReservationSystem airlineReservationSystem) {
      final int CLASS_NUMBER = getClassFromUser();
      int otherClassNumber;
      
      switch (CLASS_NUMBER) {
         case AirlineReservationSystem.FIRST_CLASS_NUMBER:
            otherClassNumber = AirlineReservationSystem.ECONOMIC_CLASS_NUMBER;
            break;
         case AirlineReservationSystem.ECONOMIC_CLASS_NUMBER:
            otherClassNumber = AirlineReservationSystem.FIRST_CLASS_NUMBER;
            break;
         default:
            return false;
      }
      
      AirlineReservationSystem.ReservationStatus status = airlineReservationSystem.reservationRequest(CLASS_NUMBER);
      System.out.println(airlineReservationSystem.getMessage());
      if (AirlineReservationSystem.ReservationStatus.CLASS_CHANGE_OFFER == status) {
         return otherClassReservationOffer(otherClassNumber, airlineReservationSystem);
      }  
      
      return true;
   }
   
   private static boolean otherClassReservationOffer(final int CLASS_NUMBER, AirlineReservationSystem airlineReservationSystem) {
      final String accept = "y";
      final String reject = "n";
      String response = GettingDataFromStandardInput.getStringWaitingForInput(String.format
                              ("%nFor accept offer enter \'y\', for reject enter \'n\', %s%n", QUIT_INFO));

      if (response.toLowerCase().equals(accept)) {
         airlineReservationSystem.otherClassReservationRequest(CLASS_NUMBER);
         System.out.println(airlineReservationSystem.getMessage());
         return true;
      }
      else if (response.toLowerCase().equals(reject)) {
         airlineReservationSystem.otherClassReservationReject();
         System.out.println(airlineReservationSystem.getMessage());
         return true;
      }
      
      return false;  
   }

   
   public static void main(String[] args) {
      System.out.printf("******** %s %n", AirlineReservationSystem.START_INFO);
      
      AirlineReservationSystem airlineReservationSystem = new AirlineReservationSystem();
      
      while (true == reservationRequest(airlineReservationSystem)) { 
      }
      
      System.out.println("Current reservation status has printed below: ");
      System.out.println(airlineReservationSystem.generateAllSeatsStatus());
      
   } 
   
} 

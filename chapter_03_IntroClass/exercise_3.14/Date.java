/* =====================================================================================
 *       Filename:  Date.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.14 - class of date
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Date {

   private int day; // values of 0 is indication of incorrect date
   private int month;
   private int year;
   
   // in constructor all elements in data are checked
   // if only one element is incorrect then all elements are 
   // initialize with 0
   public Date(int day, int month, int year) {
      if (0 != year && 0 < day) { 
          
      	boolean isCorrectData = false;
      		
         if (day <= 31 &&
            (1 == month || 3 == month || 5 == month || 7 == month ||
             8 == month || 10 == month || 12 == month)) {
            isCorrectData = true;
         } 
         else if (day <= 30 &&
            (4 == month || 6 == month || 9 == month || 11 == month)) {
            isCorrectData = true;
         }
         else if (day <= 28 && 2 == month) {
            isCorrectData = true;
         }
         else if (day == 29 && 2 == month) {
            if (0 > year) // to convenient calculate of leap year assume years BC 
               ++year;    // increased by 1
            // calculate of leap year
            if ((0 == year % 4 && 0 != year % 100) || (0 == year % 400))
               isCorrectData = true;
            if (0 >= year) // after calculate of leap year 
               --year;     // revert value of years BC
         }
         
         if (true == isCorrectData) {
            this.day = day;
 				this.month = month; 
            this.year = year;      
         }
             
      }
   }

   // in setter methods is more tolerant rule of check function arguments
   // because every of 3 setters has 1 argument and can modify 1 instance's variable 
   // lack of this more tolerant rule would make impossible to modify instance's variable
   public void setDay(int day) {
      if (0 < day) { 
         // values of 0 is indication of incorrect date in class variable
         if (0 == this.month && day <= 31) { // this.year is irrelevant in this case
            this.day = day; 
         }
		   else if (day <= 31 &&
			   (1 == this.month || 3 == this.month || 5 == this.month || 7 == this.month ||
			    8 == this.month || 10 == this.month || 12 == this.month)) {
				 this.day = day;
		   } 
         else if (day <= 30 &&
			   (4 == this.month || 6 == this.month || 9 == this.month || 11 == this.month)) {
             this.day = day;
         }
         else if (day <= 28 && 2 == this.month) {
            this.day = day;
         }
         else if (day == 29 && 2 == this.month) {
            if (0 > this.year) // to convenient calculate of leap year assume years BC 
      		   ++this.year;    // increased by 1
            // calculate of leap year
            if ((0 == this.year % 4 && 0 != this.year % 100) || (0 == this.year % 400))
                this.day = day;
            if (0 >= this.year) // after calculate of leap year 
               --this.year;     // revert value of years BC 
         }
      }
   } 

   public int getDay() {
      return day; 
   } 
   
   // in setter methods is more tolerant rule of check function arguments
   // because every of 3 setters has 1 argument and can modify 1 instance's variable 
   // lack of this more tolerant rule would make impossible to modify instance's variable
   public void setMonth(int month) {
      if (0 < month) { 
         // values of 0 is indication of incorrect date in class variable
         if (0 == this.day && month <= 12) { // this.year is irrelevant in this case
            this.month = month; 
         }
		   else if (this.day <= 31 &&
			   (1 == month || 3 == month || 5 == month || 7 == month ||
			    8 == month || 10 == month || 12 == month)) {
				 this.month = month;
		   } 
         else if (this.day <= 30 &&
			   (4 == month || 6 == month || 9 == month || 11 == month)) {
             this.month = month;
         }
         else if (this.day <= 28 && 2 == month) {
            this.month = month;
         }
         else if (this.day == 29 && 2 == month) {
            if (0 > this.year) // to convenient calculate of leap year assume years BC 
      		   ++this.year;    // increased by 1
            // calculate of leap year
            if ((0 == this.year % 4 && 0 != this.year % 100) || (0 == this.year % 400))
                this.month = month;
            if (0 >= this.year) // after calculate of leap year 
               --this.year;     // revert value of years BC 
         }
      }
   } 

   public int getMonth() {
      return month; 
   } 
   
   // in setter methods is more tolerant rule of check function arguments
   // because every of 3 setters has 1 argument and can modify 1 instance's variable 
   // lack of this more tolerant rule would make impossible to modify instance's variable
   public void setYear(int year) {
      if (0 != year) { 
         if (this.day == 29 && 2 == this.month) {
            if (0 > year) // to convenient calculate of leap year assume years BC 
      		   ++year;    // increased by 1
            // calculate of leap year
            if ((0 == year % 4 && 0 != year % 100) || (0 == year % 400)) {
               if (0 >= year) // after calculate of leap year 
                  --year;     // revert value of years BC
                  
               this.year = year; // after calculate of leap year revert value of years BC 
            }
         }
         else {
            this.year = year;
         }
      }
   } 

   public int getYear() {
      return year; 
   } 
}

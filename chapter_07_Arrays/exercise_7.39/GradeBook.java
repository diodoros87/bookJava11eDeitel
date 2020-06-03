/* =====================================================================================
 *       Filename:  GradeBook.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.39 - GradeBook class using a two-dimensional 
                                array to store grades and analyze them
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class GradeBook {
   private String courseName; // name of course this grade book represents
   
   private int studentsNumber;
   private int testsNumber;
   private int[][] grades; // two-dimensional array of student grades
   
   public static final int NO_GRADE = -1;
   private static final int MIN_POSSIBLE_GRADE = 0;
   private static final int MAX_POSSIBLE_GRADE = 100;
   
   // two-argument constructor initializes courseName and grades array
   public GradeBook(String courseName, int[][] grades) {
      this.courseName = courseName; 
      this.grades = grades; 
   } 
   
   // two-argument constructor initializes courseName and grades array
   public GradeBook(String courseName, int studentsNumber, int testsNumber) {
      this.courseName = courseName;  
      
      setStudentsNumber(studentsNumber);
      setTestsNumber(testsNumber);
      
      newGrades();
   } 

   // method to set the course name
   public void setCourseName(String courseName) {
      this.courseName = courseName; 
   } 

   // method to retrieve the course name
   public String getCourseName() {
      return courseName;
   }
   
   public void setStudentsNumber(int studentsNumber) {
      if (0 >= studentsNumber) {
         throw new IllegalArgumentException("Number of students must be more than 0");
      }
      
      this.studentsNumber = studentsNumber; 
   } 

   public int getStudentsNumber() {
      return studentsNumber;
   } 
   
   public void setTestsNumber(int testsNumber) {
      if (0 >= testsNumber) {
         throw new IllegalArgumentException("Number of tests must be more than 0");
      }
      
      this.testsNumber = testsNumber; 
   } 

   public int getTestsNumber() {
      return testsNumber;
   }
   
   public boolean setGrade(int student, int test, int grade) {
      if (1 > student || student > this.studentsNumber) {
         System.err.printf("Number of student must be in range from 1 to %d%n", this.studentsNumber);
         return false;
      }
      if (1 > test || test > this.testsNumber) {
         System.err.printf("Number of tests must be in range from 1 to %d%n", this.testsNumber);
         return false;
      }
      if (MIN_POSSIBLE_GRADE > grade || MAX_POSSIBLE_GRADE < grade) {
         System.err.printf("%nGrade must be in range from %d to %d%n", MIN_POSSIBLE_GRADE, MAX_POSSIBLE_GRADE);
         return false;
      }
      
      grades[student - 1][test - 1] = grade;
      return true;
   }
   
   public void newGrades() {
      grades = new int[studentsNumber][testsNumber];
      resetGrades();
   }
   
   public void resetGrades() {
      for (int student = 0; student < grades.length; student++) {
         for (int test = 0; test < grades[student].length; test++) {
            grades[student][test] = NO_GRADE;                           
         } 
      } 
   }

   // perform various operations on the data
   public void processGrades() {
      // output grades array
      outputGrades();

      // call methods getMinimum and getMaximum
      System.out.printf("%n%s %d%n%s %d%n%n", 
         "Lowest grade in the grade book is", getMinimum(), 
         "Highest grade in the grade book is", getMaximum());

      // output grade distribution chart of all grades on all tests
      outputBarChart();
   } 

   // find minimum grade
   public int getMinimum() {
      // assume first element of grades array is smallest        
      int lowGrade = grades[0][0];                           
                                                                 
      // loop through rows of grades array                       
      for (int[] studentGrades : grades) {                       
         // loop through columns of current row                  
         for (int grade : studentGrades) {                       
            // if grade less than lowGrade, assign it to lowGrade
            if (NO_GRADE == lowGrade) {
               lowGrade = grade; 
            }
            else if (NO_GRADE != grade && grade < lowGrade) {                              
               lowGrade = grade;                                 
            }                                                    
         }                                                       
      }                                                          

      return lowGrade; 
   } 

   // find maximum grade
   public int getMaximum() {
      // assume first element of grades array is largest
      int highGrade = grades[0][0];

      // loop through rows of grades array
      for (int[] studentGrades : grades) {
         // loop through columns of current row
         for (int grade : studentGrades) {
            // if grade greater than highGrade, assign it to highGrade
            if (grade > highGrade) {
               highGrade = grade;
            } 
         } 
      } 

      return highGrade; 
   } 

   // determine average grade for particular set of grades
   public double getAverage(int[] setOfGrades) {          
      int total = 0; 
      int totalGrades = 0;
                                                          
      // sum grades for one student                       
      for (int grade : setOfGrades) { 
         if (NO_GRADE != grade) {
            total += grade;  
            totalGrades++;
         }
      }                                                   
                                                          
      // return average of grades 
      if (0 != totalGrades) {
         return (double) total / totalGrades;   
      }
      else {
         return 0;
      }
   }                              

   // output bar chart displaying overall grade distribution
   public void outputBarChart() {
      System.out.println("Overall grade distribution:");

      // stores frequency of grades in each range of 10 grades
      int[] frequency = new int[11];
      
      // for each grade in GradeBook, increment the appropriate frequency
      for (int[] studentGrades : grades) {                               
         for (int grade : studentGrades) {
            if (NO_GRADE != grade)
               ++frequency[grade / 10];                                     
         }                                                               
      }                                                                  

      // for each grade frequency, print bar in chart
      for (int count = 0; count < frequency.length; count++) {
         // output bar label ("00-09: ", ..., "90-99: ", "100: ")
         if (count == 10) {
            System.out.printf("%5d: ", 100); 
         } 
         else {
            System.out.printf("%02d-%02d: ", 
               count * 10, count * 10 + 9); 
         } 
         
         // print bar of asterisks
         for (int stars = 0; stars < frequency[count]; stars++) {
            System.out.print("*");
         } 

         System.out.println(); 
      } 
   } 

   // output the contents of the grades array
   public void outputGrades() {
      System.out.printf("The grades are:%n%n");
      System.out.print("            "); // align column heads

      // create a column heading for each of the tests
      for (int test = 0; test < grades[0].length; test++) {
         System.out.printf("Test %d  ", test + 1);
      } 

      System.out.println("Average"); // student average column heading

      // create rows/columns of text representing array grades
      for (int student = 0; student < grades.length; student++) {
         System.out.printf("Student %2d", student + 1);

         for (int test : grades[student]) { // output student's grades
            if (NO_GRADE == test) {
               System.out.printf("%8s", "no");
            }
            else {
               System.out.printf("%8d", test);  
            }
         } 

         // call method getAverage to calculate student's average grade;
         // pass row of grades as the argument to getAverage
         double average = getAverage(grades[student]);
         System.out.printf("%9.2f%n", average);
      } 
   } 
} 

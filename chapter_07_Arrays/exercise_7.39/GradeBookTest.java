/* =====================================================================================
 *       Filename:  GradeBookTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.39 - test of GradeBook object
                                two-dimensional array of grades, then invokes method
                                   processGrades to analyze them
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class GradeBookTest {
   private static final String QUIT = "q";

   public static void main(String[] args) {
      System.out.printf("*** This program is test of students grade book %n");
      
      GradeBook myGradeBook = null;
      String courseName; 
      int studentsNumber; 
      int testsNumber;
      
      do {
         courseName = getCourseName();
         studentsNumber = getStudentsNumber();
         testsNumber = getTestsNumber();
         
         myGradeBook = createModifyGradeBook(myGradeBook, courseName, studentsNumber, testsNumber);
         setGrades(myGradeBook);
         System.out.printf("Welcome to the grade book for%n%s%n%n", myGradeBook.getCourseName());
         myGradeBook.processGrades();
      
      } while(true == isProgramContinue());
      
   } 
   
   private static GradeBook createModifyGradeBook(GradeBook gradeBook, String courseName, int studentsNumber, int testsNumber) {
      if (null == gradeBook) {
            gradeBook = new GradeBook(courseName, studentsNumber, testsNumber);
         }
      else {
         gradeBook.setCourseName(courseName);
         gradeBook.setStudentsNumber(studentsNumber);
         gradeBook.setTestsNumber(testsNumber);
         gradeBook.newGrades();
      }
      
      return gradeBook;
   }
   
   private static String getCourseName() {
      final String COURSE_NAME_PROMPT    = "Enter course name: ";
      String courseName     = GettingDataFromStandardInput.getStringWaitingForInput(COURSE_NAME_PROMPT);
    
      return courseName;
   }
   
   private static int getStudentsNumber() {
      final String STUDENTS_NAME_PROMPT  = "Enter number of students: ";
      int studentsNumber = GettingDataFromStandardInput.getInteger(STUDENTS_NAME_PROMPT);
    
      return studentsNumber;
   }
   
   private static int getTestsNumber() {
      final String TESTS_NAME_PROMPT     = "Enter number of tests: ";
      int testsNumber = GettingDataFromStandardInput.getInteger(TESTS_NAME_PROMPT);
    
      return testsNumber;
   }
   
   private static boolean isProgramContinue() {
      String isProgramContinue = GettingDataFromStandardInput.getStringWaitingForInput(String.format
                              ("%n %s %s to quit %n", "***** To continue press ENTER or only", QUIT));

      if (null == isProgramContinue || QUIT.equals(isProgramContinue.toLowerCase())) {
         return false;
      }
      
      return true;
   }
   
   private static void setGrades(GradeBook gradeBook) {
      int studentsNumber = gradeBook.getStudentsNumber();
      int testsNumber = gradeBook.getTestsNumber();
      int grade;
      boolean correctGrade;
      int student = 1;
      int test;
      
      while (student <= studentsNumber) {
         test = 1;
         while (test <= testsNumber) {
            grade = getGrade(student, test);
            
            if (grade != GradeBook.NO_GRADE) {
               correctGrade = gradeBook.setGrade(student, test, grade);
               if (false == correctGrade) {
                  continue;
               }
            }
            
            test++;
         }
         
         student++;
      }
   }
   
   private static int getGrade(int student, int test) {
      final String GRADE_PROMPT = String.format("Enter grade of test %d for student %d. ", test, student) +
                                 String.format("For no grade enter %d%n", GradeBook.NO_GRADE);
      int grade = GettingDataFromStandardInput.getInteger(GRADE_PROMPT);
    
      return grade;
      
   }
} 

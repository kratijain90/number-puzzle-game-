import java.io.*;
import java.util.Scanner;
import java.util.Random; // for random number 


class Game
{
    static Scanner sc = new Scanner (System.in);
    static Random rd = new Random();

    // function to create game beginning 
   static void skeleton()
   {
    int ch ; // choice 
    // menu 
    do 
    {
        System.out.println("1. --PLAY");
        System.out.println("2. --EXIT--");

        System.out.println("--> Enter your choice ");
        ch = sc.nextInt();

        if (ch < 1 || ch > 2)
        {
             System.out.println(" oops---choose wisely");
             continue ;
        }

        switch (ch)
       {
        case 1 : 
         playGame();   // calling 
         break ;
       
        case 2 : 
         System.out.println("Game Ended");
         break ; 

       }
   }
   while (ch!=2);
}

// choose difficulty level 
static void playGame()
{

     int minterms = 0;
     int maxterms = 0 ;
     int maxNumber = 0; // maximum no. for each level
     int maxAttempts = 0;

     

    //System.out.println("CHOOSE>>LEVEL");
    System.out.println("1. \t Easy Level");
    System.out.println("2. \t Medium Level");
    System.out.println("3. \t Hard Level");

    int difficulty = sc.nextInt();
    if (difficulty > 3 || difficulty < 1)
    {
         System.out.println("CHOOSE given LEVEL");
         return;
    }
    switch (difficulty)
    {
        case 1 :
              System.out.println(" Easy level");
               maxNumber = 50; 
               maxAttempts = 5;
               minterms = 2;
               maxterms = 3;
            
             break;

        case 2 :
              System.out.println("Medium level");
               maxNumber = 100; 
               maxAttempts = 4;
               minterms = 3;
               maxterms = 5;
              break;

        case 3 :
              System.out.println("Hard level");
               maxNumber = 500; 
               maxAttempts = 3;
               minterms = 5;
               maxterms = 8 ;

              break;     
    }
    System.out.println("Maximum Number = " + maxNumber);
    System.out.print("\n--------------\n");
    System.out.println("Maximum Attempts = " + maxAttempts);
    System.out.print("\n--------------\n");
   
   
   
    

    int terms = rd.nextInt(maxterms - minterms + 1)+ minterms ;

    int secretnumber = rd.nextInt(maxNumber)+1 ;
  
  //  System.out.println("Secret Number = " + secretnumber);

  // now cretae puzzle equation to chooose random 

    int puzzleType = rd.nextInt(2)+1;

    switch (puzzleType)
    {
        case 1:
            generateAdditionPuzzle(secretnumber , maxAttempts , terms);
            break;
        case 2:
            generateMixedOperatorPuzzle( maxAttempts ,terms);
            break;
       /* case 3:
            generateMultiplicationPuzzle(secretnumber , maxAttempts);
            break;
        case 4:
            generateSquarePuzzle(secretnumber, maxAttempts);
            break;
        case 5:
            generateLetterPuzzle(secretnumber, maxAttempts);
            break;
*/
    }

}

    static void checkAnswer(int correctAnswer, int maxAttempts)
    
    {
        int attempts = 0 ;
        while ( attempts < maxAttempts)
        {

           // showing no. of attempts
            System.out.println("\n -->>Attempt " + (attempts + 1) + "/" + maxAttempts + "<<--\n");
            
           int answer ; 
    
            // let user guess the anwer

            System.out.println( " Enter your answer :  \t ");
            answer = sc.nextInt();
    
            if ( answer == correctAnswer)
            {
             System.out.println( " You're correct!! ");
             return;
            }
            else 
                {
                    attempts++;

                    System.out.println( " oops You're wrong try again !! ");
                   
                    System.out.println("\n \t Remaining Attempts = " + (maxAttempts - attempts) + " \n ");
                }    
       }

        if ( attempts == maxAttempts)
            {
                System.out.println(" GAME OVER! YOU LOSE ");
                System.out.println(" CORRECT ANSWER WAS " + correctAnswer);
                
            }
    }
    static void generateAdditionPuzzle( int secretnumber, int maxAttempts , int terms)
    {
        /*int firstnumber ;
        int secondnumber;

        if(secretnumber == 1)  // exception
           secretnumber++;


        firstnumber = rd.nextInt(secretnumber-1)+1 ;
        secondnumber = secretnumber - firstnumber;
        */

       // no. through array 
       int numbers[] = new int [terms];

       int sum = 0 ;
       for ( int i = 0 ; i < terms - 1 ; i++)
       {
          int limit = secretnumber/terms;

          if(limit == 0)
              limit = 1;

          numbers[i] = rd.nextInt(limit) + 1;
          sum += numbers[i];
       
       }
        numbers[terms-1] = secretnumber - sum ;

       // printing of equation 

       for (int i = 0 ; i < terms ; i++)
       {
        System.out.print(numbers[i]);
       
        if (i < terms-1)
        {
            System.out.print("  +  ");
        }
       }
       System.out.print(" = ?");

       System.out.print("\n--------------\n");


        // now no. of attempts

       // calling answer function 
       checkAnswer(secretnumber, maxAttempts);
    }

    // other function for mixed operation
    static void generateMixedOperatorPuzzle(  int maxAttempts , int terms)
    {
        char operators[] = new char[terms-1];


        int numbers[] = new int [terms];
         for ( int i = 0 ; i < terms ; i++)
       {
          

          numbers[i] = rd.nextInt(10) + 1;
        
       }

       char symbols[] = {'+','-','*'};
       

       for ( int i = 0 ; i < terms-1 ; i++)
       {
          int n = rd.nextInt(symbols.length);

          operators [i] = symbols[n];
       }

       // PRINTING THE EQUATION

       System.out.print(numbers[0]);


       // now first num printed then operator rest next

       for (int i = 0 ; i < terms -1 ; i++)
       {
        System.out.print("  " + operators[i] + "  ") ;

        System.out.print(  numbers[i+1] );
       }

       System.out.print(" = ?");
       System.out.println ("\n--------------\n");


       // calculating the answer
       int tempNumbers[] = new int[terms];
       char tempOperators[] = new char[terms-1];
       
       tempNumbers[0] = numbers[0];
       
       int index = 0;

    // making switch cases to perform operation

       for(int i = 0 ; i < terms-1 ; i++)
       {
           if(operators[i] == '*')
           {
               tempNumbers[index] *= numbers[i+1];
           }
           else
           {
               tempOperators[index] = operators[i];
               index++;
       
               tempNumbers[index] = numbers[i+1];
           }
       }

        int result = tempNumbers[0];

       
       
       for(int i = 0 ; i < index ; i++)
       {
           switch(tempOperators[i])
           {
               case '+':
                   result += tempNumbers[i+1];
                   break;
       
               case '-':
                   result -= tempNumbers[i+1];
                   break;
           }
       }
       
            // check answer
           // int attempts = 0 ;
          // int answer ; 
    
        // let user guess the anwer

        checkAnswer(result , maxAttempts);
    }



    
   /* static void generateMultiplicationPuzzle( int secretnumber, int maxAttempts)
    {

    } 
    static void generateSquarePuzzle( int secretnumber, int maxAttempts)
    {

    }
    static void generateLetterPuzzle( int secretnumber, int maxAttempts)
    {

    }
*/


        
// _______MAIN DRIVER CODE_____________//
   
public static void main (String [] args)
    {
        skeleton ();
        
    }
}

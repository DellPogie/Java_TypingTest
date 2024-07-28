
// set the package name
package com.dellpogie;

// add references to the Java Standard Library
import java.util.Scanner;
import java.util.Random;

// create the typing test class
public class TypingTest {

    // entry point in Java
    public static void main(String[] args) {

        // for user input listener
        Scanner scanner = new Scanner(System.in);

        // display game intro and details
        System.out.println("\nDellPogie 2024 - Java Portfolio using the Java Standard Library\n");
        System.out.println("*******************************");
        System.out.println("||   DELLPOGIE TYPING TEST   ||");
        System.out.println("*******************************\n");

        // ask the user to input the maximum number of sentences
        System.out.print("Enter the number of sentence(s) to auto generate: ");

        // scan for user input
        int intNumberOfSentences = scanner.nextInt();

        // ask the user to input the maximum number of words per sentence
        System.out.print("Enter the number of words per sentence: ");

        // scan for user input
        int intNumberOfWordsPerSentence = scanner.nextInt();

        // very important: consume the leftover newline
        scanner.nextLine();

        // alphabet reference
        String strAlphabetReference = "abcdefghijklmnopqrstuvwxyz";

        // set the minimum and maximum word length
        int intMinWordLength = 3;
        int intMaxWordLength = 10;

        // placeholder for typing test sentence
        StringBuilder strSentence = new StringBuilder();

        // use random for auto-generated words
        Random random = new Random();

        // display details and instruction to the user
        System.out.println("\nThe Computer has auto-generated random sentence(s).");
        System.out.println("Type the following sentence(s) as fast as you can:\n");

        System.out.println("Timer Starts Now!\n");

        /* this is the logic to auto-generate random unrealistic words and sentences
           I have put in a considerable amount of time to complete this logic */
        for (int intSentencesCounter = 0; intSentencesCounter < intNumberOfSentences; intSentencesCounter++) {
            for (int intWordsCounter = 0; intWordsCounter < intNumberOfWordsPerSentence; intWordsCounter++) {
                int intWordLength = intMinWordLength + random.nextInt(intMaxWordLength - intMinWordLength + 1);
                StringBuilder strRandomWord = new StringBuilder();
                for (int intWordLengthCounter = 0; intWordLengthCounter < intWordLength; intWordLengthCounter++) {
                    int intRandomIndex = random.nextInt(strAlphabetReference.length());
                    if (intWordsCounter == 0 && intWordLengthCounter == 0) {
                        strRandomWord.append(Character.toUpperCase(strAlphabetReference.charAt(intRandomIndex)));
                    }
                    else {
                        strRandomWord.append(strAlphabetReference.charAt(intRandomIndex));
                    }
                }
                strSentence.append(strRandomWord.toString()).append(" ");
            }
            strSentence.replace(strSentence.length()-1,strSentence.length(),"");
            strSentence.append(". ");
        }

        // placeholder for auto-generated typing test sentences
        String strTypingTestSentence = strSentence.toString();

        // display the typing test to screen
        System.out.println(strTypingTestSentence);

        // start time clock
        long lngStartTime = System.currentTimeMillis();

        // scan for user input
        String strUserInput = scanner.nextLine();

        // end time clock
        long lngEndTime = System.currentTimeMillis();

        /* count the number of errors committed
           user input versus the actual typing test */
        int intTotalErrors = countErrors(strUserInput, strTypingTestSentence);

        // calculate time taken in seconds
        double dblElapsedTime = (lngEndTime - lngStartTime) / 1000.0;

        // calculate the time taken in minutes
        double timeInMinutes = dblElapsedTime / 60.0;

        // logic to calculate the typing speed words per minute (WPM)
        int intWordCount = strTypingTestSentence.split(" ").length;

        // calculate the gwpm (Gross Words Per Minute)
        double dblGrossWordsPerMinute = (intWordCount / dblElapsedTime) * 60;

        // calculate the nwpm (Net Words Per Minute)
        double dblNetWordsPerMinute = dblGrossWordsPerMinute - (intTotalErrors / timeInMinutes);

        // calculate the accuracy
        double dblAccuracy = ((strUserInput.length() - intTotalErrors) / (double) strUserInput.length()) * 100;

        // display the results to screen
        System.out.printf("\nImpressive! You've typed the sentence(s) in %.2f seconds.\n", dblElapsedTime);

        // display the user's accuracy to the screen
        if (intTotalErrors == 0) {
            System.out.println("You've typed the sentence(s) correctly!");
        } else {
            System.out.println("There were " + intTotalErrors + " mistake(s) in your typing.");
        }

        // display the user's typing speed to the screen
        System.out.printf("Your Typing Speed is %.2f Gross Words Per Minute (GWPM).\n", dblGrossWordsPerMinute);
        System.out.printf("Your Typing Speed is %.2f Net Words Per Minute (NWPM).\n", dblNetWordsPerMinute);

        // display user's accuracy in percentage to the screen
        System.out.printf("Your Accuracy is %.2f%%.\n\n", dblAccuracy);

        // aesthetics
        System.out.println("Thank you for playing! :-)");

        // clear the memory
        scanner.close();
    }

    public static int countErrors(String strUser, String strComputer) {
        int intMaxLength = Math.max(strUser.length(), strComputer.length());
        int intDifferences = 0;

        for (int i = 0; i < intMaxLength; i++) {
            char char1 = i < strUser.length() ? strUser.charAt(i) : ' ';
            char char2 = i < strComputer.length() ? strComputer.charAt(i) : ' ';

            if (char1 != char2) {
                intDifferences++;
            }
        }

        return intDifferences;
    }
}

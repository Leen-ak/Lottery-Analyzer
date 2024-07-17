/**
 * Program Name:    Lottery Prizes 
 * Purpose:			class provides utility methods for validating the prize pool, 
 * 					reading and processing ticket numbers from a file, and formatting ticket numbers. 
 * 					These methods support the main class in analyzing lottery data and generating the final report.
 * Coder:			Leen Aboukhalil
 * Date:			Dec. 3, 2023
 */

import java.util.*;
public class ProjectMethods {

	 /*
	 * Method Name:   PrizePoolValidation
	 * Purpose:       making sure the user input for prize pool to stay more than $1000
	 * Accepts:       int, Scanner 
	 * Returns:       void
	 * */
	public static void PrizePoolValidation(int prizePool, Scanner input){
		
		while(prizePool < 1000) {
			System.out.println("The prize pool at lease should be $1000, try again Please: ");
			prizePool = input.nextInt();
		}
		
	}
	
	/*
	 * Method Name: getNextSeries 
	 * Purpose:     split up the tokens numbers and convert the tokens into integers
	 * Accepts:     String  
	 * Returns:     int [] 
	 * */
	public static int [] getNextSeries(Scanner fileRead ) {
		String file = fileRead.nextLine(); 							//store what the fileRead from the file to string file
		String [] formatNumbersArray = file.split(",");				//read until the ',' and then go to the next index and read until the ',' ...
		int [] numbersArray = new int [formatNumbersArray.length]; 	//creating a new int array and initialize it with the formatNumbersArray to get the size of the array
		for(int i = 0 ; i < formatNumbersArray.length; ++i) {		//then taking the numbers in the formatNumbersArray and converting it to an integer numbers 
			numbersArray[i] = Integer.parseInt(formatNumbersArray[i]);
		}
		return numbersArray;
	}
	
     /*
	 * Method Name: countMatchingNumbers 
	 * Purpose:     Compare the numbers in each array to count the number of matches
	 * Accepts:     int [], int []
	 * Returns:     int 
	 * */
	public static int countMatchingNumbers(int [] ticketNumbersArray, int [] winningNumbersArray){
		int count = 0; 
		
		//comparing the indexes with each other looking for a match and count the match
		for(int i = 0; i < ticketNumbersArray.length; ++i) {	
			for(int j = 0; j < winningNumbersArray.length; ++j) {
				if(ticketNumbersArray[i] == winningNumbersArray[j])
					count++; 
			}
		}
		return count; 
	}
	
	 /*
	 * Method Name:   formatTicketNumbers
	 * Purpose:       Accept an integer array containing one set of ticket numbers and return a formatted string 
	 * Accepts:       int []  
	 * Returns:       String
	 * */
	public static String formatTicketNumbers(int [] ticketsNumber){
		
		String [] formatStringTicket = new String[ticketsNumber.length];
		for(int i = 0; i < ticketsNumber.length ; ++i) {
			formatStringTicket[i] = Integer.toString(ticketsNumber[i]);	//converting the integers to string with that format [ , , , ,]
		}
		String commaFormatting = String.join(", ", formatStringTicket);	
	return commaFormatting; 
		
	}
}

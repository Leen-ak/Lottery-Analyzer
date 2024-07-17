/**
 * Program Name:    Lottery Prizes Analyzer 
 * Purpose:		    analyzes lottery ticket data to determine the winners and prize distribution. It reads ticket information from a file, 
 *                  matches tickets against winning numbers, and generates a detailed report of the results, including prize amounts and winning ticket numbers.
 * Coder:			Leen Aboukhalil
 * Date:			Dec. 3, 2023
 */


import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class MainLotteryPrizes {

	public static void main(String[] args) {
		ArrayList <int[]> secondPrizeWinners = new ArrayList<>();
		ArrayList <int[]> thirdPrizeWinners = new ArrayList<> ();
		ArrayList <int[]> grandPrizeWinners = new ArrayList<>();
		Scanner input = new Scanner(System.in);

		final int GRAND_PRIZE_MATCH = 6;	//the grand prize should match all the numbers which is 6 number per ticket 
		final int SECOND_PRIZE_MATCH = 5;	//second prize should match 5 numbers out of 6
		final int THIRD_PRIZE_MATCH = 4;	//third prize should match 4 numbers out of 6
		final double GRAND_PRIZE = 0.85;	//85% of prize pool 
		final double SECOND_PRIZE = 0.07;	//7% of prize pool
		final double THIRD_PRIZE = 0.08;	//8% of prize pool
		int countTicket = 0; 				
		
		//Printing the title of the program
		System.out.println("Lottery Prizes Analyzer\n\n");
		
		//taking an input from the user 
		System.out.print("Enter the name of the lottery: ");	//the name of the lottery
		String lotteryName = input.next();
		System.out.print("Enter the amount of money in the prize pool: $");
		int prizePool = input.nextInt(); 						//The prize pool and should be at lease $1000
		ProjectMethods.PrizePoolValidation(prizePool, input); 	//make sure the user did not write a prize less than $1000
		System.out.print("Enter the paht for the data file: ");	
		String fileName = input.next();							//lottacash.csv which have the lottery numbers ticket 
		
		try {
			Scanner fileRead = new Scanner(new File(fileName)); //for reading form the file that the user wrote 
			
			int [] winnerTicketNumberArray = ProjectMethods.getNextSeries(fileRead); //storing the first line from the file which is the number of the winner ticket
						
			while(fileRead.hasNextLine()) { 								//looping until there is no lines to read 
				
				int [] ticketArray = ProjectMethods.getNextSeries(fileRead);//so ticketArray has all the ticket numbers 
				int countMatch = ProjectMethods.countMatchingNumbers(ticketArray, winnerTicketNumberArray);
				
				if(countMatch == THIRD_PRIZE_MATCH)			//comparing if there is 4 matches number at the ticket which means it is third prize winner
					thirdPrizeWinners.add(ticketArray);		//so adding that number to the thirdPrizeWinners ArryList
				else if(countMatch == SECOND_PRIZE_MATCH)	//comparing if there is 5 matches number at the ticket which means it is second prize winner
					secondPrizeWinners.add(ticketArray);	//so adding that number to the secondPrizeWinners ArrayList
				else if(countMatch == GRAND_PRIZE_MATCH)	//comparing if there is 6 matches number at the ticket which mean it is grand prize winner
					grandPrizeWinners.add(ticketArray);		//so adding that number to grandPrizeWinners ArrayList
				countTicket++; 								//counting the number of the ticket for all types 
			}
			
			//printing the lottery prize report
			System.out.println("\nLottery Prizes Report");
            System.out.println("----------------------");
            System.out.printf("\nLottery Name:      %s\n", lotteryName);
            System.out.printf("Total prize pool:  $%,d\n", prizePool);
            System.out.printf("Number of tickets: %,d\n", countTicket);
            String printWinnerTicket = ProjectMethods.formatTicketNumbers(winnerTicketNumberArray);
            System.out.printf("Winning numbers:   %s\n", printWinnerTicket);
            
            System.out.println("\n\nGrand prize winners (all numbers match)...");
            System.out.printf("\n\tNumber of winners:  %d", grandPrizeWinners.size());
            System.out.printf("\n\t%% of prize pool:    %.1f", GRAND_PRIZE * 100);
            System.out.printf("\n\tTotal prize value:  $%,.2f", GRAND_PRIZE * prizePool);
            System.out.printf("\n\tPrize per ticket:   $%,.2f", ((GRAND_PRIZE * prizePool) /grandPrizeWinners.size()));
           
            System.out.printf("\n\nSecond prize winners (%d numbers match)...", winnerTicketNumberArray.length - 1);
            System.out.printf("\n\tNumber of winners:  %d", secondPrizeWinners.size());
            System.out.printf("\n\t%% of prize pool:    %.1f", SECOND_PRIZE);
            System.out.printf("\n\tTotal prize value:  $%.2f", SECOND_PRIZE * prizePool);
            System.out.printf("\n\tPrize per ticket:   $%.2f", (SECOND_PRIZE * prizePool) / secondPrizeWinners.size());
            System.out.print("\n\tTicket Numbers:    ");
            for(int [] secondPrize : secondPrizeWinners) {
            	System.out.print(" " + Arrays.toString(secondPrize));
            }

            System.out.printf("\n\nThird prize winners (%d numbers match)..." , winnerTicketNumberArray.length - 2);
            System.out.printf("\n\tNumber of winners:  %d", thirdPrizeWinners.size());
            System.out.printf("\n\t%% of prize pool:    $%.1f", THIRD_PRIZE * 100);
            System.out.printf("\n\tTotal prize value:  $%,.2f", THIRD_PRIZE * prizePool);
            System.out.printf("\n\tPrize per ticket:   $%,.2f", (THIRD_PRIZE * prizePool) / thirdPrizeWinners.size());
            System.out.print("\n\tTicket Numbers:   ");
            for(int [] thirdPrize : thirdPrizeWinners) {
            	System.out.print("\t\t\n        " + Arrays.toString(thirdPrize));
            }
            System.out.println();
            
            fileRead.close();
            input.close();

		}catch(FileNotFoundException ex) {
			System.out.println("File Not Found!");
		}
	}
}

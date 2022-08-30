package com.company;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static com.company.CodeTest.MAX_NUMBERS;
import static com.company.CodeTest.PRIZE;
import static javax.swing.text.html.parser.DTDConstants.NUMBERS;

public class Main {

    public static void main(String[] args) {
	// This program will randomly generate a winning lottery ticket
        // Prompt the player to enter lotto numbers from 1 to 40
        // Depending on how many numbers match, the players win various prizes.


// 3- Getting winning number and ticket sets
        Set<Integer> winning = createWinningNumbers();
        Set<Integer> ticket = getTicket();

        // 4 - Keeping only winning number from user's ticket
        // using retainAll() to remove non common numbers
        Set<Integer> matches = new TreeSet<>(ticket);
        matches.retainAll(winning);

        // 5- calculating the prize
        System.out.println("Your ticket was: " +ticket);
        System.out.println("Winning Numbers: " +matches);
        if (matches.size()> 0){
            double prize = PRIZE * Math.pow(2, matches.size());
            System.out.println("Matched Numbers: " +matches);
            System.out.printf("Your prize is $%.2f\n", prize);
        }

    }

// 1- Methods - generates a set of the winning lotto numbers
    public static Set<Integer> createWinningNumbers(){
        Set<Integer> winning = new TreeSet<>();
        Random random = new Random();

        while(winning.size() < NUMBERS){
            int number = random.nextInt(MAX_NUMBERS) + 1;
            winning.add(number);
        }
        return winning;
    }


    // 2 - Reading the players lottery ticket from the console
    public static Set<Integer> getTicket(){
      Set<Integer> ticket = new TreeSet<>();
      Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + NUMBERS + " Lotto numbers: ");

        while(ticket.size() < NUMBERS) {
            int number = scanner.nextInt();
            ticket.add(number);

        }
        return ticket;
    }

}

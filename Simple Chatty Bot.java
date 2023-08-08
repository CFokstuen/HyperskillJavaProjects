package bot;

import java.util.Scanner;

public class SimpleBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String botName = "Aid";
        int birthYear = 2018;
        String introduction = "Please, remind me your name.";
        String greeting = "What a great name you have, ";
        String guessAge = "Let me guess your age.";
        String requestNumbers = "Enter remainders of dividing your age by 3, 5 and 7.";
        String ageIs = "Your age is ";
        String goodTime = "; that's a good time to start programming!";
        String amaze = "Now I will prove to you that I can count to any number you want.";
        String farewell = "Completed, have a nice day!";
        String testUser = "Let's test your programming knowledge.";
        String questionUser = "Why do we use methods?";
        String answer1 = "1. To repeat a statement multiple times.";
        String answer2 = "2. To decompose a program into several small subroutines.";
        String answer3 = "3. To determine the execution time of a program.";
        String answer4 = "4. To interrupt the execution of a program.";
        String userRetry = "Please, try again.";
        String correctAnswer = "Congratulations, have a nice day!";


        System.out.println("Hello! My name is " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
        System.out.println(introduction);
        String userName = scanner.nextLine();
        System.out.println(greeting + userName + "!");

        System.out.println(guessAge);
        System.out.println(requestNumbers);
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        scanner.nextLine();
        int userAge = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println(ageIs + userAge + goodTime);

        System.out.println(amaze);
        int number = scanner.nextInt();
        for (int i = 0; i <= number; i++) {
            System.out.println(i + "!");
        }
        System.out.println(farewell);

        System.out.println(testUser);
        System.out.println(questionUser);
        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);
        scanner.nextLine();
        while (true) {
            if (scanner.nextInt() == 2) {
                System.out.println(correctAnswer);
                break;
            } else {
                System.out.println(userRetry);
            }
        }
        scanner.close();
    }
}

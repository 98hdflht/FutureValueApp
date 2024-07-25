// Quincy Yarbrough
// 7/25/2024
// Future value app tha returns all calculations made that session in a formatted form

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class FutureValueApp {
    // linked list used to store the daa
    public static LinkedList<String> values = new LinkedList<>();

    public static void main(String[] args) {

        System.out.println("Welcome to the future value calculator\n");

        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equalsIgnoreCase("y")){
            System.out.println("DATA ENTRY");
            double monthlyInvestment = getDouble(sc, "Enter monthly investment: ", 0, 1000);
            double interestRate = getDouble(sc, "Enter yearly interest rate: ", 0, 30);
            int years = getInt(sc, "Enter number of years: ", 0, 100);
            System.out.println();

            double futureValue = calculateFutureValue(monthlyInvestment, interestRate, years);
            // added a currency format so instead of 10K it shows the full value
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            NumberFormat c = NumberFormat.getCompactNumberInstance();
            NumberFormat p = NumberFormat.getPercentInstance();
            p.setMinimumFractionDigits(1);
            // adding values to linked list
            values.add(currencyFormatter.format(monthlyInvestment));
            values.add(p.format(interestRate / 100));
            values.add(Integer.toString(years));
            values.add(currencyFormatter.format(futureValue));

            System.out.println("FORMATTED RESULTS\n" +
                    "Monthly investment:   " + c.format(monthlyInvestment) + "\n" +
                    "Yearly interest rate: " + p.format(interestRate / 100) + "\n" +
                    "Number of years:      " + years + "\n" +
                    "Future value:         " + c.format(futureValue) + "\n");

            System.out.println("Continue? (y/n):");
            choice = sc.nextLine();
            System.out.println();
            // looks for the "n" so it knows when to move on to the end
            if (choice.equalsIgnoreCase("n")) {
                printResults();
            }
        }
    }

    public static double getDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine());
                return value;
            }catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again");
            }
        }
    }

    public static double getDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            double value = getDouble(sc, prompt);
            if (value > min && value < max) {
                return value;
            } else {
                System.out.println("Error! Number must be greater than " + min + " and less than " + max + ".");

            }
        }
    }

    public static int getInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again");
            }
        }
    }

    public static int getInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            int value = getInt(sc, prompt);
            if (value > min && value < max){
                return value;
            } else {
                System.out.println("Error! Number must be greater than " + min + " and less than " + max + ".");
            }
        }
    }

    public static double calculateFutureValue(double monthlyInvestment, double interestRate, int years){
        double monthlyInterestRate = interestRate / 12 / 100;
        int months = years * 12;
        double futureValue = 0.0;
        for (int i = 1; i <= months; i++) {
            futureValue = (futureValue + monthlyInvestment ) * (1 + monthlyInterestRate);
        }
        return futureValue;
    }
    // formatted results for the final output found this format style more to my taste
    public static void printResults() {
        System.out.println("ALL CALCULATIONS");
        System.out.printf("%-15s %-10s %-10s %-15s%n", "Inv/Mo.", "Rate", "Years", "Future Value");
        while (!values.isEmpty()) {
            String investment = values.poll();
            String interest = values.poll();
            String year = values.poll();
            String value = values.poll();
            System.out.printf("%-15s %-10s %-10s %-15s%n", investment, interest, year, value);
        }

    }
}
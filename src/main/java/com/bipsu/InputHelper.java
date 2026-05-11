package com.bipsu;

import java.util.Scanner;

public class InputHelper {

    static Scanner sc = new Scanner(System.in);

    // Get a non-empty String input
    public static String getString(String prompt) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
        return input.trim();
    }

    // Get a valid integer input
    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Get a valid positive integer
    public static int getPositiveInt(String prompt) {
        int value;
        while (true) {
            value = getInt(prompt);
            if (value > 0) return value;
            System.out.println("Value must be greater than 0.");
        }
    }

    // Get a valid email (must contain @ and .)
    public static String getEmail(String prompt) {
        String email;
        while (true) {
            email = getString(prompt);
            if (email.contains("@") && email.contains(".")) return email;
            System.out.println("Invalid email format. Try again.");
        }
    }

    // Get a valid phone (numbers only, 11 digits)
    public static String getPhone(String prompt) {
        String phone;
        while (true) {
            phone = getString(prompt);
            if (phone.matches("\\d{11}")) return phone;
            System.out.println("Invalid phone number. Must be 11 digits.");
        }
    }
}



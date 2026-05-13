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
// Get a valid double/decimal input
public static double getDouble(String prompt) {
    while (true) {
        try {
            System.out.print(prompt);
            String input = sc.nextLine();
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a decimal number.");
        }
    }
}

// Get a name (letters and spaces only, no numbers)
public static String getValidName(String prompt) {
    String name;
    while (true) {
        name = getString(prompt);
        if (name.matches("[a-zA-Z ]+")) return name;
        System.out.println("Name must contain letters only. No numbers allowed.");
    }
   }
}
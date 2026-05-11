package com.bipsu;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * InputHelper.java
 * Handles all user input reading and validation for the Library System.
 * Branch: Input-&-Validation-Engineer
 */
public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // ─────────────────────────────────────────────
    // PATTERNS
    // ─────────────────────────────────────────────
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^(09|\\+639)\\d{9}$");

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[a-zA-Z\\s\\-']{2,100}$");

    private static final Pattern BOOK_ID_PATTERN =
            Pattern.compile("^BK-\\d{4,}$");          // e.g. BK-0001

    private static final Pattern MEMBER_ID_PATTERN =
            Pattern.compile("^MB-\\d{4,}$");          // e.g. MB-0001

    private static final Pattern DATE_PATTERN =
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); // YYYY-MM-DD


    // ─────────────────────────────────────────────
    // GENERIC HELPERS
    // ─────────────────────────────────────────────

    /**
     * Reads a non-empty string from the user.
     */
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            printError("Input cannot be empty. Please try again.");
        }
    }

    /**
     * Reads a positive integer from the user.
     */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) return value;
                printError("Please enter a positive number.");
            } catch (NumberFormatException e) {
                printError("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a yes/no confirmation from the user.
     * Returns true for 'y' or 'yes', false for 'n' or 'no'.
     */
    public static boolean readConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            printError("Please enter 'y' for yes or 'n' for no.");
        }
    }


    // ─────────────────────────────────────────────
    // MEMBER INPUT & VALIDATION
    // ─────────────────────────────────────────────

    /**
     * Reads and validates a member's full name.
     */
    public static String readMemberName(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (isValidName(input)) return input;
            printError("Invalid name. Use letters, spaces, hyphens only (2–100 characters).");
        }
    }

    /**
     * Reads and validates a Member ID (format: MB-XXXX).
     */
    public static String readMemberId(String prompt) {
        while (true) {
            String input = readString(prompt).toUpperCase();
            if (isValidMemberId(input)) return input;
            printError("Invalid Member ID. Format must be MB-XXXX (e.g. MB-0001).");
        }
    }

    /**
     * Reads and validates an email address.
     */
    public static String readEmail(String prompt) {
        while (true) {
            String input = readString(prompt).toLowerCase();
            if (isValidEmail(input)) return input;
            printError("Invalid email address. Please enter a valid email (e.g. student@school.edu.ph).");
        }
    }

    /**
     * Reads and validates a Philippine phone number (09XXXXXXXXX or +639XXXXXXXXX).
     */
    public static String readPhone(String prompt) {
        while (true) {
            String input = readString(prompt).replaceAll("\\s", "");
            if (isValidPhone(input)) return input;
            printError("Invalid phone number. Use format: 09XXXXXXXXX or +639XXXXXXXXX.");
        }
    }


    // ─────────────────────────────────────────────
    // BOOK INPUT & VALIDATION
    // ─────────────────────────────────────────────

    /**
     * Reads and validates a Book ID (format: BK-XXXX).
     */
    public static String readBookId(String prompt) {
        while (true) {
            String input = readString(prompt).toUpperCase();
            if (isValidBookId(input)) return input;
            printError("Invalid Book ID. Format must be BK-XXXX (e.g. BK-0001).");
        }
    }

    /**
     * Reads and validates a book title.
     */
    public static String readBookTitle(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (input.length() >= 1 && input.length() <= 200) return input;
            printError("Book title must be between 1 and 200 characters.");
        }
    }

    /**
     * Reads and validates a book author name.
     */
    public static String readBookAuthor(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (isValidName(input)) return input;
            printError("Invalid author name. Use letters, spaces, hyphens only (2–100 characters).");
        }
    }

    /**
     * Reads and validates the number of book copies (must be >= 1).
     */
    public static int readBookCopies(String prompt) {
        while (true) {
            int copies = readInt(prompt);
            if (copies >= 1 && copies <= 1000) return copies;
            printError("Number of copies must be between 1 and 1000.");
        }
    }


    // ─────────────────────────────────────────────
    // BORROW INPUT & VALIDATION
    // ─────────────────────────────────────────────

    /**
     * Reads and validates a borrow date (format: YYYY-MM-DD).
     */
    public static String readBorrowDate(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (isValidDate(input)) return input;
            printError("Invalid date format. Please use YYYY-MM-DD (e.g. 2026-05-11).");
        }
    }

    /**
     * Reads and validates a return date (format: YYYY-MM-DD).
     * Also checks that return date is after the borrow date.
     */
    public static String readReturnDate(String prompt, String borrowDate) {
        while (true) {
            String input = readString(prompt);
            if (!isValidDate(input)) {
                printError("Invalid date format. Please use YYYY-MM-DD (e.g. 2026-05-25).");
                continue;
            }
            if (input.compareTo(borrowDate) <= 0) {
                printError("Return date must be after the borrow date (" + borrowDate + ").");
                continue;
            }
            return input;
        }
    }

    /**
     * Reads and validates the number of days to borrow (1–30 days allowed).
     */
    public static int readBorrowDays(String prompt) {
        while (true) {
            int days = readInt(prompt);
            if (days >= 1 && days <= 30) return days;
            printError("Borrow period must be between 1 and 30 days.");
        }
    }


    // ─────────────────────────────────────────────
    // VALIDATION METHODS
    // ─────────────────────────────────────────────

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidBookId(String bookId) {
        return bookId != null && BOOK_ID_PATTERN.matcher(bookId).matches();
    }

    public static boolean isValidMemberId(String memberId) {
        return memberId != null && MEMBER_ID_PATTERN.matcher(memberId).matches();
    }

    public static boolean isValidDate(String date) {
        if (date == null || !DATE_PATTERN.matcher(date).matches()) return false;
        try {
            String[] parts = date.split("-");
            int month = Integer.parseInt(parts[1]);
            int day   = Integer.parseInt(parts[2]);
            return month >= 1 && month <= 12 && day >= 1 && day <= 31;
        } catch (Exception e) {
            return false;
        }
    }


    // ─────────────────────────────────────────────
    // UTILITY
    // ─────────────────────────────────────────────

    private static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
package com.bipsu;

import java.sql.*;

public class CRUDOperations {

    // ══════════════════════════════════════════════════════
    //  BOOK OPERATIONS
    // ══════════════════════════════════════════════════════

    // ADD BOOK
    public static void addBook() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String title    = InputHelper.getString("Enter Book Title: ");
            String author   = InputHelper.getString("Enter Author: ");
            String category = InputHelper.getString("Enter Category: ");
            int quantity    = InputHelper.getInt("Enter Quantity: ");

            conn = DBConnection.connect();
            String sql = "INSERT INTO books (title, author, category, quantity) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setInt(4, quantity);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        } finally {
            closeResources(ps, conn);
        }
    }

    // VIEW ALL BOOKS
    public static void viewAllBooks() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            String sql = "SELECT * FROM books";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("\n--- BOOK LIST ---");
            System.out.printf("%-5s %-30s %-20s %-15s %-5s%n",
                    "ID", "Title", "Author", "Category", "Qty");
            System.out.println("-".repeat(80));

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-5d %-30s %-20s %-15s %-5d%n",
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity"));
            }
            if (!found) System.out.println("No books found.");

        } catch (SQLException e) {
            System.out.println("Error viewing books: " + e.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }

    // SEARCH BOOK BY ID
    public static void searchBookById(int bookId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            String sql = "SELECT * FROM books WHERE book_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- BOOK DETAILS ---");
                System.out.println("ID       : " + rs.getInt("book_id"));
                System.out.println("Title    : " + rs.getString("title"));
                System.out.println("Author   : " + rs.getString("author"));
                System.out.println("Category : " + rs.getString("category"));
                System.out.println("Quantity : " + rs.getInt("quantity"));
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error searching book: " + e.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }

    // UPDATE BOOK
    public static void updateBook() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            int bookId      = InputHelper.getInt("Enter Book ID to update: ");
            String title    = InputHelper.getString("Enter new Title: ");
            String author   = InputHelper.getString("Enter new Author: ");
            String category = InputHelper.getString("Enter new Category: ");
            int quantity    = InputHelper.getInt("Enter new Quantity: ");

            conn = DBConnection.connect();
            String sql = "UPDATE books SET title=?, author=?, category=?, quantity=? WHERE book_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setInt(4, quantity);
            ps.setInt(5, bookId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        } finally {
            closeResources(ps, conn);
        }
    }

    // DELETE BOOK
    public static void deleteBook() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            int bookId = InputHelper.getInt("Enter Book ID to delete: ");
            String confirm = InputHelper.getString("Are you sure? (yes/no): ");

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Delete cancelled.");
                return;
            }

            conn = DBConnection.connect();
            String sql = "DELETE FROM books WHERE book_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        } finally {
            closeResources(ps, conn);
        }
    }

    // ══════════════════════════════════════════════════════
    //  MEMBER OPERATIONS
    // ══════════════════════════════════════════════════════

    // ADD MEMBER
    public static void addMember() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String name  = InputHelper.getString("Enter Member Name: ");
            String email = InputHelper.getString("Enter Email: ");
            String phone = InputHelper.getString("Enter Phone: ");

            conn = DBConnection.connect();
            String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Member added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        } finally {
            closeResources(ps, conn);
        }
    }

    // VIEW ALL MEMBERS
    public static void viewAllMembers() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            String sql = "SELECT * FROM members";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("\n--- MEMBER LIST ---");
            System.out.printf("%-5s %-25s %-25s %-15s%n",
                    "ID", "Name", "Email", "Phone");
            System.out.println("-".repeat(75));

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-5d %-25s %-25s %-15s%n",
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"));
            }
            if (!found) System.out.println("No members found.");

        } catch (SQLException e) {
            System.out.println("Error viewing members: " + e.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }

    // SEARCH MEMBER BY ID
    public static void searchMemberById(int memberId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            String sql = "SELECT * FROM members WHERE member_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, memberId);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- MEMBER DETAILS ---");
                System.out.println("ID    : " + rs.getInt("member_id"));
                System.out.println("Name  : " + rs.getString("name"));
                System.out.println("Email : " + rs.getString("email"));
                System.out.println("Phone : " + rs.getString("phone"));
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error searching member: " + e.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }

    // UPDATE MEMBER
    public static void updateMember() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            int memberId = InputHelper.getInt("Enter Member ID to update: ");
            String name  = InputHelper.getString("Enter new Name: ");
            String email = InputHelper.getString("Enter new Email: ");
            String phone = InputHelper.getString("Enter new Phone: ");

            conn = DBConnection.connect();
            String sql = "UPDATE members SET name=?, email=?, phone=? WHERE member_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, memberId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Member updated successfully!");
            } else {
                System.out.println("Member ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
        } finally {
            closeResources(ps, conn);
        }
    }

    // DELETE MEMBER
    public static void deleteMember() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            int memberId = InputHelper.getInt("Enter Member ID to delete: ");
            String confirm = InputHelper.getString("Are you sure? (yes/no): ");

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Delete cancelled.");
                return;
            }

            conn = DBConnection.connect();
            String sql = "DELETE FROM members WHERE member_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, memberId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Member deleted successfully!");
            } else {
                System.out.println("Member ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        } finally {
            closeResources(ps, conn);
        }
    }

    // ══════════════════════════════════════════════════════
    //  BORROW / RETURN OPERATIONS (with TRANSACTION)
    // ══════════════════════════════════════════════════════

    // BORROW BOOK — uses transaction (commit/rollback)
    public static void borrowBook() {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            int memberId = InputHelper.getInt("Enter Member ID: ");
            int bookId   = InputHelper.getInt("Enter Book ID: ");

            conn = DBConnection.connect();

            // Disable auto-commit to start transaction
            conn.setAutoCommit(false);

            // Step 1: Check if book has available quantity
            PreparedStatement checkPs = conn.prepareStatement(
                    "SELECT quantity FROM books WHERE book_id = ?");
            checkPs.setInt(1, bookId);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next() || rs.getInt("quantity") <= 0) {
                System.out.println("Book not available or does not exist.");
                conn.rollback(); // rollback if book unavailable
                return;
            }

            // Step 2: Insert borrow record
            String sql1 = "INSERT INTO borrow (member_id, book_id, borrow_date) VALUES (?, ?, CURDATE())";
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, memberId);
            ps1.setInt(2, bookId);
            ps1.executeUpdate();

            // Step 3: Decrease book quantity
            String sql2 = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, bookId);
            ps2.executeUpdate();

            // Commit both operations together
            conn.commit();
            System.out.println("Book borrowed successfully!");

        } catch (SQLException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
            try {
                if (conn != null) conn.rollback(); // rollback on error
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        } finally {
            closeResources(ps1, conn);
            closeResources(ps2, null);
        }
    }

    // RETURN BOOK — uses transaction (commit/rollback)
    public static void returnBook() {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            int borrowId = InputHelper.getInt("Enter Borrow Record ID: ");

            conn = DBConnection.connect();
            conn.setAutoCommit(false);

            // Step 1: Get the book_id from borrow record
            PreparedStatement checkPs = conn.prepareStatement(
                    "SELECT book_id FROM borrow WHERE borrow_id = ? AND return_date IS NULL");
            checkPs.setInt(1, borrowId);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Borrow record not found or already returned.");
                conn.rollback();
                return;
            }

            int bookId = rs.getInt("book_id");

            // Step 2: Update return date
            String sql1 = "UPDATE borrow SET return_date = CURDATE() WHERE borrow_id = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, borrowId);
            ps1.executeUpdate();

            // Step 3: Increase book quantity
            String sql2 = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, bookId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Book returned successfully!");

        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        } finally {
            closeResources(ps1, conn);
            closeResources(ps2, null);
        }
    }

    // VIEW ALL BORROW RECORDS
    public static void viewBorrowRecords() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            String sql = "SELECT b.borrow_id, m.name, bk.title, b.borrow_date, b.return_date " +
                         "FROM borrow b " +
                         "JOIN members m ON b.member_id = m.member_id " +
                         "JOIN books bk ON b.book_id = bk.book_id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("\n--- BORROW RECORDS ---");
            System.out.printf("%-5s %-20s %-25s %-15s %-15s%n",
                    "ID", "Member", "Book", "Borrow Date", "Return Date");
            System.out.println("-".repeat(85));

            boolean found = false;
            while (rs.next()) {
                found = true;
                String returnDate = rs.getString("return_date") != null
                        ? rs.getString("return_date") : "Not returned";
                System.out.printf("%-5d %-20s %-25s %-15s %-15s%n",
                        rs.getInt("borrow_id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("borrow_date"),
                        returnDate);
            }
            if (!found) System.out.println("No borrow records found.");

        } catch (SQLException e) {
            System.out.println("Error viewing records: " + e.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }

    // ══════════════════════════════════════════════════════
    //  UTILITY: Close Resources
    // ══════════════════════════════════════════════════════

   static void closeResources(PreparedStatement ps, Connection conn){
    try {
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        System.out.println("Error closing resources: " + e.getMessage());
    }
   }
   static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn){
    try {
        if (rs !=null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        System.out.println("Error closing resources: " + e.getMessage());
    }
   }
}

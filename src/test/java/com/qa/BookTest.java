package com.qa;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void testConstructorwithSettersandGetters(){
        Book myBook = new Book ("The Great Gatsby","Classic Literature",180,"F.Scott Fitzgerald",330);
        assertEquals("The Great Gatsby",myBook.getTitle());
        assertEquals("Classic Literature",myBook.getGenre());
        assertEquals(180,myBook.getCopies());
        assertEquals("F.Scott Fitzgerald",myBook.getAuthor());
        assertEquals(330,myBook.getNumberOfPages());
    }

    @Test
    void testNumberOfPagesGetterSetter() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        assertEquals(100, b.getNumberOfPages());
        b.setNumberOfPages(250);
        assertEquals(250, b.getNumberOfPages());
    }

    @Test
    void test_getLoanDays_is30() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        assertEquals(30, b.getLoanDays());
    }

    @Test
    void test_dueDate_isBorrowedDatePlusLoanDays() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate borrowed = LocalDate.now().minusDays(5);
        assertEquals(borrowed.plusDays(30), b.dueDate(borrowed));
    }

    @Test
    void test_isOverdue_beforeDueDate_false() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate today = LocalDate.now();
        // Borrowed so that due date is tomorrow -> not overdue today
        LocalDate borrowed = today.minusDays(b.getLoanDays() - 1);
        assertFalse(b.isOverdue(borrowed));
    }

    @Test
    void test_isOverdue_onDueDate_false() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate today = LocalDate.now();
        // Borrowed exactly loanDays ago -> due today -> not overdue
        LocalDate borrowed = today.minusDays(b.getLoanDays());
        assertFalse(b.isOverdue(borrowed));
    }

    @Test
    void test_isOverdue_afterDueDate_true() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate today = LocalDate.now();
        // Borrowed so that due date was yesterday -> overdue today
        LocalDate borrowed = today.minusDays(b.getLoanDays() + 1);
        assertTrue(b.isOverdue(borrowed));
    }

    @Test
    void test_dueDate_usesGetLoanDaysOverride() {
        class ShortLoanBook extends Book {
            ShortLoanBook() { super("X", "Y", 1, "Z", 10); }
            @Override public int getLoanDays() { return 10; }
        }

        Book b = new ShortLoanBook();
        LocalDate today = LocalDate.now();
        LocalDate borrowed = today.minusDays(11); // due was yesterday for 10-day loan
        assertEquals(borrowed.plusDays(10), b.dueDate(borrowed));
        assertTrue(b.isOverdue(borrowed)); // overdue today
    }
}
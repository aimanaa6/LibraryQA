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
        LocalDate borrowed = LocalDate.of(2025, 1, 1);

        LocalDate expected = borrowed.plusDays(30);
        assertEquals(expected, b.dueDate(borrowed));
    }

    @Test
    void test_isOverdue_beforeDueDate_false() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate borrowed = LocalDate.of(2025, 2, 1);
        LocalDate dayBeforeDue = borrowed.plusDays(b.getLoanDays() - 1);

        assertFalse(b.isOverdue(borrowed, dayBeforeDue));
    }

    @Test
    void test_isOverdue_onDueDate_false() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate borrowed = LocalDate.of(2025, 2, 1);
        LocalDate due = b.dueDate(borrowed);

        assertFalse(b.isOverdue(borrowed, due));
    }

    @Test
    void test_isOverdue_afterDueDate_true() {
        Book b = new Book("Test", "Fiction", 1, "Anon", 100);
        LocalDate borrowed = LocalDate.of(2025, 2, 1);
        LocalDate dayAfterDue = b.dueDate(borrowed).plusDays(1);

        assertTrue(b.isOverdue(borrowed, dayAfterDue));
    }

    @Test
    void test_dueDate_usesGetLoanDaysOverride() {
        // Proves dueDate() relies on getLoanDays() rather than a hardcoded value
        class ShortLoanBook extends Book {
            ShortLoanBook() { super("X", "Y", 1, "Z", 10); }
            @Override public int getLoanDays() { return 10; }
        }

        Book b = new ShortLoanBook();
        LocalDate borrowed = LocalDate.of(2025, 3, 10);
        assertEquals(borrowed.plusDays(10), b.dueDate(borrowed));
    }
}
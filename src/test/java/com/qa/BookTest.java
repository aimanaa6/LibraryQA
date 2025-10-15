package com.qa;

import org.junit.jupiter.api.Test;

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
    void getNumberOfPages() {
    }

    @Test
    void setNumberOfPages() {
    }

    @Test
    void getLoanDays() {
    }

    @Test
    void dueDate() {
    }

    @Test
    void isOverdue() {
    }
}
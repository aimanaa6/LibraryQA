package com.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MemberParameterResolver.class)
class MemberTest {
    private Book sampleBook = new Book("Test Book", "Fiction", 1, "Anon", 123);;


    @Test
    @DisplayName("Member should be able to borrow when under max limit")
    void testCanBorrowInitially(Member<Book> member) {
        assertTrue(member.isAbleToBorrow(), "New member should be able to borrow");
    }

    @ParameterizedTest(name = "Borrowing up to limit: attempt {0}")
    @ValueSource(ints = {1, 5, 10})
    @DisplayName("Add loan succeeds while under max loan limit")
    void testAddLoanWithinLimit(int attempt, Member<Book> member) {
        Book book = new Book("Book " + attempt, "Fiction", 1, "Author", 100);
        assertTrue(member.addLoan(book, LocalDate.of(2025, 1, attempt)),
                "Should allow borrowing within limit");
    }

    @Test
    @DisplayName("Member cannot borrow more than MAX_LOANS")
    void testAddLoanFailsWhenAtMax(Member<Book> member) {
        // Fill up to max loans
        IntStream.rangeClosed(1, member.getMAX_LOANS())
                .forEach(i -> member.addLoan(new Book("B" + i, "Genre", 1, "Author", 100),
                        LocalDate.of(2025, 1, i)));

        assertFalse(member.isAbleToBorrow(), "At capacity, cannot borrow more");

        // Try adding one more
        assertFalse(member.addLoan(sampleBook, LocalDate.of(2025, 2, 1)),
                "Adding beyond max should fail");
    }

    @Test
    @DisplayName("Member cannot borrow the same item twice")
    void testDuplicateLoanFails(Member<Book> member) {
        LocalDate borrowDate = LocalDate.of(2025, 3, 10);
        assertTrue(member.addLoan(sampleBook, borrowDate),
                "First borrow should succeed");
        assertFalse(member.addLoan(sampleBook, borrowDate.plusDays(1)),
                "Borrowing same item twice should fail");
    }

    @Test
    @DisplayName("Removing an existing loan should succeed and free capacity")
    void testRemoveLoan(Member<Book> member) {
        assertTrue(member.addLoan(sampleBook, LocalDate.of(2025, 4, 1)));
        assertTrue(member.removeLoan(sampleBook), "Removing an existing loan should return true");
        assertTrue(member.isAbleToBorrow(), "After removal, member can borrow again");
    }

    @Test
    @DisplayName("getBorrowedDate returns Optional.empty when not borrowed; Optional with correct date after borrowing")
    void testGetBorrowedDate(Member<Book> member) {
        LocalDate date = LocalDate.of(2025, 5, 15);

        // Not borrowed yet → Optional.empty
        assertTrue(member.getBorrowedDate(sampleBook).isEmpty(),
                "Should be Optional.empty if not borrowed");

        // After borrowing → Optional contains the exact stored date
        assertTrue(member.addLoan(sampleBook, date));
        assertTrue(member.getBorrowedDate(sampleBook).isPresent(),
                "Should be present after borrowing");
        assertEquals(date, member.getBorrowedDate(sampleBook).orElseThrow(),
                "Should return the stored borrowed date");
    }
}
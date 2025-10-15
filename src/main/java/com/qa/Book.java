package com.qa;

import java.time.LocalDate;

public class Book extends LibraryItem implements Loanable{
    public Book(String title, String genre, int copies, String author, int numberOfPages) {
        super(title, genre, copies, author);
        this.numberOfPages = numberOfPages;

    }
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public int getLoanDays() {
        return 30;
    }

    @Override
    public LocalDate dueDate(LocalDate borrowedDate) {
//        use borrowed date parameter and calculate due date using getter return value
        return borrowedDate.plusDays(getLoanDays());
    }

    @Override
    public boolean isOverdue(LocalDate borrowedDate, LocalDate todaysDate) {
        return todaysDate.isAfter(dueDate(borrowedDate));
    }
    private int numberOfPages;
}

package com.qa;

import java.time.LocalDate;

public interface Loanable {
    int getLoanDays();
    LocalDate dueDate(LocalDate borrowedDate);
    boolean isOverdue(LocalDate borrowedDate, LocalDate todaysDate);
}

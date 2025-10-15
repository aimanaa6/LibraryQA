package com.qa;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// generics - and can work with LibraryItem
public class Member <T extends LibraryItem> {
    private String name;
    private int id;
    private LocalDate dateJoined;
    final int MAX_LOANS = 10;

    private final Map<T,LocalDate> loans = new HashMap<>();

    public boolean isAbleToBorrow(){
        return loans.size()<MAX_LOANS;
    }

    boolean addLoan(T item, LocalDate borrowedDate) {
        if (!isAbleToBorrow())
            return false;
        if (loans.containsKey(item))
            return false;
        loans.put(item, borrowedDate);
        return true;
    }

    boolean removeLoan(T item){
        return loans.remove(item)!= null;

    }

    public LocalDate getBorrowedDate(T item){
        return loans.get(item);
    }

    public Map<T, LocalDate> getLoans() {
        return loans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getMAX_LOANS() {
        return MAX_LOANS;
    }

    public Member(String name, int id, LocalDate dateJoined){
        this.name = name;
        this.id = id;
        this.dateJoined = dateJoined;
    }

}


package com.qa;

public abstract class LibraryItem {
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getCopies() {
        return copies;
    }

    public String getAuthor() {
        return author;
    }
//    constructor
    public LibraryItem(String title, String genre,int copies, String author){
        this.title = title;
        this.genre = genre;
        this.copies = copies;
        this.author = author;
    }


    private final String title;
    private final String genre;
    private final int copies;
    private final String author;
}



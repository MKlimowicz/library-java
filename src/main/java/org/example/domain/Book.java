package org.example.domain;

public class Book extends Publication{

    private String author;
    private String pages;
    private String isbn;

    public Book(String title, String publisher, int year, String author, String pages, String isbn) {
        super(title, publisher, year);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return String.format("" +
                        "[Book] ID: %s,  Tytuł: %s, " +
                        "Wydawca: %s, " +
                        "Rok wydania: %s, " +
                        "Author: %s, " +
                        "Liczba stron: %s " +
                        "Numer ISBN: %s",
                getId(), getTitle(), getPublisher(), getYear(), author, pages, isbn
        );
    }
}

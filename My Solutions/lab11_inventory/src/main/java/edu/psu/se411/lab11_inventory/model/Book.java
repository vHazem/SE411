package edu.psu.se411.lab11_inventory.model;

public class Book extends Item {
    private String author;

    public Book(int id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%-20s | ID: %-5d | Title: %-35s | Author: %s", 
                            "Book", getId(), getName(), author);
    }
}

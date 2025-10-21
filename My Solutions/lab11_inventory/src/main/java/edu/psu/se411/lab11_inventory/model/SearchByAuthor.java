package edu.psu.se411.lab11_inventory.model;

public class SearchByAuthor implements SearchCriteria<Book> {
    private String author;

    public SearchByAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean matches(Book book) {
        return book.getAuthor().equalsIgnoreCase(author);
    }
}

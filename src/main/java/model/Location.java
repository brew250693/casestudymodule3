package model;

public class Location {
    public Location(String name) {
        this.name = name;
    }

    private int id;
    private String name, description, book_amount, book_quanity;

    public Location(int id) {
        this.id = id;
    }

    public Location(int id, String name, String description, String book_amount, String book_quanity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.book_amount = book_amount;
        this.book_quanity = book_quanity;
    }

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Location(String name, String description, String book_amount, String book_quanity) {
        this.name = name;
        this.description = description;
        this.book_amount = book_amount;
        this.book_quanity = book_quanity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBook_amount() {
        return book_amount;
    }

    public void setBook_amount(String book_amount) {
        this.book_amount = book_amount;
    }

    public String getBook_quanity() {
        return book_quanity;
    }

    public void setBook_quanity(String book_quanity) {
        this.book_quanity = book_quanity;
    }
}

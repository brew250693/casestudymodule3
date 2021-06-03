package model;

public class Location {
    private int id;
    private String name, description, book_amount, book_quantity;

    public Location(int id, String name, String description, String book_amount, String book_quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.book_amount = book_amount;
        this.book_quantity = book_quantity;
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

    public String getBook_quantity() {
        return book_quantity;
    }

    public void setBook_quantity(String book_quantity) {
        this.book_quantity = book_quantity;
    }
}

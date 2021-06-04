package model;

public class Book {
    private int id;
    private String name, description, picture;
    private Status status;
    private Category category;
    private String author;
    private Location location;

    public Book(String name, String description, String picture, String author) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.author = author;
    }

    public Book(String name, String description, String picture, Status status, Category category, String author, Location location) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.status = status;
        this.category = category;
        this.author = author;
        this.location = location;
    }

    public Book(int id, String name, String description, String picture, Status status, Category category, String author, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.status = status;
        this.category = category;
        this.author = author;
        this.location = location;
    }

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(int id, String name, String description, String picture, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.author = author;
    }

    public Book(Status status, Category category, Location location) {
        this.status = status;
        this.category = category;
        this.location = location;
    }

    public Book(String name, String description, Status status, String author, Category category, Location location) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.author = author;
        this.location = location;
    }

    public Book(int id, String name, String description, Status status, String author, Category category, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.author = author;
        this.location = location;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", status=" + status.getName() +
                ", category=" + category.getName() +
                ", author='" + author + '\'' +
                ", location=" + location.getName() +
                '}';
    }
}

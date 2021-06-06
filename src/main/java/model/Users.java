package model;

public class Users {
    private int id;
    private String name;
    private String birth;
    private String email;
    private String phone;
    private String password;


    public Users(String name, String birth, String email, String phone, String password) {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Users(String name, String birth, String email, String phone) {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
    }

    public Users(int id, String name, String birth, String email, String phone) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
    }

    public Users(int id, String password) {
        this.id = id;
        this.password = password;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

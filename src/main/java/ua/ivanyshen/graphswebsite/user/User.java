package ua.ivanyshen.graphswebsite.user;

/**
 * @author - Max Ivanyshen
 */

public class User {

    public String name;
    public String email;
    public String pass1;
    public String pass2;

    public User() {}

    public User(String name, String email, String pass1, String pass2) {
        this.name = name;
        this.email = email;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pass1='" + pass1 + '\'' +
                ", pass2='" + pass2 + '\'' +
                '}';
    }
}

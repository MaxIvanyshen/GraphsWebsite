package ua.ivanyshen.graphswebsite.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author - Max Ivanyshen
 */

@Document(collection = "users")
public class User {

    @Id
    public int id;
    public String name;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    public String email;
    public String pass1;
    public String pass2;
    public String mainPass;
    public boolean premium;
    public boolean enabled;

    public User() {}

    public User(String name, String email, String pass1, String pass2) {
        this.name = name;
        this.email = email;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainPass() {
        return mainPass;
    }

    public void setMainPass(String mainPass) {
        this.mainPass = mainPass;
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
                ", pass='" + mainPass + '\'' +
                '}';
    }

    public void setEnabled(boolean enabled) {
        this.enabled = true;
    }
}
package ua.ivanyshen.graphswebsite.dao;

import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ua.ivanyshen.graphswebsite.Graph;
import ua.ivanyshen.graphswebsite.Infographics;
import ua.ivanyshen.graphswebsite.user.User;

import java.util.Random;

public class userDAO {
    public static MongoOperations ops;

    {
        ops = new MongoTemplate(MongoClients.create(), "users");
    }

    public static void saveUser(User user) {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        Random r = new Random();
        user.setMainPass(encryptor.encrypt(user.getPass1()));
        user.setId(r.nextInt(1000000000));
        user.setPass1("");
        user.setPass2("");
        ops.insert(user);
    }

    public static void deleteUser(User user) {
        Query q = new Query(Criteria.where("name").is(user.getName()));
        ops.findAndRemove(q, User.class);
    }

    public static User saveIg(User user, Infographics ig) {
        user.getInfographics().add(ig);
        ops.save(user);
        return user;
    }

    public User findUserByEmail(String email) {
        return ops.findOne(new Query(Criteria.where("email").is(email)), User.class);
    }

    public User findUserById(int id) {
        return ops.findOne(new Query(Criteria.where("_id").is(id)), User.class);
    }

    public static User saveGraph(User user, Graph graph) {
        user.getGraphs().add(graph);
        ops.save(user);
        return user;
    }
}

package ua.ivanyshen.graphswebsite.dao;

import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.ivanyshen.graphswebsite.user.User;

import java.util.Random;

@Repository
public class userDAO {
    public static MongoOperations ops;

    {
        ops = new MongoTemplate(MongoClients.create(), "users");
    }

    public static void save(User user) {
        Random r = new Random();
        if(user.getPass1().equals(user.getPass2()))
            user.setMainPass(user.getPass1());
        user.setId(r.nextInt(10000000));
        ops.insert(user);
    }

    public static void deleteUser(User user) {
        Query q = new Query(Criteria.where("name").is(user.getName()));
        ops.findAndRemove(q, User.class);
    }

}

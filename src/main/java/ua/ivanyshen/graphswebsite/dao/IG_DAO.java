package ua.ivanyshen.graphswebsite.dao;

import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.ivanyshen.graphswebsite.Infographics;
import ua.ivanyshen.graphswebsite.user.User;

import java.util.ArrayList;

public class IG_DAO {
    public static MongoOperations ops;

    {
        ops = new MongoTemplate(MongoClients.create(), "users");
    }

    public static Infographics findIgByName(User user, String name) {
        ArrayList<Infographics> igs = user.getInfographics();
        for(int i=0; i<igs.size(); ++i) {
            if(igs.get(i).getName().equals(name))
                return igs.get(i);
        }
        return null;
    }
}

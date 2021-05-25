package ua.ivanyshen.graphswebsite.dao;

import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ua.ivanyshen.graphswebsite.Graph;
import ua.ivanyshen.graphswebsite.user.User;

import java.util.ArrayList;

public class graphDAO {
    public static MongoOperations ops;

    {
        ops = new MongoTemplate(MongoClients.create(), "users");
    }


    public Graph findByName(String graphName, int id) {
        User user = ops.findOne(new Query(Criteria.where("_id").is(id)), User.class);
        ArrayList<Graph> list = user.getGraphs();
        Graph graph = new Graph();
        for(int i=0; i<list.size(); ++i)
            if(list.get(i).getName().equals(graphName))
                graph = list.get(i);
        return graph;
    }
}

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

    public void addParam(Graph graph) {
        String[] paramsNew = new String[graph.getParams().length+1];
        for(int i=0; i<graph.getParams().length; ++i)
            paramsNew[i] = graph.getParams()[i];
        graph.setParams(paramsNew);
        paramsNew[paramsNew.length-1] = "";

        int[] valuesNew = new int[graph.getValues().length+1];
        for(int i=0; i<graph.getValues().length; ++i)
            valuesNew[i] = graph.getValues()[i];
        graph.setValues(valuesNew);
        valuesNew[valuesNew.length-1] = 0;
    }
}

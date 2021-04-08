package ua.ivanyshen.graphswebsite.graph;

/**
 * @author - Max Ivanyshen
 */

public class Graph {
    private String column;
    private int id;
    private int columnNumber;

    public Graph() {}

    public Graph(String column, int id) {
        this.column = column;
        this.id = id;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    @Override
    public String toString() {
        return "Graph{Column: "+column+";\nid: "+id+"}";
    }
}

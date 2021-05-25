package ua.ivanyshen.graphswebsite;

/**
 * @author - Max Ivanyshen
 */

public class Graph {
    public String name;
    public String type;
    public int rowsNumber;
    public String id;
    public String[] colors;
    public String[] params;
    public int[] values;

    public Graph() {
        colors = new String[] {"Aquamarine", "Red", "Green"};
    }

    public Graph(String id, int[] values) {
        this.id = id;
        this.values = values;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRowsNumber(int rowsNumber) {
       // colors = new String[rowsNumber];
        params = new String[rowsNumber];
        for(int i=0; i<params.length; ++i)
            params[i] = "";
        values = new int[rowsNumber];
        for(int i=0; i<values.length; ++i)
            values[i] = 0;
        this.rowsNumber = rowsNumber;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}

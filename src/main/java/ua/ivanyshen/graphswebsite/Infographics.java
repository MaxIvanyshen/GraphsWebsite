package ua.ivanyshen.graphswebsite;

public class Infographics {
    public String name;
    public int id;
    public String img;
    public int[] idArr = new int[] {1, 2, 3, 4, 5, 6};
    public String[] params;
    public String[] desc;
    public String[] values;
    public String ig;

    public String[] getDesc() {
        return desc;
    }

    public void setDesc(String[] desc) {
        this.desc = desc;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public int[] getIdArr() {
        return idArr;
    }

    public void setIdArr(int[] idArr) {
        this.idArr = idArr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

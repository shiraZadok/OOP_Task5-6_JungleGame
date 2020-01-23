package element;

import Server.Game_Server;
import Server.game_service;
import com.google.gson.annotations.JsonAdapter;
import dataStructure.Edge;
import dataStructure.edge_data;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

public class Fruits implements elementFruitRobot{

    private Point3D pos;
    private double value;
    private int type;
    private String pic;

    /**
     * a default constructor.
     */
    public Fruits() {
        this.pos = null;
        this.value = 0;
        this.type = 0;
        this.pic = null;
    }

    /**
     * constructor of new fruit.
     * @param pos is the location.
     * @param value is the amount of points the fruit is worth.
     * @param type banana or apple.
     * @param pic the picture for gui.
     */
    public Fruits(Point3D pos, double value, int type, String pic){
        this.pos = pos;
        this.value = value;
        this.type = type;
        this.pic = pic;
    }

    /**
     * This function receives a json file and saves from it all the values of the fields of the fruit.
     * @param json is the file we get.
     * @return the fruit.
     */
    @Override
    public elementFruitRobot init(String json) {
        Fruits temp = new Fruits();
        try {
            JSONObject fruit = new JSONObject(json);
            JSONObject fruitt = fruit.getJSONObject("Fruit");
            temp.type = fruitt.getInt("type");
            temp.value = fruitt.getDouble("value");
            if(temp.type==1) temp.pic="apple.png";
            else if(temp.type==-1) temp.pic= "banana.png";
            else temp.pic = "";
            String pos = fruitt.getString("pos");
            temp.pos = new Point3D(pos);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @return a string that represent the picture of the fruit.
     */
    @Override
    public String get_pic() {
        return this.pic;
    }

    /**
     * This function updates the picture of the fruit.
     * @param json is the new picture.
     */
    @Override
    public void set_pic(String json) {
        this.pic = json;
    }

    /**
     * @return a point that represent the location of the fruit.
     */
    public Point3D getLocation() {
        return this.pos;
    }

    /**
     * This function updates the location of the fruit.
     * @param p is the new location.
     */
    public void setLocation(Point3D p) {
        this.pos = p;
    }

    /**
     * @return the value of the fruit.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * This function updates the value of the fruit.
     * @param v is the new value.
     */
    public void setValue(double v) {
        this.value = v;
    }

    /**
     * @return the type of the fruit.
     */
    public int getType() {
        return this.type;
    }

    /**
     * This function updates the type of the fruit.
     * @param t is the new type.
     */
    public void setType(int t) {
        this.type = t;
    }
}

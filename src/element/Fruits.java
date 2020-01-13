package element;

import Server.Game_Server;
import Server.game_service;
import com.google.gson.annotations.JsonAdapter;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

public class Fruits implements elementFruitRobot{

    private Point3D pos;
    private double value;
    private int type;
    private String pic;

    public Fruits() {
        this.pos = null;
        this.value = 0;
        this.type = 0;
        this.pic = null;
    }

    public Fruits( double value, int type, Point3D pos){
        this.pos = pos;
        this.value = value;
        this.type = type;
    }

    public String toString(){
        return "pos:"+ pos.toString()+ "\n" + "value:"+ this.value+ "\n" + "type:" + this.type;
    }

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

    @Override
    public String get_pic() {
        return this.pic;
    }

    @Override
    public void set_pic(String json) {
        this.pic = json;
    }

    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public Point3D getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(Point3D p) {
        this.pos = p;
    }

    @Override
    public double getWeight() {
        return this.value;
    }

    @Override
    public void setWeight(double w) {
        this.value = w;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.type;
    }

    @Override
    public void setTag(int t) {
        this.type = t;
    }
}

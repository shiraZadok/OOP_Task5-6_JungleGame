package element;

import Server.Game_Server;
import Server.game_service;
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
    public void init(String json) {
        try {
            JSONObject fruit = new JSONObject(json);
            this.type = fruit.getInt("type");
            this.value = fruit.getInt("value");

            if(this.value==1) this.pic="apple.png";
            else if(this.value==-1) this.pic= "banana.png";
            else {
                this.pic = "";
            }


            String pos = fruit.getString("pos");
            String[] stringPos = pos.split(",");
            double[] doublePos = new double[stringPos.length];
            for (int j = 0; j < stringPos.length; j++) {
                doublePos[j] = Double.valueOf(stringPos[j]);
            }
            this.pos = new Point3D(doublePos[0], doublePos[1], doublePos[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String get_pic() {
        if(this.value==1) return "apple.png";
        else if(this.value==-1) return "banana.png";
        else{
            return"";
        }
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

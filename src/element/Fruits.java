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
    public elementFruitRobot init(String json) {
        Fruits newFruit = new Fruits();
            try
            {
                JSONObject fruit = new JSONObject(json);
                System.out.println("the line = "+ fruit);
                int type = fruit.getInt("type");
                double value = fruit.getInt("value");
                String pos = fruit.getString("pos");
                String[] stringPos = pos.split(",");
                double [] doublePos = new double[stringPos.length];
                for (int j=0; j<stringPos.length;j++){
                    doublePos[j]= Double.valueOf(stringPos[j]);
                }
                newFruit = new Fruits(value, type, new Point3D(doublePos[0], doublePos[1], doublePos[2]));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return newFruit;
        }

    @Override
    public String get_pic() {
        return null;
    }

    @Override
    public void set_pic(String json) {

    }

    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public Point3D getLocation() {
        return null;
    }

    @Override
    public void setLocation(Point3D p) {

    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

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
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}

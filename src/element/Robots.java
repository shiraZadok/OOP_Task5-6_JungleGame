package element;

import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

public class Robots implements elementFruitRobot {

    private int src;
    private int dest;
    private int id;
    private Point3D pos;
    private int speed;
    private double value;
    private String pic;

    public Robots(){
        this.src = 0;
        this.dest = 0;
        this.id = 0;
        this.pos = null;
        this.speed = 0;
        this.value = 0;
        this.pic = "robot.png";
    }

    public Robots(int src, int dest, int id, int speed, int value, Point3D pos){
        this.src = src;
        this.dest = dest;
        this.id = id;
        this.pos = pos;
        this.speed = speed;
        this.value = value;
        this.pic = "robot.png";
    }

    @Override
    public elementFruitRobot init(String json) {
        Robots temp = new Robots();
        try {
            JSONObject robot = new JSONObject(json);
            JSONObject robott = robot.getJSONObject("Robot");
            temp.src = robott.getInt("src");
            //System.out.println("srcRobot:" + temp.src); //////////////////
            temp.dest = robott.getInt("dest");
            //System.out.println("destRobot:" + temp.dest); ///////////////
            temp.value = robott.getDouble("value");
            temp.id = robott.getInt("id");
            String pos = robott.getString("pos");
            temp.pos = new Point3D(pos);
            temp.speed = robott.getInt("speed");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public String get_pic() {
        return "robot.png";
    }

    @Override
    public void set_pic(String json) {

    }

    public int getId() {
        return this.id;    }

    public Point3D getLocation() {
        return this.pos;
    }

    public void setLocation(Point3D p) {
        this.pos = p;

    }

    public int getSrc(){
        return this.src;
    }

    public void setSrc(int s){
        this.src = s;
    }

    public int getDest(){
        return this.dest;
    }

    public void setDest(int d){
        this.dest = d;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setSpeed(int sp){
        this.speed=sp;
    }

    public double getValue(){
        return this.value;
    }

    public void setValue(double v){
        this.value = v;
    }


}

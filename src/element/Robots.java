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
    private String myFunction;

    /**
     * a default constructor.
     */
    public Robots(){
        this.src = 0;
        this.dest = 0;
        this.id = 0;
        this.pos = null;
        this.speed = 0;
        this.value = 0;
        this.pic = "robot.png";
        this.myFunction = "";
    }

    /**
     * constructor of new robot.
     * @param src is the node the robot is on.
     * @param dest is the node the robot goes to.
     * @param id is the robot id.
     * @param pos is the robot location
     * @param speed is the robot speed.
     * @param value is the value the robot collected.
     * @param pic is the robot picture for gui.
     */
    public Robots(int src,int dest,int id,Point3D pos,int speed,double value,String pic) {
        this.src = src;
        this.dest = dest;
        this.id = id;
        this.pos = pos;
        this.speed = speed;
        this.value = value;
        this.pic = pic;
        this.myFunction = "";

    }

    /**
     * This function receives a json file and saves from it all the values of the fields of the robot.
     * @param json is the file we get.
     * @return the robot.
     */
    @Override
    public elementFruitRobot init(String json) {
        Robots temp = new Robots();
        try {
            JSONObject robot = new JSONObject(json);
            JSONObject robott = robot.getJSONObject("Robot");
            temp.src = robott.getInt("src");
            temp.dest = robott.getInt("dest");
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

    /**
     * @return a string that represent the picture of the robot.
     */
    @Override
    public String get_pic() {
        return "monkey.png";
    }

    /**
     * This function updates the picture of the robot.
     * @param json is the new picture.
     */
    @Override
    public void set_pic(String json) {

    }

    /**
     * @return the robot id.
     */
    public int getId() { return this.id; }

    /**
     * @return a point that represent the location of the robot.
     */
    public Point3D getLocation() {
        return this.pos;
    }

    /**
     * @return the key of node the robot is on.
     */
    public int getSrc(){
        return this.src;
    }

    /**
     * This function updates the src of the robot.
     * @param s is the new src.
     */
    public void setSrc(int s){
        this.src = s;
    }

    /**
     * @return the key of node the robot goes to.
     */
    public int getDest(){
        return this.dest;
    }

    /**
     * This function updates the dest of the robot.
     * @param d is the new dest.
     */
    public void setDest(int d){
        this.dest = d;
    }

    /**
     * @return the value the robot collected.
     */
    public double getValue(){
        return this.value;
    }

    /**
     * This function updates the value of the robot.
     * @param v is the new value.
     */
    public void setValue(double v){
        this.value = v;
    }

    public double getSpeed(){return this.speed;}

    /**
     * @return the name of his function
     */
    public String getMyFunction(){
        return this.myFunction;
    }

    /**
     * This function updates the myFunction of the robot.
     * @param s is the new value.
     */
    public void setMyFunction(String s){
        this.myFunction = s;
    }


    public static void main(String[] args) {

        Robots[] checkforBuildRobot = new Robots[8];
        Point3D[] checkPoint3D = new Point3D[8];
        checkPoint3D[0] = new Point3D(1,2,0);
        checkPoint3D[1] = new Point3D(3,4,0);
        checkPoint3D[2] = new Point3D(5,6,0);
        checkPoint3D[3] = new Point3D(7,8,0);
        checkPoint3D[4] = new Point3D(9,10,0);
        checkPoint3D[5] = new Point3D(11,12,0);
        checkPoint3D[6] = new Point3D(13,14,0);
        checkPoint3D[7] = new Point3D(15,16,0);

        checkforBuildRobot [0] = new Robots(1,1,1,checkPoint3D[0],1,1,"monkey.png");
        checkforBuildRobot [1] = new Robots(2,2,2,checkPoint3D[1],2,2,"monkey.png");
        checkforBuildRobot [2] = new Robots(3,3,3,checkPoint3D[2],3,3,"monkey.png");
        checkforBuildRobot [3] = new Robots(4,4,4,checkPoint3D[3],4,4,"monkey.png");
        checkforBuildRobot [4] = new Robots(5,5,5,checkPoint3D[4],5,5,"monkey.png");
        checkforBuildRobot [5] = new Robots(6,6,6,checkPoint3D[5],6,6,"monkey.png");
        checkforBuildRobot [6] = new Robots(7,7,7,checkPoint3D[6],7,7,"monkey.png");
        checkforBuildRobot [7] = new Robots(8,8,8,checkPoint3D[7],8,8,"monkey.png");


    }
}

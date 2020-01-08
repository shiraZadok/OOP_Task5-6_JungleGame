package element;

import utils.Point3D;

 public class Fruits {

    private Point3D pos;
    private double value;
    private int type;

    public Fruits(Point3D pos, double value, int type){
      this.pos = pos;
      this.value = value;
      this.type = type;
    }


    public String toString(){
        return "value:"+ this.value+"\n" + "type:" + this.type;
    }
}

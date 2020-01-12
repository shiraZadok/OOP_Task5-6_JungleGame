package element;

import utils.Point3D;

 public class Fruits {

    private Point3D pos;
    private double value;
    private int type;


    public Fruits(double value, int type,Point3D pos){
      this.pos = pos;
      this.value = value;
      this.type = type  ;
    }


    public String toString(){
        return "pos:"+ pos.toString()+ "\n" + "value:"+ this.value+ "\n" + "type:" + this.type;
    }
}

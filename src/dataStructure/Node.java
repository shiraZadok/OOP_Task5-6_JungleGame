package dataStructure;

import utils.Point3D;

import java.io.Serializable;

public class Node implements node_data, Serializable {

    private int Key;
    private int Tag;
    private double Weight;
    private String Info;
    private Point3D Location;

    public Node() {
        this.Key = 0;
        this.Tag = 0;
        this.Weight = 0;
        this.Info = null;
        this.Location = null;
    }

    public Node(int Key, int Tag, double Weight, String Info, Point3D Location) {
        this.Key = Key;
        this.Tag = Tag;
        this.Weight = Weight;
        this.Info = Info;
        this.Location = new Point3D(Location);
    }

    public Node(Node n) {
        this.Key = n.Key;
        this.Tag = n.Tag;
        this.Weight = n.Weight;
        this.Info = n.Info;
        this.Location = new Point3D(n.Location);
    }

    @Override
    public int getKey() {
        return this.Key;
    }

    @Override
    public Point3D getLocation() {
        return this.Location;
    }

    @Override
    public void setLocation(Point3D p) {
        this.Location = new Point3D(p);
    }

    @Override
    public double getWeight() {
        return this.Weight;
    }

    @Override
    public void setWeight(double w) {
        this.Weight = w;
    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
        this.Info = s;
    }

    @Override
    public int getTag() {
        return this.Tag;
    }

    @Override
    public void setTag(int t) {
        this.Tag = t;
    }

    public String toString() {
        return ""+this.getKey();
    }
}

package dataStructure;

import java.io.Serializable;

public class Edge implements edge_data,Serializable{

    private int Src;
    private int Dest;
    private int Tag;
    private double Weight;
    private String Info;

    public Edge(){
        this.Src = 0;
        this.Dest = 0;
        this.Tag = 0;
        this.Weight = 0;
        this.Info = null;
    }

    public Edge(int Src, int Dest, int Tag, double Weight, String Info){
        this.Src = Src;
        this.Dest = Dest;
        this.Tag = Tag;
        this.Weight = Weight;
        this.Info = Info;
    }

    public Edge(Edge e){
        this.Src = e.Src;
        this.Dest = e.Dest;
        this.Tag = e.Tag;
        this.Weight = e.Weight;
        this.Info = e.Info;
    }

    @Override
    public int getSrc() {
        return this.Src;
    }

    @Override
    public int getDest() {
        return this.Dest;
    }

    @Override
    public double getWeight() {
        return this.Weight;
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
}

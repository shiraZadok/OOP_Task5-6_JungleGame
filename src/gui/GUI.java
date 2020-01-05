package gui;

import algorithms.*;
import dataStructure.*;
import utils.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class GUI extends Thread {

    public Graph_Algo ga = new Graph_Algo();
    private int modeCount = 0;

    public GUI(){
        StdDraw.g = this;
        modeCount = this.ga.algo.getMC();
        this.start();
    }

    public void GUIgraph(graph d) {
        StdDraw.clear();
        this.ga.init(d);
        StdDraw.g=this;
        double minX = 0;
        double minY = 0;
        double maxX = 0;
        double maxY = 0;
        Iterator it = d.getV().iterator();
        while (it.hasNext()) {
            node_data temp = (node_data) it.next();
            Point3D p = temp.getLocation();
            minX = Math.min(minX, p.x());
            minY = Math.min(minY, p.y());
            maxX = Math.max(maxX, p.x());
            maxY = Math.max(maxY, p.y());
        }
        double ScaleX =(maxX-minX)*0.04;
        double ScaleY=(maxY-minY)*0.04;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(minX - (maxX - minX) * 0.2+1, maxX * 1.1);
        StdDraw.setYscale(minY - (maxY - minY) * 0.2+1, maxY * 1.1);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(0.05);
        Iterator it1 = d.getV().iterator();
        while (it1.hasNext()) {
            node_data temp = (node_data) it1.next();
            Point3D p1 = temp.getLocation();
            StdDraw.filledCircle(p1.x(), p1.y(),ScaleX*0.2);
            StdDraw.text(p1.x(), p1.y() + (((maxX-minX)*0.04)*0.2), "" + temp.getKey());
        }
        StdDraw.setPenRadius(0.005);
        Iterator edge1 = d.getV().iterator();
        while (edge1.hasNext()) {
            node_data temp1 = (node_data) edge1.next();
            if (d.getE(temp1.getKey()) != null) {
                Iterator edge2 = d.getE(temp1.getKey()).iterator();
                while (edge2.hasNext()) {
                    StdDraw.setPenColor(Color.RED);
                    edge_data temp2 = (edge_data) edge2.next();
                    node_data n1 = d.getNode(temp2.getSrc());
                    node_data n2 = d.getNode(temp2.getDest());
                    Point3D p1 = n1.getLocation();
                    Point3D p2 = n2.getLocation();
                    StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
                    double x = 0.2 * p1.x() + 0.8 * p2.x();
                    double y = 0.2 * p1.y() + 0.8 * p2.y();
                    StdDraw.text(x, y + 0.1, "" + temp2.getWeight());
                    StdDraw.setPenColor(Color.YELLOW);
                    double x1 = 0.1 * p1.x() + 0.9 * p2.x();
                    double y1 = 0.1 * p1.y() + 0.9 * p2.y();
                    StdDraw.filledCircle(x1, y1, ScaleX*0.2);
                }
            }
        }
    }

    public void GUIPath(List<node_data> l){
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.GREEN);
        for (int i = 0; i <l.size()-1 ; i++) {
            Point3D src = this.ga.algo.getNode(l.get(i).getKey()).getLocation();
            Point3D dest = this.ga.algo.getNode(l.get(i+1).getKey()).getLocation();
            StdDraw.line(src.x(),src.y(),dest.x(),dest.y());
        }
    }

    public void backRed(graph d) {
        StdDraw.setPenRadius(0.005);
        Iterator edge1 = d.getV().iterator();
        while (edge1.hasNext()) {
            node_data temp1 = (node_data) edge1.next();
            if (d.getE(temp1.getKey()) != null) {
                Iterator edge2 = d.getE(temp1.getKey()).iterator();
                while (edge2.hasNext()) {
                    StdDraw.setPenColor(Color.RED);
                    edge_data temp2 = (edge_data) edge2.next();
                    node_data n1 = d.getNode(temp2.getSrc());
                    node_data n2 = d.getNode(temp2.getDest());
                    Point3D p1 = n1.getLocation();
                    Point3D p2 = n2.getLocation();
                    StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
                    double x = 0.2 * p1.x() + 0.8 * p2.x();
                    double y = 0.2 * p1.y() + 0.8 * p2.y();
                    StdDraw.text(x, y + 0.1, "" + temp2.getWeight());
                }
            }
        }
    }

    public void run(){
        while(true){
            if(modeCount!=this.ga.algo.getMC()){
                GUIgraph(ga.algo);
                modeCount = ga.algo.getMC();
            }
        }
    }
}

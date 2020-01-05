package graphGUI;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.*;
import java.awt.*;
import java.security.Guard;
import java.util.Iterator;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class GUI extends Thread {

    public Graph_Algo ga = new Graph_Algo();
    private int modeCount = 0;

    public GUI(){
        StdDraw.g = this;
        StdDraw.g.ga = this.ga;
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
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(minX - 1, maxX + 1);
        StdDraw.setYscale(minY - 1, maxY + 1);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(0.05);
        int i = 1;
        Iterator it1 = d.getV().iterator();
        while (it1.hasNext()) {
            node_data temp = (node_data) it1.next();
            Point3D p1 = temp.getLocation();
            StdDraw.filledCircle(p1.x(), p1.y(), 0.05);
            StdDraw.text(p1.x(), p1.y() + 0.1, "" + i);
            i++;
        }
        StdDraw.setPenRadius(0.01);
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
                    StdDraw.filledCircle(x1, y1, 0.05);
                }
            }
        }
    }

    public void GUIPath(List<node_data> l){
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.GREEN);
        for (int i = 0; i <l.size()-1 ; i++) {
            Point3D src = this.ga.algo.getNode(l.get(i).getKey()).getLocation();
            Point3D dest = this.ga.algo.getNode(l.get(i+1).getKey()).getLocation();
            StdDraw.line(src.x(),src.y(),dest.x(),dest.y());
        }
    }

    public void backRed(graph d) {
        StdDraw.setPenRadius(0.01);
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
                GUIgraph(this.ga.algo);
                modeCount = this.ga.algo.getMC();
            }
        }
    }

    public static void main(String[] args) {
        Point3D x = new Point3D(1,4,0);
        Point3D y = new Point3D(2,5,0);
        Point3D q = new Point3D(4,3,0);
        node_data a = new Node(x);
        node_data b =new Node(y);
        node_data c = new Node(q);
        DGraph d =new DGraph();
        d.addNode(a);
        d.addNode(b);
        d.addNode(c);
        d.connect(a.getKey(),b.getKey(),4);
        d.connect(b.getKey(),a.getKey(),4);
        d.connect(a.getKey(),c.getKey(),50);
        Graph_Algo p = new Graph_Algo();
        p.init(d);
        GUI k = new GUI();
        k.GUIgraph(d);
        d.connect(c.getKey(),a.getKey(),30);

//        Point3D x = new Point3D(14,4,0);
//        Point3D x2 = new Point3D(-75,14,0);
//        Point3D x3 = new Point3D(80,5,0);
//        Point3D x4 = new Point3D(1,4,0);
//        Point3D x5 = new Point3D(-5,1,0);
//        Point3D x6 = new Point3D(8,3,0);
//        Point3D x7 = new Point3D(4,1,0);
//        Point3D x8 = new Point3D(75,14,0);
//        node_data a1 = new Node(x);
//        node_data a2 = new Node(x2);
//        node_data a3 = new Node(x3);
//        node_data a4 = new Node(x4);
//        node_data a5 = new Node(x5);
//        node_data a6 = new Node(x6);
//        node_data a7 = new Node(x7);
//        node_data a8 = new Node(x8);
//        DGraph d = new DGraph();
//        d.addNode(a1);
//        d.addNode(a2);
//        d.addNode(a3);
//        d.addNode(a4);
//        d.addNode(a5);
//        d.addNode(a6);
//        d.addNode(a7);
//        d.addNode(a8);
//        d.connect(a1.getKey(),a2.getKey(),5);
//        d.connect(a1.getKey(),a5.getKey(),2);
//        d.connect(a1.getKey(),a3.getKey(),6);
//        d.connect(a1.getKey(),a6.getKey(),5);
//        d.connect(a3.getKey(),a4.getKey(),7);
//        d.connect(a2.getKey(),a8.getKey(),8);
//        d.connect(a2.getKey(),a7.getKey(),3);
//        d.connect(a5.getKey(),a1.getKey(),5);
//        d.connect(a5.getKey(),a6.getKey(),2);
//        d.connect(a6.getKey(),a1.getKey(),3);
//        d.connect(a6.getKey(),a5.getKey(),3);
//        d.connect(a6.getKey(),a7.getKey(),3);
//        d.connect(a7.getKey(),a6.getKey(),3);

    }
}

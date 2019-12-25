package dataStructure;

import utils.Point3D;
import java.awt.*;
import java.util.Iterator;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI {

//    private DGraph d = null;
//
//    public GUI(){
//        GUIgraph(this.d);
//    }

    public void GUIgraph(DGraph d) {
        double minX=0;
        double minY=0;
        double maxX=0;
        double maxY=0;
        Iterator it = d.getV().iterator();
        while (it.hasNext()) {
            node_data temp = (node_data)it.next();
            Point3D p =temp.getLocation();
            minX=Math.min(minX,p.x());
            minY=Math.min(minY,p.y());
            maxX=Math.max(maxX,p.x());
            maxY=Math.max(maxY,p.y());
        }
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(minX-1,maxX+1);
        StdDraw.setYscale(minY-1,maxY+1);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(0.05);
        int i=1;
        Iterator it1 = d.getV().iterator();
        while (it1.hasNext()) {
            node_data temp = (node_data)it1.next();
            Point3D p1 =temp.getLocation();
            StdDraw.filledCircle(p1.x(),p1.y(),0.05);
            StdDraw.text(p1.x(),p1.y()+0.1,""+i);
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
                    double x = 0.2*p1.x()+0.8*p2.x();
                    double y = 0.2*p1.y()+0.8*p2.y();
                    StdDraw.text(x,y+0.1,""+temp2.getWeight());

                    StdDraw.setPenColor(Color.YELLOW);
                    double x1 = 0.1*p1.x()+0.9*p2.x();
                    double y1 = 0.1*p1.y()+0.9*p2.y();
                    StdDraw.filledCircle(x1,y1,0.05);
                }
            }
        }

//        MenuBar menuBar = new MenuBar();
//        Menu menu = new Menu("Menu");
//        menuBar.add(menu);
//        this.setMenuBar(menuBar);
//
//        MenuItem item1 = new MenuItem("Item 1");
//        item1.addActionListener(this);
//
//        MenuItem item2 = new MenuItem("Item 2");
//        item2.addActionListener(this);
//
//        menu.add(item1);
//        menu.add(item2);
//
//        this.addMouseListener(this);
}







    public static void main(String[] args) {
//        GUI G = new GUI();
//        Point3D x = new Point3D(1,4,0);
//        Point3D y = new Point3D(3,20,0);
//        Point3D q = new Point3D(5,30,0);
//        Point3D z = new Point3D(10,7,0);
//        Point3D s = new Point3D(15,10,0);
//        Point3D t = new Point3D(7,9,0);
//        node_data a = new Node(1,2,3, "asf", x);
//        node_data b =new Node(2,4,6,"gik",y);
//        node_data c = new Node(3,50,50,"sf",q);
//        node_data d = new Node(4,50,50,"sf",z);
//        node_data e = new Node(5,50,50,"sf",s);
//        node_data f = new Node(6,50,50,"sf",t);
//        DGraph g =new DGraph();
//        g.addNode(a);
//        g.addNode(b);
//        g.addNode(c);
//        g.addNode(d);
//        g.addNode(e);
//        g.addNode(f);
//        g.connect(a.getKey(),b.getKey(),5);
//        g.connect(b.getKey(),f.getKey(),3);
//        g.connect(a.getKey(),c.getKey(),4);
//        g.connect(c.getKey(),d.getKey(),5);
//        g.connect(c.getKey(),e.getKey(),1);
//        g.connect(e.getKey(),f.getKey(),1);
//        G.GUIgraph(g);

        GUI g = new GUI();
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
        d.connect(a.getKey(),c.getKey(),50);
        d.connect(b.getKey(),c.getKey(),4);
        g.GUIgraph(d);
    }
}

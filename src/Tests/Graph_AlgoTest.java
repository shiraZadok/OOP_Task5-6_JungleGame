package Tests;

import algorithms.*;
import dataStructure.DGraph;
import dataStructure.Node;
import org.junit.Test;
import utils.Point3D;
import static org.junit.Assert.assertEquals;





public class Graph_AlgoTest {


//    @Before
//    public void BeforeEach(){
//
//    }

    @Test
    public void initGraph() {
    }

    @Test
    public void initFile() {
    }


    @Test
    public void save() {
    }

    @Test
    public void isConnected() {
        ////////////////////////TRY1///////////////////////////
        Graph_Algo G = new Graph_Algo();
        DGraph g1 = new DGraph();
        Point3D p1 [] = new Point3D[2];
        Node n1 [] = new Node[2];

        p1[0] = new Point3D(0,0,0);
        p1[1] = new Point3D(5,0,0);

        n1[0] = new Node(p1[0]);
        n1[1] = new Node(p1[1]);

        g1.addNode(n1[0]);
        g1.addNode(n1[1]);

        g1.connect(n1[0].getKey(), n1[1].getKey(), 4);

        G.init(g1);

        assertEquals(false, G.isConnected());
        //System.out.println("success1");


        //////////////////////////TRY2/////////////////////
        Graph_Algo G2 = new Graph_Algo();
        Graph_Algo G3 = new Graph_Algo();
        DGraph g2 = new DGraph();
        DGraph g3 = new DGraph();
        Point3D p2 [] = new Point3D[5];
        Node n2 [] = new Node[5];

        Graph_Algo Ag = new Graph_Algo();

        p2[0] = new Point3D(1, 6, 0);
        p2[1] = new Point3D(0, 2, 3);
        p2[2] = new Point3D(1, 4, 0);
        p2[3] = new Point3D(5, 2, 0);
        p2[4] = new Point3D(6,5, 0);

        n2[0] = new Node (p2[0]);
        n2[1] = new Node(p2[1]);
        n2[2] = new Node(p2[2]);
        n2[3] = new Node(p2[3]);
        n2[4] = new Node(p2[4]);

        g2.addNode(n2[0]);
        g2.addNode(n2[1]);
        g2.addNode(n2[2]);
        g2.addNode(n2[3]);
        g2.addNode(n2[4]);

        g3.addNode(n2[0]);
        g3.addNode(n2[1]);
        g3.addNode(n2[2]);
        g3.addNode(n2[3]);
        g3.addNode(n2[4]);

        g2.connect(n2[1].getKey(), n2[2].getKey(), 11);
        g2.connect(n2[2].getKey(), n2[3].getKey(), 11);
        g2.connect(n2[3].getKey(), n2[2].getKey(), 11);
        g2.connect(n2[3].getKey(), n2[4].getKey(), 11);
        g2.connect(n2[4].getKey(), n2[3].getKey(), 11);


        g3.connect(n2[0].getKey(), n2[1].getKey(), 9);
        g3.connect(n2[1].getKey(), n2[0].getKey(), 9);
        g3.connect(n2[1].getKey(), n2[4].getKey(), 10);
        g3.connect(n2[4].getKey(), n2[3].getKey(), 11);
        g3.connect(n2[3].getKey(), n2[2].getKey(), 11);
        g3.connect(n2[2].getKey(), n2[1].getKey(), 11);

        G2.init(g2);
        G3.init(g3);

        assertEquals(false, G2.isConnected());
        //System.out.println("success2");

        assertEquals(true, G3.isConnected());
        //System.out.println("success3");




    }

    @Test
    public void shortestPathDist() {
    }


    @Test
    public void shortestPath() {
    }

    @Test
    public void TSP() {
    }

    @Test
    public void copy() {
    }
}
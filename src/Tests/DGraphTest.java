package Tests;

import org.junit.Before;
import org.junit.Test;
import utils.Point3D;
import dataStructure.*;

import static org.junit.Assert.assertEquals;


public class DGraphTest {
    static DGraph g = new DGraph();
    static Point3D p [] = new Point3D[8];
    static Node n [] = new Node[8];

    /**
     * This method build an graph before any check of methods.
     */
    @Before
    public void BeforeEach() {
        p[0] = new Point3D(0,0,0);
        p[1] = new Point3D(1,4,8);
        p[2] = new Point3D(5,4,8);
        p[3] = new Point3D(5,0,0);
        p[4] = new Point3D(0,4,0);
        p[5] = new Point3D(0,0,3);
        p[6] = new Point3D(3,7,5);
        p[7] = new Point3D(9,1,4);

        n[0] = new Node(p[0]);
        n[1] = new Node(p[1]);
        n[2] = new Node(p[2]);
        n[3] = new Node(p[3]);
        n[4] = new Node(p[4]);
        n[5] = new Node(p[5]);
        n[6] = new Node(p[6]);
        n[7] = new Node(p[7]);

//        for (int i=0; i<n.length; i++){
//            System.out.println(n[i].getKey());
//        }

        // put all the node in the graph-g
        for(int i=0; i<n.length; i++){
            g.addNode(n[i]);
        }
        //connection between all the nodes with the node (0,0,0)
        g.connect(1,2,3);
        g.connect(1,3,3);
        g.connect(1,4,3);
        g.connect(1,5,3);
        g.connect(1,6,3);
        g.connect(1,7,3);
        g.connect(1,8,3);

        //connection between some node to the other
        g.connect(2,8,5);
        g.connect(3,6,10);
        g.connect(4,3,1);
        g.connect(6,7,2);
        g.connect(5,7,4);
        g.connect(3,8,8);
        g.connect(7,8,5);
        g.connect(8,1,11);
    }

    @Test
    public void getNode() {

        for (int i=0; i<n.length; i++){
            assertEquals(n[i].getLocation().toString(),g.getNode(i+1).getLocation().toString());
        }
    }

    @Test
    public void getEdge() {
        edge_data[] ans = new edge_data[15];
        ans[0] = new Edge(1,2,3);
        ans[1] = new Edge(1,3,3);
        ans[2] = new Edge(1,4,3);
        ans[3] = new Edge(1,5,3);
        ans[4] = new Edge(1,6,3);
        ans[5] = new Edge(1,7,3);
        ans[6] = new Edge(1,8,3);
        ans[7] = new Edge(2,8,5);
        ans[8] = new Edge(3,6,10);
        ans[9] = new Edge(4,3,1);
        ans[10] = new Edge(6,7,2);
        ans[11] = new Edge(5,7,4);
        ans[12] = new Edge(3,8,8);
        ans[13] = new Edge(7,8,5);
        ans[14] = new Edge(8,1,11);

        for (int i=0; i<ans.length; i++){
            assertEquals(ans[i].toString(),(g.getEdge(ans[i].getSrc(), ans[i].getDest())).toString());
        }
    }

    @Test
    public void addNode() {
        Point3D addP [] = new Point3D[3];
        Node addN [] = new Node[3];

        addP [0] = new Point3D(0,2,3);
        addP [1] = new Point3D(4,7,2);
        addP [2] = new Point3D(8,1,1);

        addN[0] = new Node(addP[0]);
        addN[1] = new Node(addP[1]);
        addN[2] = new Node(addP[2]);

        for (int i=0; i<addN.length; i++){
            int ansNode = i+9;
            int ansMode = i+24;
            g.addNode(addN[i]);
            assertEquals(ansNode, g.nodeSize());
            assertEquals(ansMode, g.getMC());
            assertEquals(addN[i], g.getNode(i+9));
        }
    }

    @Test
    public void connect() {
        //check if all the edge that connect to the node (0,0,0) with the key 1 exist.
        for (int i=2; i<8; i++) {
            assertEquals(true, g.getE(1).contains(g.getEdge(1, i)));
        }
    }

    @Test
    public void getV() {

    }

    @Test
    public void getE() {
    }

    @Test
    public void removeNode() {
        node_data delete = g.removeNode(n[0].getKey());

        //check if the node that returns from the function it correct
        assertEquals(n[0].toString(), delete.toString());

        //check if the node exist
        for (int i=2; i<8; i++) {
            assertEquals(null, g.getNode(n[0].getKey()));
        }

        //check if the edge that was connect to the node exist
        for (int i=2; i<8; i++) {
            assertEquals(null, g.getEdge(n[0].getKey(), i));
        }
    }

    @Test
    public void removeEdge() {
        edge_data delete1 = g.removeEdge(1,2);
        edge_data delete2 = g.removeEdge(8,1);

        //check if the edge didn't exist
        assertEquals(null,g.getEdge(1,2));
        assertEquals(null,g.getEdge(8,1) );

    }

    @Test
    public void nodeSize() {
        int ans = 8;
        assertEquals(ans,g.nodeSize());
    }

    @Test
    public void edgeSize() {
        int ans = 15;
        assertEquals(ans,g.edgeSize());
    }

    @Test
    public void getMC() {
        int ans = 23;
        assertEquals(ans,g.getMC());
    }
}
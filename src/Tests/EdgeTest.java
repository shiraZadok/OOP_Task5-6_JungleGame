package Tests;

import dataStructure.Edge;
import dataStructure.edge_data;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class EdgeTest {

    static edge_data[] checkforBuildEdge = new edge_data[8];

    @Before
    public void BeforeEach() {
        checkforBuildEdge [0] = new Edge(1,2,3,4,"test");
        checkforBuildEdge [1] = new Edge(5,6,7,8,"test");
        checkforBuildEdge [2] = new Edge(9,10,11,12,"test");
        checkforBuildEdge [3] = new Edge(13,14,15,16,"test");
        checkforBuildEdge [4] = new Edge(17,18,19,20,"test");
        checkforBuildEdge [5] = new Edge(21,22,23,24,"test");
        checkforBuildEdge [6] = new Edge(25,26,27,28,"test");
        checkforBuildEdge [7] = new Edge(29,30,31,32,"test");

    }

    @Test
    public void getSrc() {
        int [] ans = {1,5,9,13,17,21,25,29};
        for (int i = 0; i < checkforBuildEdge.length; i++) {
            assertEquals(ans[i], checkforBuildEdge[i].getSrc());
        }
    }

    @Test
    public void getDest() {
        int [] ans = {2,6,10,14,18,22,26,30};
        for (int i = 0; i < checkforBuildEdge.length; i++) {
            assertEquals(ans[i], checkforBuildEdge[i].getDest());
        }
    }

    @Test
    public void getWeight() {
        double [] ans = {4,8,12,16,20,24,28,32};
        for (int i = 0; i < checkforBuildEdge.length; i++) {
            assertEquals(ans[i], checkforBuildEdge[i].getWeight(), 0.0001);
        }
    }

    @Test
    public void getInfo() {
        for (int i = 0; i <checkforBuildEdge.length ; i++) {
            assertEquals(checkforBuildEdge[i].getInfo(), "test");
        }
    }

    @Test
    public void setInfo() {
        for (int i = 0; i <checkforBuildEdge.length; i++) {
            checkforBuildEdge[i].setInfo("after");
        }
        for (int i = 0; i <checkforBuildEdge.length; i++) {
            assertEquals("after", checkforBuildEdge[i].getInfo());
        }
    }

    @Test
    public void getTag() {
        int [] ans = {3,7,11,15,19,23,27,31};
        for (int i = 0; i < checkforBuildEdge.length; i++) {
            assertEquals(ans[i], checkforBuildEdge[i].getTag());
        }
    }

    @Test
    public void setTag() {
        for (int i = 0; i <checkforBuildEdge.length; i++) {
            checkforBuildEdge[i].setTag(1);
        }
        for (int i = 0; i <checkforBuildEdge.length; i++) {
            assertEquals(1, checkforBuildEdge[i].getTag(), 0.0001);
        }
    }
}

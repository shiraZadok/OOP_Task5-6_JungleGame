package Tests;

import dataStructure.Node;
import dataStructure.node_data;
import org.junit.Test;
import org.junit.Before;
import utils.Point3D;
import static org.junit.Assert.*;

public class NodeTest {

    static node_data[] checkforBuildNode = new node_data[8];
    static Point3D[] checkPoint3D = new Point3D[8];

    @Before
    public void BeforeEach() {

        checkPoint3D[0] = new Point3D(1,2,0);
        checkPoint3D[1] = new Point3D(3,4,0);
        checkPoint3D[2] = new Point3D(5,6,0);
        checkPoint3D[3] = new Point3D(7,8,0);
        checkPoint3D[4] = new Point3D(9,10,0);
        checkPoint3D[5] = new Point3D(11,12,0);
        checkPoint3D[6] = new Point3D(13,14,0);
        checkPoint3D[7] = new Point3D(15,16,0);

        checkforBuildNode[0] = new Node(1,2,3, "asf", checkPoint3D[0]);
        checkforBuildNode[1] = new Node(4,5,6, "asf", checkPoint3D[1]);
        checkforBuildNode[2] = new Node(7,8,9, "asf", checkPoint3D[2]);
        checkforBuildNode[3] = new Node(11,12,13, "asf", checkPoint3D[3]);
        checkforBuildNode[4] = new Node(14,15,16, "asf", checkPoint3D[4]);
        checkforBuildNode[5] = new Node(17,18,19, "asf", checkPoint3D[5]);
        checkforBuildNode[6] = new Node(20,21,22, "asf", checkPoint3D[6]);
        checkforBuildNode[7] = new Node(23,24,25, "asf", checkPoint3D[7]);
    }

    @Test
    public void getKey() {
        int ans[] = {1, 4, 7, 11, 14, 17, 20, 23};
        for (int i = 0; i < checkforBuildNode.length; i++) {
            assertEquals(ans[i], checkforBuildNode[i].getKey());
        }
    }

    @Test
    public void getLocation() {
        Point3D ans[] = {checkPoint3D[0],
                checkPoint3D[1],
                checkPoint3D[2],
                checkPoint3D[3],
                checkPoint3D[4],
                checkPoint3D[5],
                checkPoint3D[6],
                checkPoint3D[7]
        };
        for (int i = 0; i < checkforBuildNode.length; i++) {
            assertEquals(ans[i], checkforBuildNode[i].getLocation());
        }
    }


    @Test
    public void setLocation() {
        Point3D ans[] = new Point3D[8];
        for (int i = 0; i <checkPoint3D.length ; i++) {
            checkPoint3D[i].add(1,1,0);
            checkforBuildNode[i].setLocation(checkPoint3D[i]);
        }
        for (int i = 0; i <checkforBuildNode.length ; i++) {
            assertEquals(checkPoint3D[i],checkforBuildNode[i].getLocation());
        }
    }

    @Test
    public void getWeight() {
        double ans[] = {3,6,9,13,16,19,22,25};
        for (int i = 0; i < checkforBuildNode.length; i++) {
            assertEquals(ans[i], checkforBuildNode[i].getWeight(), 0.0001);
        }
    }

    @Test
    public void setWeight() {
        for (int i = 0; i <checkforBuildNode.length; i++) {
            checkforBuildNode[i].setWeight(1);
        }
        for (int i = 0; i <checkforBuildNode.length; i++) {
            assertEquals(11, checkforBuildNode[i].getWeight(), 0.0001);
        }
    }

    @Test
    public void getInfo() {
        for (int i = 0; i <checkforBuildNode.length ; i++) {
            assertEquals(checkforBuildNode[i].getInfo(), "asf");
        }
    }

    @Test
    public void setInfo() {
        for (int i = 0; i <checkforBuildNode.length; i++) {
            checkforBuildNode[i].setInfo("after");
        }
        for (int i = 0; i <checkforBuildNode.length; i++) {
            assertEquals("after", checkforBuildNode[i].getInfo());
        }
    }

    @Test
    public void getTag() {
        int ans [] = {2,5,8,12,15,18,21,24};
        for (int i = 0; i <checkforBuildNode.length; i++) {
            assertEquals(ans[i], checkforBuildNode[i].getTag(), 0.0001);
        }
    }

    @Test
    public void setTag() {
        for (int i = 0; i <checkforBuildNode.length; i++) {
            checkforBuildNode[i].setTag(1);
        }
        for (int i = 0; i <checkforBuildNode.length; i++) {
            assertEquals(1, checkforBuildNode[i].getTag(), 0.0001);
        }
    }
}
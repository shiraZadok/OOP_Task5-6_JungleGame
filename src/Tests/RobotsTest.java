package Tests;

import element.Robots;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;
import static org.junit.Assert.*;

public class RobotsTest {

    static Robots[] checkforBuildRobot = new Robots[8];
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

        checkforBuildRobot [0] = new Robots(1,1,1,checkPoint3D[0],1,1,"monkey.png");
        checkforBuildRobot [1] = new Robots(2,2,2,checkPoint3D[1],2,2,"monkey.png");
        checkforBuildRobot [2] = new Robots(3,3,3,checkPoint3D[2],3,3,"monkey.png");
        checkforBuildRobot [3] = new Robots(4,4,4,checkPoint3D[3],4,4,"monkey.png");
        checkforBuildRobot [4] = new Robots(5,5,5,checkPoint3D[4],5,5,"monkey.png");
        checkforBuildRobot [5] = new Robots(6,6,6,checkPoint3D[5],6,6,"monkey.png");
        checkforBuildRobot [6] = new Robots(7,7,7,checkPoint3D[6],7,7,"monkey.png");
        checkforBuildRobot [7] = new Robots(8,8,8,checkPoint3D[7],8,8,"monkey.png");
    }

    @Test
    public void init() {
    }

    @Test
    public void get_pic() {
        for (int i = 0; i < checkforBuildRobot.length; i++) {
            assertEquals("monkey.png", checkforBuildRobot[i].get_pic());
        }
    }

    @Test
    public void set_pic() {
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            checkforBuildRobot[i].set_pic("monkey.png");
        }
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            assertEquals("monkey.png", checkforBuildRobot[i].get_pic());
        }
    }

    @Test
    public void getId() {
        for (int i = 0; i < checkforBuildRobot.length; i++) {
            assertEquals(i+1, checkforBuildRobot[i].getId());
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
        for (int i = 0; i < checkforBuildRobot.length; i++) {
            assertEquals(ans[i], checkforBuildRobot[i].getLocation());
        }
    }

    @Test
    public void getSrc() {
        for (int i = 0; i < checkforBuildRobot.length; i++) {
            assertEquals(i+1, checkforBuildRobot[i].getSrc());
        }
    }

    @Test
    public void setSrc() {
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            checkforBuildRobot[i].setSrc(10);
        }
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            assertEquals(10, checkforBuildRobot[i].getSrc());
        }
    }

    @Test
    public void getDest() {
        for (int i = 0; i < checkforBuildRobot.length; i++) {
            assertEquals(i+1, checkforBuildRobot[i].getDest());
        }
    }

    @Test
    public void setDest() {
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            checkforBuildRobot[i].setDest(10);
        }
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            assertEquals(10, checkforBuildRobot[i].getDest());
        }
    }

    @Test
    public void getValue() {
        for (int i = 0; i < checkforBuildRobot.length; i++) {
            assertEquals(i+1, checkforBuildRobot[i].getValue(),0.0001);
        }
    }

    @Test
    public void setValue() {
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            checkforBuildRobot[i].setValue(10);
        }
        for (int i = 0; i <checkforBuildRobot.length; i++) {
            assertEquals(10, checkforBuildRobot[i].getValue(),0.0001);
        }
    }
}
package Tests;

import element.Robots;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;
import static org.junit.Assert.*;

public class RobotsTest {

    static Robots[] checkforBuildRobot = new Robots[8];
    static Point3D[] checkPoint3D = new Point3D[8];
    static String[] checkJsonString = new String[8];

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

        checkJsonString[0] = new String("{\"Robot\":{\"src\":1,\"pos\":\"1.0,2.0,0.0\",\"id\":1,\"dest\":1,\"value\":1.0,\"speed\":1}}");
        checkJsonString[1] = new String("{\"Robot\":{\"src\":2,\"pos\":\"3.0,4.0,0.0\",\"id\":2,\"dest\":2,\"value\":2.0,\"speed\":2}}");
        checkJsonString[2] = new String("{\"Robot\":{\"src\":3,\"pos\":\"5.0,6.0,0.0\",\"id\":3,\"dest\":3,\"value\":3.0,\"speed\":3}}");
        checkJsonString[3] = new String("{\"Robot\":{\"src\":4,\"pos\":\"7.0,8.0,0.0\",\"id\":4,\"dest\":4,\"value\":4.0,\"speed\":4}}");
        checkJsonString[4] = new String("{\"Robot\":{\"src\":5,\"pos\":\"9.0,10.0,0.0\",\"id\":5,\"dest\":5,\"value\":5.0,\"speed\":5}}");
        checkJsonString[5] = new String("{\"Robot\":{\"src\":6,\"pos\":\"11.0,12.0,0.0\",\"id\":6,\"dest\":6,\"value\":6.0,\"speed\":6}}");
        checkJsonString[6] = new String("{\"Robot\":{\"src\":7,\"pos\":\"13.0,14.0,0.0\",\"id\":7,\"dest\":7,\"value\":7.0,\"speed\":7}}");
        checkJsonString[7] = new String("{\"Robot\":{\"src\":8,\"pos\":\"15.0,16.0,0.0\",\"id\":8,\"dest\":8,\"value\":8.0,\"speed\":8}}");

    }

    @Test
    public void init() {
        Robots r = new Robots();
        for (int i = 0; i<checkJsonString.length; i++){
            r = (Robots)r.init(checkJsonString[i]);
            assertEquals(i+1, r.getSrc());
            assertEquals(i+1, r.getDest());
            assertEquals(i+1, r.getValue(), 0.0001);
            assertEquals(i+1, r.getId());
            assertEquals((i*2)+1, r.getLocation().x(), 0.0001);
            assertEquals((i*2)+2, r.getLocation().y(), 0.0001);
            assertEquals(0.0, r.getLocation().z(), 0.0001);
        }
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
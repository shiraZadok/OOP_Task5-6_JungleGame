package Tests;

import element.Fruits;
import org.junit.Test;
import org.junit.Before;
import utils.Point3D;
import static org.junit.Assert.*;

public class FruitsTest {

    static Fruits[] checkforBuildFruit = new Fruits[8];
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

        checkforBuildFruit [0] = new Fruits(new Point3D(1,2,0),1,-1,"banana.png");
        checkforBuildFruit [1] = new Fruits(new Point3D(3,4,0),2,-1,"banana.png");
        checkforBuildFruit [2] = new Fruits(new Point3D(5,6,0),3,-1,"banana.png");
        checkforBuildFruit [3] = new Fruits(new Point3D(7,8,0),4,-1,"banana.png");
        checkforBuildFruit [4] = new Fruits(new Point3D(9,10,0),5,-1,"banana.png");
        checkforBuildFruit [5] = new Fruits(new Point3D(11,12,0),6,-1,"banana.png");
        checkforBuildFruit [6] = new Fruits(new Point3D(13,14,0),7,-1,"banana.png");
        checkforBuildFruit [7] = new Fruits(new Point3D(15,16,0),8,-1,"banana.png");
    }

    @Test
    public void TesttoString() {

    }

    @Test
    public void init() {

    }

    @Test
    public void get_pic() {
        for (int i = 0; i < checkforBuildFruit.length; i++) {
            assertEquals("banana.png", checkforBuildFruit[i].get_pic());
        }
    }

    @Test
    public void set_pic() {
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            checkforBuildFruit[i].set_pic("apple.png");
        }
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            assertEquals("apple.png", checkforBuildFruit[i].get_pic());
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
        for (int i = 0; i < checkforBuildFruit.length; i++) {
            assertEquals(ans[i], checkforBuildFruit[i].getLocation());
        }
    }

    @Test
    public void setLocation() {
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            checkforBuildFruit[i].setLocation(new Point3D(0,0,0));
        }
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            assertEquals(new Point3D(0,0,0), checkforBuildFruit[i].getLocation());
        }
    }

    @Test
    public void getValue() {
        for (double i = 1; i < checkforBuildFruit.length; i++) {
            assertEquals(i, checkforBuildFruit[(int)i-1].getValue(), 0.0001);
        }
    }

    @Test
    public void setValue() {
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            checkforBuildFruit[i].setValue(10);
        }
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            assertEquals(10, checkforBuildFruit[i].getValue(),0.0001);
        }
    }

    @Test
    public void getType() {
        for (int i = 0; i < checkforBuildFruit.length; i++) {
            assertEquals(-1, checkforBuildFruit[i].getType());
        }
    }

    @Test
    public void setType() {
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            checkforBuildFruit[i].setType(-1);
        }
        for (int i = 0; i <checkforBuildFruit.length; i++) {
            assertEquals(-1, checkforBuildFruit[i].getType());
        }
    }
}
package Tests;

import Server.Game_Server;
import Server.game_service;
import element.Fruits;
import element.FruitsList;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class FruitsListTest {

    static String[] checkJsonString = new String[8];
    static List<String> checkforBuildFruitList = new LinkedList<>();
    static game_service server;

    @Before
    public void BeforeEach() {

        checkJsonString[0] = "{\"Fruit\":{\"value\":1.0,\"type\":-1,\"pos\":\"1.0,2.0,0.0\"}}";
        checkJsonString[1] = "{\"Fruit\":{\"value\":2.0,\"type\":-1,\"pos\":\"3.0,4.0,0.0\"}}";
        checkJsonString[2] = "{\"Fruit\":{\"value\":3.0,\"type\":-1,\"pos\":\"5.0,6.0,0.0\"}}";
        checkJsonString[3] = "{\"Fruit\":{\"value\":4.0,\"type\":-1,\"pos\":\"7.0,8.0,0.0\"}}";
        checkJsonString[4] = "{\"Fruit\":{\"value\":5.0,\"type\":-1,\"pos\":\"9.0,10.0,0.0\"}}";
        checkJsonString[5] = "{\"Fruit\":{\"value\":6.0,\"type\":-1,\"pos\":\"11.0,12.0,0.0\"}}";
        checkJsonString[6] = "{\"Fruit\":{\"value\":7.0,\"type\":-1,\"pos\":\"13.0,14.0,0.0\"}}";
        checkJsonString[7] = "{\"Fruit\":{\"value\":8.0,\"type\":-1,\"pos\":\"15.0,16.0,0.0\"}}";

        checkforBuildFruitList.add(checkJsonString[0]);
        checkforBuildFruitList.add(checkJsonString[1]);
        checkforBuildFruitList.add(checkJsonString[2]);
        checkforBuildFruitList.add(checkJsonString[3]);
        checkforBuildFruitList.add(checkJsonString[4]);
        checkforBuildFruitList.add(checkJsonString[5]);
        checkforBuildFruitList.add(checkJsonString[6]);
        checkforBuildFruitList.add(checkJsonString[7]);

    }

    @Test
    public void listF() {
        FruitsList f = new FruitsList();
        f.listF(checkforBuildFruitList);
        for(int i =0; i<f.fruits.size(); i++){
            Fruits check = f.fruits.get(i);
            assertEquals((i*2)+1, check.getLocation().x(), 0.0001);
            assertEquals((i*2)+2, check.getLocation().y(), 0.0001);
            assertEquals(0.0, check.getLocation().z(), 0.0001);
            assertEquals(i+1, check.getValue(), 0.0001);
            assertEquals(-1, check.getType());
            assertEquals("banana.png", check.get_pic());
        }
    }

    @Test
    public void getAmountFruits() {
        this.server = Game_Server.getServer(0);
        FruitsList f = new FruitsList(server);
        int amount = f.getAmountFruits();
        assertEquals(1, amount);

    }
}
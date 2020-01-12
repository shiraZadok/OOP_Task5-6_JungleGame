package element;

import Server.Game_Server;
import Server.game_service;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

import java.util.LinkedList;
import java.util.List;

public class FruitsList {

    public List<Fruits> fruits ;
    private int amountFruits;
    private game_service numGame;

    public FruitsList() {
        this.fruits = new LinkedList<>();
        this.amountFruits = fruits.size();
        this.numGame = null;
    }

    public FruitsList(game_service numGame) {
        this.numGame = numGame;
        this.amountFruits = getAmountFruits();
        this.fruits = new LinkedList<>();
        listF(this.numGame.getFruits());
    }

    public void listF(List<String> temp) {
        for (String f : temp) {
            try {
                Fruits fr = new Fruits();
                JSONObject line = new JSONObject(f);
                JSONObject fruit = line.getJSONObject("Fruit");
                fr.init(fruit.toString());
                System.out.println(fr.get_pic());
                this.fruits.add(fr);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.amountFruits = this.fruits.size();
    }

    public int getAmountFruits() {
        int amount = 0;
        try {
            String gameString = this.numGame.toString();
            JSONObject gameJson = new JSONObject(gameString);
            JSONObject gameServer = gameJson.getJSONObject("GameServer");
            amount = gameServer.getInt("fruits");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return amount;
    }
}

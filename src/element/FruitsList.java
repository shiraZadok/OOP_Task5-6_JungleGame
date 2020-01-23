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
    private game_service numGame;

    /**
     * a default constructor.
     */
    public FruitsList() {
        this.fruits = new LinkedList<>();
        this.numGame = null;
    }


    /**
     * constructor of new FruitsList.
     * @param server is the type of the game.
     */
    public FruitsList(game_service server) {
        this.numGame = server;
        this.fruits = new LinkedList<>();
        listF(this.numGame.getFruits());
    }

    /**
     * This function creates a list that contains all the fruits in the game.
     * @param json is a file of the fruits.
     * @return the list the function build.
     */
    public void listF(List<String> json) {
        for (String f : json) {
            Fruits fr = new Fruits();
            fr = (Fruits) fr.init(f);
            this.fruits.add(fr);
        }
    }

    /**
     * This function calculates how much fruit there is in the game.
     * @return the amount of fruits.
     */
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

package element;

import Server.game_service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class RobotsList {

    public List<Robots> robots ;
    private int amountRobots;
    private game_service numGame;

    /**
     * constructor of new RobotsList.
     * @param numGame is the type of the game.
     */
    public RobotsList(game_service numGame) {
        this.numGame = numGame;
        this.amountRobots = getAmountRobots();
        this.robots = new LinkedList<>();
        this.robots = listR(this.numGame.getRobots());
    }

    /**
     * This function creates a list that contains all the robots in the game.
     * @param temp is a file of the robots.
     * @return the list the function build.
     */
    public List<Robots> listR(List<String> temp) {
        List<Robots> tempR = new LinkedList<>();
        for (String r : temp) {
            Robots ro = new Robots();
            ro = (Robots) ro.init(r);
            tempR.add(ro);
        }
        this.robots = tempR;
        return this.robots;
    }

    /**
     * This function calculates how much robot there is in the game.
     * @return the amount of robots.
     */
    public int getAmountRobots() {
        int amount = 0;
        try {
            String gameString = this.numGame.toString();
            JSONObject gameJson = new JSONObject(gameString);
            JSONObject gameServer = gameJson.getJSONObject("GameServer");
            amount = gameServer.getInt("robots");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return amount;
    }
}

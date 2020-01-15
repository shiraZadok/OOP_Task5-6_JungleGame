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

    public RobotsList(game_service numGame) {
        this.numGame = numGame;
        this.amountRobots = getAmountRobots();
        this.robots = new LinkedList<>();
        this.robots = listR(this.numGame.getRobots());
        //System.out.println("this.numGame.getRobots" + this.numGame.getRobots()); /////////////////
    }

    public List<Robots> listR(List<String> temp) {
        List<Robots> tempR = new LinkedList<>();
        for (String r : temp) {
            //System.out.println("the line of the robot" + r); ////////////////
            Robots ro = new Robots();
            ro = (Robots) ro.init(r);
            tempR.add(ro);
        }
        this.robots = tempR;
        this.amountRobots = this.robots.size();
        return this.robots;
    }

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

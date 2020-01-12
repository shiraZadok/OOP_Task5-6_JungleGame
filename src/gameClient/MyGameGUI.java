package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import element.Fruits;
import gui.GUI;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

import java.util.LinkedList;
import java.util.List;

public class MyGameGUI extends Thread {

    private DGraph GraphGame;
    private GUI g;
    private List<Fruits> fruits;
    private int server;

    public MyGameGUI(int g){
        this.GraphGame = new DGraph();
        this.g = new GUI();
        this.fruits = new LinkedList<>();
        this.server = g;
    }

    public void init() {
        game_service game = Game_Server.getServer(this.server);
        String graph= game.getGraph();
        this.GraphGame.init(graph);
        this.g.GUIgraph(this.GraphGame);
        List<String> temp = game.getFruits();
        for(String s : temp){
            System.out.println(s);
        }
    }

    public void initFruits(){
        game_service game = Game_Server.getServer(this.server);
        int i = 0;
        for(String fruit_string : game.getFruits())
        {
            try
            {
                JSONObject line = new JSONObject(fruit_string);
                JSONObject fruit = line.getJSONObject("Fruit");
                System.out.println("the line = "+ fruit);
                int type = fruit.getInt("type");
                double value = fruit.getInt("value");
                String pos = fruit.getString("pos");
                String[] stringPos = pos.split(",");
                double [] doublePos = new double[stringPos.length];
                for (int j=0; j<stringPos.length;j++){
                    doublePos[j]= Double.valueOf(stringPos[j]);
                }
               Fruits newFruit = new Fruits(value, type, new Point3D(doublePos[0], doublePos[1], doublePos[2]));
                this.fruits.add(newFruit);
                System.out.println(i+ " ==== \n" + fruits.get(i).toString());
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI(8);
        m.initFruits();
    }
}

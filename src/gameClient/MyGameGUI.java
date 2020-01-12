package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import element.Fruits;
import element.FruitsList;
import gui.GUI;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;
import utils.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class MyGameGUI extends Thread {

    private DGraph GraphGame;
    private GUI g;
    private FruitsList fruits;
    private game_service server;

    public MyGameGUI(int g){
        game_service game = Game_Server.getServer(g);
        String graph= game.getGraph();
        this.GraphGame= new DGraph();
        this.GraphGame.init(graph);
        this.g = new GUI(this.GraphGame);
        List<String> temp = game.getFruits();
        for(String s : temp){
            System.out.println(s);
        }
        this.server = game;
        this.fruits = new FruitsList(this.server);
        for(Fruits f : this.fruits.fruits){
            StdDraw.picture(f.getLocation().x(),f.getLocation().y(),f.get_pic(),0.0005,0.0005);
        }
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI(8);

    }
}

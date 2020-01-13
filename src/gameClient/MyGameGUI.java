package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import element.Fruits;
import element.FruitsList;
import gui.GUI;
import utils.StdDraw;
import java.util.List;

public class MyGameGUI extends Thread {

    private DGraph GraphGame;
    private GUI g;
    private FruitsList fruits;
    private game_service server;

    public MyGameGUI(int g){
        this.server = Game_Server.getServer(g);
        String graph= this.server.getGraph();
        this.GraphGame= new DGraph();
        this.GraphGame.init(graph);
        this.g = new GUI(this.GraphGame);
        this.g.GUIgraph(this.GraphGame);
        List<String> temp = this.server.getFruits();
        for(String s : temp){
            System.out.println(s);
        }
        this.fruits = new FruitsList(this.server);
        for(Fruits f : this.fruits.fruits){
            StdDraw.picture(f.getLocation().x(),f.getLocation().y(),f.get_pic(),0.001,0.0008);
        }
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI(8);

    }
}

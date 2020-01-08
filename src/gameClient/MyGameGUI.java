package gameClient;

import com.google.gson.Gson;
import Server.Fruit;
import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import element.Fruits;
import gui.GUI;
import org.w3c.dom.ls.LSOutput;

import java.io.FileReader;
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
        Gson gson = new Gson();
        List<String> temp = game.getFruits();
        int i = 0;
        for(String s : temp){
            System.out.println(s);
            Fruits fruit = gson.fromJson(s, Fruits.class);
            this.fruits.add(fruit);
            System.out.println(fruits.get(i).toString());
            i++;
        }
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI(8);
        m.initFruits();
    }
}
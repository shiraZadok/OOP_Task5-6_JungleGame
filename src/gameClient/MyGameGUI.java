package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import element.Fruits;
import element.FruitsList;
import element.Robots;
import element.RobotsList;
import gui.GUI;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;
import utils.StdDraw;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class MyGameGUI extends Thread {

    public RobotsList robots;
    public FruitsList fruits;
    private DGraph GraphGame;
    private GUI g;
    public   game_service server;
    public   Game_Algo game_algo;
    private boolean b , menual, auto = false;
    private Robots r;

    public MyGameGUI() {

        StdDraw.setCanvasSize(1024,512);
        StdDraw.setYscale(-51,50);
        StdDraw.setXscale(-51,50);
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.1);
        StdDraw.picture(0,0,"jungle_open.jpg");
        StdDraw.picture(-3,0,"welcome.png");
        StdDraw.text(-3,-8,"to the jungle");
        String[] chooseNumOfGame = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        Object selectedNumOfGame = JOptionPane.showInputDialog(null, "Choose a num of game", "Message", JOptionPane.INFORMATION_MESSAGE, null, chooseNumOfGame, chooseNumOfGame[0]);

        int num = Integer.parseInt((String) selectedNumOfGame);
        this.server = Game_Server.getServer(num);

        String[] chooseGame = {"Manual game", "Auto game"};
        Object selectedGame = JOptionPane.showInputDialog(null, "Choose a game mode", "Message", JOptionPane.INFORMATION_MESSAGE, null, chooseGame, chooseGame[0]);

        StdDraw.gameGui = this;

        if (selectedGame == "Manual game"){
            this.menual = true;
            String graph= this.server.getGraph();
            this.robots = new RobotsList(this.server);
            this.fruits = new FruitsList(this.server);
            this.GraphGame= new DGraph();
            this.GraphGame.init(graph);
            this.g = new GUI(this.GraphGame);
            this.g.GUIgraph();
            this.game_algo = new Game_Algo(this.server);
            FruitsGui();
            StdDraw.show();
            System.out.println(this.server.toString());
        }

        if (selectedGame=="Auto game") {
            this.auto = true;
            String graph= this.server.getGraph();
            this.GraphGame= new DGraph();
            this.GraphGame.init(graph);
            this.g = new GUI(this.GraphGame);
            this.g.GUIgraph();
            this.game_algo = new Game_Algo(this.server);
            FruitsGui();
            RobotsGui();
            StdDraw.show();
            this.server.startGame();
            this.start();
            KML_Logger k = new KML_Logger();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        k.objKML();
                    }
                    catch (ParseException |InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }

    public void FruitsGui(){
        this.fruits = new FruitsList(this.server);
        for(Fruits f : this.fruits.fruits){
            StdDraw.picture(f.getLocation().x(),f.getLocation().y(),f.get_pic(),0.001,0.0008);
        }
    }

    public void RobotsGui(){
        this.robots = new RobotsList(this.server);
        this.game_algo.locationRobot();
        this.robots = new RobotsList(this.server);
        for(Robots c : this.robots.robots){
            StdDraw.picture(c.getLocation().x(), c.getLocation().y(), "monkey.png",0.0009,0.001);
        }
    }

    public void AddRobot(int key){
        this.robots = new RobotsList(this.server);
        this.server.addRobot(key);
        StdDraw.picture(this.GraphGame.getNode(key).getLocation().x(), this.GraphGame.getNode(key).getLocation().y(), "monkey.png",0.002,0.001);
        StdDraw.show();
    }

    private void moveRobots() {
        List<String> log = this.server.move();
        if(log!=null)
        {
            this.robots.listR(log);
            for (Robots r : this.robots.robots){
                System.out.println("\n");
                System.out.println("the Src of the robot - " + r.getId() +" before nextNode" +r.getSrc());
                if (r.getDest() ==-1){
                    this.game_algo.nextNode(r, this.GraphGame);
                }
            }
        }
        this.server.move();
    }

    public void moveRobotByClick() throws InterruptedException {
        double x;
        double y;
        JFrame jf = new JFrame();
        if (this.b == false) {
            x = StdDraw.mouseX();
            y = StdDraw.mouseY();
            node_data n = getTheRobot(x, y);
            if (n == null) {
                JOptionPane.showMessageDialog(jf, "Please press again");
            } else {
                this.b = true;
            }
        }
        else {
            x = StdDraw.mouseX();
            y = StdDraw.mouseY();
            node_data nextNode = getNextNode(x, y);
            if (this.r != null) {
                for (edge_data e : this.GraphGame.getE(this.r.getSrc())) {
                    if (nextNode.getKey() == e.getDest()) {
                        this.server.chooseNextEdge(this.r.getId(), nextNode.getKey());
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(jf, "The Robot can't move their");
            }
            this.b = false;
        }
    }

    private void moveRobotsToDest() {
        List<String> log = this.server.move();
        if(log!=null)
        {
            this.robots.listR(log);
            for (Robots r : this.robots.robots){
                if (r.getDest() ==-1){
                    this.server.move();
                }
            }
        }
        this.server.move();
    }

    private node_data getTheRobot(double x, double y) {
        this.robots.listR(this.server.getRobots());
        for(Robots n : this.robots.robots) {
            double nX = n.getLocation().x();
            double nY = n.getLocation().y();
            if ((x < nX + 0.0004) && (x > nX - 0.0004))
                if ((y < nY + 0.0004) && (y > nY - 0.0004)){
                    Point3D ansP = new Point3D(nX, nY, 0);
                    node_data ans = new Node(ansP);
                    this.r = n;
                    return ans;
                }
        }
        return null;
    }

    private node_data getNextNode(double x, double y) {
        for(node_data n : this.GraphGame.getV()){
            double nX = n.getLocation().x();
            double nY = n.getLocation().y();
            if((x<nX+0.0005) && (x>nX-0.0005))
                if ((y<nY+0.0005) && (y>nY-0.0005))
                    return n;
        }
        return null;
    }

    public void run(){
        while (this.server.isRunning()){
            FruitsGui();
            RobotsGui();
//            StdDraw.setPenColor(Color.BLACK);
//            StdDraw.setPenRadius(0.0005);
//            StdDraw.text(50,40,"TIMER: "+this.server.timeToEnd()/1000);
            if(this.auto==true) moveRobots();
            if(this.menual==true) moveRobotsToDest();
            StdDraw.show();
            this.g.DrawGraph(this.GraphGame);
//            StdDraw.picture(0,0,"jungle_open.jpg");
            try {
                sleep(10);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        JFrame jf = new JFrame();
        JOptionPane.showMessageDialog(jf,"THE GAME IS OVER"+"\n"+"YOUR GRADE IS : " + myGrade(this.server) );
    }

    public double myGrade(game_service server){
        double myGrade =0 ;
        try {
            String json = server.toString();
            JSONObject gameJson = new JSONObject(json);
            JSONObject gameServer = gameJson.getJSONObject("GameServer");
            myGrade = gameServer.getDouble("grade");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return myGrade;
    }

    public GUI getG(){
        return this.g;
    }
    public FruitsList getFruits(){
        return this.fruits;
    }
    public game_service getServer(){
        return this.server;
    }
    public RobotsList getRobots(){
        return this.robots;
    }
    public Game_Algo getGame_algo(){
        return this.game_algo;
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI();
    }
}

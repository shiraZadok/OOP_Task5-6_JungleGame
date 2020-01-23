package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.*;
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
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class MyGameGUI extends Thread {

    public RobotsList robots;
    public FruitsList fruits;
    private DGraph GraphGame;
    private GUI g;
    public game_service server;
    public Game_Algo game_algo;
    private boolean b , menual, auto = false;
    private Robots r;
    private int numGame;
    public static final String jdbcUrl="jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
    public static final String jdbcUser="student";
    public static final String jdbcUserPassword="OOP2020student";



    /**
     * a default constructor.
     */
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
        JFrame jf = new JFrame();
        String s = JOptionPane.showInputDialog(jf, "Please enter your id");
        int id = Integer.parseInt(s);
        Game_Server.login(id);
        String[] chooseNumOfGame = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        Object selectedNumOfGame = JOptionPane.showInputDialog(null, "Choose a num of game", "Message", JOptionPane.INFORMATION_MESSAGE, null, chooseNumOfGame, chooseNumOfGame[0]);

        int num = Integer.parseInt((String) selectedNumOfGame);
        this.server = Game_Server.getServer(num);
        this.numGame = num;
        String[] chooseGame = {"Manual game", "Auto game"};
        Object selectedGame = JOptionPane.showInputDialog(null, "Choose a game mode", "Message", JOptionPane.INFORMATION_MESSAGE, null, chooseGame, chooseGame[1]);

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

    /**
     * This function draw the fruits.
     */
    public void FruitsGui(){
        this.fruits = new FruitsList(this.server);
        for(Fruits f : this.fruits.fruits){
            StdDraw.picture(f.getLocation().x(),f.getLocation().y(),f.get_pic(),0.001,0.0008);
        }
    }

    /**
     * This function draw the robots.
     */
    public void RobotsGui(){
        this.robots = new RobotsList(this.server);
        if(this.numGame==5){
            this.server.addRobot(this.GraphGame.getNode(7).getKey());
        }
        this.game_algo.locationRobot();
        this.robots = new RobotsList(this.server);
        for(Robots c : this.robots.robots){
            StdDraw.picture(c.getLocation().x(), c.getLocation().y(), "monkey.png",0.0009,0.001);
        }
    }

    /**
     * This function adds a robot to the list of all existing robots in the game.
     * @param key is the key of robot.
     */
    public void AddRobot(int key){
        this.robots = new RobotsList(this.server);
        this.server.addRobot(key);
        StdDraw.picture(this.GraphGame.getNode(key).getLocation().x(), this.GraphGame.getNode(key).getLocation().y(), "monkey.png",0.002,0.001);
        StdDraw.show();
    }

    /**
     * This function updating new robot location.
     */
    private void moveRobotsAutomatic() {
        this.robots.listR(this.server.move());
        for (Robots r : this.robots.robots){
            if (r.getDest() ==-1){
                List<edge_data> edgeOfFruit = this.game_algo.getListOfEdgeF();
                this.game_algo.nextNode(r, this.GraphGame, edgeOfFruit, this.robots);
            }
        }
    }

    /**
     * This function updating new robot location in the manual game.
     * @throws InterruptedException
     */
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

    /**
     * This function move the robot to him dest.
     */
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
    }

    /**
     * An auxiliary function that found the node on which the robot is clicked.
     * @param x is the x of point that clicked.
     * @param y is the y of point that clicked.
     * @return the node on which the robot is clicked.
     */
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

    /**
     * An auxiliary function that found the node on which the robot is clicked.
     * @param x is the x of point that clicked.
     * @param y is the y of point that clicked.
     * @return the node on which the robot is clicked.
     */
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

    /**
     * The run function of the thread.
     */
    public void run(){
        while (this.server.isRunning()){
            FruitsGui();
            RobotsGui();
            if(this.auto==true) moveRobotsAutomatic();
            if(this.menual==true) moveRobotsToDest();
            StdDraw.show();
            this.g.DrawGraph(this.GraphGame);
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.setPenRadius(0.0005);
            StdDraw.text(this.g.getMinXY(this.GraphGame).x(),this.g.getMaxXY(this.GraphGame).y()+0.001,"TIMER: "+this.server.timeToEnd()/1000);
            StdDraw.text(this.g.getMinXY(this.GraphGame).x()+0.003,this.g.getMaxXY(this.GraphGame).y()+0.001,"SCORE: "+myGrade(this.server));
            StdDraw.text(this.g.getMinXY(this.GraphGame).x()+0.006,this.g.getMaxXY(this.GraphGame).y()+0.001,"MOVES: "+numOfMoves(this.server));
            try {
                sleep(numOfSleep());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        JFrame jf = new JFrame();
        JOptionPane.showMessageDialog(jf,"THE GAME IS OVER"+"\n"+"YOUR GRADE IS : " + myGrade(this.server) );
    }

    /**
     * This function takes from the server as points earned in the game.
     * @param server is the type of game.
     * @return the grade of game.
     */
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

    /**
     * This function calculates the amount of moves during the game.
     * @param server is the type of the game.
     * @return the amount of moves.
     */
    public double numOfMoves(game_service server){
        double myMoves =0 ;
        try {
            String json = server.toString();
            JSONObject gameJson = new JSONObject(json);
            JSONObject gameServer = gameJson.getJSONObject("GameServer");
            myMoves = gameServer.getDouble("moves");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return myMoves;
    }

    /**
     * This function check the num of sleep for the run function.
     * @return the num of sleep.
     */
    public int numOfSleep() {
        int ans = 100;
        if(this.numGame==0 || this.numGame==1 || this.numGame==3)return 100;
        if(this.numGame==5)return 120;
        if(this.numGame==9) return 100;
        if(this.numGame==11) return 100;
        if(this.numGame==13) ans = 80;
        for (Robots r : this.robots.robots) {
            List<edge_data> edgeFruit = this.game_algo.getListOfEdgeF();
            for (edge_data e : edgeFruit) {
                if (r.getSrc() == e.getSrc()) {
                    return 80;
                }
            }
        }
        return ans;
    }

    /**
     * This function extracts the results from DB of the game by id.
     */
    public void myScore(){
        int level = 0;
        int amountOfGames = 0;
        int amountOfMoves = 0;
        int amountOfScore = 0;
        ArrayList<Integer> score = new ArrayList<>();
        ArrayList<Integer> moves = new ArrayList<>();
        int movesExpected [] = {290,580,0,580,0,500,0,0,0,580,0,580,0,580,0,0,290,0,0,580,290,0,0,1140};
        int scoreExpected [] = {145,450,0,720,0,570,0,0,0,510,0,1050,0,310,0,0,235,0,0,250,200,0,0,1000};
        //Creates and initializes all cells to 0
        for (int i = 0; i < 24; i++) {
            score.add(i,0);
            moves.add(i,0);
        }
        JFrame jf = new JFrame();
        String s = JOptionPane.showInputDialog(jf, "Please enter your id");
        int id = Integer.parseInt(s);
        StdDraw.setCanvasSize(1024,512);
        StdDraw.setYscale(-51,50);
        StdDraw.setXscale(-51,50);
        StdDraw.clear(Color.MAGENTA);
        Font f = new Font("Calibri",Font.BOLD,16);
        StdDraw.setFont(f);
        StdDraw.setPenColor(Color.black);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
            Statement statement = connection.createStatement();
            String allCustomersQuery = "SELECT * FROM Logs WHERE UserID =" + id + " ORDER BY levelID , score;";
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);
            while (resultSet.next()) {
                amountOfGames++;
                level = resultSet.getInt("levelID");
                if(level>=0) {
                    amountOfMoves = resultSet.getInt("moves");
                    amountOfScore = resultSet.getInt("score");
                    if (amountOfScore >= scoreExpected[level] && amountOfScore > score.get(level) && amountOfMoves <= movesExpected[level]) {
                        score.remove(level);
                        moves.remove(level);
                        score.add(level, amountOfScore);
                        moves.add(level, amountOfMoves);
                    }
                }
            }

            int[] betterFriends = new int[24];
            Hashtable<Integer, Integer> theBest = new Hashtable<>();
            for (int i = 0; i < 24; i++) {
                String allCustomersQuery2 = "SELECT * FROM oop.Logs where levelID = " + i + " and score > " + score.get(i) + " and moves <= " + movesExpected[i] + ";";
                ResultSet resultSet2 = statement.executeQuery(allCustomersQuery2);
                while (resultSet2.next()) {
                    if (!theBest.contains(resultSet2.getInt("userID"))) {
                        theBest.put(resultSet2.getInt("userID"), resultSet2.getInt("score"));
                    }
                }
                betterFriends[i] = theBest.size();
                theBest.clear();
            }
            StdDraw.text(-40, 46, "Level");
            StdDraw.text(-20, 46, "best score");
            StdDraw.text(0, 46, "best moves");
            StdDraw.text(25, 46, "The place in relation to others");
            int y = 1;
            for (int i = 0; i <24 ; i++) {
                if(i==0 || i==1 || i==3 || i==5 || i==9 || i==11 || i==13 || i==16 || i==19 || i==20 || i==23) {
                    StdDraw.text(-40, 46 - (y * 7), "" + i);
                    StdDraw.text(-20, 46 - (y * 7), "" + score.get(i));
                    StdDraw.text(0, 46 - (y * 7), "" + moves.get(i));
                    StdDraw.text(25, 46 - (y * 7), "" + betterFriends[i]);
                    y++;
                }
            }

            StdDraw.text(-30, -45, "The amount of games you have played is: " + amountOfGames);
            StdDraw.text(40, -45, "Your level is: " + level);

            resultSet.close();
            statement.close();
            connection.close();
            StdDraw.show();
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * @return the gui that draw the graph..
     */
    public GUI getG(){
        return this.g;
    }

    /**
     * @return the FruitsList.
     */
    public FruitsList getFruits(){
        return this.fruits;
    }

    /**
     * @return the server.
     */
    public game_service getServer(){
        return this.server;
    }

    /**
     * @return the RobotsList.
     */
    public RobotsList getRobots(){
        return this.robots;
    }

    /**
     * @return the Game_Algo.
     */
    public Game_Algo getGame_algo(){
        return this.game_algo;
    }

    /**
     * @param id is our id.
     * @param level is our level.
     * @return the KML string (for our check).
     */
    public static String getKML(int id, int level) {
        String ans = null;
        String allCustomersQuery = "SELECT * FROM Users where userID="+id+";";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection =
                    DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);
            if(resultSet!=null && resultSet.next()) {
                ans = resultSet.getString("kml_"+level);
            }
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI();
//        String kml = getKML(315081422,0);
//        System.out.println("***** KML file example: ******");
//        System.out.println(kml);

    }
}

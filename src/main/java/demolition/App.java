package demolition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class App extends PApplet {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;
    // load sprites in the map
    // the icon of the lives
    private PImage picon;
    // The icon of the clock
    private PImage cicon;
    // The sprite of the empty map
    private PImage empty;
    // The wall that can be broken
    private PImage broken;
    // The solid wall
    private PImage wall;
    // The goal
    private PImage goal;   
    // set characters
    private Player player;
    private RedEnemy redEnemy;
    private YellowEnemy yellowEnemy;
    // List of Map
    public static ArrayList<String[]> map = new ArrayList<>();
    // List of Goal location
    private ArrayList<int[]> goal_location = new ArrayList<>();
    // List of bombs location
    private ArrayList<Bomb> bomb_location = new ArrayList<>();
    // List of explotion's center
    private ArrayList<Explosion> explosion_location = new ArrayList<>();
    // List that store the location of red enemy
    private ArrayList<RedEnemy> red_list = new ArrayList<>();
    // List that store the location of yellow enemy
    private ArrayList<YellowEnemy> yellow_list = new ArrayList<>();    
    // lives of player
    private int lives;
    // current level's map path
    private String levelpath;
    // How many levels 
    private int maxlevel;
    // Time that the player have
    private int time;
    // Current level which set 1 at first
    private int level = 1;
    // The state of the player 1: alive 2: reload 3: levelup
    private int alive = 1;
    private PFont newFont;
    public App() {}
    public void settings() { size(WIDTH, HEIGHT); }

    /**
     * Read the map and load sprites
     */
    public void readFile(){
            // The name of .json file
            JSONObject myJson = loadJSONObject("config.json");
            JSONArray levels = myJson.getJSONArray("levels");
            // Get the max level
            maxlevel = levels.size() + 1;
            //System.out.println(maxlevel);
            lives = myJson.getInt("lives");
            JSONObject levelObject = levels.getJSONObject(level-1);
            time = levelObject.getInt("time");
            levelpath = levelObject.getString("path");
        try {
            map.clear();
            BufferedReader setMap = new BufferedReader(new FileReader(new File(levelpath)));
            //load the map list
            for(int y = 0; y<13; y++){
                map.add(setMap.readLine().split(""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Load the images
        picon = loadImage("src\\main\\resources\\icons\\player.png");
        cicon = loadImage("src\\main\\resources\\icons\\clock.png");
        empty = loadImage("src\\main\\resources\\empty\\empty.png");
        broken = loadImage("src\\main\\resources\\broken\\broken.png");
        wall = loadImage("src\\main\\resources\\wall\\solid.png");
        goal = loadImage("src\\main\\resources\\goal\\goal.png");
        // create the new object
        for(int y=0; y<13; y++){
            for(int x=0; x<15; x++){
                if(map.get(y)[x].equals("G")){
                    goal_location.add(new int[]{x, y});
                }
                if(map.get(y)[x].equals("P")){
                    player = new Player(x, y);
                    GameObject.setPApplet(this);
                    player.loadImages();                   
                }
                if(map.get(y)[x].equals("Y")){ 
                    yellowEnemy = new YellowEnemy(x, y);
                    yellowEnemy.loadImages();
                    yellow_list.add(yellowEnemy);
                    map.get(y)[x] = " ";
                }
                if(map.get(y)[x].equals("R")){
                    redEnemy = new RedEnemy(x, y);
                    redEnemy.loadImages();
                    red_list.add(redEnemy);
                    map.get(y)[x] = " ";
                }
            }
        }
    }
    public void setup() {
        frameRate(FPS);
        readFile();
        Bomb.loadImages();    
        Explosion.loadImages();
    }
    public void draw() {
        background(255, 150, 0);
        surface.setTitle("Demolition");
        newFont = createFont("src\\main\\resources\\PressStart2P-Regular.ttf", 20);
        // Win the game
        if (level == maxlevel) {
            WIN();
        // Loss
        } else if (time == 0 || lives == 0) {
            GameOver();
            return;
        }else{
            // Check the state of player
            if(alive == 2){
                alive = 1;
                lives -= 1;
                int currentLife = lives;
                int currentTime = time;
                if( lives == 0){                    
                    GameOver();
                }else if(alive == 1){
                    red_list.clear();
                    yellow_list.clear();
                    explosion_location.clear();
                    bomb_location.clear();
                    goal_location.clear(); 
                    readFile();
                    lives = currentLife;
                    time = currentTime;
                }
            }else if(alive == 3){
                alive = 1;
                int currentLife = lives;          
                goal_location.clear();                
                explosion_location.clear();
                bomb_location.clear();
                red_list.clear();
                yellow_list.clear();
                readFile();
                lives = currentLife;
            }
            // Display the life message
            image(picon, 128, 16);
            textFont(newFont);
            text(lives+" ", 168, 45);
            fill(0);
            // Display the time message
            image(cicon, 280, 16);
            textFont(newFont);
            text(time+" ", 320, 45);
            fill(0);
            // Traverse map
            for(int y=0; y<13; y++){
                for(int x=0; x<15; x++){
                    if(map.get(y)[x].equals("W")){                    
                        image(wall, x*32, y*32+64);
                    }
                    if(map.get(y)[x].equals(" ")){
                        image(empty, x*32, y*32+64);
                    }
                    if(map.get(y)[x].equals("B")){
                        image(broken, x*32, y*32+64);
                    }
                    if(map.get(y)[x].equals("G")){   
                        image(goal, x*32, y*32+64); 
                    }
                    if(map.get(y)[x].equals("P")){
                        image(empty, x*32, y*32+64);
                    }
                    if(map.get(y)[x].equals("Y")){
                        image(empty, x*32, y*32+64);
                        map.get(y)[x] = " ";
                    }
                    if(map.get(y)[x].equals("R")){
                        image(empty, x*32, y*32+64);
                        map.get(y)[x] = " ";
                    }
                }
            }            
            // Draw the player
            player.draw(frameCount);
            // Draw the red enemy
            for(int i = red_list.size()-1; i>=0; i--){
                red_list.get(i).draw(frameCount);
                red_list.get(i).redAI(frameCount);
            }            
            // Draw the yellow enemy
            for(int i = yellow_list.size()-1; i>=0; i--){
                yellow_list.get(i).draw(frameCount);
                yellow_list.get(i).yellowAI(frameCount);
            }
            // If bombs are set.
            if(!bomb_location.isEmpty()){
                for(int i = bomb_location.size()-1; i>=0; i--){
                    
                    bomb_location.get(i).draw();                    
                    if(bomb_location.get(i).bombTimer(frameCount) == -1){                      
                        explosion_location.add(new Explosion(bomb_location.get(i).getX(), bomb_location.get(i).getY(), frameCount));                        
                        bomb_location.remove(i);
                    }
                }
            }
            // If bombs explode
            if (!explosion_location.isEmpty()){
                for(int j = explosion_location.size()-1; j>=0; j--){
                    explosion_location.get(j).draw();
                    int[] xCoordinates = explosion_location.get(j).getScopeX();
                    int[] yCoordinates = explosion_location.get(j).getScopeY();
                    //Check if the character is in blast range
                    for(int y=0; y<9; y++){
                        if(player.getX() == xCoordinates[y] && player.getY() == yCoordinates[y]){
                            alive = 2;
                            break;
                        }
                        for(int i = red_list.size()-1; i>=0 ; i--){
                            if(red_list.get(i).getX() == xCoordinates[y] && red_list.get(i).getY() == yCoordinates[y]){
                                red_list.remove(i);
                            }
                        }
                        for(int i=yellow_list.size()-1; i>=0; i--){
                            if(yellow_list.get(i).getX() == xCoordinates[y] && yellow_list.get(i).getY() == yCoordinates[y]){
                                yellow_list.remove(i);
                            }
                        }                        
                    }    
                    if(explosion_location.get(j).explosion_timer(frameCount) == -1){
                        explosion_location.remove(j);
                    }
                }
            }            
            if(frameCount%60 == 0){
                time--;
            }                       
            player_checkCrash();
            levelup();
        }
    }

    /**
     * Display success message
     */
    private void WIN(){
        textAlign(CENTER);
        textFont(newFont);
        fill(0);
        text("YOU WIN", 240, 240);
    }

    /**
     * Display loss warning
     */
    private void GameOver(){
        textAlign(CENTER);
        textFont(newFont);
        fill(0);
        text("GAME OVER", 240, 240);
    }

    /**
     * If the player arrive the goal, change the state of the player.
     */
    public void levelup(){
        for(int i=goal_location.size()-1; i>=0; i--){
            if(player.getX() ==  goal_location.get(i)[0] && player.getY()== goal_location.get(i)[1]){                
                level += 1;
                alive = 3;
            }
        }
    }

    /**
     * Check that the player's coordinates are equal to those of red and yellow enemies.
     */
    public void player_checkCrash(){
        for(int i=red_list.size()-1; i>=0; i--){
            if((player.getX() == red_list.get(i).getX() && player.getY() == red_list.get(i).getY())) {
               alive = 2;
            }
        }
        for(int i=yellow_list.size()-1; i>=0; i--){
            if((player.getX() == yellow_list.get(i).getX() && player.getY() == yellow_list.get(i).getY())) {
               alive = 2;
            }
        }
    }

    /**
     * Get the player's action
     */
    public void keyPressed(){
        int DOWN = 1; int LEFT = 2; int UP = 3; int RIGHT = 4;
        if(keyCode == 37){
            player.move(LEFT);
        }
        if(keyCode == 38){
            player.move(UP);
        }
        if(keyCode == 39){
            player.move(RIGHT);
        }
        if(keyCode == 40){
            player.move(DOWN);
        }
        if(keyCode == 32){
            bomb_location.add(new Bomb(player.getX(), player.getY(), frameCount));
        }
    }
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
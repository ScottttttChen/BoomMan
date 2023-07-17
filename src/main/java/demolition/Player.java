package demolition;

public class Player extends CharacterObject{

    /**
     * set the coordinates of the new object
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Player(int x, int y){
        super(x, y);
    }

    /**
     * load the images of the player
     * transmit the part of the path to parent class
     */
    public void loadImages(){
        super.loadDownImages("src\\main\\resources\\player\\player");
        super.loadLeftImages("src\\main\\resources\\player\\player_left");
        super.loadRightImages("src\\main\\resources\\player\\player_right");
        super.loadUpImages("src\\main\\resources\\player\\player_up");
    }

    /**
     * Decide how the player moving
     * @param move The direction that player should go.
     */
    public void move(int move){
        if(move == LEFT){
            super.direction = LEFT;
            // Change the map
            if(!App.map.get(super.y)[super.x-1].equals("B") && !App.map.get(super.y)[super.x-1].equals("W")){                
                App.map.get(super.y)[super.x] = " ";
                App.map.get(super.y)[super.x-1] = "P";
                super.x -=1;
            }
        }
        if(move == UP){
            super.direction = UP;
            // Change the map
            if(!App.map.get(super.y-1)[super.x].equals("B") && !App.map.get(super.y-1)[super.x].equals("W")){
                App.map.get(super.y)[super.x] = " ";
                App.map.get(super.y-1)[super.x] = "P";
                super.y -=1;
            }
        }
        if(move == RIGHT){
            super.direction = RIGHT;
            // Change the map
            if(!App.map.get(super.y)[super.x+1].equals("B") && !App.map.get(super.y)[super.x+1].equals("W")){
                App.map.get(super.y)[super.x] = " ";
                App.map.get(super.y)[super.x+1] = "P";
                super.x += 1;
            }
        }
        if(move == DOWN){
            super.direction = DOWN;
            // Change the map
            if(!App.map.get(super.y+1)[super.x].equals("B") && !App.map.get(super.y+1)[super.x].equals("W")){
                App.map.get(super.y)[super.x] = " ";
                App.map.get(super.y+1)[super.x] = "P";
                super.y += 1;
            }
        }        
    }    
}
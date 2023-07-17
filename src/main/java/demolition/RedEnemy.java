package demolition;

import java.util.Random;

public class RedEnemy extends CharacterObject{

    /**
     * set the coordinates of the red enemy
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public RedEnemy(int x, int y){
        super(x, y);
    }

    /**
     * Load the images of the red enemy
     * Transmit the part of the path to parent class
     */
    public void loadImages(){
        super.loadDownImages("src\\main\\resources\\red_enemy\\red_down");
        super.loadLeftImages("src\\main\\resources\\red_enemy\\red_left");
        super.loadRightImages("src\\main\\resources\\red_enemy\\red_right");
        super.loadUpImages("src\\main\\resources\\red_enemy\\red_up");
    }


    /**
     * Decide how the red enemy moving
     * @param frameCount Current frame number
     */
    public void redAI(int frameCount){
        if(frameCount %60 != 0){return;}
        if(direction == LEFT && (App.map.get(y)[x-1].equals("W") || App.map.get(y)[x-1].equals("B"))){
            super.direction = RedDirection();
        }else if(direction == UP && (App.map.get(y-1)[x].equals("W") || App.map.get(y-1)[x].equals("B"))){
            super.direction = RedDirection();
        }else if(direction == RIGHT && (App.map.get(y)[x+1].equals("W") || App.map.get(y)[x+1].equals("B"))){
            super.direction = RedDirection();
        }else if(direction == DOWN && (App.map.get(y+1)[x].equals("W") || App.map.get(y+1)[x].equals("B"))){
            super.direction = RedDirection();
        }
        if(direction == LEFT && !App.map.get(y)[x-1].equals("W") && !App.map.get(y)[x-1].equals("B")){
            x -= 1;
        }
        if(direction == UP && !App.map.get(y-1)[x].equals("W") && !App.map.get(y-1)[x].equals("B")){
            y -= 1 ;
        }
        if(direction == RIGHT && !App.map.get(y)[x+1].equals("W") && !App.map.get(y)[x+1].equals("B")){
            x += 1;
        }
        if(direction == DOWN && !App.map.get(y+1)[x].equals("W") && !App.map.get(y+1)[x].equals("B")){
            y += 1 ;
        }    
    }

    /**
     * Calculate the direction if the red enemy cannot move on.
     * @return The direction of the red enemy.
     */
    private int RedDirection(){
        Random random_direction = new Random();
        direction = random_direction.nextInt(4)+1;
        return direction;
    }    
}
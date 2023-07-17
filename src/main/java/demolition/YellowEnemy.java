package demolition;

public class YellowEnemy extends CharacterObject{

    /**
     * set the coordinates of the yellow enemy
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public YellowEnemy(int x, int y){
        super(x, y);
    }

    /**
     * Load the images of the yellow enemy
     * Transmit the part of the path to parent class
     */
    public void loadImages(){
        super.loadDownImages("src\\main\\resources\\yellow_enemy\\yellow_down");
        super.loadLeftImages("src\\main\\resources\\yellow_enemy\\yellow_left");
        super.loadRightImages("src\\main\\resources\\yellow_enemy\\yellow_right");
        super.loadUpImages("src\\main\\resources\\yellow_enemy\\yellow_up");
    }

    /**
     * Decide how the yellow enemy moving
     * @param frameCount Current frame number
     */
    public void yellowAI(int frameCount){
        if(frameCount%60 != 0){return;}
        if(direction == LEFT && (App.map.get(y)[x-1].equals("W") || App.map.get(y)[x-1].equals("B"))){
            super.direction = UP;
        }else if(direction == UP && (App.map.get(y-1)[x].equals("W") || App.map.get(y-1)[x].equals("B"))){
            super.direction = RIGHT;
        }else if(direction == RIGHT && (App.map.get(y)[x+1].equals("W") || App.map.get(y)[x+1].equals("B"))){
            super.direction = DOWN;
        }else if(direction == DOWN && (App.map.get(y+1)[x].equals("W") || App.map.get(y+1)[x].equals("B"))){
            super.direction = LEFT;
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
}
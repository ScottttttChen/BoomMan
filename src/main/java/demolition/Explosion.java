package demolition;

import processing.core.PImage;

public class Explosion extends GameObject{
    private int setFrame;
    private int LeftState = 0;
    private int RightState = 0;
    private int UpState = 0;
    private int DownState = 0;
    private int[][] scope;
    private static PImage[] explosionImages = new PImage [7];

    /**
     * Create a new explosion, set coordinates and get the initial frame;
     *
     * @param x X-coordinate of the object
     * @param y Y-coordinate of the object
     * @param setFrame Get the initial frame from the App
     */
    public Explosion(int x, int y, int setFrame) {
        super(x, y);
        this.setFrame = setFrame;
        scope = new int[9][2];
        for(int i=0; i<9; i++){
            for(int j=0; j<2;j++){
                scope[i][j] = -1;
            }
        }
    }

    /**
     * load the explosion's sprites
     * @return The array that include the PImage object
     */
    public static PImage[] loadImages() {
        explosionImages[0] = pic.loadImage("src\\main\\resources\\explosion\\centre.png");
        explosionImages[1] = pic.loadImage("src\\main\\resources\\explosion\\end_top.png");
        explosionImages[2] = pic.loadImage("src\\main\\resources\\explosion\\end_bottom.png");
        explosionImages[3] = pic.loadImage("src\\main\\resources\\explosion\\end_left.png");
        explosionImages[4] = pic.loadImage("src\\main\\resources\\explosion\\end_right.png");
        explosionImages[5] = pic.loadImage("src\\main\\resources\\explosion\\horizontal.png");
        explosionImages[6] = pic.loadImage("src\\main\\resources\\explosion\\vertical.png");
        return explosionImages;
    }

    /**
     * Draw the explosion
     * Estimate the scope of the explosion
     * Change the map if the scope of the explosion include "B"
     */
    public void draw(){
        int CENTER = 0;
        int LEFT1 = 1;
        int LEFT2 = 2;
        int UP1 = 3;
        int UP2 = 4;
        int RIGHT1 = 5;
        int RIGHT2 = 6;
        int DOWN1 = 7;
        int DOWN2 = 8;

        // draw the center part
        pic.image(explosionImages[0], super.x*32, super.y*32+64);
        // center of explosion
        scope[CENTER][0] = super.x; scope[CENTER][1] = super.y;
        // check left area and draw
        if(!App.map.get(super.y)[super.x-1].equals("W")){
            if(LeftState == 1){
                pic.image(explosionImages[3], super.x*32-32, super.y*32+64);
            } else{
                if (App.map.get(super.y)[super.x-1].equals("B") || App.map.get(super.y)[super.x-2].equals("W")) {
                    pic.image(explosionImages[3], super.x*32-32, super.y*32+64);
                    // set scope: left 1
                    scope[LEFT1][0] = super.x-1; scope[LEFT1][1]= super.y;
                    App.map.get(super.y)[super.x-1] = " ";
                    //State of the explosion. If scope is 1, just draw the end of the direction
                    LeftState = 1;

                } else if(App.map.get(super.y)[super.x-1].equals(" ") || App.map.get(super.y)[super.x-1].equals("G") || App.map.get(super.y)[super.x-1].equals("P")){
                    pic.image(explosionImages[5], super.x*32-32, super.y*32+64);
                    // set scope: left 1
                    scope[LEFT1][0] = super.x-1; scope[LEFT1][1]= super.y;
                    pic.image(explosionImages[3], super.x*32-64, super.y*32+64);
                    // set scope: left 2
                    scope[LEFT2][0] = super.x-2; scope[LEFT2][1] = super.y;
                    if(App.map.get(super.y)[super.x-2].equals("B")){
                        App.map.get(super.y)[super.x-2] = " ";
                    }                       
                } 
            }
        }
        // check right area and draw
        if(!App.map.get(super.y)[super.x+1].equals("W")){
            if (RightState == 1) {
                pic.image(explosionImages[4], super.x*32+32, super.y*32+64);
            }
            else{
                if((App.map.get(super.y)[super.x+2].equals("W") || App.map.get(super.y)[super.x+1].equals("B"))
                 ){
                    pic.image(explosionImages[4], super.x*32+32, super.y*32+64);
                    // set scope: right 1
                    scope[RIGHT1][0] = super.x+1; scope[RIGHT1][1] = super.y;
                    App.map.get(super.y)[super.x+1] = " ";
                    //State of the explosion. If scope is 1, just draw the end of the direction
                    RightState =1;
                } else if(App.map.get(super.y)[super.x+1].equals(" ") || App.map.get(super.y)[super.x+1].equals("P") || App.map.get(super.y)[super.x+1].equals("G")){
                    pic.image(explosionImages[5], super.x*32+32, super.y*32+64);
                    // set scope: right 1
                    scope[RIGHT1][0] = super.x+1; scope[RIGHT1][1] = super.y;
                    pic.image(explosionImages[4], super.x*32+64, super.y*32+64);
                    //set scope: right 2
                    scope[RIGHT2][0] = super.x+2; scope[RIGHT2][1] = super.y;
                    if(App.map.get(super.y)[super.x+2].equals("B")){
                        App.map.get(super.y)[super.x+2] = " ";
                    }
                }
            }
        }

        // check up area and draw
        if(!App.map.get(super.y-1)[super.x].equals("W") ){
            if (UpState == 1) {
                pic.image(explosionImages[1], super.x*32, super.y*32+64-32);
            }else{
                if (App.map.get(super.y-1)[super.x].equals("B") || App.map.get(super.y-2)[super.x].equals("W")) {
                    pic.image(explosionImages[1], super.x*32, super.y*32+64-32);
                    // set scope: up 1
                    scope[UP1][0] = super.x; scope[UP1][1] = super.y-1;
                    App.map.get(super.y-1)[super.x] = " ";
                    //State of the explosion. If scope is 1, just draw the end of the direction
                    UpState = 1;
                }
                else if(App.map.get(super.y-1)[super.x].equals(" ") || App.map.get(super.y-1)[super.x].equals("P") || App.map.get(super.y-1)[super.x].equals("G")){
                    pic.image(explosionImages[6], super.x*32, super.y*32+64-32);
                    // set scope: up 1
                    scope[UP1][0] = super.x; scope[UP1][1] = super.y-1;
                    pic.image(explosionImages[1], super.x*32, super.y*32+64-64);
                    //set scope: up 2
                    scope[UP2][0] = super.x; scope[UP2][1] = super.y-2;
                    if(App.map.get(super.y-2)[super.x].equals("B")){
                        App.map.get(super.y-2)[super.x] = " ";
                    }
                } 
            }
        }
        // check down area and draw
        if(!App.map.get(super.y+1)[super.x].equals("W") ){
            if(DownState == 1){
                pic.image(explosionImages[2], super.x*32, super.y*32+64+32);
            }else{
                if (App.map.get(super.y+1)[super.x].equals("B") || App.map.get(super.y+2)[super.x].equals("W")) {
                    pic.image(explosionImages[2], super.x*32, super.y*32+64+32);
                    // set scope: down 1
                    scope[DOWN1][0] = super.x; scope[DOWN1][1] = super.y+1;
                    App.map.get(super.y+1)[super.x] = " ";
                    //State of the explosion. If scope is 1, just draw the end of the direction
                    DownState = 1;
                }else if(App.map.get(super.y+1)[super.x].equals(" ") || App.map.get(super.y+1)[super.x].equals("P") || App.map.get(super.y+1)[super.x].equals("G")){
                    pic.image(explosionImages[6], super.x*32, super.y*32+64+32);
                    // set scope: down 1
                    scope[DOWN1][0] = super.x; scope[DOWN1][1] = super.y+1;
                    pic.image(explosionImages[2], super.x*32, super.y*32+64+64);
                    //set scope: down 2
                    scope[DOWN2][0] = super.x; scope[DOWN2][1] = super.y+2;
                    if(App.map.get(super.y+2)[super.x].equals("B")){
                        App.map.get(super.y+2)[super.x] = " ";
                    }
                }  
            }
        }
    }

    /**
     * calculate the state of the explosion
     * @param frameCount current frame number
     * @return if the number is -1, the explosion is finish
     */
    public int explosion_timer(int frameCount){
        if((frameCount - this.setFrame+1) % 30 == 0){
            return -1;
        }
        return 1;
    }

    /**
     * get all x-coordinate of the explosion scope
     * @return the int array that include x-coordinates
     */
    public int[] getScopeX(){
        int[] xCoordinates = new int[9];
        for(int i=0; i<9 ;i++){
            xCoordinates[i] = scope[i][0];
        }
        return xCoordinates;
    }

    /**
     * get all y-coordinate of the explosion scope
     * @return the int array that include y-coordinates
     */
    public int[] getScopeY(){
        int[] yCoordinates = new int[9];
        for(int i=0; i<9; i++){
            yCoordinates[i] = scope[i][1];
        }
        return yCoordinates;
    }
}
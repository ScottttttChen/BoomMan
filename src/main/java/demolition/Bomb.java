package demolition;

import processing.core.PImage;

public class Bomb extends GameObject{
    private int setFrame;

    private int IndexOfBomb = 0;
    private static PImage[] bombs = new PImage[8];

    /**
     * Create a new bomb, and set coordinates and get the initial frame;
     *
     * @param x X-coordinate of the object
     * @param y Y-coordinate of the object
     * @param setFrame Get the initial frame from the App
     */
    public Bomb(int x, int y, int setFrame){
        super(x, y);
        this.setFrame = setFrame;
    }

    /**
     * load the bomb's sprites
     * @return The array that include the PImage object
     */
    public static PImage[] loadImages(){
        bombs[0] = pic.loadImage("src\\main\\resources\\bomb\\bomb1.png");
        bombs[1] = pic.loadImage("src\\main\\resources\\bomb\\bomb2.png");
        bombs[2] = pic.loadImage("src\\main\\resources\\bomb\\bomb3.png");
        bombs[3] = pic.loadImage("src\\main\\resources\\bomb\\bomb4.png");
        bombs[4] = pic.loadImage("src\\main\\resources\\bomb\\bomb5.png");
        bombs[5] = pic.loadImage("src\\main\\resources\\bomb\\bomb6.png");
        bombs[6] = pic.loadImage("src\\main\\resources\\bomb\\bomb7.png");
        bombs[7] = pic.loadImage("src\\main\\resources\\bomb\\bomb8.png");
        return bombs;
    }

    /**
     * draw the images on the screen
     */
    public void draw(){
        pic.image(bombs[IndexOfBomb], super.x*32, super.y*32+64);
    }

    /**
     * calculate the state of the bomb
     *
     * @param frameCount The current frame number
     * @return If the indexOfBomb is from 0 to 7, return the index number; if the indexOfBomb is 8 return -1.
     */
    public int bombTimer(int frameCount){
        if( (frameCount - this.setFrame) % 15 == 0)
            IndexOfBomb++;
            if(IndexOfBomb == 8){
                return -1;
            }
        return IndexOfBomb;
    }
}
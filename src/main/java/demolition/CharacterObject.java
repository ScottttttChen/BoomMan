package demolition;

import processing.core.PImage;

public abstract class CharacterObject extends GameObject{
    protected int DOWN = 1;
    protected int LEFT = 2;
    protected int UP = 3;
    protected int RIGHT = 4;

    protected int direction = DOWN;    

    private int indexOfSprite = 0;

    private PImage[] downSprites = new PImage[4];
    private PImage[] leftSprites = new PImage[4];
    private PImage[] upSprites  = new PImage[4];
    private PImage[] rightSprites  = new PImage[4];

    /**
     * set the coordinates for sub-class
     *
     * @param x The X-coordinate of the object
     * @param y The Y-coordinate of the object
     */
    public CharacterObject(int x, int y){
        super(x, y);
    }

    /**
     * Load the images that the direction is down.
     * @param path Part of the down sprites' path.
     * @return The array of the down images
     */
    public PImage[] loadDownImages(String path) {
        downSprites[0] = pic.loadImage(path+"1.png");
        downSprites[1] = pic.loadImage(path+"2.png");
        downSprites[2] = pic.loadImage(path+"3.png");
        downSprites[3] = pic.loadImage(path+"4.png");        
        return downSprites;
    }
    /**
     * Load the images that the direction is left.
     * @param path Part of the down sprites' path.
     * @return The array of the left images
     */
    public PImage[] loadLeftImages(String path) {
        leftSprites[0] = pic.loadImage(path+"1.png");
        leftSprites[1] = pic.loadImage(path+"2.png");
        leftSprites[2] = pic.loadImage(path+"3.png");
        leftSprites[3] = pic.loadImage(path+"4.png");        
        return leftSprites;
    }

    /**
     * Load the images that the direction is up.
     * @param path Part of the up sprites' path.
     * @return The array of the up images.
     */
    public PImage[] loadUpImages(String path) {
        upSprites[0] = pic.loadImage(path+"1.png");
        upSprites[1] = pic.loadImage(path+"2.png");
        upSprites[2] = pic.loadImage(path+"3.png");
        upSprites[3] = pic.loadImage(path+"4.png");        
        return upSprites;
    }
    /**
     * Load the images that the direction is right.
     * @param path Part of the right sprites' path.
     * @return The array of the right images.
     */
    public PImage[] loadRightImages(String path) {
        rightSprites[0] = pic.loadImage(path+"1.png");
        rightSprites[1] = pic.loadImage(path+"2.png");
        rightSprites[2] = pic.loadImage(path+"3.png");
        rightSprites[3] = pic.loadImage(path+"4.png");        
        return rightSprites;
    }

    /**
     *  The sub-class of the CharacterObject must implement this abstract method to load the images
     */
    public abstract void loadImages();


    /**
     * Draw sprites for different direction
     * @param frameCount Get the frame number from App to calculate the index of the images.
     */
    public void draw(int frameCount){
        // Calculate the index of sprite.
        if(frameCount%15 ==0){
            indexOfSprite++;
        }
        if(indexOfSprite == 4){
            indexOfSprite = 0;
        }
        // Draw different sprite for different direction.
        if(direction == DOWN){
            pic.image(downSprites[indexOfSprite], super.x*32, super.y*32+48);
        } else if(direction == LEFT){
            pic.image(leftSprites[indexOfSprite], super.x*32, super.y*32+48);            
        } else if(direction == UP){
            pic.image(upSprites[indexOfSprite], super.x*32, super.y*32+48);
        } else if(direction == RIGHT){
            pic.image(rightSprites[indexOfSprite], super.x*32, super.y*32+48);
        }
    }    
}
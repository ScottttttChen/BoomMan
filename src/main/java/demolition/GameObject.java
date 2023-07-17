package demolition;

import processing.core.PApplet;

public abstract class GameObject {
    protected int x;
    protected int y;

    public static PApplet pic;

    /**
     * set the coordinates for sub-class
     * @param x The X-coordinate of the object
     * @param y The Y-coordinate of the object
     */
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * set a PApplet object
     * @param pApplet PApplet object
     */
    public static void setPApplet(PApplet pApplet){
        pic = pApplet;
    }

    /**
     * get x-coordinate
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * get y-coordinate
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }
}
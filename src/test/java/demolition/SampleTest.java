package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {
    
    @Test
    public void simpleTest() {
        assertEquals(480, App.HEIGHT);
    }
    // the width of the screen
    @Test
    public void widthTest(){
        assertEquals(480, App.WIDTH);        
    }
    // the fps of the app
    @Test
    public void fpsTest(){
        assertEquals(60, App.FPS);        
    }     

    //@Test
    //public void player_checkCrashTest(){
    //    App app = new App();
    //    app.player_checkCrash();
    //    Player player = new Player(1, 1);
    //    RedEnemy redEnemy = new RedEnemy(1, 1);
    //    
    //    assertEquals(2,player_checkCrash().alive);
    //}

    @Test
    public void moveDownTest(){
        Player player = new Player(1, 1);
        player.move(1);
        assertEquals(1, player.getX());
        assertEquals(2, player.getY());
    }
    
    @Test
    public void moveRightTest(){
        Player player = new Player(1,1);
        player.move(4);
        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    public void moveUpTest(){
        Player player = new Player(1,1);
        player.move(3);
        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }
    @Test
    public void moveLeftTest(){
        Player player = new Player(1,1);
        player.move(2);
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
    }

    
    
}

import greenfoot.*;

public class GameOverOverlay extends Actor {
    public GameOverOverlay(String message) {
        GreenfootImage img = new GreenfootImage(message, 24, Color.BLACK, new Color(0,0,0,0));
        setImage(img);
        
    }
}


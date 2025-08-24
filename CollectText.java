import greenfoot.*;

public class CollectText extends Actor {
    public void update(String targetState) {
        setImage(new GreenfootImage("Collect: " + targetState, 24, Color.BLACK, new Color(0,0,0,0)));
    }
}
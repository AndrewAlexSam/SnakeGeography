import greenfoot.*;

public class StateMarker extends Actor {
    private String stateName;
    private boolean collected = false;

    public StateMarker(String name) {
        stateName = name;
        GreenfootImage img = new GreenfootImage(5, 5);
        img.setColor(Color.RED);
        img.fillOval(0, 0, 5, 5);
        setImage(img);
    }

    public String getStateName() {
        return stateName;
    }

    public void collect() {
        collected = true;
        int maxWidth = 50;
        int fontSize = 20;
        GreenfootImage img;
        do {
            img = new GreenfootImage(stateName, fontSize, Color.BLACK, new Color(0, 0, 0, 0));
            fontSize--;
        } while (img.getWidth() > maxWidth && fontSize > 8);
        setImage(img);
    }
    
}

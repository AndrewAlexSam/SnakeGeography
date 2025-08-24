import greenfoot.*;

public class Orb extends Actor {
    private String stateName;
    private int size = 8;
    private long spawnTime;

    public Orb(String stateName) {
        this.stateName = stateName;
        spawnTime = System.currentTimeMillis();
        updateImage();
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(Color.BLUE);
        img.fillOval(0, 0, size, size);
        setImage(img);
        getImage().setTransparency(255);
    }

    public String getStateName() {
        return stateName;
    }
    
    public long getSpawnTime() {
        return spawnTime;
    }
}

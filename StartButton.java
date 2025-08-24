import greenfoot.*;  

public class StartButton extends Actor {
    public StartButton() {
        setImage(new GreenfootImage("Start", 36, Color.WHITE, Color.BLACK));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            USWorld world = (USWorld)getWorld();
            world.startGame();
            getWorld().removeObject(this);
        }
    }
}
import greenfoot.*;

public class Player extends Actor {
    public void act() {
        checkKeys();
        checkOrbCollision();
    }

    private void checkKeys() {
        if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - 4);
        if (Greenfoot.isKeyDown("down")) setLocation(getX(), getY() + 4);
        if (Greenfoot.isKeyDown("left")) setLocation(getX() - 4, getY());
        if (Greenfoot.isKeyDown("right")) setLocation(getX() + 4, getY());
    }

    private void checkOrbCollision() {
        Actor orb = getOneIntersectingObject(Orb.class);
        if (orb != null) ((USWorld)getWorld()).collectOrb((Orb)orb);
    }
}

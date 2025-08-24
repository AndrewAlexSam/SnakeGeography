import greenfoot.*;

public class ScoreText extends Actor {
    public void update(int score, int lives) {
        setImage(new GreenfootImage("Score: " + score + " | Lives: " + lives, 24, Color.BLACK, new Color(0,0,0,0)));
    }
}
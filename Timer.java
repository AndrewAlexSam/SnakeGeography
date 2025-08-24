import greenfoot.*;

public class Timer extends Actor {
    private long startTime;
    private boolean running;
    private long elapsedTime;

    public Timer() {
        startTime = 0;
        running = false;
        elapsedTime = 0;
        updateDisplay(0, 0);
    }

    public void act() {
        if (running) {
            long currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime; 
            int seconds = (int) (elapsedTime / 1000);
            int milliseconds = (int) (elapsedTime % 1000);
            updateDisplay(seconds, milliseconds);
        }
    }

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void stop() {
        running = false;
    }

    public int getElapsedTime() {
        return (int) (elapsedTime / 1000);
    }

    public int getElapsedMilliseconds() {
        return (int) (elapsedTime % 1000);
    }

    private void updateDisplay(int seconds, int milliseconds) {
        String displayText = "Time: " + seconds + "." + milliseconds + "s";
        GreenfootImage image = new GreenfootImage(displayText, 24, Color.BLACK, Color.WHITE);
        setImage(image);
    }
}
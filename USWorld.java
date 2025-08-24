import greenfoot.*;  
import java.util.*;

public class USWorld extends World {
    private int score = 0, lives = 3, wrongCount = 0, maxWrong = 3;
    private List<String> remainingStates = new ArrayList<>();
    private List<Orb> currentOrbs = new ArrayList<>();
    private String currentTarget;
    private long lastCollectTime = 0, collectDelay = 500;
    private Player player;
    private Timer timer;
    private boolean gameStarted = false;
    private ScoreText scoreText;
    private CollectText collectText;
    public static int totalGamesPlayed = 0;
    public static int highScore = 0;

    public static void resetHighScore() {
        totalGamesPlayed = 0;
    }
    public static int getHighScore() {
    return highScore;
    }
    public USWorld() {
        super(600, 400, 1);
        setupStates();
        setupStateMarkers();
        prepareStartScreen();
    }

    private void prepareStartScreen() {
        StartButton startButton = new StartButton();
        addObject(startButton, getWidth() / 2, getHeight() / 2);
    }

    public void startGame() {
        if (!gameStarted) {
            gameStarted = true;
            player = new Player();
            addObject(player, 300, 200);

            scoreText = new ScoreText();
            addObject(scoreText, getWidth() - 100, 20);

            collectText = new CollectText();
            addObject(collectText, getWidth() - 100, getHeight() - 20);

            timer = new Timer();
            addObject(timer, getWidth() / 2, 20);
            timer.start();

            spawnOrbsSet();
        }
    }

    public void act() {
        if (gameStarted) {
            scoreText.update(score, lives);
            if (remainingStates.isEmpty() || lives <= 0) endGame();
        }
    }

    public void endGame() {
        if (score > highScore) highScore = score;
        if (timer != null) timer.stop();
        for (Orb orb : new ArrayList<>(currentOrbs)) removeObject(orb);
        currentOrbs.clear();
        for (StateMarker marker : getObjects(StateMarker.class)) removeObject(marker);
        if (player != null) removeObject(player);
        if (scoreText != null) removeObject(scoreText);
        if (collectText != null) removeObject(collectText);

        GreenfootImage endScreenText = new GreenfootImage(
            "Game Over!\nHigh Score: " + highScore + "\nTime: " +
            (timer != null ? timer.getElapsedTime() + "." + timer.getElapsedMilliseconds() + "s" : "0.0s"),
            24, Color.BLACK, new Color(0,0,0,0)
        );
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        getBackground().drawImage(endScreenText, x - endScreenText.getWidth()/2, y - endScreenText.getHeight()/2);
        Greenfoot.stop();
    }

    private void setupStates() {
        remainingStates.addAll(Arrays.asList(
            "Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia",
            "Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts",
            "Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey",
            "New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island",
            "South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"
        ));
    }

    private void setupStateMarkers() {
        addObject(new StateMarker("Alabama"), 405, 275);
        addObject(new StateMarker("Alaska"), 75, 325);
        addObject(new StateMarker("Arizona"), 130, 245);
        addObject(new StateMarker("Arkansas"), 342, 250);
        addObject(new StateMarker("California"), 55, 200);
        addObject(new StateMarker("Colorado"), 205, 190);
        addObject(new StateMarker("Connecticut"), 537, 127);
        addObject(new StateMarker("Delaware"), 513, 168);
        addObject(new StateMarker("Florida"), 473, 330);
        addObject(new StateMarker("Georgia"), 445, 275);
        addObject(new StateMarker("Hawaii"), 205, 370);
        addObject(new StateMarker("Idaho"), 125, 110);
        addObject(new StateMarker("Illinois"), 372, 175);
        addObject(new StateMarker("Indiana"), 404, 175);
        addObject(new StateMarker("Iowa"), 330, 150);
        addObject(new StateMarker("Kansas"), 280, 200);
        addObject(new StateMarker("Kentucky"), 420, 204);
        addObject(new StateMarker("Louisiana"), 342, 300);
        addObject(new StateMarker("Maine"), 558, 67);
        addObject(new StateMarker("Maryland"), 500, 168);
        addObject(new StateMarker("Massachusetts"), 537, 116);
        addObject(new StateMarker("Michigan"), 415, 125);
        addObject(new StateMarker("Minnesota"), 318, 92);
        addObject(new StateMarker("Mississippi"), 374, 275);
        addObject(new StateMarker("Missouri"), 340, 200);
        addObject(new StateMarker("Montana"), 180, 75);
        addObject(new StateMarker("Nebraska"), 270, 160);
        addObject(new StateMarker("Nevada"), 90, 170);
        addObject(new StateMarker("New Hampshire"), 541, 100);
        addObject(new StateMarker("New Jersey"), 522, 150);
        addObject(new StateMarker("New Mexico"), 195, 250);
        addObject(new StateMarker("New York"), 510, 115);
        addObject(new StateMarker("North Carolina"), 483, 227);
        addObject(new StateMarker("North Dakota"), 265, 75);
        addObject(new StateMarker("Ohio"), 439, 165);
        addObject(new StateMarker("Oklahoma"), 290, 240);
        addObject(new StateMarker("Oregon"), 65, 95);
        addObject(new StateMarker("Pennsylvania"), 490, 150);
        addObject(new StateMarker("Rhode Island"), 547, 126);
        addObject(new StateMarker("South Carolina"), 470, 250);
        addObject(new StateMarker("South Dakota"), 265, 115);
        addObject(new StateMarker("Tennessee"), 405, 232);
        addObject(new StateMarker("Texas"), 270, 300);
        addObject(new StateMarker("Utah"), 140, 180);
        addObject(new StateMarker("Vermont"), 528, 90);
        addObject(new StateMarker("Virginia"), 490, 195);
        addObject(new StateMarker("Washington"), 85, 50);
        addObject(new StateMarker("West Virginia"), 460, 190);
        addObject(new StateMarker("Wisconsin"), 361, 110);
        addObject(new StateMarker("Wyoming"), 190, 130);
    }

    private StateMarker findMarkerByName(String name) {
        for (StateMarker marker : getObjects(StateMarker.class)) {
            if (marker.getStateName().equals(name)) return marker;
        }
        return null;
    }

    private void spawnOrbAtState(String stateName){
        StateMarker marker = findMarkerByName(stateName);
        if(marker != null){
            Orb orb = new Orb(stateName);
            addObject(orb, marker.getX(), marker.getY());
            currentOrbs.add(orb);
        }
    }

    private void spawnOrbsSet(){
        for(Orb orb: currentOrbs) removeObject(orb);
        currentOrbs.clear();
        if(remainingStates.isEmpty()) return;
        String targetState = remainingStates.get(Greenfoot.getRandomNumber(remainingStates.size()));
        currentTarget = targetState;
        spawnOrbAtState(targetState);
        List<String> decoys = new ArrayList<>(remainingStates);
        decoys.remove(targetState);
        Collections.shuffle(decoys);
        int decoyCount = Math.min(4, decoys.size());
        for(int i=0;i<decoyCount;i++) spawnOrbAtState(decoys.get(i));
        if(collectText != null) collectText.update(targetState);
    }

    public void collectOrb(Orb orb){
        long now = System.currentTimeMillis();
        if(now - orb.getSpawnTime() < 300) return;
        if(now - lastCollectTime < collectDelay) return;
        lastCollectTime = now;

        removeObject(orb);
        currentOrbs.remove(orb);

        if(orb.getStateName().equals(currentTarget)){
            score++;
            remainingStates.remove(orb.getStateName());
            StateMarker marker = findMarkerByName(orb.getStateName());
            if(marker != null) marker.collect();
            if(!remainingStates.isEmpty()) spawnOrbsSet();
            else endGame();
        } else {
            lives--;
            wrongCount++;
            if(wrongCount >= maxWrong || lives <= 0) endGame();
        }
    }
}

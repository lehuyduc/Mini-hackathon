package main.levels;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class LevelManager {

    private int currentLevel = 1;
    public static final int MAX_LEVEL = 50;
    private GameLevel[] levels = new GameLevel[MAX_LEVEL];

    private void init() {
        levels[1] = new level1();
    }

    public LevelManager() {
        currentLevel = 1;
        init();
    }

    private void nextLevel() {
        currentLevel++;
    }

    public void run() {
        if (levels[currentLevel].hasWon()) {nextLevel(); return;}
        if (levels[currentLevel].hasLose()) {
            levels[currentLevel].createLevel(); return;
        }
        levels[currentLevel].run();
    }
}

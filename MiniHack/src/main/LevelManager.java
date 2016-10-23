package main;

import controllers.EnemyControllerManager;
import controllers.WallControllerManager;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class LevelManager {

    private int currentLevel = 1;
    public static final int MAX_LEVEL = 50;

    public LevelManager() {
        currentLevel = 0;
    }

    private void newLevel() {
        EnemyControllerManager.instance.clear();
        WallControllerManager.instance.clear();
        for (int i=0;i<30;i++)
            for (int j=0;j<30;j++) GamePlay.wallDown[i][j] = GamePlay.wallRight[i][j] = false;
    }

    private void nextLevel() {
        currentLevel++;
    }

    public void run() {
        if (currentLevel==0) {
            currentLevel = 1;
            newLevel();
            GameLevel.instance.createLevel(1);
        }

        if (GameLevel.instance.hasWon()) {
            newLevel(); nextLevel();
            GameLevel.instance.createLevel(currentLevel);
        }

        if (GameLevel.instance.hasLose()) {
            newLevel();
            GameLevel.instance.createLevel(currentLevel);
        }

        GameLevel.instance.run();
    }

    public static final LevelManager instance = new LevelManager();
}

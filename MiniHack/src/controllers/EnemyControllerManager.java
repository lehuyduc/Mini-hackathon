package controllers;

import main.GamePlay;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class EnemyControllerManager extends ControllerManager {

    private EnemyControllerManager() {
        super();
    }

    private boolean finished() {
        for (SingleController singleController : singleControllers)
            if (singleController.isFighting || singleController.isFighting) return false;
        return true;
    }

    public void run() {
        if (!PlayerController.instance.finished()) return;
        for (SingleController singleController : singleControllers)
            singleController.run();
        if (finished()) GamePlay.playerTurn = true;
    }

    public static EnemyControllerManager instance = new EnemyControllerManager();
}

package controllers;

import main.GamePlay;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class MummyControllerManager extends ControllerManager {

    private MummyControllerManager() {
        super();
    }

    public void run() {
        for (SingleController singleController : singleControllers)
            singleController.run();
        GamePlay.playerTurn = true;
    }

    public static MummyControllerManager instance = new MummyControllerManager();
}

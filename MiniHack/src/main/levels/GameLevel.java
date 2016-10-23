package main.levels;

import controllers.MummyControllerManager;
import controllers.PlayerController;
import controllers.SingleController;
import main.Background;
import main.GamePlay;
import models.GameObject;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class GameLevel {

    protected int MAP_WIDTH = 6,
            MAP_HEIGHT = 6;
    protected int MAP_TOP = 1, MAP_LEFT = 1;
    protected Point[] wallDowns;
    protected boolean[][] wallDown;
    protected Point[] wallRights;
    protected boolean[][] wallRight;
    protected Image[][] backgroundTile;
    
    public GameLevel() {
        wallDowns = new Point[20];
        wallDown = GamePlay.wallDown;
        wallRights = new Point[20];
        wallRight = GamePlay.wallRight;
        backgroundTile = Background.backgroundTile;
    }


    public boolean hasLose() {
        Vector<SingleController> singleControllers = MummyControllerManager.instance.getManager();
        for (SingleController singleController : singleControllers) {
            GameObject go = singleController.getGameObject();
            if (go.getX()== PlayerController.instance.getX() &&
                    go.getY()==PlayerController.instance.getY()) return true;
        }
        return false;
    }

    public boolean hasWon() {
        if (PlayerController.instance.getX() == GamePlay.exitX &&
                PlayerController.instance.getY() == GamePlay.exitY) return true;
        return false;
    }


    void createLevel() {
        
    }
    
    void run() {
        
    }
}

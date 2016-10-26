package controllers;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class ControllerController {

    private ControllerController() {

    }

    public void init() {
        EnemyControllerManager.instance.clear();
        WallControllerManager.instance.clear();
        CollisionManager.instance.clear();
        PlayerController.instance.setX(-1);
        PlayerController.instance.setY(-1);
    }

    public void draw(Graphics g) {
        PlayerController.instance.draw(g);
        EnemyControllerManager.instance.draw(g);
        WallControllerManager.instance.draw(g);
    }

    public void run() {
        PlayerController.instance.run();
        EnemyControllerManager.instance.run();
        CollisionManager.instance.run();
        WallControllerManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}

package controllers;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class ControllerController {

    private ControllerController() {

    }

    public void init() {
        MummyControllerManager.instance.clear();
        PlayerController.instance.setX(-1);
        PlayerController.instance.setY(-1);
    }

    public void draw(Graphics g) {
        PlayerController.instance.draw(g);
        MummyControllerManager.instance.draw(g);
        WallControllerManager.instance.draw(g);
    }

    public void run() {
        PlayerController.instance.run();
        MummyControllerManager.instance.run();
        WallControllerManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}

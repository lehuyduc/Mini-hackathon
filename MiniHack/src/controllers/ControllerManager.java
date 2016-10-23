package controllers;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class ControllerManager implements BaseController {

    protected Vector<SingleController> singleControllers;

    public ControllerManager() {
        singleControllers = new Vector<>();
    }

    public Vector<SingleController> getManager() {
        return singleControllers;
    }

    public int size() {
        return singleControllers.size();
    }

    public void add(SingleController singleController) {
        singleControllers.add(singleController);
    }

    public void clear() {
        singleControllers.clear();
    }


    public void draw(Graphics g) {
        for (SingleController singleController : singleControllers)
            singleController.draw(g);
    }

    public void run() {
        for (SingleController singleController : singleControllers)
            singleController.run();
    }
}

package controllers;

import java.awt.*;
import java.util.Iterator;
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
        if (singleController instanceof Colliable) CollisionManager.instance.add((Colliable)singleController);
    }

    public void remove(SingleController sc) {
        singleControllers.remove(sc);
    }

    public void remove() {
        Iterator<SingleController> it = singleControllers.iterator();
        while (it.hasNext()) {
            SingleController sc = it.next();
            if (sc.deleteNow()) it.remove();
        }
    }

    public void clear() {
        singleControllers.clear();
    }


    public void draw(Graphics g) {
        for (SingleController singleController : singleControllers)
            singleController.draw(g);
    }

    //Sau mỗi lần run sẽ kiểm tra xem xóa phần tử đc chưa, nếu đc thì xóa
    public void run() {
        for (SingleController singleController : singleControllers)
            singleController.run();
        remove();
    }
}

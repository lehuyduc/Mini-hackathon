package controllers.movement;

import models.GameObject;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class MoveLeft extends Move {

    public void move(GameObject go) {
        go.setX(go.getX()-1);
    }
}

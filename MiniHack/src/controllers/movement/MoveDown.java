package controllers.movement;

import models.GameObject;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class MoveDown extends Move {

    public void move(GameObject go) {
        go.setY(go.getY() + 1);
    }
}

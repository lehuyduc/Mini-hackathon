package controllers;

import controllers.movement.MoveType;
import main.GameConfig;
import models.Explosion;
import models.GameObject;
import views.AnimationView;

/**
 * Created by Le Huy Duc on 27/10/2016.
 */
public class ExplosionController extends SingleControllerWithAnimation {

    private long firstExplosion = 0;

    public ExplosionController(int column,int row) {
        super(32);
        gameObject = new Explosion(column,row);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
        unitName = "explosion";
        moveType = MoveType.STAY;
        firstExplosion = System.currentTimeMillis();
    }

    public void moveAnimation() {

    }


    public boolean deleteNow() {
        long now = System.currentTimeMillis();
        return firstExplosion - now >= GameConfig.EXPLOSION_TIME;
    }
}

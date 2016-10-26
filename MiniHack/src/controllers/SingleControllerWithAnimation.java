package controllers;

import controllers.movement.Move;
import controllers.movement.MoveType;
import main.GamePlay;
import views.AnimationView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class SingleControllerWithAnimation extends SingleController {

    protected MoveType moveType;
    protected boolean initAnimation = false;
    protected AnimationView animationView;
    protected Point targetPoint, targetGrid;
    protected String unitName;


    public SingleControllerWithAnimation() {

    }

    public void initAnimation() {
        animationView.setSheet(unitName + "_" + MoveType.fileNameOf(moveType) + ".png",5);
    }

    public void moveAnimation() {
        if (!initAnimation) initAnimation();
        Move moveDirection = Move.create(moveType);
        moveDirection.move(gameObject);
        animationView.currentImage++;
        if (getX()==targetPoint.x && getY()==targetPoint.y) {
            isMoving = false;
            setColumn(targetGrid.x);
            setRow(targetGrid.y);
            initAnimation = false;
            GamePlay.playerTurn = false;
        }
    }
}

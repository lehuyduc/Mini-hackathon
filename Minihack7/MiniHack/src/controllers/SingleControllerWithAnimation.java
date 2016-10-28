package controllers;

import controllers.movement.Move;
import controllers.movement.MoveType;
import controllers.mummy.EnemyControllerWhite;
import main.GameConfig;
import views.AnimationView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class SingleControllerWithAnimation extends SingleController {

    protected MoveType moveType;
    protected boolean initAnimation = false;
    protected AnimationView animationView;
    protected Point beginPoint, targetPoint, targetGrid;
    protected String unitName;
    protected int nImage = 0;


    public SingleControllerWithAnimation(int nImage) {
        this.nImage = nImage;
    }

    public SingleControllerWithAnimation() {
    }

    public void initAnimation() {
        if (moveType!=MoveType.STAY)
            animationView.setSheet(unitName + "_" + MoveType.fileNameOf(moveType) + ".png",nImage);
        else animationView.setSheet(unitName + ".png",nImage);
    }

    public long lastImage = 0;
    public void moveAnimation() {
        if (!initAnimation) initAnimation();
        Move moveDirection = Move.create(moveType);
        moveDirection.move(gameObject);

        long now = System.currentTimeMillis();
        if (now - lastImage >= GameConfig.ANIMATION_DELAY) {
            animationView.currentImage++;
            lastImage = now;
        }

        //Nếu đã đi >= mục tiêu thì dừng
        boolean doneMoveHorizontal = (targetPoint.x != beginPoint.x &&
                (getX() - beginPoint.x) / (targetPoint.x - beginPoint.x) >= 1);

        boolean doneMoveVertical = (targetPoint.y != beginPoint.y &&
                (getY() - beginPoint.y) / (targetPoint.y - beginPoint.y) >= 1);
        if (this instanceof EnemyControllerWhite) {

        }
        if (doneMoveHorizontal || doneMoveVertical) {
            isMoving = false;
            setColumn(targetGrid.x);
            setRow(targetGrid.y);
            initAnimation = false;
        }
    }
}

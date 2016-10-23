package controllers;

import controllers.movement.Move;
import controllers.movement.MoveType;
import main.GameConfig;
import main.GamePlay;
import models.GameObject;
import models.Player;
import utilities.KeyInput;
import utilities.KeyInputListener;
import utilities.Utils;
import views.AnimationView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class PlayerController extends SingleController {

    private static int SIZEX = 35, SIZEY = 35;

    private KeyInput keyInput = new KeyInput();
    public KeyInputListener keyInputListener = new KeyInputListener(keyInput);
    long lastMove = System.currentTimeMillis();
    private MoveType moveType;
    private boolean initAnimation = false;
    private AnimationView animationView;
    private Point targetPoint, targetGrid;


    private PlayerController(int column, int row) {
        super();
        gameObject = new Player(column,row);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
    }

    static int counter = 0;
    public boolean tryMove(GameObject go,int x2,int y2) {
        int x1 = go.getColumn(), y1 = go.getRow();
        int sql = GameConfig.DEFAULT_TILE_LENGTH;
        if (Utils.canMoveTo(x1,y1,x2,y2)) {
            lastMove = System.currentTimeMillis();
            isMoving = true;
            animationView.firstImage = System.currentTimeMillis();
            targetPoint = new Point(x2*sql+sql/2,y2*sql+sql/2);
            targetGrid = new Point(x2,y2);
            return true;
        }
        return false;
    }

    public void move(GameObject go) {
        int x2 = go.getColumn(), y2 = go.getRow();
        if (keyInput.keyDown) {y2++; if (tryMove(go,x2,y2)) {moveType = MoveType.DOWN; return;}}
        if (keyInput.keyUp) {y2--; if (tryMove(go,x2,y2)) {moveType = MoveType.UP; return;}}
        if (keyInput.keyRight) {x2++; if (tryMove(go,x2,y2)) {moveType = MoveType.RIGHT; return;}}
        if (keyInput.keyLeft) {x2--; if (tryMove(go,x2,y2)) {moveType = MoveType.LEFT; return;}}
        if (keyInput.keySpace) {GamePlay.playerTurn = false; lastMove = System.currentTimeMillis();}
    }

    public void initAnimation() {
        if (moveType==MoveType.DOWN) animationView.setSheet("explorer_down.png",5);
        if (moveType==MoveType.RIGHT) animationView.setSheet("explorer_right.png",5);
        if (moveType==MoveType.LEFT) animationView.setSheet("explorer_left.png",5);
        if (moveType==MoveType.UP) animationView.setSheet("explorer_up.png",5);
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

    public boolean finished() {
        return !isMoving && !isFighting;
    }

    public void draw(Graphics g) {
        System.out.println(gameObject.getX() + " " + gameObject.getY());
        if (animationView.nImage==0) animationView.setSheet("explorer_right.png",5);
        if (isMoving) animationView.drawImage(g,gameObject);
        else animationView.drawImage(g,gameObject,true);
    }

    boolean moveTurn = true;
    public void run() {
        if (isMoving) {moveAnimation(); return;}
        if (!GamePlay.playerTurn) return;
        long now = System.currentTimeMillis();
        if (now - lastMove < 150) return;
        move(gameObject);
    }

    public static final PlayerController instance = new PlayerController(0,0);
}

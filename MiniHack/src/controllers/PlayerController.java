package controllers;

import main.GamePlay;
import models.GameObject;
import models.Player;
import utilities.KeyInput;
import utilities.KeyInputListener;
import utilities.Utils;
import views.ImageView;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class PlayerController extends SingleController {

    private static int SIZEX = 35, SIZEY = 35;

    private KeyInput keyInput = new KeyInput();
    public KeyInputListener keyInputListener = new KeyInputListener(keyInput);
    long lastMove = System.currentTimeMillis();

    public PlayerController(int column, int row) {
        super();
        gameObject = new Player(column,row);
        gameView = new ImageView("plane2.png");
    }

    static int counter = 0;
    public void tryMove(GameObject go,int x2,int y2) {
        int x1 = go.getColumn(), y1 = go.getRow();
        if (Utils.canMoveTo(x1,y1,x2,y2)) {
            go.setColumn(x2);
            go.setRow(y2);
            lastMove = System.currentTimeMillis();
            System.out.println(getColumn() + " " + getRow());
            GamePlay.playerTurn = false;
        }
    }

    public void move(GameObject go) {
        int x2 = go.getColumn(), y2 = go.getRow();
        if (keyInput.keyDown) {y2++; tryMove(go,x2,y2); return;}
        if (keyInput.keyUp) {y2--; tryMove(go,x2,y2); return;}
        if (keyInput.keyRight) {x2++; tryMove(go,x2,y2); return;}
        if (keyInput.keyLeft) {x2--; tryMove(go,x2,y2); return;}
    }



    boolean moveTurn = true;
    public void run() {
        if (!GamePlay.playerTurn) return;
        long now = System.currentTimeMillis();
        if (now - lastMove < 100) return;
        move(gameObject);
    }

    public static final PlayerController instance = new PlayerController(0,0);
}

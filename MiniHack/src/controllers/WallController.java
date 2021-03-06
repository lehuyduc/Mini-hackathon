package controllers;

import main.GameConfig;
import main.GamePlay;
import models.Wall;
import views.ImageView;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class WallController extends SingleController {

    private WallType wallType;

    public void checkDefault() {
        gameView.setImage("bullet.png");
        int sql = GamePlay.TILE_LENGTH;
        if (this.wallType == WallType.DOWN) {
            setWidth(GamePlay.TILE_LENGTH);
            setHeight(GamePlay.WALL_DOWN_HEIGHT);
            setX(sql * getColumn() + sql/2);
            setY(sql * getRow() + sql);
        }

        if (this.wallType == WallType.RIGHT) {
            setWidth(GamePlay.WALL_RIGHT_WIDTH);
            setHeight(GamePlay.TILE_LENGTH);
            setX(sql * getColumn() + sql);
            setY(sql * getRow() + sql/2);
        }
    }

    private WallController() {

    }

    private WallController(int column, int row, WallType wallType) {
        gameObject = new Wall(0,0);
        gameView = new ImageView("bullet.png");
        setColumn(column);
        setRow(row);
        this.wallType = wallType;
        checkDefault();
    }

    public static WallController create(int column,int row, WallType wallType) {
        return new WallController(column,row,wallType);
    }

    public void run() {

    }
}

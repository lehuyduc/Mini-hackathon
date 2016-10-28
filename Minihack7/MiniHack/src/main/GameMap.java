package main;

import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class GameMap {

    private int MAP_TILE_SIZE; // MAP_TILE_SIZE là số lượng ô trong bảng
    private int INNER_MAP_SIZE; // INNER_MAP_SIZE là chiều dài ô vuông bên trong, ko tính exit
    private int TILE_LENGTH;
    private int WALL_DOWN_HEIGHT;
    private int WALL_RIGHT_WIDTH;

    public static Image[][] backgroundTile = new Image[12][12];
    public static Image mapBackground;


    private GameMap() {
        init();
    }

    // gán lại các biến = các biến ở GamePlay
    public void getMapInfo() {
        MAP_TILE_SIZE = GameConfig.MAP_TILE_SIZE;
        TILE_LENGTH = GameConfig.TILE_LENGTH;
        INNER_MAP_SIZE = TILE_LENGTH * MAP_TILE_SIZE;
        WALL_DOWN_HEIGHT = GameConfig.DOWN_WALL_HEIGHT;
        WALL_RIGHT_WIDTH = GameConfig.RIGHT_WALL_WIDTH;
        mapBackground = Utils.getImage("floor" + MAP_TILE_SIZE + ".jpg");
    }

    public void init() {
        getMapInfo();
    }

    public void draw(Graphics g) {
        getMapInfo();
        int sql = TILE_LENGTH;
        g.drawImage(mapBackground,TILE_LENGTH,TILE_LENGTH, INNER_MAP_SIZE, INNER_MAP_SIZE,null);
    }

    public static final GameMap instance = new GameMap();
}

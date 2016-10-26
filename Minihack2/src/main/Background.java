package main;

import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class Background {
    public static final int MAX_WIDTH = GameConfig.MAX_MAP_WIDTH,
            MAX_HEIGHT = GameConfig.MAX_MAP_HEIGHT;
    
    private int MAP_WIDTH, MAP_HEIGHT;
    private int MAP_TOP, MAP_LEFT;
    private int TILE_LENGTH;
    private int WALL_DOWN_HEIGHT;
    private int WALL_RIGHT_WIDTH;

    public static Image[][] backgroundTile = new Image[MAX_WIDTH][MAX_HEIGHT];
    public static Image background;
    public static Image mapBackground;


    public Background() {
        init();
    }


    public void init() {
        MAP_WIDTH = GamePlay.MAP_WIDTH;
        MAP_HEIGHT = GamePlay.MAP_HEIGHT;
        MAP_TOP = GamePlay.MAP_TOP;
        MAP_LEFT = GamePlay.MAP_LEFT;
        TILE_LENGTH = GameConfig.DEFAULT_TILE_LENGTH;
        WALL_DOWN_HEIGHT = GameConfig.DEFAULT_DOWN_WALL_HEIGHT;
        WALL_RIGHT_WIDTH = GameConfig.DEFAULT_RIGHT_WALL_WIDTH;

        background = Utils.getImage("background.png");
        mapBackground = Utils.getImage("floor" + "" + GamePlay.MAP_WIDTH + ".jpg");

        for (int i=0;i<MAX_WIDTH;i++)
            for (int j=0;j<MAX_HEIGHT;j++) backgroundTile[i][j] = null;
    }

    public void draw(Graphics g) {
        int sql = GamePlay.TILE_LENGTH;
        g.drawImage(background,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);
        if (GameWindow.gameState==GameState.GAME)
                g.drawImage(mapBackground,MAP_TOP*sql,MAP_LEFT*sql,sql*MAP_WIDTH,sql*MAP_HEIGHT,null);

        for (int column=0;column<=MAP_WIDTH+1;column++)
            for (int row=0;row<=MAP_HEIGHT+1;row++) {
                sql = GamePlay.TILE_LENGTH;
                int x = column + MAP_LEFT - 1;
                int y = row + MAP_TOP - 1;
                int cornerX = x * sql;
                int cornerY = y * sql;
                if (backgroundTile[column][row]==null) continue;

                g.drawImage(backgroundTile[column][row],cornerX,cornerY,sql,sql,null);
            }
    }

    public static final Background instance = new Background();
}

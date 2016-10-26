package main;

import controllers.*;
import utilities.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class GameLevel {

    protected int MAP_WIDTH = 6,
            MAP_HEIGHT = 6;
    protected int MAP_TOP = 1, MAP_LEFT = 1;
    protected Point[] wallDowns;
    protected boolean[][] wallDown;
    protected Point[] wallRights;
    protected boolean[][] wallRight;
    protected Image[][] backgroundTile;
    protected EnemyController[] mummyControllers = new EnemyController[20];
    public static final int[] dx = {0,0,0,1,-1,-1,1,-1,1};
    public static final int[] dy = {0,1,-1,0,0,-1,-1,1,1};

    private int exitX = -124443, exitY = 123123;
    private Scanner input;
    
    public GameLevel() {
        wallDowns = new Point[20];
        wallDown = GamePlay.wallDown;
        wallRights = new Point[20];
        wallRight = GamePlay.wallRight;
        backgroundTile = GameMap.backgroundTile;
    }

    private void levelSetting() {
        MAP_WIDTH = input.nextInt();
        MAP_HEIGHT = input.nextInt();
        MAP_LEFT = input.nextInt();
        MAP_TOP = input.nextInt();
        GamePlay.MAP_TILE_WIDTH = MAP_WIDTH;
        GamePlay.MAP_TILE_HEIGHT = MAP_HEIGHT;
        GamePlay.MAP_TILE_LEFT = MAP_LEFT;
        GamePlay.MAP_TILE_TOP = MAP_TOP;
        GamePlay.TILE_LENGTH = 360 / MAP_WIDTH;
    }

    private void createPlayer() {
        int x,y;
        x = input.nextInt();
        y = input.nextInt();
        PlayerController.instance.setColumn(x+MAP_LEFT-1);
        PlayerController.instance.setRow(y+MAP_TOP-1);
        PlayerController.instance.setIsAlive(true);
        PlayerController.instance.setHealth(1);
        GamePlay.playerTurn = true;
    }

    private void createExit() {
        exitX = input.nextInt();
        exitY = input.nextInt();
        GamePlay.exitX = exitX + MAP_LEFT - 1;
        GamePlay.exitY = exitY + MAP_TOP - 1;

        int x = 0,y = 0;
        if (exitY==0) x = 0*55 + 1;
        if (exitX==MAP_WIDTH+1) x = 1*55 + 2;
        if (exitY==MAP_HEIGHT+1) x = 2*55 + 3;
        if (exitX==0) x = 3*55 + 4;

        BufferedImage exitSprite = Utils.getImage("stairs6.gif");
        backgroundTile[exitX][exitY] = exitSprite.getSubimage(x,y,55,55);
    }

    private boolean nextToExit(int column0,int row0) {
        int column,row;
        for (int i=0;i<=4;i++) {
            column = column0 + dx[i];
            row = row0 + dy[i];
            if (column==GamePlay.exitX && row==GamePlay.exitY) return true;
        }
        return false;
    }

    private void createMapWall() {
        int x,y,noX,noY;
        WallType noType;
        // noX ... là vị trí và kiểu của ô cạnh exit

        for (int i=1;i<=MAP_WIDTH;i++) {
            x = i + MAP_LEFT - 1;
            y = 0 + MAP_TOP - 1;
            if (!nextToExit(x,y)) WallControllerManager.instance.add(WallController.create(x,y,WallType.DOWN));
            y = MAP_HEIGHT  + MAP_TOP - 1;
            if (!nextToExit(x,y)) WallControllerManager.instance.add(WallController.create(x,y,WallType.DOWN));
        }

        for (int j=1;j<=MAP_HEIGHT;j++) {
            x = 0 + MAP_LEFT - 1;
            y = j + MAP_TOP - 1;
            if (!nextToExit(x,y)) WallControllerManager.instance.add(WallController.create(x,y,WallType.RIGHT));
            x = MAP_WIDTH + MAP_LEFT - 1;
            if (!nextToExit(x,y)) WallControllerManager.instance.add(WallController.create(x,y,WallType.RIGHT));
        }
    }

    private void createWallDown() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallDowns[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallDowns[i].x + MAP_LEFT - 1;
            int y = wallDowns[i].y + MAP_TOP - 1;
            wallDown[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.DOWN));
        }
    }

    private void createWallRight() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallRights[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallRights[i].x + MAP_LEFT - 1;
            int y = wallRights[i].y + MAP_TOP - 1;
            wallRight[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.RIGHT));
        }
    }

    private void createMummy() {
        int n, x, y;
        String type;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            x = input.nextInt();
            y = input.nextInt();
            type = input.next();
            if (MummyType.valueOf(type)==MummyType.WHITE)
                mummyControllers[i] = EnemyControllerWhite.create(x,y);
            if (MummyType.valueOf(type)==MummyType.RED)
                mummyControllers[i] = EnemyControllerRed.create(x,y);
            if (MummyType.valueOf(type)==MummyType.SCORPION)
                mummyControllers[i] = EnemyControllerScorpion.create(x,y);
        }

        for (int i=1;i<=n;i++) {
            EnemyController mummy = mummyControllers[i];
            mummy.setColumn(mummy.getColumn()+MAP_LEFT-1);
            mummy.setRow(mummy.getRow()+MAP_TOP-1);
            EnemyControllerManager.instance.add(mummy);
        }
    }

    public void createLevel(int level) {
        try {
            this.input = new Scanner(new File("map/" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GameMap.instance.init();
        levelSetting();
        createPlayer();
        createExit();
        createMapWall();
        createWallDown();
        createWallRight();
        createMummy();
    }



    public boolean hasLose() {
        return PlayerController.instance.getHealth() <= 0;
    }

    public boolean hasWon() {
        if (PlayerController.instance.getColumn() == GamePlay.exitX &&
                PlayerController.instance.getRow() == GamePlay.exitY) return true;
        return false;
    }

    public void draw() {

    }

    void run() {
        
    }

    public static final GameLevel instance = new GameLevel();
}

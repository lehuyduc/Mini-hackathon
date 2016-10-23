package main.levels;

import controllers.*;
import main.Background;
import main.GamePlay;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class level1 extends GameLevel {

    private boolean hasInit = false;

    private void levelSetting() {
        MAP_WIDTH = 6;
        MAP_HEIGHT = 6;
        MAP_TOP = 1; MAP_LEFT = 1;
    }

    private void createWallDown() {
        int n = 4;
        wallDowns[1] = new Point(2,4);
        wallDowns[2] = new Point(3,1);
        wallDowns[3] = new Point(5,2);
        wallDowns[4] = new Point(6,4);
        for (int i=1;i<=n;i++) {
            int x = wallDowns[i].x + MAP_LEFT - 1;
            int y = wallDowns[i].y + MAP_TOP - 1;
            wallDown[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.DOWN));
        }
    }

    private void createWallRight() {
        int m = 5;
        wallRights[1] = new Point(1,3);
        wallRights[2] = new Point(2,5);
        wallRights[3] = new Point(3,2);
        wallRights[4] = new Point(4,3);
        wallRights[5] = new Point(5,5);
        for (int i=1;i<=m;i++) {
            int x = wallRights[i].x + MAP_LEFT - 1;
            int y = wallRights[i].y + MAP_TOP - 1;
            wallRight[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.RIGHT));
        }
    }

    private void createMummy() {
        int x,y;
        x = 2+MAP_LEFT-1; y = 6+MAP_TOP-1;
        MummyControllerManager.instance.add(MummyController.create(x,y));
    }

    public void createLevel() {
        hasInit = true;

        levelSetting();
        GamePlay.MAP_WIDTH = MAP_WIDTH;
        GamePlay.MAP_HEIGHT = MAP_HEIGHT;

        createWallDown();
        createWallRight();
        createMummy();

        Background.instance.init();
        int exitX = MAP_LEFT + MAP_WIDTH;
        int exitY = MAP_TOP + 2;
        backgroundTile[exitX][exitY] = Utils.getImage("power-up.png");
        GamePlay.exitX = exitX;
        GamePlay.exitY = exitY;
    }

    public void run() {
        if (!hasInit) createLevel();
    }
}

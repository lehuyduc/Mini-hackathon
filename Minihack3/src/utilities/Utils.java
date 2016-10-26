package utilities;

import main.GamePlay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Utils {

    public static BufferedImage getImage(String link) {
        BufferedImage res = null;
        try {
            res = ImageIO.read(new File("images/" + link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Scanner getScanner(String link) {
        Scanner res = null;
        try {
            res = new Scanner(new File(link));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean insideMap(int column,int row) {
        int column0 = GamePlay.MAP_TILE_LEFT, row0 = GamePlay.MAP_TILE_TOP;
        int width = GamePlay.MAP_TILE_WIDTH, height = GamePlay.MAP_TILE_HEIGHT;
        return (column>=column0 && row>=row0 && column<column0+width && row<row0+height);
    }

    public static boolean hasWall(int x1,int y1,int x2,int y2) {
        if (!insideMap(x2,y2)) return true;
        if (x1==x2) {
            if (y1<y2) return GamePlay.wallDown[x1][y1];
            else return GamePlay.wallDown[x2][y2];
        }
        if (y1==y2) {
            if (x1<x2) return GamePlay.wallRight[x1][y1];
            else return GamePlay.wallRight[x2][y2];
        }
        return true;
    }

    public static boolean canMoveTo(int column1,int row1,int column2,int row2) {
        if (column2==GamePlay.exitX && row2==GamePlay.exitY) return true;
        return !hasWall(column1,row1,column2,row2) && insideMap(column2,row2);
    }

    public static int manDistance(int x1,int y1,int x2,int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
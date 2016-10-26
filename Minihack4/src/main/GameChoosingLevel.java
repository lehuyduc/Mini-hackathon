package main;

import utilities.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class GameChoosingLevel extends Frame implements GameGame {

    private Image background = null;

    public void init() {

        background = Utils.getImage("adventuremap.jpg");

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private GameChoosingLevel() {
        init();
    }

    public void draw(Graphics g) {
        g.drawImage(background,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);
    }

    public void run() {

    }

    public static GameChoosingLevel instance = new GameChoosingLevel();
}

package main;

import controllers.*;
import main.GameConfig;
import models.Player;
import utilities.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameWindow extends Frame implements Runnable {

    private int BACKGROUND_WIDTH = GameConfig.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = GameConfig.BACKGROUND_HEIGHT;
    public static  GameState gameState = GameState.MENU;

    BufferedImage backBufferImage = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,BufferedImage.TYPE_INT_RGB);



    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addKeyListener(PlayerController.instance.keyInputListener);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER && gameState==GameState.MENU) {
                    gameState = GameState.CHOOSING_LEVEL;
                    return;
                }
                if (e.getKeyCode()==KeyEvent.VK_ENTER && gameState==GameState.CHOOSING_LEVEL) {
                    gameState = GameState.GAME;
                    GamePlay.instance.init();
                    return;
                }
                if (e.getKeyCode()== KeyEvent.VK_ESCAPE && gameState==GameState.GAME)
                    gameState = GameState.CHOOSING_LEVEL;
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
//

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();

        if (gameState==GameState.MENU) GameMenu.instance.draw(backBufferGraphics);
        if (gameState==GameState.CHOOSING_LEVEL) GameChoosingLevel.instance.draw(backBufferGraphics);
        if (gameState==GameState.GAME) GamePlay.instance.draw(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(25);

                if (gameState==GameState.MENU) GameMenu.instance.run();
                if (gameState==GameState.CHOOSING_LEVEL) GameChoosingLevel.instance.run();
                if (gameState==GameState.GAME) GamePlay.instance.run();

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

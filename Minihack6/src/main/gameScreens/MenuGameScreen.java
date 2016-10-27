package main.gameScreens;

import controllers.SingleController;
import main.GameConfig;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class MenuGameScreen extends GameScreen{

    private Image background;

    public MenuGameScreen(ScreenManager screenManager) {
        super(screenManager);
        init();
    }

    @Override
    public void init() {
        background = Utils.getImage("menuback.jpg");
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background,0,0, GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);
    }

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            this.screenManager.change(new PlayGameScreen(screenManager),true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

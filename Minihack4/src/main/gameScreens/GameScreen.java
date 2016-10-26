package main.gameScreens;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public abstract class GameScreen implements KeyListener, MouseListener, MouseMotionListener{
    protected ScreenManager screenManager;
    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    abstract void init();
    abstract void update(Graphics g);
    abstract void run();
}

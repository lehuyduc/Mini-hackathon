package main;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameConfig {
    public static final int DEFAULT_TILE_LENGTH = 60;
    public static final int BACKGROUND_WIDTH = 640,
                            BACKGROUND_HEIGHT = 480;
    public static final int DEFAULT_MAP_SIZE = 480; // đã tính viền ngoài chứa exit
    public static final int DEFAULT_DOWN_WALL_HEIGHT = 15;
    public static final int DEFAULT_RIGHT_WALL_WIDTH = 15;
    public static final int DEFAULT_EXPLOSION_TIME = 1000; // ms


    public static int TILE_LENGTH = DEFAULT_TILE_LENGTH;
    public static int MAP_SIZE = DEFAULT_MAP_SIZE;
    public static int RIGHT_WALL_WIDTH = DEFAULT_RIGHT_WALL_WIDTH;
    public static int DOWN_WALL_HEIGHT = DEFAULT_DOWN_WALL_HEIGHT;
    public static int MAP_TILE_SIZE = 6;
    public static int EXPLOSION_TIME = DEFAULT_EXPLOSION_TIME;


    public static final int THREAD_DELAY = 18;
    public static final int ANIMATION_DELAY = 100;
}

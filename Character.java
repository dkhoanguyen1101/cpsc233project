import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Character implements Common {
    private static final int SPEED = 4;
    public static final double PROB_MOVE = 0.02;

    private static BufferedImage image;
    private int id;

    // character's position (unit: tile)
    private int x, y;
    // character's position (unit: pixel)
    private int px, py;

    // character's direction (LEFT, RIGHT, UP or DOWN)
    private int direction;
    // character's animation counter
    private int count;

    private boolean isMoving;
    private int moveLength;

    private int moveType;

    // reference to Map
    private Map map;

    public Character(int x, int y, int id, int direction,
                     int moveType, Map map) {
        // init character
        this.x = x;
        this.y = y;
        px = x * CS;
        py = y * CS;
        this.id = id;
        this.direction = direction;
        this.moveType = moveType;
        this.map = map;

        count = 0;

        if (image == null) {
            loadImage("image/character.gif");
        }
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        int cx = (id % 8) * (CS * 2);
        int cy = (id / 8) * (CS * 4);
        // switch image based on animation counter
        g.drawImage(image,
                    LAYOUT_LENGTH + px - offsetX,
                    py - offsetY,
                    LAYOUT_LENGTH + px - offsetX + CS,
                    py - offsetY + CS,
                    cx + count * CS,
                    cy + direction * CS,
                    cx + CS + count * CS,
                    cy + direction * CS + CS,
                    null);
    }

    public boolean move() {
        switch (direction) {
        case LEFT:
            if (moveLeft()) {
                // return true if pixel-based scrolling is completed.
                return true;
            }
            break;
        case RIGHT:
            if (moveRight()) {
                return true;
            }
            break;
        case UP:
            if (moveUp()) {
                return true;
            }
            break;
        case DOWN:
            if (moveDown()) {
                return true;
            }
            break;
        }

        return false;
    }

    private boolean moveLeft() {
        int nextX = x - 1;
        int nextY = y;
        if (nextX < 0) {
            nextX = 0;
        }
        if (!map.isBound(nextX, nextY)) {
            px -= Character.SPEED;
            if (px < 0) {
                px = 0;
            }
            moveLength += Character.SPEED;
            if (moveLength >= CS) {
                // hero moves to left tile
                x--;
                px = x * CS;
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
            px = x * CS;
            py = y * CS;
        }
        return false;
    }

    private boolean moveRight() {
        int nextX = x + 1;
        int nextY = y;
        if (nextX > BOARD_COLS - 1) {
            nextX = BOARD_COLS - 1;
        }
        if (!map.isBound(nextX, nextY)) {
            px += Character.SPEED;
            if (px > BOARD_WIDTH - CS){
                px = BOARD_WIDTH - CS;
            }
            moveLength += Character.SPEED;
            if (moveLength >= CS) {
                x++;
                px = x * CS;
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
            px = x * CS;
            py = y * CS;
        }

        return false;
    }

    private boolean moveUp() {
        int nextX = x;
        int nextY = y - 1;
        if (nextY < 0) {
            nextY = 0;
        }
        if (!map.isBound(nextX, nextY)) {
            py -= Character.SPEED;
            if (py < 0) {
                py = 0;
            }
            moveLength += Character.SPEED;
            if (moveLength >= CS) {
                y--;
                py = y * CS;
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
            px = x * CS;
            py = y * CS;
        }
        return false;
    }

    private boolean moveDown() {
        int nextX = x;
        int nextY = y + 1;
        if (nextY > BOARD_ROWS - 1) {
            nextY = BOARD_ROWS - 1;
        }
        if (!map.isBound(nextX, nextY)) {
            py += Character.SPEED;
            if (py > BOARD_HEIGHT - CS){
                py = BOARD_HEIGHT - CS;
            }
            moveLength += Character.SPEED;
            if (moveLength >= CS) {
                y++;
                py = y * CS;
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
            px = x * CS;
            py = y * CS;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPX() {
        return px;
    }

    public int getPY() {
        return py;
    }

    public void setDirection(int dir) {
        direction = dir;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean flag) {
        isMoving = flag;
        moveLength = 0;
    }

    public int getMoveType() {
        return moveType;
    }

    private void loadImage(String filename) {
        try {
            image = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

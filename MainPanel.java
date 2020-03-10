import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainPanel extends JPanel implements MouseListener, Runnable, Common {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 20ms/frame = 50fps
    private static final int PERIOD = 20;

    // map list
    private Map maps;
    // current map number

    // our hero!
    private Character hero;

    // action Arrows
    private ActionMouse leftArrow;
    private ActionMouse rightArrow;
    private ActionMouse upArrow;
    private ActionMouse downArrow;

    private Thread gameLoop;

    // double buffering
    private Graphics dbg;
    private Image dbImage = null;

    public MainPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, BOARD_HEIGHT));

        setFocusable(true);
        addMouseListener(this);

        // create action Arrows
        leftArrow = new ActionMouse();
        rightArrow = new ActionMouse();
        upArrow = new ActionMouse();
        downArrow = new ActionMouse();

        // create map
        maps = new Map(this);
        // create character
        hero = new Character(6, 6, 0, DOWN, 0, maps);

        // add characters to the map
        maps.addCharacter(hero);
        
        // start game loop
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void run() {
        long beforeTime, timeDiff, sleepTime;

        beforeTime = System.currentTimeMillis();
        while (true) {
            checkInput();
            gameUpdate();
            gameRender();
            printScreen();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleepTime = PERIOD - timeDiff;
            // sleep at least 5ms
            if (sleepTime <= 0) {
                sleepTime = 5;
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            beforeTime = System.currentTimeMillis();
        }
    }

    private void checkInput() {
        mainWindowCheckInput();
    }

    private void gameUpdate() {
            heroMove();
     //       characterMove();
    }

    private void gameRender() {
        if (dbImage == null) {
            // buffer image
            dbImage = createImage(PANEL_WIDTH, BOARD_HEIGHT);
            if (dbImage == null) {
                return;
            } else {
                // device context of buffer image
                dbg = dbImage.getGraphics();
            }
        }

        dbg.setColor(Color.WHITE);
        dbg.fillRect(0, 0, PANEL_WIDTH, BOARD_HEIGHT);

        // calculate offset so that the hero is in the center of a screen.
        int offsetX = hero.getPX() - PANEL_WIDTH / 2;
        // do not scroll at the edge of the map
        if (offsetX < 0) {
            offsetX = 0;
        } else if (offsetX > BOARD_WIDTH - PANEL_WIDTH) {
            offsetX = BOARD_WIDTH - PANEL_WIDTH;
        }

        int offsetY = hero.getPY() - BOARD_HEIGHT / 2;
        // do not scroll at the edge of the map
        if (offsetY < 0) {
            offsetY = 0;
        } else if (offsetY > BOARD_HEIGHT - BOARD_HEIGHT) {
            offsetY = BOARD_HEIGHT - BOARD_HEIGHT;
        }

        // draw map
        maps.draw(dbg, offsetX, offsetY);
    }

    private void printScreen() {
        Graphics g = getGraphics();
        if ((g != null) && (dbImage != null)) {
            g.drawImage(dbImage, 0, 0, null);
        }
        Toolkit.getDefaultToolkit().sync();
        if (g != null) {
            g.dispose();
        }
    }

    private void mainWindowCheckInput() {
        if (leftArrow.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(LEFT);
                hero.setMoving(true);
            }
        }

        if (rightArrow.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(RIGHT);
                hero.setMoving(true);
            }
        }

        if (upArrow.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(UP);
                hero.setMoving(true);
            }
        }

        if (downArrow.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(DOWN);
                hero.setMoving(true);
            }
        }

    }

    private void heroMove() {
        if (hero.isMoving()) {
            if (hero.move()) {
                Event event = maps.checkEvent(hero.getX(), hero.getY());
                if (event instanceof MoveEvent) {
                    // move to another map
                    MoveEvent m = (MoveEvent)event;
                    maps.removeCharacter(hero);
                    hero = new Character(m.destX, m.destY, 0, DOWN, 0, maps);
                    maps.addCharacter(hero);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int pos_x = e.getX() - LAYOUT_LENGTH;
        int pos_y = e.getY();
        int hero_x =hero.getX();
        int hero_y = hero.getY();
        if (pos_x > hero_x * CS && pos_x < (hero_x + 1) * CS){
            if (pos_y > (hero_y + 1) * CS && pos_y < (hero_y + 2) * CS) {
                downArrow.press();
            } else if (pos_y > (hero_y - 1) * CS && pos_y < hero_y * CS){
                upArrow.press();
            }
        }

        if (pos_y > hero_y * CS && pos_y < (hero_y + 1) * CS){
            if (pos_x > (hero_x + 1) * CS && pos_x < (hero_x + 2) * CS) {
                rightArrow.press();
            } else if (pos_x > (hero_x - 1) * CS && pos_x < hero_x * CS){
                leftArrow.press();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        int pos_x = e.getX() - LAYOUT_LENGTH;
        int pos_y = e.getY();
        int hero_x =hero.getX();
        int hero_y = hero.getY();
        if (pos_x > hero_x * CS && pos_x < (hero_x + 1) * CS){
            if (pos_y > (hero_y + 1) * CS && pos_y < (hero_y + 2) * CS) {
                downArrow.release();
            } else if (pos_y > (hero_y - 1) * CS && pos_y < hero_y * CS){
                upArrow.release();
            }
        }

        if (pos_y > hero_y * CS && pos_y < (hero_y + 1) * CS){
            if (pos_x > (hero_x + 1) * CS && pos_x < (hero_x + 2) * CS) {
                rightArrow.release();
            } else if (pos_x > (hero_x - 1) * CS && pos_x < hero_x * CS){
                leftArrow.release();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }
}
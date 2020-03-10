import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;

public class Map implements Common {


    // chip set
    private static BufferedImage boardImage;
    private static BufferedImage layoutImage;

    // characters in this map
    private final Vector<Character> characters = new Vector<Character>();
    // events in this map
    private final Vector<Event> events = new Vector<Event>();

    // reference to MainPanel
    private MainPanel panel;

    public Map(final MainPanel panel) {
        boardImage = loadImage("image/board.jpeg");
        layoutImage = loadImage("image/layout.jpg");
    }

    public void draw(final Graphics g, final int offsetX, final int offsetY) {
        g.drawImage(boardImage, LAYOUT_LENGTH, 0, PANEL_WIDTH, BOARD_HEIGHT, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, panel);
        g.drawImage(layoutImage, 0, 0, LAYOUT_LENGTH, BOARD_HEIGHT, 0, 0, layoutImage.getWidth(),
                layoutImage.getHeight(), panel);
        // draw characters in this map
        final Character c = characters.get(characters.size() - 1);
        c.draw(g, offsetX, offsetY);
    }

    public boolean isBound(final int x, final int y) {

        // Are there other characters?
        for (int i = 0; i < characters.size(); i++) {
            final Character c = characters.get(i);
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void addCharacter(final Character c) {
        characters.add(c);
    }

    public void removeCharacter(final Character c) {
        characters.remove(c);
    }

    // is there a character in (x, y) ?
    public Character checkCharacter(final int x, final int y) {
        for (int i = 0; i < characters.size(); i++) {
            final Character c = characters.get(i);
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    public Event checkEvent(final int x, final int y) {
        for (int i = 0; i < events.size(); i++) {
            final Event event = events.get(i);
            if (event.x == x && event.y == y) {
                return event;
            }
        }
        return null;
    }

    public Vector<Character> getCharacters() {
        return characters;
    }

    private BufferedImage loadImage(final String filename) {
        try {
            final BufferedImage image = ImageIO.read(getClass().getResource(filename));
            return image;
        } catch (final IOException e) {
            return null;
        }
    }
}

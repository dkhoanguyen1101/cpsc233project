public interface Common {
    // direction
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    // chipset size (pixel)
    public static final int CS = 32;
    public static final int LAYOUT_LENGTH = 208;
    public static final int BOARD_COLS = 16;
    public static final int BOARD_ROWS = 16;
    public static final int BOARD_WIDTH = BOARD_COLS*CS;
    public static final int BOARD_HEIGHT = BOARD_ROWS*CS;
    public static final int PANEL_WIDTH = LAYOUT_LENGTH + BOARD_WIDTH;
}

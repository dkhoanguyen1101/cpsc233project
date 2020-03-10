public class MoveEvent extends Event {
    public int destMapNo;
    public int destX;
    public int destY;

    public MoveEvent(int x, int y, int chipNo, int destX, int destY) {
        super(x, y, chipNo, false);
        this.destX = destX;
        this.destY = destY;
    }
}

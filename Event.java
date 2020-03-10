public abstract class Event {
    protected int x;
    protected int y;
    protected int id;
    protected boolean isBound;

    public Event(int x, int y, int id, boolean isBound) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.isBound = isBound;
    }
}

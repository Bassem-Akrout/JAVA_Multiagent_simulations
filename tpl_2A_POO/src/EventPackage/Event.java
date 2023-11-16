package EventPackage;

public abstract class Event {
    private final long date;

    public Event(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public abstract void execute();
}
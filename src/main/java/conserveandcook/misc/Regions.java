package conserveandcook.misc;

public enum Regions {
    AMERICA(3),
    ASIA(2),
    EUROPE(1);

    private final int id;

    Regions(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

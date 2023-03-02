package model;

public class ToyAdditional {
    private ToyType type;
    private int count;

    public ToyAdditional(ToyType type, int count) {
        this.type = type;
        this.count = count;
    }

    public ToyType getType() {
        return type;
    }

    public void setType(ToyType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

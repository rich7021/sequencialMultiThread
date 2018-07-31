package rifu.demo.sequencialMultiThread.enumeration;

public enum Actions {
    WASHING(1), WAXING(0);

    private int value;

    Actions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

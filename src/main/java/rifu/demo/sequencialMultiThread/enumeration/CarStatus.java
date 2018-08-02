package rifu.demo.sequencialMultiThread.enumeration;

public enum CarStatus {
    MAINTAIN, WASH, WAX, FINISH;

    public static CarStatus getInitStatus() {
        return WASH;
    }

    public static CarStatus getNextStatus(CarStatus currentStatus) {
        switch (currentStatus) {
            case MAINTAIN:
                return WASH;
            case WASH:
                return WAX;
            case WAX:
                return WASH;
        }
        return FINISH;
    }

}

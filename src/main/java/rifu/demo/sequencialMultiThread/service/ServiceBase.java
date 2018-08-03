package rifu.demo.sequencialMultiThread.service;

public abstract class ServiceBase implements Runnable {

    protected Shop shop;
    protected boolean isRunning = true;

    public ServiceBase(Shop shop) {
        this.shop = shop;
    }

    public abstract void terminate();

}

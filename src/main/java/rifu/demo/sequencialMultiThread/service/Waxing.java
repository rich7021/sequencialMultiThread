package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.enumeration.CarStatus;

public class Waxing implements Runnable {

    private Services services;
    private boolean isRunning = true;

    public Waxing(Services services) {
        this.services = services;
    }

    @Override
    public void run() {
        while (isRunning) {
            Car carToBeServiced = services.getNextServiceCar(CarStatus.WAX);
            try {
                if (carToBeServiced != null) {
                    services.wax(carToBeServiced);
                    //                    services.finish(carToBeServiced);
                    services.waitNext(carToBeServiced);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminate() {
        this.isRunning = false;
    }
}

package rifu.demo.sequencialMultiThread.entity;

import rifu.demo.sequencialMultiThread.enumeration.CarStatus;

public class Car {
    private CarStatus status = CarStatus.getInitStatus();
    private String name;

    public Car(String name, CarStatus status) {
        init(name, status);
    }

    private void init(String name, CarStatus status) {
        if (name == null || name.trim().equals("")) {
            throw new RuntimeException("Car name cannot be blank");
        }
        this.name = name;
        this.status = (status != null) ? status : this.status;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

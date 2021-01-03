package nikolay.romanov.models;

import java.util.StringJoiner;

public abstract class Car {
    private CarType carType;
    private String brand;
    private String model;
    private double enginePower;
    private int price;

    public Car(CarType carType, String brand, String model, double enginePower, int price) {
        this.carType = carType;
        this.brand = brand;
        this.model = model;
        this.enginePower = enginePower;
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add(brand)
                .add(model)
                .toString();
    }
}

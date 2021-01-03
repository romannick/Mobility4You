package nikolay.romanov.models;

import java.util.StringJoiner;

public class HybridCar extends Car {
    private long batteryCapacity;
    private double engineDisplacement;

    public HybridCar(CarType carType, String brand, String model, double enginePower, int price, long batteryCapacity, double engineDisplacement) {
        super(carType, brand, model, enginePower, price);
        this.batteryCapacity = batteryCapacity;
        this.engineDisplacement = engineDisplacement;
    }

    public long getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(long batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(double engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getCarType().getName() + " ", "")
                .add(super.toString())
                .add(engineDisplacement + "L")
                .add(this.getEnginePower() + "KW")
                .add(batteryCapacity + "Ah")
                .add(this.getPrice() + " euro")
                .toString();
    }
}

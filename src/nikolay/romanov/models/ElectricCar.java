package nikolay.romanov.models;

import java.util.StringJoiner;

public class ElectricCar extends Car {
    private long batteryCapacity;

    public ElectricCar(CarType carType, String brand, String model, double enginePower, int price, long batteryCapacity) {
        super(carType, brand, model, enginePower, price);
        this.batteryCapacity = batteryCapacity;
    }

    public long getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(long batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getCarType().getName() + " ", "")
                .add(super.toString())
                .add(this.getEnginePower() + "KW")
                .add(batteryCapacity + "Ah")
                .add(this.getPrice() + " euro")
                .toString();
    }
}

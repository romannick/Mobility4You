package nikolay.romanov.models;

import java.util.StringJoiner;

public class GasCar extends Car {
    private double engineDisplacement;

    public GasCar(CarType carType, String brand, String model, double enginePower, int price, double engineDisplacement) {
        super(carType, brand, model, enginePower, price);
        this.engineDisplacement = engineDisplacement;
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
                .add(this.getPrice() + " euro")
                .toString();
    }
}

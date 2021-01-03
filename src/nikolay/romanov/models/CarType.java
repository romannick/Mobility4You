package nikolay.romanov.models;

public enum CarType {
    ELECTRIC_CAR("ELECTRIC_CAR", 0),
    GAS_CAR("GAS_CAR", 1),
    HYBRID_CAR("HYBRID_CAR", 2);

    private final String name;
    private final int value;

    CarType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static CarType findByName(String name) {
        for (CarType carType : values()) {
            if (carType.getName().equals(name)) {
                return carType;
            }
        }

        return null;
    }
}

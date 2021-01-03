package nikolay.romanov.models;

public enum MenuOption {
    SHOW_CATALOGUE(1),
    ADD_ELECTRIC_CAR(2),
    ADD_GAS_POWERED_CAR(3),
    ADD_HYBRID_CAR(4),
    SHOW_CATALOGUE_SORTED_CAR_TYPE(5),
    SHOW_CATALOGUE_SORTED_BRAND(6),
    WRITE_TO_FILE(7),
    STOP_THE_PROGRAM(8);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOption findByValue(int value) {
        for (MenuOption menuOption : values()) {
            if (menuOption.getValue() == value) {
                return menuOption;
            }
        }

        return null;
    }
}

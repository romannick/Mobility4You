package nikolay.romanov;

import nikolay.romanov.models.Car;
import nikolay.romanov.models.CarType;
import nikolay.romanov.models.ElectricCar;
import nikolay.romanov.models.GasCar;
import nikolay.romanov.models.HybridCar;
import nikolay.romanov.models.MenuOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * @author Nikolay Romanov
 */

public class Main {

    public static void main(String[] args) {
        Scanner menuOptionScanner = new Scanner(System.in);
        String pathToFile = args[0];

        printMenu();
        List<Car> cars = parseCsvFile(pathToFile);

        while (true) {
            MenuOption menuOption = MenuOption.findByValue(menuOptionScanner.nextInt());

            if (menuOption == null) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (menuOption) {
                case SHOW_CATALOGUE:
                    showCatalogue(cars);
                    break;
                case ADD_ELECTRIC_CAR:
                    addCar(cars, CarType.ELECTRIC_CAR);
                    break;
                case ADD_GAS_POWERED_CAR:
                    addCar(cars, CarType.GAS_CAR);
                    break;
                case ADD_HYBRID_CAR:
                    addCar(cars, CarType.HYBRID_CAR);
                    break;
                case SHOW_CATALOGUE_SORTED_CAR_TYPE:
                    showCatalogueSortedByCarType(cars);
                    break;
                case SHOW_CATALOGUE_SORTED_BRAND:
                    showCatalogueSortedByBrand(cars);
                    break;
                case WRITE_TO_FILE:
                    writeToFile(cars, pathToFile);
                    break;
                case STOP_THE_PROGRAM:
                    System.exit(0);
            }
        }
    }


    private static void showCatalogue(List<Car> cars) {
        System.out.println("Catalogue:");
        cars.forEach(System.out::println);
    }

    private static void addCar(List<Car> cars, CarType carType) {
        System.out.println("Creating new car:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter brand:");
        String brand = scanner.nextLine();

        System.out.print("Enter model:");
        String model = scanner.nextLine();

        System.out.print("Enter price (in euro):");
        int price = scanner.nextInt();

        switch (carType) {
            case ELECTRIC_CAR:
                cars.add(createElectricCar(brand, model, price, scanner));
                break;
            case GAS_CAR:
                cars.add(createGasCar(brand, model, price, scanner));
                break;
            case HYBRID_CAR:
                cars.add(createHybridCar(brand, model, price, scanner));
                break;
        }
    }

    private static void showCatalogueSortedByCarType(List<Car> cars) {
        System.out.println("Cars sorted by car type:");
        cars.sort(Comparator.comparingInt(car -> car.getCarType().getValue()));
        cars.forEach(System.out::println);
    }

    private static void showCatalogueSortedByBrand(List<Car> cars) {
        System.out.println("Cars sorted by brand:");
        cars.sort((car1, car2) -> car1.getBrand().compareToIgnoreCase(car2.getBrand()));
        cars.forEach(System.out::println);
    }

    private static void writeToFile(List<Car> cars, String pathToFile) {
        System.out.println("Writing to file:");

        try {
            FileWriter fileWriter = new FileWriter(pathToFile);

            cars.forEach(car -> {
                try {
                    fileWriter.write(car.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("Please make your choice:\n" +
                "1 - Show the entire Mobility4You catalogue\n" +
                "2 - Add a new electric car\n" +
                "3 - Add a new gas-powered car\n" +
                "4 - Add a new hybrid car\n" +
                "5 - Show the entire Mobility4You catalogue sorted by car-type\n" +
                "6 - Show the entire Mobility4You catalogue sorted by brand (alphabetically)\n" +
                "7 - Write to file\n" +
                "8 - Stop the program\n");
    }

    private static ElectricCar createElectricCar(String brand, String model, int price, Scanner scanner) {
        System.out.print("Enter engine power (in KW):");
        double enginePower = scanner.nextDouble();

        System.out.print("Enter battery capacity (in Ah):");
        long batteryCapacity = scanner.nextLong();

        ElectricCar electricCar = new ElectricCar(CarType.ELECTRIC_CAR, brand, model, enginePower, price, batteryCapacity);
        System.out.println("Successfully created new car: " + electricCar.toString());

        return electricCar;
    }

    private static GasCar createGasCar(String brand, String model, int price, Scanner scanner) {
        System.out.print("Enter engine engine displacement (in L):");
        double engineDisplacement = scanner.nextDouble();

        System.out.print("Enter engine power (in KW):");
        double enginePower = scanner.nextDouble();

        GasCar gasCar = new GasCar(CarType.GAS_CAR, brand, model, enginePower, price, engineDisplacement);
        System.out.println("Successfully created new car: " + gasCar.toString());

        return gasCar;
    }

    private static HybridCar createHybridCar(String brand, String model, int price, Scanner scanner) {
        System.out.print("Enter engine engine displacement (in L):");
        double engineDisplacement = scanner.nextDouble();

        System.out.print("Enter engine power (in KW):");
        double enginePower = scanner.nextDouble();

        System.out.print("Enter battery capacity (in Ah):");
        long batteryCapacity = scanner.nextLong();

        HybridCar hybridCar = new HybridCar(CarType.HYBRID_CAR, brand, model, enginePower, price, batteryCapacity, engineDisplacement);
        System.out.println("Successfully created new car: " + hybridCar.toString());

        return hybridCar;
    }

    private static List<Car> parseCsvFile(String pathName) {
        List<Car> cars = new ArrayList<>();

        try {
            File mobilityTxt = new File(pathName);
            Scanner fileScanner = new Scanner(mobilityTxt);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitLine = line.split(", ");
                String[] carTypeAndBrand = splitLine[0].split(" ");
                CarType carType = CarType.findByName(carTypeAndBrand[0]);

                if (carType == null) {
                    System.out.println("Parsed invalid car type " + carTypeAndBrand[0]);
                    continue;
                }

                String brand = carTypeAndBrand[1];
                String model = splitLine[1];

                switch (carType) {
                    case ELECTRIC_CAR: {
                        double enginePower = Double.parseDouble(splitLine[2].substring(0, splitLine[2].length() - 2));
                        int price = Integer.parseInt(splitLine[4].split(" ")[0]);
                        long batteryCapacity = Long.parseLong(splitLine[3].substring(0, splitLine[3].length() - 2));

                        cars.add(new ElectricCar(carType, brand, model, enginePower, price, batteryCapacity));
                        break;
                    }
                    case GAS_CAR: {
                        double engineDisplacement = Double.parseDouble(splitLine[2].substring(0, splitLine[2].length() - 1));
                        double enginePower = Double.parseDouble(splitLine[3].substring(0, splitLine[3].length() - 2));
                        int price = Integer.parseInt(splitLine[4].split(" ")[0]);

                        cars.add(new GasCar(carType, brand, model, enginePower, price, engineDisplacement));
                        break;
                    }
                    case HYBRID_CAR: {
                        double engineDisplacement = Double.parseDouble(splitLine[2].substring(0, splitLine[2].length() - 1));
                        double enginePower = Double.parseDouble(splitLine[3].substring(0, splitLine[3].length() - 2));
                        long batteryCapacity = Long.parseLong(splitLine[4].substring(0, splitLine[4].length() - 2));
                        int price = Integer.parseInt(splitLine[5].split(" ")[0]);

                        cars.add(new HybridCar(carType, brand, model, enginePower, price, batteryCapacity, engineDisplacement));
                        break;
                    }
                }

            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading mobility.txt: " + e.getMessage());
        }

        return cars;
    }
}

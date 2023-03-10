import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Car extends UserInput {

    private String registrationNumber;
    private String brand;
    private String model;
    private String fuelType;
    private int odometer;
    private String registrationDate; //TODO skiftet til String
    private Type type;
    private Scanner scanner = new Scanner(System.in);
    private static SQLHandler sqlHandler = new SQLHandler();

    private static ArrayList<Car> cars = new ArrayList<>();

    public Car(String registrationNumber, String brand, String model, String fuelType, int odometer,
               String registrationDate, Type type) {
        setRegistrationNumber(registrationNumber);
        setBrand(brand);
        setModel(model);
        setFuelType(fuelType);
        setOdometer(odometer);
        setRegistrationDate(registrationDate);
        setType(type);
    }

    public Car() {
    }

    public void carMenu() {
        boolean isRunning = true;

        sqlHandler.generateCarList();
        //reRun carList - Er loading time for lang? - kræver det for meget af computeren at køre generateCarList() hver gang?

        while (isRunning) {
            printCarMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1 -> registerCar();
                //case 2 -> editCar();
                case 3 -> deleteCar();
                case 4 -> viewAllCars();
                case 5 -> carSearchMenu();
                case 9 -> isRunning = false;
            }
        }
    }

    public void printCarMenu() {
        System.out.println("1. Register new car");
        System.out.println("2. Edit car");
        System.out.println("3. Delete car");
        System.out.println("4. View all cars");
        System.out.println("5. Search menu");
        System.out.println("9. BACK");
    }

    private void registerCar() {
        Car carTemp = new Car();
        carTemp.setRegistrationNumber(stringIn("Write the registration number of the car: "));
        carTemp.setBrand(stringIn("Write the brand of the car: "));
        carTemp.setModel(stringIn("Write the model of the car: "));
        carTemp.setFuelType(stringIn("Write the fuel type of the car: ")); //TODO skal vi bruge enum som fuel type?
        carTemp.setOdometer(intIn("How many km. has the car driven: "));
        carTemp.setType(typeIn("What type of car is it"));
        carTemp.setRegistrationDate(dateIn("Write the registration Date of the car"));

        System.out.println("DEBUG" + carTemp);
        sqlHandler.executeUpdate(getInsertCarQuery(carTemp));
        System.out.println("DEBUG: Car was successfully added to the database :)");
    }

    public String getUpdateCarQuery(Car car) {
        //query nedenunder opdatere en bil baseret på registration_number
        String query = "UPDATE car SET brand = \'" + car.getBrand() + "\', model = \'" + car.getModel() +
                "\', fuel_type = \'" + car.getFuelType() + "\', odometer = " + car.getOdometer() +
                ", first_registration_mon_yr = \'" + car.getRegistrationDate() + "\', rental_type = \'" +
                car.getType() + "\' WHERE registration_number = \'" + car.getRegistrationNumber() + "\'";
        //System.out.println(query); //For debugging
        return query;
    }

    public String getInsertCarQuery(Car car) {
        //query nedenunder indsætter en ny bil ind i databasen.
        String query = "INSERT INTO car VALUES " +
                "(" + car.getRegistrationNumber() + ", \'" + car.getBrand() +
                "\', \'" + car.getModel() + "\', \'" + car.getFuelType() + "\', " +
                car.getOdometer() + ", \'" + car.getRegistrationDate() +
                "\', \'" + car.getType() + "\')";
        //System.out.println(query); //For debugging
        return query;
    }

    private void deleteCar() {
        //TODO
    }

    private void viewAllCars() {
        //TODO
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public void printCarSearchMenu() {
        System.out.println("1. Registration number");
        System.out.println("2. Car brand");
        System.out.println("3. Car model");
        System.out.println("4. Fuel type");
        System.out.println("5. First registration by month and year");
        System.out.println("6. Rental type");
        System.out.println("9. BACK");
    }

    public void carSearchMenu() {
        boolean isRunning = true;

        while (isRunning) {
            printCarSearchMenu();
            int choice = readMenuChoice();
            switch (choice) {
//                case 1 -> //TODO SQL handler here? To search;
//                case 2 -> ;////TODO
//                case 3 -> ;//TODO
//                case 4 -> ;//TODO
//                case 5 -> ;//TODO
//                case 9 -> isRunning = false;
            }
        }
    }

    public int readMenuChoice() {
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCarToList(Car car) {
        cars.add(car);
    }

    @Override
    public String toString() {
        //TODO skal denne bruges? - evt. laves om
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", odometer=" + odometer +
                ", registrationDate=" + registrationDate +
                ", type=" + type +
                '}';
    }
}

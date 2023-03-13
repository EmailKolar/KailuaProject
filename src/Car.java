import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Car extends UserInput {

    private String registrationNumber;
    private String brand;
    private String model;
    private String fuelType;
    private int odometer;
    private String registrationDate;
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

        while (isRunning) {
            sqlHandler.generateCarList();
            printCarMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1 -> registerCar();
                case 2 -> editCar();
                case 3 -> deleteCar();
                case 4 -> viewAllCars();
                case 5 -> searchForCar();
                case 9 -> isRunning = false;
            }
        }
    }

    private void editCar() {
        //TODO TEST ME
        boolean registrationNumberDoesNotExist = true;

        //Chooses car to edit by typing registration number
        String tempRegNum = stringIn("Please type the registration number of the car you want to edit: ");
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getRegistrationNumber().equals(tempRegNum)) {
                registrationNumberDoesNotExist = false;
                //Prints car they want to edit
                System.out.println("\nThis is the car you've chosen to edit:\n" + cars.get(i));

                //Edit car
                cars.get(i).setBrand(stringIn("Write the brand of the car: "));
                cars.get(i).setModel(stringIn("Write the model of the car: "));
                cars.get(i).setFuelType(stringIn("Write the fuel type of the car: "));
                cars.get(i).setOdometer(intIn("How many km. has the car driven: "));
                cars.get(i).setType(typeIn("What type of car is it"));
                cars.get(i).setRegistrationDate(dateIn("Write the registration Date of the car"));
                sqlHandler.executeUpdate(getUpdateCarQuery(cars.get(i)));
                System.out.println("Car has been updated <3\n");
            }
        }
        if (registrationNumberDoesNotExist) {
            System.out.println("Registration number does not exist\n");
        }
        //Then update each element? Or choose specific element to update?

    }

    public void printCarMenu() {
        System.out.println("1. Register new car");
        System.out.println("2. Edit car");
        System.out.println("3. Delete car");
        System.out.println("4. View all cars");
        System.out.println("5. Search For a Car");
        System.out.println("9. BACK");
    }

    private void registerCar() {   //FIXME When registering new car this message appears: SQLException: Unknown column 'ABDC483' in 'field list'
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
                "(\'" + car.getRegistrationNumber() + "\', \'" + car.getBrand() +
                "\', \'" + car.getModel() + "\', \'" + car.getFuelType() + "\', " +
                car.getOdometer() + ", \'" + car.getRegistrationDate() +
                "\', \'" + car.getType() + "\')";
        //System.out.println(query); //For debugging
        return query;
    }

    private void deleteCar() {
        boolean carDoesNotExist = true;
        String tempRegistrationNumber = stringIn("Please type the registration number of the car you want to delete: ");
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getRegistrationNumber().equals(tempRegistrationNumber)) {
                String query = "DELETE FROM car WHERE registration_number = \'" + tempRegistrationNumber + "\'";
                sqlHandler.executeUpdate(query);
                carDoesNotExist = false;
                System.out.println("Car has been deleted\n");
            }
        }
        if (carDoesNotExist) {
            System.out.println("Registration number does not exist\n");
        }
    }

    private void viewAllCars() {
        //TODO Laurits Yoink (formater toString pænt) TEST ME Mathias & Emil thoughts???
        //FIXME Everytime we press 4 to view all cars the amount of cars double
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public int readMenuChoice() {
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void searchForCar(){
        System.out.println("You can search by Reg. num., Brand, Model, Fuel type, Reg. Date or Type");
        String userInput = stringIn("Enter search parameter: ");

        for (Car car : cars) {
            if (userInput.equals(car.getRegistrationNumber())) {
                System.out.println(car);
            } else if (userInput.equals(car.getBrand())) {
                System.out.println(car);
            } else if (userInput.equals(car.getModel())) {
                System.out.println(car);
            } else if (userInput.equals(car.getFuelType())) {
                System.out.println(car);
            } else if (userInput.equals(car.getRegistrationDate())) {
                System.out.println(car);
            } else if (userInput.equals(car.getType().toString())) {
                System.out.println(car);
            }
        }
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
        //TODO TEST ME Mathias & Emil thoughts???

        return String.format("|Registration number    |Brand    |Model    |Fuel type    |Odometer at start    " +
                "|Registration date    |Type\n|%-23s|%-9s|%-9s|%-13s|%-21s|%-21s\n", registrationNumber, brand, model,
                fuelType, odometer, registrationDate, type.toString());
//        return "Car{" +
//                "registrationNumber='" + registrationNumber + '\'' +
//                ", brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                ", fuelType='" + fuelType + '\'' +
//                ", odometer=" + odometer +
//                ", registrationDate=" + registrationDate +
//                ", type=" + type +
//                '}';
    }
}



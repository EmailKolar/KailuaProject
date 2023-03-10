import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    SQLHandler sqlHandler = new SQLHandler();
    Scanner scanner = new Scanner(System.in);
    Car car = new Car();
    Renter renter = new Renter();
    Contract contract = new Contract();

    public static void main(String[] args) {
        new Main().run();
    }

    void run() {
        //4 lines below is test code for carList and editing of car object
        //sqlHandler.generateCarList();
        //ArrayList<Car> cars = car.getCars();
        //cars.get(0).setBrand("Porsche");
        //sqlHandler.executeUpdate(car.getNewCarQuery(cars.get(0)));

        menu();
    }


    public void menu() {
        boolean isRunning = true;

        while (isRunning) {
            printMainMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1 -> car.carMenu();
                case 2 -> renter.renterMenu();
                case 3 -> contract.contractMenu();
                case 9 -> isRunning = false;
            }
        }
    }

    public void printMainMenu() {
        System.out.println("1. Register, edit, delete, or view CARS from database");
        System.out.println("2. Register, edit, delete, or view RENTERS from database");
        System.out.println("3. Register, edit, delete, or view CONTRACTS from database");
        System.out.println("9. EXIT");
    }

    public int readMenuChoice() {
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

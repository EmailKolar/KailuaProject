import java.util.Scanner;

public class Car {

    Scanner scanner = new Scanner(System.in);

    public void carMenu(){
        boolean isRunning = true;

        while(isRunning){
            printCarMenu();
            int choice = readMenuChoice();
            switch (choice){
                case 1 -> registerCar();
                case 2 -> editCar();
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

    }

    private void editCar() {

    }

    private void deleteCar() {

    }

    private void viewAllCars() {

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

    public void carSearchMenu(){
        boolean isRunning = true;

        while(isRunning){
            printCarSearchMenu();
            int choice = readMenuChoice();
            switch (choice){
//                case 1 -> //TODO SQL handler here? To search;
//                case 2 -> ;
//                case 3 -> ;
//                case 4 -> ;
//                case 5 -> ;
//                case 9 -> isRunning = false;
            }
        }
    }

    public int readMenuChoice(){
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


}

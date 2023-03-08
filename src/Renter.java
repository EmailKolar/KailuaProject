import java.util.Scanner;

public class Renter {

    Scanner scanner = new Scanner(System.in);

    public void renterMenu(){
        boolean isRunning = true;

        while(isRunning){
            printRenterMenu();
            int choice = readMenuChoice();
            switch (choice){
                case 1 -> registerRenter();
                case 2 -> editRenter();
                case 3 -> deleteRenter();
                case 4 -> viewAllRenters();
                case 5 -> renterSearchMenu();
                case 9 -> isRunning = false;
            }
        }
    }

    public void printRenterMenu() {
        System.out.println("1. Register new contract");
        System.out.println("2. Edit contract");
        System.out.println("3. Delete contract");
        System.out.println("4. View all contracts");
        System.out.println("5. Search menu");
        System.out.println("9. BACK");
    }

    private void registerRenter() {

    }

    private void editRenter() {

    }

    private void deleteRenter() {

    }

    private void viewAllRenters() {

    }

    public void printRenterSearchMenu() {
        System.out.println("1. Driver license number");
        System.out.println("2. Name");
        System.out.println("3. Address");
        System.out.println("4. Zip code");
        System.out.println("5. City");
        System.out.println("6. Mobile phone");
        System.out.println("7. Phone");
        System.out.println("8. Email");
        System.out.println("9. BACK");
    }

    public void renterSearchMenu(){
        boolean isRunning = true;

        while(isRunning){
            printRenterSearchMenu();
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

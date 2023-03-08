import java.util.Scanner;

public class Contract {
        Scanner scanner = new Scanner(System.in);

    public void menu(){
        boolean isRunning = true;

        while(isRunning){
            printContractMenu();
            int choice = readMenuChoice();
            switch (choice){
                case 1 -> registerContract();
                case 2 -> editContract();
                case 3 -> deleteContract();
                case 4 -> viewAllContracts();
                case 9 -> isRunning = false;
            }
        }
    }

    public void printContractMenu() {
        System.out.println("1. Register new contract");
        System.out.println("2. Edit contract");
        System.out.println("3. Delete contract");
        System.out.println("4. View all contracts");
        System.out.println("5. Search menu"

        );
        System.out.println("9. BACK");
    }

    public void registerContract() {

    }

    public void editContract() {

    }

    public void deleteContract() {

    }

    public void viewAllContracts() {

    }

    public int readMenuChoice(){
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

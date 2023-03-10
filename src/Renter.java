import java.util.ArrayList;
import java.util.Scanner;

public class Renter extends UserInput {

    Scanner scanner = new Scanner(System.in);
    private String driverLicenseNumber;
    private String name;
    private String address;
    private String zip;
    private String city;
    private String mobilePhone;
    private String phone;
    private String email;
    private static ArrayList<Renter> renters = new ArrayList<>();

    public Renter() {

    }

    public Renter(String driverLicenseNumber, String name, String address,
                  String zip, String city, String mobilePhone, String phone, String email) {
        setDriverLicenseNumber(driverLicenseNumber);
        setName(name);
        setAddress(address);
        setZip(zip);
        setCity(city);
        setMobilePhone(mobilePhone);
        setPhone(phone);
        setEmail(email);
    }

    public void renterMenu() {
        boolean isRunning = true;

        while (isRunning) {
            printRenterMenu();
            int choice = readMenuChoice();
            switch (choice) {
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
        //TODO
    }

    private void editRenter() {
        //TODO
    }

    private void deleteRenter() {
        //TODO
    }

    private void viewAllRenters() {
        //TODO
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

    public void renterSearchMenu() {
        boolean isRunning = true;

        while (isRunning) {
            printRenterSearchMenu();
            int choice = readMenuChoice();
            switch (choice) {
//                case 1 -> //TODO SQL handler here? To search;
//                case 2 -> ; //TODO
//                case 3 -> ; //TODO
//                case 4 -> ; //TODO
//                case 5 -> ; //TODO
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

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Renter> getRenters() {
        return renters;
    }

    public void addRenterToList(Renter renter) {
        renters.add(renter);
    }

}

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
    private static SQLHandler sqlHandler = new SQLHandler();


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
            sqlHandler.generateRenterList(); //Creates ArrayList of renters
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
        System.out.println("1. Register new renter");
        System.out.println("2. Edit renter");
        System.out.println("3. Delete renter");
        System.out.println("4. View all renter");
        System.out.println("5. Search menu");
        System.out.println("9. BACK");
    }

    private void registerRenter() {
        //TODO TEST ME
        Renter renterTemp = new Renter();
        //TODO If driver license number already exists write message?
        renterTemp.setDriverLicenseNumber(stringIn("Write the driver license number of the renter (8 characters): "));
        renterTemp.setName(stringIn("Write the full name of the renter: "));
        renterTemp.setAddress(stringIn("Write the address of the renter (Street + number): "));
        renterTemp.setZip(stringIn("Write the zip code of the renter: "));
        renterTemp.setCity(stringIn("Write the city of the renter: "));
        renterTemp.setMobilePhone(stringIn("Write the mobile phone number of the renter: "));
        renterTemp.setPhone(stringIn("Write the phone number of the renter (Press enter if renter does not have one): "));
        renterTemp.setEmail(stringIn("Write the email address of the renter: "));

        System.out.println("DEBUG" + renterTemp);
        sqlHandler.executeUpdate(getInsertRenterQuery(renterTemp));
        System.out.println("DEBUG: Renter was successfully added to the database :)");
    }

    private void editRenter() {
        //TODO

    }

    public String getUpdateRenterQuery(Renter renter) {  //TODO TEST ME
        //query nedenunder opdatere en renter baseret på registration_number
        String query = "UPDATE renter SET driver_license_number = \'" + renter.getDriverLicenseNumber() +
                "\', name = \'" + renter.getName() +
                "\', address = \'" + renter.getAddress() + "\', zip = " + renter.getZip() +
                ", city = \'" + renter.getCity() + "\', mobile_phone = \'" +
                renter.getMobilePhone() + "\', phone = \'" +
                renter.getPhone() + "\', email = \'" +
                renter.getEmail() + "\' WHERE driver_license_number = \'" + renter.getDriverLicenseNumber() + "\'";
        //System.out.println(query); //For debugging
        return query;
    }

    public String getInsertRenterQuery(Renter renter) { //TODO TEST ME
        //query nedenunder indsætter en ny renter ind i databasen.
        String query = "INSERT INTO renter VALUES " +
                "(\'" + renter.getDriverLicenseNumber() + "\', \'" + renter.getName() +
                "\', \'" + renter.getAddress() + "\', \'" + renter.zip + "\', \'" +
                renter.city + "\', \'" + renter.getMobilePhone() +
                "\', \'" + renter.getPhone() + "\', \'" + renter.getEmail() + "\')";
        System.out.println(query); //For debugging
        return query;
    }

    private void deleteRenter() {
        boolean renterDoesNotExist = true;
        String tempDriverLicenseNumber = stringIn("Please type the driver license number of the renter you want to delete: ");
        for (int i = 0; i < renters.size(); i++) {
            if (renters.get(i).getDriverLicenseNumber().equals(tempDriverLicenseNumber)) {
                String query = "DELETE FROM renter WHERE driver_license_number = \'" + tempDriverLicenseNumber + "\'";
                sqlHandler.executeUpdate(query);
                renterDoesNotExist = false;
                System.out.println("Renter has been deleted\n");
            }
        }
        if (renterDoesNotExist) {
            System.out.println("Driver license number does not exist\n");
        }
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
        boolean numberExist = false;
        for (int i = 0; i < renters.size(); i++) {
            if (renters.get(i).getDriverLicenseNumber().equals(driverLicenseNumber)) {
                numberExist = true;
            }
        }
        if (!numberExist){
            this.driverLicenseNumber = driverLicenseNumber;
        }else{
            setDriverLicenseNumber(stringIn("The given driver license number already exist. please try again"));
        }
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

    @Override
    public String toString() {
        return "Renter{" +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

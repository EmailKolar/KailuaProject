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
        this.driverLicenseNumber = driverLicenseNumber;
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
                case 5 -> renterSearch();
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
        //TODO If driver license number already exists write message? - Mathias TEST ME
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
        boolean driverLicenseNumberDoesNotExist = true;
        String tempRegNum = stringIn("Please type the driver license number of the renter you want to edit: ");
        for (int i = 0; i < renters.size(); i++) {
            if (renters.get(i).getDriverLicenseNumber().equals(tempRegNum)) {
                driverLicenseNumberDoesNotExist = false;
                //Edit renter
                renters.get(i).setName(stringIn("Write the full name of the renter: "));
                renters.get(i).setAddress(stringIn("Write the address of the renter (Street + number): "));
                renters.get(i).setZip(stringIn("Write the zip code of the renter: "));
                renters.get(i).setCity(stringIn("Write the city of the renter: "));
                renters.get(i).setMobilePhone(stringIn("Write the mobile phone number of the renter: "));
                renters.get(i).setPhone(stringIn("Write the phone number of the renter (Press enter if renter does not have one): "));
                renters.get(i).setEmail(stringIn("Write the email address of the renter: "));
                sqlHandler.executeUpdate(getUpdateRenterQuery(renters.get(i)));
                System.out.println("Renter has been updated <3\n");
            }
        }
        if (driverLicenseNumberDoesNotExist) {
            System.out.println("Driver license number does not exist\n");
        }
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
        System.out.println("DRV.LSC.NUM   NAME                       ADDRESS                    ZIP   CITY" +
                "                     PHONE     M.NUM     EMAIL");

        for(Renter renter : renters){
            System.out.println(renter);
        }
    }

    public void renterSearch(){
        //TODO TEST ME
        System.out.println("You can search by Driver license num, Name, Address, Zip, City, Phone number or Email");
        String userInput = stringIn("Enter search parameter: ");

        for(Renter renter : renters){
            if(userInput.equals(renter.getDriverLicenseNumber())){
                System.out.println(renter);
            } else if (userInput.equals(renter.getName())) {
                System.out.println(renter);
            }else if (userInput.equals(renter.getAddress())) {
                System.out.println(renter);
            }else if (userInput.equals(renter.getZip())) {
                System.out.println(renter);
            }else if (userInput.equals(renter.getCity())) {
                System.out.println(renter);
            }else if (userInput.equals(renter.getPhone())) {
                System.out.println(renter);
            }else if (userInput.equals(renter.getMobilePhone())) {
                System.out.println(renter);
            }else if (userInput.equals(renter.getEmail())) {
                System.out.println(renter);
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
                break;
            }
        }
        if (!numberExist){
            this.driverLicenseNumber = driverLicenseNumber;
        } else {
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

    public void addRenterToList(Renter renter) {
        renters.add(renter);
    }

    @Override
    public String toString() {
        return String.format("%-13s %-26s %-26s %-5s %-24s %-9s %-9s %s ",
                driverLicenseNumber,name,address,zip,city,mobilePhone,phone,email);
    }
}

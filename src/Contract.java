import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Contract extends UserInput{
    Scanner scanner = new Scanner(System.in);

    private int contractID;
    private Date fromDateTime;
    private Date toDateTime;
    private String driverLicenseNumber;
    private String registrationNumber;
    private int odometerAtStart;

    private ArrayList<Contract> contracts = new ArrayList<>();

    public Contract(int contractID, Date fromDateTime, Date toDateTime, String driverLicenseNumber,
                    String registrationNumber, int odometerAtStart) {
        setContractID(contractID);
        setFromDateTime(fromDateTime);
        setToDateTime(toDateTime);
        setDriverLicenseNumber(driverLicenseNumber);
        setRegistrationNumber(registrationNumber);
        setOdometerAtStart(odometerAtStart);

    }

    public Contract() {

    }


    public void contractMenu() {
        boolean isRunning = true;

        while (isRunning) {
            printContractMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1 -> registerContract();
                case 2 -> editContract();
                case 3 -> deleteContract();
                case 4 -> viewAllContracts();
                case 5 -> contractSearch();
                case 9 -> isRunning = false;
            }
        }
    }

    public void printContractMenu() {
        System.out.println("1. Register new contract");
        System.out.println("2. Edit contract");
        System.out.println("3. Delete contract");
        System.out.println("4. View all contracts");
        System.out.println("5. Search menu");
        System.out.println("9. BACK");
    }

    private void registerContract() {
        Contract contractTemp = new Contract();
        contractTemp.setRegistrationNumber(stringIn("Write the registration number of the car")); //TODO check if reg num exist
        contractTemp.setDriverLicenseNumber(stringIn("write the license number of the driver")); //TODO check if renter with given license exist
        contractTemp.setFromDateTime(dateIn("Write the starting time of contract"));
        contractTemp.setToDateTime(dateIn("Write the end time of the contract"));
        contractTemp.setOdometerAtStart(intIn("Write the odometer number on the given car "));
        //TODO nice to have oven over ^ set odometer automatisk based on given car reg num

        System.out.println("DEBUG" + contractTemp);
//        sqlHandler.executeUpdate(getInsertCarQuery(carTemp));
//        System.out.println("DEBUG: Contract was successfully added to the database :)");
    }

    private void editContract() {
        //TODO
    }

    private void deleteContract() {
        //TODO
    }

    private void viewAllContracts() {
        //TODO
    }

    public void printContractSearchMenu() {
        System.out.println("1. Search by contract ID");
        System.out.println("2. Search by date created");
        System.out.println("3. Search by driver license number");
        System.out.println("4. Search by registration number");
        System.out.println("9. BACK");
    }

    public void contractSearch() {
        System.out.println("Search param broooo");
        String userInput = stringIn("Enter param: ");
        for (Contract contract : contracts) {
            if (userInput.equals(contract.getContractID())){
                System.out.println(contract);
            } else if (userInput.equals(contract.getFromDateTime())) {
                System.out.println(contract);
            } else if (userInput.equals(contract.getDriverLicenseNumber())) {
                System.out.println(contract);
            } else if (userInput.equals(contract.getRegistrationNumber())) {
                System.out.println(contract);
            } else if (userInput.equals(String.valueOf(contract.getOdometerAtStart()))) {
                System.out.println(contract);
            }
        }
    }

    public int readMenuChoice() {
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public void setFromDateTime(Date fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public void setOdometerAtStart(int odometerAtStart) {
        this.odometerAtStart = odometerAtStart;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setToDateTime(Date toDateTime) {
        this.toDateTime = toDateTime;
    }

    public Date getFromDateTime() {
        return fromDateTime;
    }

    public Date getToDateTime() {
        return toDateTime;
    }

    public int getOdometerAtStart() {
        return odometerAtStart;
    }

    public String getContractID() {
        return contractID;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void addContractToList(Contract contract) {
        contracts.add(contract);
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }
}

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Contract extends UserInput {
    Scanner scanner = new Scanner(System.in);

    private String contractID;
    private String fromDateTime;
    private String toDateTime;
    private String driverLicenseNumber;
    private String registrationNumber;
    private int odometerAtStart;

    private ArrayList<Contract> contracts = new ArrayList<>(); //TODO gøre static?
    private static SQLHandler sqlHandler = new SQLHandler();

    public Contract(String contractID, String fromDateTime, String toDateTime, String driverLicenseNumber,
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
            sqlHandler.generateContractList(); //Creates ArrayList of contracts
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
        sqlHandler.executeUpdate(getInsertContractQuery(contractTemp));
        System.out.println("DEBUG: Contract was successfully added to the database :)");
    }

//    public String getUpdateContractQuery(Contract contract) { //TODO fix?
//        //query nedenunder opdaterer en contract baseret på registration_number
//        String query = "UPDATE car SET brand = \'" + car.getBrand() + "\', model = \'" + car.getModel() +
//                "\', fuel_type = \'" + car.getFuelType() + "\', odometer = " + car.getOdometer() +
//                ", first_registration_mon_yr = \'" + car.getRegistrationDate() + "\', rental_type = \'" +
//                car.getType() + "\' WHERE registration_number = \'" + car.getRegistrationNumber() + "\'";
//        //System.out.println(query); //For debugging
//        return query;
//    }
//
    public String getInsertContractQuery(Contract contract) {
        //query nedenunder indsætter en ny contract ind i databasen.
        String query = "INSERT INTO contract VALUES (null, \'" +  contract.getFromDateTime() +"\', " +
                "\'" + contract.driverLicenseNumber +"\', \'" + contract.getRegistrationNumber() +"\', " +
                "\'" + contract.getToDateTime() +"\', \'" + contract.getOdometerAtStart();
        //System.out.println(query); //For debugging
        return query;
    }


    private void editContract() {
        //TODO UPDATE Laurits Yoink
    }

    private void deleteContract() {
        //TODO TEST ME
        boolean contractDoesNotExist = true;
        String tempContractID = stringIn("Please type the contract ID of the contract you want to delete: ");
        for (int i = 0; i < contracts.size(); i++) {
            if (contracts.get(i).getContractID().equals(tempContractID)) {
                String query = "DELETE FROM contract WHERE contract_id = \'" + tempContractID + "\'";
                sqlHandler.executeUpdate(query);
                contractDoesNotExist = false;
                System.out.println("Contract has been deleted\n");
            }
        }
        if (contractDoesNotExist) {
            System.out.println("Contract ID does not exist\n");
        }
    }

    private void viewAllContracts() {
        //TODO for loop print arraylist ud (formater toString så det er pænt) Laurits Yoink
    }

    @Override
    public String toString() {
        return "Contract{" +
                "scanner=" + scanner +
                ", contractID='" + contractID + '\'' +
                ", fromDateTime='" + fromDateTime + '\'' +
                ", toDateTime='" + toDateTime + '\'' +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", odometerAtStart=" + odometerAtStart +
                ", contracts=" + contracts +
                '}';
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

    public void setFromDateTime(String fromDateTime) {
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

    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getToDateTime() {
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

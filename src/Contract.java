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

    private static ArrayList<Contract> contracts = new ArrayList<>();
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
            contracts = new ArrayList<>();
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
        contractTemp.setRegistrationNumber(stringIn("Write the registration number of the car"));
        contractTemp.setDriverLicenseNumber(stringIn("write the license number of the driver"));
        contractTemp.setFromDateTime(dateIn("Write the starting time of contract"));
        contractTemp.setToDateTime(dateIn("Write the end time of the contract"));
        contractTemp.setOdometerAtStart(intIn("Write the odometer number on the given car "));

        //System.out.println("DEBUG" + contractTemp);
        sqlHandler.executeUpdate(getInsertContractQuery(contractTemp));
        System.out.println("Contract was successfully added to the database");
    }

    public String getUpdateContractQuery(Contract contract) { //TODO TEST ME
        //query nedenunder opdaterer en contract baseret på contract_id
        String query = "UPDATE contract SET from_date_time = \'" + contract.getFromDateTime() +
                "\', to_date_time = \'" + contract.getToDateTime() +
                "\', driver_license_number = \'" + contract.getDriverLicenseNumber()
                + "\', registration_number = " + contract.getRegistrationNumber() +
                ", odometer_at_start = \'" + contract.getOdometerAtStart() + "\'" +
                 " WHERE contract_id = \'" + contract.contractID + "\'";
        //System.out.println(query); //For debugging
        return query;
    }

    public String getInsertContractQuery(Contract contract) {
        //query nedenunder indsætter en ny contract ind i databasen.
        String query = "INSERT INTO contract VALUES (null, \'" +  contract.getFromDateTime() +"\', " +
                "\'" + contract.driverLicenseNumber +"\', \'" + contract.getRegistrationNumber() +"\', " +
                "\'" + contract.getToDateTime() +"\', " + contract.getOdometerAtStart() + ");";
        //System.out.println(query); //For debugging
        return query;
    }


    private void editContract() {
        //TODO TEST ME
        boolean contractIDDoesNotExist = true;
        String tempRegNum = stringIn("Please type the driver license number of the renter you want to edit: ");
        for (Contract contract : contracts) {
            if (contract.getDriverLicenseNumber().equals(tempRegNum)) {
                contractIDDoesNotExist = false;
                //Edit renter
                contract.setRegistrationNumber(stringIn("Write the registration number of the car"));
                contract.setDriverLicenseNumber(stringIn("write the license number of the driver"));
                contract.setFromDateTime(dateIn("Write the starting time of contract"));
                contract.setToDateTime(dateIn("Write the end time of the contract"));
                contract.setOdometerAtStart(intIn("Write the odometer number on the given car "));
                sqlHandler.executeUpdate(getUpdateContractQuery(contract));
                System.out.println("Renter has been updated <3\n");
            }
        }

        if (contractIDDoesNotExist) {
            System.out.println("Driver license number does not exist\n");
        }

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
        System.out.println("ContractID   FromDate      ToDate       Driver lcs.       Reg. num.    Odometer at start");
        for(Contract contract : contracts){
            System.out.println(contract);
        }
    }

    @Override
    public String toString() {

        return String.format("%-12s %-13s %-12s %-17s %-12s %s",
                contractID,fromDateTime,toDateTime,driverLicenseNumber,registrationNumber,odometerAtStart);
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

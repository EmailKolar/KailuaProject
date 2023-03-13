import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInput {
    Scanner in = new Scanner(System.in);

    public String stringIn(String message) {
        System.out.println(message);
        String str = in.nextLine();
        return str;
    }

    public int intIn(String message) {
        System.out.println(message);
        int num = in.nextInt();
        in.nextLine();
        return num;
    }

    public String dateIn(String message) {
        System.out.println(message);
        //TODO check for correct formatting 'yyyy-mm-dd' TEST ME
        String date;
        boolean isValid;
        do {
            System.out.println("Use the following format: \"yyyy-mm-dd\"");
            date = in.next();
            isValid = isValidDate(date);
        }while (!isValid);
        return date;
    }
    private boolean isValidDate(String date){  //TODO TEST ME
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter dateChecker = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate dateTemp = null;
        try {
            dateTemp = LocalDate.parse(date,dateChecker);
            return true;
        }catch (DateTimeException e){
            //dateIn("The given date is not valid - Try again");
            return false;
        }

    }






    public Type typeIn(String message) {
        System.out.println(message);
        boolean isValid = false;
        Type type = Type.FAMILY;
        while (!isValid) {
            switch (intIn("Family(1) Sport(2) Luxury(3)")) {
                case 1:
                    isValid = true;
                    type = Type.FAMILY;
                    break;
                case 2:
                    isValid = true;
                    type = Type.SPORT;
                    break;
                case 3:
                    isValid = true;
                    type = Type.LUXURY;
                    break;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
        return type;
    }


}

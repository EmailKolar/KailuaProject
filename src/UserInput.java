import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInput {
    Scanner in = new Scanner(System.in);

    public String stringIn(String message) {
        System.out.println(message);
        String str = in.nextLine();
        //in.nextLine();
        return str;
    }

    public int intIn(String message) {
        System.out.println(message);
        int num = in.nextInt();
        //in.nextLine();
        return num;
    }

    public String dateIn(String message) {
        System.out.println(message);
        System.out.println("Use the following format: \"yyyy-mm-dd\"");
        String date = in.next();
        in.nextLine();
        //TODO check for correct formatting 'yyyy-mm-dd'
        return date;
    }

    public Type typeIn(String message) {
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

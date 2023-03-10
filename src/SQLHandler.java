import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {

    Car car = new Car();
    Renter renter = new Renter();
    Contract contract = new Contract();

    public static final String database_url = "jdbc:mysql://localhost:3306/kailua";
    public static java.sql.Connection con;


    public ResultSet getRS(String sql) {
        try {
            con = DriverManager.getConnection(database_url, "root", "sesame80");
            Statement s = con.createStatement();
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public void executeUpdate(String query) {
        try {
            con = DriverManager.getConnection(database_url, "root", "sesame80");
            Statement s = con.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public void generateContractList(String sql) {
        try {
            ResultSet rs = getRS(sql);
            if (rs != null) {
                while (rs.next()) {
                    Contract c1 = new Contract(rs.getString(1), rs.getString(2), rs.getString(5),
                            rs.getString(3), rs.getString(4), rs.getInt(6));
                    contract.addContractToList(c1);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }


    public void generateCarList() {
        try {
            ResultSet rs = getRS("SELECT * FROM kailua.car");
            if (rs != null) {
                while (rs.next()) {
                    Car car1 = new Car(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getInt(5), rs.getString(6),
                            Type.valueOf(rs.getString(7)));
                    car.addCarToList(car1);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public void generateRenterList(String sql) {
        try {
            ResultSet rs = getRS(sql);
            if (rs != null) {
                while (rs.next()) {
                    Renter tempRenter = new Renter(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8));
                    renter.addRenterToList(renter);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }

    }
}


//GRAVEYARD RIP PRINT F
//public void printRenter(String sql) {
//        try {
//            ResultSet rs = getRS(sql);
//            if (rs != null) {
//                System.out.printf("%-13S %-28S %-30S %-6S %-28S %-6S %-10S %S",
//                        "Dr. Lcs. Num", "Name", "Address", "Zip", "City", "M. Phone", "Phone", "Email");
//                System.out.println();
//                while (rs.next()) {
//                    System.out.printf("%-13s %-28s %-30s %-6s %-28s %-6s %-10s %s", rs.getString(1)
//                            , rs.getString(2), rs.getString(3), rs.getString(4)
//                            , rs.getString(5), rs.getString(6), rs.getString(7),
//                            rs.getString(8));
//                    System.out.println();
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("SQLException: " + e.getMessage());
//            System.exit(1);
//        }
//    }
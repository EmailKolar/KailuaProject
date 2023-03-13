import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {

    Car car = new Car();
    Renter renter = new Renter();
    Contract contract = new Contract();

    private static final String database_url = "jdbc:mysql://localhost:3306/kailua";
    private static java.sql.Connection con;


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

    public void generateContractList() {
        String query = "SELECT * FROM contract";
        try {
            ResultSet rs = getRS(query);
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

    public void generateRenterList() {
        String query = "SELECT * FROM renter";
        try {
            ResultSet rs = getRS(query);
            if (rs != null) {
                while (rs.next()) {
                    Renter tempRenter = new Renter(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8));
                    renter.addRenterToList(tempRenter);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }

    }
}
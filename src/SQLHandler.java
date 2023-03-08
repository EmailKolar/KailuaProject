import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {

    public static final String database_url = "jdbc:mysql://localhost:3306/kailua";
    public static java.sql.Connection con;

    public ResultSet getRS(String sql){
        try{
            con = DriverManager.getConnection(database_url,"root","sesame80");
            Statement s = con.createStatement();
            return s.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public void printRenter(String sql){

        try{
            ResultSet rs = getRS(sql);
            if(rs != null){
                System.out.printf("%S %-10S %-30S %-20S %-6S %-23S %-7S %-10S",
                        "Dr. Lcs. Num","Name","Address","Zip","City","M. Phone","Phone", "Email");
                System.out.println();
                while(rs.next()){
                    System.out.printf("%s %-21s %-26s %-18s %-7s %-21s %-10s %-14s",rs.getString(1)
                            ,rs.getString(2),rs.getString(3),rs.getString(4)
                            ,rs.getString(5),rs.getString(6),rs.getString(7),
                            rs.getString(8));
                    System.out.println();
                }
            }
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }


    }

//    System.out.println("Driver Licence number: "+rs.getString("driver_license_number"));
//                    System.out.println("Name: "+rs.getString("name"));
//                    System.out.println("Address: "+rs.getString("address"));
//                    System.out.println("Zip: "+rs.getString("zip"));
//                    System.out.println("City: "+rs.getString("city"));
//                    System.out.println("Mobile Phone Number: "+rs.getString("mobile_phone"));
//                    System.out.println("Phone Number: "+rs.getString("phone"));
//                    System.out.println("Email Address: "+rs.getString("email"));
//                    System.out.println();


}

package sqlexample_mysql8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Example for MySQL 8.0 connector
// Have a populated database first with sqlexample.sql in e.g. MySQL workbench

public class SqlExample {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sqlexample?" +    
                    "serverTimezone=UTC&user=student&password=asd123");
            
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM person;");
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println("ID=" + id + ", NAME=" + name);
                }
            }
        }
        catch (Exception e){
            System.out.println("" + e);
        } 
    }
}

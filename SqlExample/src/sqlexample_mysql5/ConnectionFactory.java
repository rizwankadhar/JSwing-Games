package sqlexample_mysql5;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static MysqlConnectionPoolDataSource conn;
    
    private ConnectionFactory(){}
    
    public static Connection getConnection() 
        throws ClassNotFoundException, SQLException{
        if (conn == null){
            Class.forName("com.mysql.jdbc.Driver"); // Load the Driver
            conn = new MysqlConnectionPoolDataSource();
            conn.setServerName("localhost");
            conn.setPort(3306);
            conn.setDatabaseName("sqlexample");
            conn.setUser("student");
            conn.setPassword("asd123");
        }
        return conn.getPooledConnection().getConnection();
    }
}

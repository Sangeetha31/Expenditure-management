package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public static Connection conDB(){
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/total_paisa","root","root");
            System.out.println("Got a connection");
            return con;
        }catch(Exception e){
            System.out.println("Got a null");
            return null;
        }
    }
}

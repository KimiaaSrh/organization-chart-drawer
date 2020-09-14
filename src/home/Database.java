package home;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javafx.event.ActionEvent;


public class Database {
    private static Connection connection = null;
    
    public void connectToDb(){
		Scanner inp = new Scanner(System.in);
		//System.out.println("jsbck");
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver:" +
                            "//localhost:1433;databaseName=org_testdatabase",
                    "sa", "sqlserverpassword");


            //Statement  statement = connection.createStatement();
            
            Statement  statement1 = connection.createStatement();
         
            statement1.execute("insert into organization(org_name,org_id) values('sag7','sag8')");
            /*
            ResultSet resultSet = statement.executeQuery("SELECT * FROM passengerTable ");
        	
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("averageRate"));
                System.out.println("---------------------------------------");
            }

            statement.close();
            */
            statement1.close();
            connection.close();
	}
        catch (Exception e) {
            e.printStackTrace();
        }

}


}

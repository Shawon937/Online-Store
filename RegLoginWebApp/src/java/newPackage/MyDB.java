package newPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 */
public class MyDB {
    private Statement stmt = null;
    private Connection connection;
    //String name=null;
    //String username=null;
    //String email=null;
    //String password=null;
    
    public void getConnection() {
        
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;user=sa;password=1234;" +
            "databaseName=userdb;";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Database connected...");
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //return connection;
    }
    
    public void insertDataToDB(String query) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            //return true;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Unable to insert data");
            //return false;
        }
        
        
    }
    
    public ResultSet getFromDB(String query) {
        ResultSet results = null;
        String[] array = new String[4];
        try {

            stmt = connection.createStatement();
            results = stmt.executeQuery(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to read data");
        }
        return results;
    }
    
    public void disconnectFromDB() {

        try {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to close connection");
        }
    }
}

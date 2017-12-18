package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.rmi.CORBA.StubDelegate;

import java.sql.PreparedStatement;


 




public class SQLiteJDBCDriverConnection {
    
	
	
	

    public static void connect() {
        Connection conn = null;
        try {
        	
           
            String url = "jdbc:sqlite:sqlite_database_file_path";
           
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
  
    
    
    
    public static void createNewDatabase(String fileName) {
    	 
        String url = "jdbc:sqlite:sqlite_database_file_path" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite_database_file_path";
       
        String sql = "CREATE TABLE IF NOT EXISTS Student (\n"
                + "	studentId integer PRIMARY KEY,\n"
                + "	studentName text NOT NULL);";
               
        
        
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
   
    public static class InsertApp {
      
    	
        private Connection connect() {
            // SQLite connection string
            String url = "jdbc:sqlite:sqlite_database_file_path";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }
     
       
        public void insert(int studentId, String studentName) {
            String sql = "INSERT INTO Student(studentId,studentName) VALUES(?,?)";
     
            try (Connection conn = this.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                pstmt.setString(2, studentName);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
     
        
  
     
    }
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        connect();
        createNewDatabase("test.db");
        createNewTable();
        InsertApp app = new InsertApp();
        app.insert(2016, "Sercan Gül");
        app.insert(2015, "Berk Gürsoy");
        
        
        
        
        
    }
}
    
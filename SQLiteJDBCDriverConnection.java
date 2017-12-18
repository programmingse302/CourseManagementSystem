package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

 




public class SQLiteJDBCDriverConnection {
    
	
	
	
	
	
	
	
	
	
	

    public static void connect() {
        Connection conn = null;
        try {
        	
           
            String url = "jdbc:sqlite:C:\\Users\\Doruk\\Desktop\\SwingSqlite\\lib\\sqlite-jdbc-3.21.0.jar";
           
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
    	 
        String url = "jdbc:sqlite:C:\\\\Users\\\\Doruk\\\\Desktop\\\\SwingSqlite\\\\lib\\\\sqlite-jdbc-3.21.0.jar" + fileName;
 
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
        String url = "jdbc:sqlite:C:\\Users\\Doruk\\Desktop\\SwingSqlite\\lib\\sqlite-jdbc-3.21.0.jar";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS student (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + ");";
        
        String sql1 = "CREATE TABLE IF NOT EXISTS course (\n"
                + "	courseId integer PRIMARY KEY,\n"
                + "	courseName text NOT NULL,\n"
                + ");";
        
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
        connect();
        createNewDatabase("test.db");
        createNewTable();
    }
}
/*
 * Word Puzzle Time Twitter Bot
 * July 2018
 * Andrea Moncada
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/* https://www.sqlitetutorial.net/sqlite-java */

public class SQLiteConnection {
	private static Timestamp timestamp;
	private static String dbPath;
	private static String dbName;
	private static String tName;
	
	/**
	 * Connect to SQLite database using this class.
	 */
	public SQLiteConnection (String databasePath, String databaseName, String tableName) {
		timestamp = new Timestamp(System.currentTimeMillis());
		dbPath = databasePath;
		dbName = databaseName;
		tName = tableName;
	}
	
	 /**
     * Connect to a database
     * 
     * @param dbname - the database name (including file extension)
     */
    void connect(String dbName) {
        Connection conn = null;
        try {
            // db parameters
            String url = dbPath + dbName;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established. " + timestamp);
            
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
    
    /**
     * Connect to the database
     *
     * @param dbname - the database name (including file extension)
     * @return the Connection object
     */
    private Connection connection(String dbName) {
        // SQLite connection string
        String url = dbPath + dbName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    
    
    /**
     * Insert row into existing table in database. Table schema must match method parameters.
     *
     * @param type String of puzzle type
     * @param puzzle String of puzzle to display
     * @param answer String of puzzle solution
     * @param hint String of puzzle hint
     * @param published integer boolean flag indicating if word has been published
     * @param timestamp String time stamp of publish time
     */
    void insertPuzzleRow(String type, String puzzle, String answer, String hint, int published, String timestamp ) {
        String sql = "INSERT INTO " + tName + "(TYPE, PUZZLE, ANSWER, HINT, PUBLISHED, TIMESTAMP) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = this.connection(dbName);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        		pstmt.setString(1, type);
        		pstmt.setString(2, puzzle);
            pstmt.setString(3, answer);
            pstmt.setString(4, hint);
            pstmt.setInt(5, published);
            pstmt.setString(6, timestamp);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }// end insertAnagram
    
    
    /**
     * Select a random word in table, where PUBLISHED = 0 
     * 
     * @return ID of selected word
     */
    public int selectRandomPuzzleId(String dbTable){
    		int id = 0;
    	
        String sql = "SELECT id FROM " + dbTable + " WHERE PUBLISHED = 0 ORDER BY RANDOM() LIMIT 1";
        
        
        try (Connection conn = this.connection(dbName);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            	id = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id; 
    }
    
    /**
     * Returns puzzle type associated with given id.
     * 
     * @param id - Primary key id of puzzle type
     * @param dbTable - Table name
     * @return puzzle type associated with given id
     */
    public String getPuzzleType(int id, String dbTable){
		String hint = "";
    String sql = "SELECT TYPE FROM " + dbTable + " WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        	 hint = rs.getString("TYPE");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return hint; 
}
    
    
    /**
     * Returns puzzle associated with given id.
     * 
     * @param id - Primary key id of puzzle
     * @param dbTable - Table name
     * @return puzzle associated with given id
     */
    public String getPuzzle(int id, String dbTable){
		String puzzle = "";
    String sql = "SELECT PUZZLE FROM " + dbTable + " WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
    		puzzle = rs.getString("PUZZLE");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return puzzle; 
}
    
    /**
     * Returns puzzle answer associated with given id.
     * 
     * @param id - Primary key id of shuffled word
     * @param dbTable - Table name
     * @return puzzle answer associated with given id
     */
    public String getAnswer(int id, String dbTable){
		String answer = "";
    String sql = "SELECT ANSWER FROM " + dbTable + " WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        	 answer = rs.getString("ANSWER");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return answer; 
}
    
    /**
     * Returns hint associated with given id.
     * 
     * @param id - Primary key id of hint
     * @param dbTable - Table name
     * @return hint associated with given id
     */
    public String getHint(int id, String dbTable){
		String hint = "";
    String sql = "SELECT HINT FROM " + dbTable + " WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        	 hint = rs.getString("HINT");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return hint; 
}
    
    /**
     * Change Published flag to true and add time stamp to row of given Id.
     */
    public void flagAsPubilshed(int id, String dbTable) {
    		String time = timestamp.toString();
        String sql = "UPDATE " + dbTable + " SET PUBLISHED = ?, "
        		+ "TIMESTAMP = ?" 
        		+ "WHERE ID = ?";
 
        try (Connection conn = this.connection(dbName);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set PUBLISHED to true (1)
            pstmt.setInt(1, 1);
            pstmt.setString(2, time); 
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }	
    

}// end SQLiteConnection

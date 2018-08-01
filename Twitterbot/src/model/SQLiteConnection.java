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
	//private static String home = System.getProperty("user.home");;
	private static String dbPath;
	//private static String dbPath = "jdbc:sqlite:" + home + "/shire/sqlite/";
	private static String dbName;
	//private static String dbName = "twBot.db";

//To do:
	//1) Look over encapsulation and security of code
	//2) Debug files to work on the server
	
	/**
	 * 
	 */
	public SQLiteConnection (String databasePath, String databaseName) {
		timestamp = new Timestamp(System.currentTimeMillis());
		dbPath = databasePath;
		dbName = databaseName;
	}
	
	 /**
     * Connect to a database
     * 
     * @param dbname - the database name (including file extension)
     */
    private void connect(String dbName) {
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
 
    
    /*
		TABLE ANAGRAM(
		ID INTEGER PRIMARY KEY,
		WORD TEXT,
		SHUFFLED TEXT,
		THEME TEXT,
		PUBLISHED INT,
		TIMESTAMP TEXT
		);
    */ 
    
    /**
     * Custom method for inserting a new row into the ANAGRAM table in twBot.db
     *
     * @param word String of original word
     * @param shuffled String of shuffled word
     * @param theme String describing word theme
     * @param published integer boolean flag indicating if word has been published
     * @param timestamp String time stamp of publish time
     */
    void insertAnagram(String word, String shuffled, String theme, int published, String timestamp ) {
        String sql = "INSERT INTO anagram(WORD, SHUFFLED, THEME, PUBLISHED, TIMESTAMP) VALUES(?,?,?,?,?)";
 
        try (Connection conn = this.connection(dbName);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        		pstmt.setString(1, word);
            pstmt.setString(2, shuffled);
            pstmt.setString(3, theme);
            pstmt.setInt(4, published);
            pstmt.setString(5, timestamp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }// end insertAnagram
    
    
    /**
     * Select a random word in ANAGRAM table, where PUBLISHED = 0 
     * 
     * @return ID of selected word
     */
    public int selectRandomWord(){
    		int id = 0;
    	
        String sql = "SELECT id FROM ANAGRAM WHERE PUBLISHED = 0 ORDER BY RANDOM() LIMIT 1";
        
        
        try (Connection conn = this.connection(dbName);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            	id = rs.getInt("id");
        	
             //loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") +  "\t");
 //           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id; 
    }
    
    /**
     * Returns word associated with given id.
     * 
     * @param id - Primary key id of word
     * @return word associated with given id
     */
    public String getWord(int id){
		String word = "";
    String sql = "SELECT WORD FROM ANAGRAM WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        	 word = rs.getString("WORD");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return word; 
}
    
    /**
     * Returns shuffled word associated with given id.
     * 
     * @param id - Primary key id of shuffled word
     * @return shuffled word associated with given id
     */
    public String getShuffledWord(int id){
		String word = "";
    String sql = "SELECT SHUFFLED FROM ANAGRAM WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        	 word = rs.getString("SHUFFLED");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return word; 
}
    
    /**
     * Returns theme associated with given id.
     * 
     * @param id - Primary key id of theme
     * @return theme associated with given id
     */
    public String getTheme(int id){
		String word = "";
    String sql = "SELECT THEME FROM ANAGRAM WHERE ID = " + id;
   
    try (Connection conn = this.connection(dbName);
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        	 word = rs.getString("THEME");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return word; 
}
    
    /**
     * Change Published flag to true and add time stamp to row of given Id.
     */
    public void flagAsPubilshed(int id) {
    		String time = timestamp.toString();
        String sql = "UPDATE ANAGRAM SET PUBLISHED = ?, "
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

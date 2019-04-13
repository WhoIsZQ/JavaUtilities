import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ReadInFile {
	
	private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private final static List<List<String>> records = new ArrayList<>();
	
	static String playerId;
	static String playerName;
	static String playerPosition;
	static String playerTeam;
	static PropertyFileReader prop = new PropertyFileReader();
		
	public static void main(String[] args) throws IOException, Exception {
		dbConnect();
		retriveFile();
	}
	
	static void retriveFile() throws IOException {
		String filePath = prop.getfilePath();
		File folder = new File(filePath);
		File[] files = folder.listFiles();		//List of files of that directory
		for(File file : files) {
			//Contains the .csv or .txt
			if(file.getName().endsWith(".csv") || file.getName().endsWith(".txt")) {		 
				String fileName = filePath + File.separatorChar + file.getName().toString();		
				readFile(fileName);
			} else {
				LOGGER.log(Level.INFO, "Not correct file format");
				System.out.println("Check File Format - " + file.getName());	
			}
		}
	}
	
	private static void readFile(String filePath) throws IOException {
		//reading the file 
		try (BufferedReader br = new BufferedReader(new FileReader(filePath));){
			String line;
			while((line = br.readLine())!= null) {
				//splitting the value when we get a comma
				String[] value = line.split(",");
				//adding to the list for further use
				records.add(Arrays.asList(value));
				System.out.println("Records - " + records);
				//records.add(Arrays.asList(value));
				
				playerId = value[0];
				playerName = value[1];
				playerTeam = value[2];
				playerPosition = value[3];

				//uploadPlayerInfo(playerId, playerName, playerTeam, playerPosition); 
			}
			br.close();
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.INFO, "File Not found!!!");
		}
	}
	
	
	//method to parse player info
	public static void dbConnect() throws Exception {

		Connection conn = null;
//		MysqlDataSource dataSource = new MysqlDataSource();
		
		String DatabaseURL = prop.DatabaseURL;
		String user = prop.getUser();
		String password = prop.getPassword();	
		
		System.out.println("Connection details: " + DatabaseURL + " " + user + " " + password); 
		
		System.out.println("Connecting to database...");
		
		//DB connection
		try {
			conn = DriverManager.getConnection(DatabaseURL, user, password);
			System.out.println("Successfully connect to DB!. Uploading data...");			
		} catch (SQLException e) {
			System.out.println("Unable to connect to DB. Check connection details");
		} finally {
		}
	}
	
	private static void uploadPlayerInfo(String playerId2, String playerName2, String playerTeam2, String playerPosition2) {

			String insertSQL = "INSERT INTO javamysql VALUES " + "(" + playerId2 + "," + playerName2 + "," + playerTeam2 + "," + playerPosition2 + ")";
			System.out.println(insertSQL);		
	}
}
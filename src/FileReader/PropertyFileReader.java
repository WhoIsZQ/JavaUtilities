import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
		String result = "";
		InputStream input;
		
		static String USER;
		static String PASSWORD;
		static String DatabaseURL;
		static String FILEPATH;
		static String PROPERTYFILE;

		public void getPropertyValues() throws IOException {
			
		try {		
		Properties prop = new Properties();
		String propertyFileName = "config.properties";
		
		input = getClass().getClassLoader().getResourceAsStream(propertyFileName);
				
		if(input != null) {
			prop.load(input);
		} else {
			throw new FileNotFoundException("property file '" + propertyFileName + "' not found. Check name and path");
		}
		
		USER = prop.getProperty("USER");
		PASSWORD = prop.getProperty("PASSWORD");
		DatabaseURL = prop.getProperty("DatabaseURL");
		FILEPATH = prop.getProperty("FILEPATH");		
		PROPERTYFILE = prop.getProperty("PROPERTYFILE");
		
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 		
			finally {
			input.close();
		}			
	
	}

	public String getUser() {
		return USER;
	}	
	public String getPassword() {
		return PASSWORD;
	}
	public String getDatabaseURL() {
		return DatabaseURL;
	}
	public String getfilePath() {
		return FILEPATH;
	}
	public String getPropertyFile() {
		return PROPERTYFILE;
	}
	
}
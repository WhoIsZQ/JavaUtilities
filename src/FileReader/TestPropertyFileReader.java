import java.io.IOException;

public class TestPropertyFileReader {

	public static void main(String[] args) throws Exception {
		
		PropertyFileReader pro = new PropertyFileReader();
		pro.getPropertyValues();
		
		ReadInFile readIn = new ReadInFile();
		readIn.retriveFile();
			
	}

}
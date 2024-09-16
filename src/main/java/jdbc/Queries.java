package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Queries {
	
	private final static String BASE_PATH = "C:\\Users\\francesco.cutuli\\eclipse-workspace\\products\\src\\main\\resources";
	private final static String FILE_NAME = "query.sql";
	private final static String PATH = BASE_PATH + FILE_NAME;
	
	public static FileReader fileReader = null; 
    public static BufferedReader buffFileReader = null;
			
    public static String getFromFile(String magicString) throws IOException {
		String line;
		String query = "";
        boolean found = false;
        
    	try {
			
			fileReader = new FileReader(PATH);
			buffFileReader = new BufferedReader(fileReader);
			
			while ((line = buffFileReader.readLine()) != null) {
				if (found || line.startsWith("# " + magicString)) {
				    query += line + "\n";
				    found = true;
				    if (line.isBlank())break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (buffFileReader != null){
				buffFileReader.close();
            }
		}
		return query;
    }
}
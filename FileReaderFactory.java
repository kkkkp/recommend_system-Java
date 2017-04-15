import java.util.*;
public class FileReaderFactory {
	private String delimiter;
	
	public FileReaderFactory(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public FileReader createFileReader() {
		if (delimiter.equals("option1")) {

		}
		else if (delimiter.equals("option2")) {
			
		}
		else if (delimiter.equals("option3")) {
			
		}
		else {
			return null;
		}
	}
}

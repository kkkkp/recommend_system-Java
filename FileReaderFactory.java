import java.util.HashMap;

public class FileReaderFactory {
	private String delimiter;
	
	public FileReaderFactory(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public FileReader createFileReader(String file, HashMap<Integer, User> users, HashMap<String, Item> items) {
		if (delimiter.equals("::")) {
			return new OldMovieFileReader(file, users, items);
		}
		else if (delimiter.equals("\"")) {
			return new BookFileReader(file, users, items);
		}
		else if (delimiter.equals(",")) {
			return new NewMovieFileReader(file, users, items);
		}
		else {
			return null;
		}
	}
}

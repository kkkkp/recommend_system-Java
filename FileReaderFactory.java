import java.util.HashMap;

/**
 * File reader factory
 * @author Han Zhu
 *
 */
public class FileReaderFactory {
	private String delimiter;
	
	/**
	 * Constructor.
	 * @param delimiter delimiter of the file
	 */
	public FileReaderFactory(String delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * Create a file reader based on delimiter type.
	 * @param file name of the file to read.
	 * @param users container to pass into file reader constructor
	 * @param items container to pass into file reader constructor
	 * @return the desired file reader
	 */
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

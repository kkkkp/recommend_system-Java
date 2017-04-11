import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileReader
 * @author Patrick_Pu
 *
 */
public class FileReader {
	
	private String filename;
	private ArrayList<String> lines;
	
	/**
	 * The constructor
	 * @param file the file to read
	 */
	public FileReader(String file) {
		this.filename = file;
		this.lines = new ArrayList<String>();
		readFile();
	}
	
	/**
	 * This will read in the entire file.
	 * It will store the contents in the lines ArrayList.
	 */
	private void readFile() {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The accessor method for lines
	 * @return the lines arraylist
	 */
	public ArrayList<String> getLines() {
		return lines;
	}
}

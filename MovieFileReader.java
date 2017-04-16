import java.io.File;
import java.util.*;

/**
 * FileReader
 * @author Patrick_Pu
 *
 */
public class MovieFileReader implements FileReader {
	
	private String filename;

	/**
	 * The constructor
	 * @param file the file to read
	 */
	public MovieFileReader(String file, HashMap<Integer, User> users, HashMap<Integer, Item> items) {
		this.filename = file;
		readFile(users, items);
	}
	
	/**
	 * Read file line by line. Load data into hashmap, User, and item wrappers.
	 * @param users container of users.
	 * @param items container of items.
	 */
	public void readFile(HashMap<Integer, User> users, HashMap<Integer, Item> items) {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			int count = 0, uid = 0, mid = 0;
			double score = 0;
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] seg = line.split("::");
				uid = Integer.parseInt(seg[0]);
				mid = Integer.parseInt(seg[1]);
				score = Double.parseDouble(seg[2]);
				
				if (!users.containsKey(uid)) {
					users.put(uid, new User());
				}
				if (!items.containsKey(mid)) {
					items.put(mid, new Item());
				}
				
				User user = users.get(uid);
				Item item = items.get(mid);
				user.insert(mid, score);
				item.insert(uid, score);
				count++;
			}
			
			in.close();
			System.out.println(" - lines: " + count);
			System.out.println(" - users: " + users.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

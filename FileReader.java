import java.io.File;
import java.util.*;

/**
 * FileReader
 * @author Patrick_Pu
 *
 */
public class FileReader {
	
	private String filename;

	/**
	 * The constructor
	 * @param file the file to read
	 */
	public FileReader(String file, HashMap<Integer, User> users, HashMap<Integer, Movie> movies) {
		this.filename = file;
		readFile(users, movies);
	}
	
	/**
	 * Read file line by line. Load data into hashmap, User, and Movie wrappers.
	 * @param users container of users.
	 * @param movies container of movies.
	 */
	private void readFile(HashMap<Integer, User> users, HashMap<Integer, Movie> movies) {
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
				if (!movies.containsKey(mid)) {
					movies.put(mid, new Movie());
				}
				
				User user = users.get(uid);
				Movie movie = movies.get(mid);
				user.insert(mid, score);
				movie.insert(uid, score);
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

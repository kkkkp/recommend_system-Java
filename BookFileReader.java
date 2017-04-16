import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * File reader for book ratings.
 * @author Han Zhu
 *
 */
public class BookFileReader implements FileReader {
	private String filename;

	/**
	 * The constructor
	 * @param file the file to read
	 */
	public BookFileReader(String file, HashMap<Integer, User> users, HashMap<String, Item> items) {
		this.filename = file;
		readFile(users, items);
	}
	
	@Override
	public void readFile(HashMap<Integer, User> users, HashMap<String, Item> items) {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			int count = 0, uid = 0;
			String mid = "";
			double score = 0;
			
			in.nextLine();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] seg = line.split("\"");
//				System.out.println(Arrays.toString(seg));
				uid = Integer.parseInt(seg[1]);
				if (seg[3].equals("\\")) {
					mid = seg[4].trim();
				} else {
					mid = seg[3].trim();
				}
				score = Double.parseDouble(seg[seg.length - 1]);
				
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
			System.out.println(" - items: " + items.size());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}

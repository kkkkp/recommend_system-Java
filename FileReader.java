import java.util.HashMap;

/**
 * File reader interface.
 * @author Han Zhu
 *
 */
public interface FileReader {

	/**
	 * Read a file and store user and item information into data center containers.
	 * @param users user id to user object mapping
	 * @param items item id to item object mapping
	 */
	public void readFile(HashMap<Integer, User> users, HashMap<String, Item> items);

}

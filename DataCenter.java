import java.util.*;

/**
 * Storage for user-provided data.
 * @author Patrick_Pu
 *
 */
public class DataCenter {

	private HashMap<Integer, User> users;
	private HashMap<Integer, Item> items;
	private MovieFileReader fr;
	
	/**
	 * Initialize instance variables.
	 */
	public DataCenter() {
		this.users = new HashMap<>();
		this.items = new HashMap<>();
	}
	
	/**
	 * Initialize a FileReader object to read file.
	 * @param filename name of file to read.
	 */
	public void loadData(String filename) {
		this.fr = new MovieFileReader(filename, users, items);	
	} 
	
	/**
	 * Getter for users.
	 * @return all users
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
	/**
	 * Get all items.
	 * @return all items.
	 */
	public HashMap<Integer, Item> getItems() {
		return items;
	}

	
	/**
	 * Get the users who have rated the item.
	 * @param item item that users have all rated.
	 * @return a set of user ids.
	 */
	public Set<Integer> getUsersByItem(int mid) {
		return items.get(mid).getUsers();
	}
	
	/**
	 * Get the items that a user have rated.
	 * @param user
	 * @return all items that user have rated.
	 */
	public Set<Integer> getItemsByUser(int uid) {
		return users.get(uid).getItems();
	}
	
	/**
	 * Get a user rating on a item.
	 * @param uid user
	 * @param mid item
	 * @return user's rating on item.
	 */
	public double getRating(int uid, int mid) {
		return users.get(uid).getScore(mid);
	}
	
	/**
	 * Get the average rating of a user.
	 * @param uid user id
	 * @return average rating of user.
	 */
	public double getAvgRatingScoreByUser(int uid) {
		return users.get(uid).getAvg();
	}
	
	/**
	 * Calculate average rating of a item.
	 * @param mid item id
	 * @return average rating of item.
	 */
	public double getAvgRatingScoreByItem(int mid) {
		return items.get(mid).getAvg();
	}
}

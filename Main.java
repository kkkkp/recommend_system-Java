import java.util.*;

/**
 * Tester of the predictor.
 * @author Han Zhu
 *
 */
public class Main {
	public static void main(String[] args) {
		DataCenter dc = new DataCenter();
		Algorithm algo = new AlgorithmKNN();

		Scanner in = new Scanner(System.in);		
		
		System.out.println("Enter a filename: ");
		String filename = in.nextLine();
		
		System.out.println("Enter user id: ");
		int uid = in.nextInt();
		
		System.out.println("Enter item id: ");
		int mid = in.nextInt();
		
		System.out.println("Enter a threshold: ");
		int n = in.nextInt();

		
		load(dc, algo, filename);
		predict(algo, uid, mid);
		topN(algo, uid, n);
		

		in.close();
	}
	
	/**
	 * Load data and display elapsed time.
	 * @param dc storage class to load data into.
	 * @param algo predictor.
	 * @param filename file to read.
	 */
	public static void load(DataCenter dc, Algorithm algo, String filename) {
		System.out.println("\nLoading data...");
		long start = System.currentTimeMillis();
		dc.loadData(filename);
		long end = System.currentTimeMillis();
		System.out.println("done (" + (end - start) + " ms)");
		algo.loadDataCenter(dc);
	}
	

	/**
	 * Predict a user's rating on a item and display elapsed time.
	 * @param algo prediction algorithm to use
	 * @param uid user
	 * @param mid item
	 */
	public static void predict(Algorithm algo, int uid, int mid) {
		System.out.println("\nGetting prediction...");
		long start = System.currentTimeMillis();
		double rating = algo.getRatingByUserAndItem(uid, mid);
		long end = System.currentTimeMillis();
		System.out.println("Rating [rating = " + rating + "]");
		System.out.println("done (" + (end - start) + " ms)");
	}
	

	/**
	 * Predict a user's top n highest-rated items.
	 * @param algo prediction algorithm to use
	 * @param uid user
	 * @param n threshold
	 */
	public static void topN(Algorithm algo, int uid, int n) {
		System.out.println("\nGetting top-n predictions...");
		long start = System.currentTimeMillis();
		Set<Integer> predictions = algo.getTopNRatingItems(uid, n);
		long end = System.currentTimeMillis();
		System.out.println("Items: " + predictions);
		System.out.println("done (" + (end - start) + " ms)");
	}
}

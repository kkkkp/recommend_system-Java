import java.util.*;

/**
 * Tester of the predictor.
 * @author Han Zhu
 *
 */
public class Main {
	public static void main(String[] args) {
		Algorithm algo = new AlgorithmKNN();

		Scanner in = new Scanner(System.in);		
		
		System.out.println("Enter a filename: ");
		String filename = in.nextLine();
		
		System.out.println("Enter the file delimiter: ");
		String delimiter = in.nextLine();
		DataCenter dc = new DataCenter(delimiter);
		
		System.out.println("Enter user id: ");
		int uid = in.nextInt();
		in.nextLine();
		
		System.out.println("Enter item id: ");
		String mid = in.nextLine();
		
		System.out.println("Enter a threshold: ");
		int n = in.nextInt();

		
		load(dc, algo, filename);
		predict(algo, uid, mid);
		topN(algo, uid, n);

		in.close();
//		TODO: to see 2C, uncomment
//		experiment(dc);
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
	public static void predict(Algorithm algo, int uid, String mid) {
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
		Set<String> predictions = algo.getTopNRatingItems(uid, n);
		long end = System.currentTimeMillis();
		System.out.println("Items: " + predictions);
		System.out.println("done (" + (end - start) + " ms)");
	}
	
	/**
	 * 
	 * @param dc
	 * @param filename
	 */
	public static void experiment(DataCenter dc) {
		Algorithm knnPearson = new AlgorithmKNN();
		Algorithm knnCosine = new Algorithm2A();
		Algorithm baseline = new Algorithm2B();
		
		knnPearson.loadDataCenter(dc);
		knnCosine.loadDataCenter(dc);
		baseline.loadDataCenter(dc);
		
		double[] mse = new double[3];
		mse[0] = trial(knnPearson, dc);
		mse[1] = trial(knnCosine, dc);
		mse[2] = trial(baseline, dc);
		System.out.println("The standard deviation of prediciton: " + Arrays.toString(mse));
	}
	
	/**
	 * 
	 * @param algo
	 * @param dc
	 * @return
	 */
	public static double trial(Algorithm algo, DataCenter dc) {
		double sum = 0;
		int count = 0;
		System.out.println("For algorithm: " + algo.getClass().getName() + "...");
		long start = System.currentTimeMillis();
		for (Integer uid: dc.getUsers().keySet()) {
			for (Integer mid: dc.getItemsByUser(uid)) {
				sum += Math.pow(dc.getRating(uid, mid) - algo.getRatingByUserAndItem(uid, mid), 2);
				count++;
				long end = System.currentTimeMillis();
				if (end - start > 10000) {
					System.out.println(" - Heartbeating for " + (end - start) + " ms @" + count);
					start = System.currentTimeMillis();
				}
			}
		}
		return sum / count;
	}
}

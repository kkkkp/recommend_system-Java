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
<<<<<<< HEAD
		Scanner in = new Scanner(System.in);
		
// 		TODO: uncomment	
//		System.out.print("Enter a filename: ");
//		String filename = in.nextLine();
//		System.out.print("Enter user id: ");
//		int uid = in.nextInt();
//		System.out.print("Enter movie id: ");
//		int mid = in.nextInt();
//		System.out.print("Enter a number: ");
//		int n = in.nextInt();
		
		
		String filename = "ratings.dat";
		int uid = 5;
		int mid = 10;
		int n = 5;
		System.out.println("Enter a filename: " + filename);
		
		System.out.println("Enter user id: " + uid);
		
		System.out.println("Enter movie id: " + mid);
		
		System.out.println("Enter a number: " + n);
		
=======
		Scanner in = new Scanner(System.in);		
		
		System.out.println("Enter a filename: ");
		String filename = in.nextLine();
		
		System.out.println("Enter user id: ");
		int uid = in.nextInt();
		
		System.out.println("Enter movie id: ");
		int mid = in.nextInt();
		
		System.out.println("Enter a threshold: ");
		int n = in.nextInt();
>>>>>>> xpu2
		
		load(dc, algo, filename);
		predict(algo, uid, mid);
		topN(algo, uid, n);
		
<<<<<<< HEAD
	}
	
=======
		in.close();
	}
	
	/**
	 * Load data and display elapsed time.
	 * @param dc storage class to load data into.
	 * @param algo predictor.
	 * @param filename file to read.
	 */
>>>>>>> xpu2
	public static void load(DataCenter dc, Algorithm algo, String filename) {
		System.out.println("\nLoading data...");
		long start = System.currentTimeMillis();
		dc.loadData(filename);
		long end = System.currentTimeMillis();
		System.out.println("done (" + (end - start) + " ms)");
		algo.loadDataCenter(dc);
	}
	
<<<<<<< HEAD
=======
	/**
	 * Predict a user's rating on a movie and display elapsed time.
	 * @param algo prediction algorithm to use
	 * @param uid user
	 * @param mid movie
	 */
>>>>>>> xpu2
	public static void predict(Algorithm algo, int uid, int mid) {
		System.out.println("\nGetting prediction...");
		long start = System.currentTimeMillis();
		double rating = algo.getRatingByUserAndMovie(uid, mid);
		long end = System.currentTimeMillis();
		System.out.println("Rating [rating = " + rating + "]");
		System.out.println("done (" + (end - start) + " ms)");
	}
	
<<<<<<< HEAD
=======
	/**
	 * Predict a user's top n highest-rated movies.
	 * @param algo prediction algorithm to use
	 * @param uid user
	 * @param n threshold
	 */
>>>>>>> xpu2
	public static void topN(Algorithm algo, int uid, int n) {
		System.out.println("\nGetting top-n predictions...");
		long start = System.currentTimeMillis();
		Set<Integer> predictions = algo.getTopNRatingMovies(uid, n);
		long end = System.currentTimeMillis();
		System.out.println("Movies: " + predictions);
		System.out.println("done (" + (end - start) + " ms)");
	}
}

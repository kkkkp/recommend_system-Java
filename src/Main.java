import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		DataCenter dc = new DataCenter();
		AlgorithmKNN predictor = new AlgorithmKNN();
		
		System.out.println("Enter the name of the ratings file:");
		String fileName = in.nextLine();
		
		System.out.println("Loading...");
		dc.loadData(fileName);
		predictor.loadDataCenter(dc);
		
		boolean quit = false;
		while (!quit) {
			System.out.println("Choose an option:");
			System.out.println("\ta. Predict a user's preference for an item.");
			System.out.println("\tb. Predict a user's top n preferences.");
			System.out.println("\tq. Quit.");
			String choice = in.nextLine();
			switch (choice.toLowerCase()) {
				case "a":
					System.out.println("Enter a user ID:");
					User user = new User(Integer.parseInt(in.nextLine()));
					
					System.out.println("Enter a movie ID:");
					Movie movie = new Movie(Integer.parseInt(in.nextLine()));
					
					System.out.println("Calculating...");
					double pref = predictor.getRatingByUserAndMovie(user, movie);
					System.out.println("User " + user.getId() + "'s predicted rating for movie " + movie.getId() + " is " + pref);
					
					break;
				case "b":
					break;
				case "q":
					quit = true;
					break;
				default:
					System.out.println("Please enter a valid option.");
			}
		}
		
		in.close();
	}
}

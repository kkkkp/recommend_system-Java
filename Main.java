import java.util.*;

public class Main {
	public static void main(String[] args) {
		DataCenter dc = new DataCenter();
		System.out.print("Enter a filename: ");
		Scanner in = new Scanner(System.in);
		String filename = in.nextLine();
		
		dc.loadData(filename);
	}
}

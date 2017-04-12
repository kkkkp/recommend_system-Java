
public class Rating {
	private int score;
//	private boolean liked;
//	private boolean purchased;
	
	public Rating() {
		this.score = 0;
//		this.liked = false;
//		this.purchased = false;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
//	public void setLiked(boolean liked) {
//		this.liked = liked;
//	}
//	
//	public void setPurchased(boolean purchased) {
//		this.purchased = purchased;
//	}
	
	public int getScore() {
		return score;
	}
	
//	public boolean getLiked() {
//		return liked;
//	}
//	
//	public boolean getPurchased() {
//		return purchased;
//	}
	
	public int toNumber() {
		return score;
	}
}


public class Rating {
	private double score;
//	private boolean liked;
//	private boolean purchased;
	
	public Rating(double score) {
		this.score = score;
//		this.liked = false;
//		this.purchased = false;
	}
	
//	public void setScore(double score) {
//		this.score = score;
//	}
	
//	public void setLiked(boolean liked) {
//		this.liked = liked;
//	}
//	
//	public void setPurchased(boolean purchased) {
//		this.purchased = purchased;
//	}
	
	public double getScore() {
		return score;
	}
	
//	public boolean getLiked() {
//		return liked;
//	}
//	
//	public boolean getPurchased() {
//		return purchased;
//	}
	
	public double toNumber() {
		return score;
	}
}

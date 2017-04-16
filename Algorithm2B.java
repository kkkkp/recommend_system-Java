import java.util.*;

/**
 * Use Baseline Predictor to make recommendations.
 * @author Han Zhu
 *
 */
public class Algorithm2B implements Algorithm {

	private DataCenter dc;
	private HashMap<Integer, Double> userBaseLine;
	private HashMap<String, Double> itemBaseLine;
	private double avg;
	
	@Override
	public void loadDataCenter(DataCenter dc) {
		this.dc = dc;
		this.userBaseLine = new HashMap<>();
		this.itemBaseLine = new HashMap<>();
		this.avg = -1;
	}

	@Override
	public double getRatingByUserAndItem(int uid, String mid) {
		return getAvg() + getUserBaseLine(uid) + getItemBaseLine(mid);
	}

	@Override
	public Set<String> getTopNRatingItems(int uid, int n) {
		Set<String> items = new HashSet<>();
		PriorityQueue<ItemContainer> pq = new PriorityQueue<>();

		int count = 0;
		long start = System.currentTimeMillis();
		long end = 0;

		for (String mid: dc.getItems().keySet()) {
			count++;
			if (dc.getItemsByUser(uid).contains(mid)) {
				continue;
			}
			ItemContainer mc = new ItemContainer(mid, getRatingByUserAndItem(uid, mid));

			if (pq.size() < n) {
				pq.offer(mc);
			} else if (pq.peek().getPredict() < mc.getPredict()) {
				pq.poll();
				pq.offer(mc);
			}
			end = System.currentTimeMillis();
			
			if (end - start > 10000) {
				System.out.println(" - Heartbeating " + (end -  start) + " @" + count);
				start = end;
			}
		}
		
		while (!pq.isEmpty()) {
			items.add(pq.poll().getId());
		}
		
		return items;
	}
	
	/**
	 * Get overall average rating.
	 * @return overall average rating
	 */
	private double getAvg() {
		if (avg != -1) {
			return avg;
		}
		double sum = 0;
		int count = 0;
		for (Integer uid: dc.getUsers().keySet()) {
			for (String mid: dc.getItemsByUser(uid)) {
				sum += dc.getRating(uid, mid);
				count++;
			}
		}
		avg = sum / count;
		return avg;
	}
	
	/**
	 * Get the baseline prediction for a user.
	 * @param uid user
	 * @return baseline for user
	 */
	private double getUserBaseLine(int uid) {
		if (userBaseLine.containsKey(uid)) {
			return userBaseLine.get(uid);
		}
		
		double sum = 0;
		double rst = 0;
		for (String mid: dc.getItemsByUser(uid)) {
			sum += dc.getRating(uid, mid);
		}
		rst = sum / dc.getItemsByUser(uid).size() - getAvg();
		userBaseLine.put(uid, rst);
		return rst;
	}
	
	/**
	 * Get the baseline prediction for an item.
	 * @param mid item
	 * @return baseline for an item
	 */
	private double getItemBaseLine(String mid) {
		if (itemBaseLine.containsKey(mid)) {
			return itemBaseLine.get(mid);
		}
		
		double sum = 0;
		double rst = 0;
		for (Integer uid: dc.getUsersByItem(mid)) {
			sum += dc.getRating(uid, mid) - getUserBaseLine(uid);
		}
		rst = sum / dc.getUsersByItem(mid).size() - getAvg();
		itemBaseLine.put(mid, rst);
		return rst;
	}

}

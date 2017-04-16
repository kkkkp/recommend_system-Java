import java.util.*;

/**
 * Uses k nearest neighbor algorithm to make recommendation.
 * @author Patrick_Pu
 *
 */
public class AlgorithmKNN implements Algorithm {
	private DataCenter dc;
	private int SIZE = 20;
	private HashMap<int[], Double> similarities;

	@Override
	public void loadDataCenter(DataCenter dc) {
		this.dc = dc;
		this.similarities = new HashMap<>();
	}

	@Override
	public double getRatingByUserAndItem(int uid, String mid) {
		Set<Integer> neighbors = getNeighbors(uid, mid);
//		System.out.println("neighbors: " + neighbors.size());

		double avg = dc.getAvgRatingScoreByUser(uid);
		double numerator = 0, denominator = 0;

		for (Integer u: neighbors) {
			double s = getSimilarity(uid, u);
//			System.out.println("similarity: " + s);
			numerator += s * (dc.getRating(u, mid) - dc.getAvgRatingScoreByUser(u));
			denominator += Math.abs(s);
		}

		if (denominator == 0) {
			return avg;
		}
		return avg + (numerator / denominator);
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
			}
			else if (pq.peek().getPredict() < mc.getPredict()) {
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
	 * Get a user's neighbors - users who have rated the item.
	 * @param uid user to predict
	 * @param mid item to predict
	 * @return a set of user ids.
	 */
	private Set<Integer> getNeighbors(int uid, String mid) {
		Set<Integer> neighbors = new HashSet<>();
		PriorityQueue<UserContainer> pq = new PriorityQueue<>();

		for (Integer n: dc.getUsersByItem(mid)) {
			if (uid == n) {
				continue;
			}

			UserContainer uc = new UserContainer(n, getSimilarity(uid, n));
			if (pq.size() < SIZE) {
				pq.offer(uc);
			}
			else if (pq.peek().getSimilarity() < uc.getSimilarity()) {
				pq.poll();
				pq.offer(uc);
			}
		}

		while (!pq.isEmpty()) {
			neighbors.add(pq.poll().getId());
		}

		return neighbors;
	}

	/**
	 * Calculate the similarity between two users.
	 * @param uid1 id of first user
	 * @param uid2 id of second user
	 * @return similarity between users.
	 */
	private double getSimilarity(int uid1, int uid2) {
		if (uid1 > uid2) {
			int temp = uid2;
			uid2 = uid1;
			uid1 = temp;
		}

		int[] tuple = new int[] {uid1, uid2};
		if (similarities.containsKey(tuple)) {
			return similarities.get(tuple);
		}

		Set<String> commons = getCommonItems(uid1, uid2);
//		System.out.println("commons: " + commons.size());
		double numerator = 0, denominator1 = 0, denominator2 = 0, score1 = 0, score2 = 0, rst = 0;
		double avg1 = dc.getAvgRatingScoreByUser(uid1);
		double avg2 = dc.getAvgRatingScoreByUser(uid2);

		for (String m: commons) {
			score1 = dc.getRating(uid1, m) - avg1;
			score2 = dc.getRating(uid2, m) - avg2;
			numerator += score1 * score2;
			denominator1 += Math.pow(score1, 2);
			denominator2 += Math.pow(score2, 2);
		}


		if (denominator1 <= 0.00001 || denominator2 <= 0.00001) {
			rst = 0;
		} else {
			rst = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
		}
		similarities.put(tuple, rst);

		return rst;
	}

	/**
	 * Find the items that are rated by both users.
	 * @param uid1 id of first user
	 * @param uid2 id of second user
	 * @return common items of both users.
	 */
	private Set<String> getCommonItems(int uid1, int uid2) {
		Set<String> rst = new HashSet<>(dc.getItemsByUser(uid1));
		Set<String> compare = dc.getItemsByUser(uid2);
		rst.retainAll(compare);
		return rst;
	}
}

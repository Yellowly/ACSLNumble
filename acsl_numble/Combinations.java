package acsl_numble;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
	private static void helper(List<int[]> combinations, int[] data, int start, int end, int idx) {
		if (idx == data.length) {
			combinations.add(data.clone());
		} else if (start <= end) {
			data[idx] = start;
			helper(combinations, data, start + 1, end, idx + 1);
			helper(combinations, data, start + 1, end, idx);
		}
	}
	/**
	 * generates all possible combinations of r items from n total numbers
	 * @param n - largest number to choose
	 * @param r - size of the combinations
	 * @return - List<int[]> holding all combinations of r integers from n
	 */
	public static List<int[]> generate(int n, int r) {
		List<int[]> res = new ArrayList<>();
		helper(res,new int[r],0,n-1,0);
		return res;
	}
	
	/**
	 * generates all combinations of r true's in an array of size n
	 * rather slow and inefficient, but it's an easy implementation 
	 * @param n - size of the array of true/false
	 * @param r - number of true's in the array
	 * @return - List<boolean[]> holding all combinations of true/false with r true's in an array of size n
	 */
	public static List<boolean[]> generateBools(int n, int r) {
		List<boolean[]> res = new ArrayList<>();
		List<int[]> combinations = generate(n,r);
		for(int[] comb : combinations) {
			boolean[] temp = new boolean[n];
			for(int i=0;i<r;i++) {
				temp[comb[i]]=true;
			}
			res.add(temp);
		}
		return res;
	}
	
	/**
	 * Total number of combinations of r items from n
	 * Used for calculating probabilities 
	 * nCr
	 * if you don't know what nCr is then take pre-calc or just google it.
	 * @param n - items to choose from
	 * @param r - number of items
	 * @return - Total number of combinations of r items from n
	 */
	public static int combinations(int n, int r) {
		return (int) ((factorial(n))/(factorial(n-r)*factorial(r)));
	}
	
	private static long factorial(int n) {
		if(n<0) return -1;
		long res = 1;
		for(int i=0;i<=n;i++) {
			res*=i;
		}
		return res;
	}
}

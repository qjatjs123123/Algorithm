
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
			
		ArrayList<Deque<Integer>> q = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			Deque<Integer> tmp = new LinkedList<>();
			int[] t1 = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			for (Integer t : t1) tmp.add(t);
			q.add(tmp);
		}
		
		int cnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < cnt; i++) {
			String[] tmp = br.readLine().split(" ");
			int num = Integer.parseInt(tmp[0]) - 1;
			int direct = Integer.parseInt(tmp[1]);
			
			ArrayList<int[]> left = new ArrayList<>();
			ArrayList<int[]> right = new ArrayList<>();
			
			int[] t1 = {num, direct};
			left.add(t1);
			
			int cur_idx = num;
			int cur_direct = direct;
			for (int new_idx = num - 1; new_idx >= 0; new_idx--) {
				Deque<Integer> cur_left = q.get(new_idx);
				Deque<Integer> cur_right = q.get(cur_idx);
				
				if (getIndex(cur_left, 2) == getIndex(cur_right, 6)) break;
				cur_direct *= -1;
				int[] t = {new_idx, cur_direct};
				left.add(t);
				cur_idx = new_idx;
			}
			
			cur_idx = num;	
			cur_direct = direct;
			for (int new_idx = num + 1; new_idx < 4; new_idx++) {
				Deque<Integer> cur_left = q.get(cur_idx);
				Deque<Integer> cur_right = q.get(new_idx);
				
				if (getIndex(cur_left, 2) == getIndex(cur_right, 6)) break;
				cur_direct *= -1;
				int[] t = {new_idx, cur_direct};
				left.add(t);
				cur_idx = new_idx;
			}
			
			for (int j = 0; j < left.size(); j++) {
				int[] t = left.get(j);
				int idx = t[0];
				int n_direct = t[1];
				
				if (n_direct == 1) q.get(idx).addFirst(q.get(idx).pollLast());
				else q.get(idx).addLast(q.get(idx).pollFirst());
			}			
		}	
		System.out.println(getAnswer(q));
    }
	
	static int getAnswer(ArrayList<Deque<Integer>> q) {
		int ans = 0;
		for (int i = 0; i < 4 ; i++ ) {
			if (q.get(i).getFirst() == 1) ans += Math.pow(2, i);
		}
		return ans;
	}
	
	static int getIndex(Deque<Integer> q, int idx) {
		Iterator<Integer> it = q.iterator();
		int cnt = 0;
		int ans = 0;
		while (it.hasNext()) {
			int t = it.next();
			if (cnt == idx) {
				ans = t;
				break;
			}
			cnt++;
		}
		return ans;
	}
}
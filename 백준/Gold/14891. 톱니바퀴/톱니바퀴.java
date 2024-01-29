import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	static Deque[] wheel = new Deque[5];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= 4; i++) {
			int[] list = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			Deque<Integer> tmp = new LinkedList<>();
			
			for (int num : list) tmp.addLast(num);
			wheel[i] = tmp;
		}
		
		int k = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < k; i++) {
			int[] list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int idx = list[0];
			int direct = list[1];
			
			Deque<int[]> queue = new LinkedList<>();
			int[] tmp = {idx, direct};
			queue.add(tmp);
			int new_direct = direct;
			//left
			for (int l = idx; l > 1; l--) {
				int cur_left = getNum(true, wheel[l]);
				int new_right = getNum(false, wheel[l - 1]);
				
				if (cur_left == new_right) break;
				
				new_direct *= -1;
				int[] arr = {l - 1, new_direct};
				queue.add(arr);
			}
			
			new_direct = direct;
			//right
			for (int r = idx; r < 4; r++) {
				int cur_right = getNum(false, wheel[r]);
				int new_left = getNum(true, wheel[r + 1]);
				
				if (cur_right == new_left) break;
				
				new_direct *= -1;
				int[] arr = {r+1, new_direct};
				queue.add(arr);
			}
			
			//rotate
			while (!queue.isEmpty()) {
				int[] arr = queue.pollFirst();
				int cur_direct = arr[1];
				Deque cur_deque = wheel[arr[0]];
				
				if (cur_direct == 1) {
					cur_deque.addFirst(cur_deque.pollLast());
				}else {
					cur_deque.addLast(cur_deque.pollFirst());
				}
				
			}
			
		}
		int ans = 0;
		for (int j = 1; j <= 4; j++) {
			Deque<Integer> deque = wheel[j];
			
			if (deque.pollFirst() == 1) ans += Math.pow(2, j-1);
		}
		System.out.println(ans);
	}
	
	static int getNum(boolean isLeft, Deque<Integer> deque) {
		
		int point = isLeft ? 6 : 2;
		int idx = 0;
		
		for (Integer num : deque) {
			if (idx == point) return num;
			idx++;
		}
		return -1;
	}
}
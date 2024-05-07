import java.io.*;
import java.util.*;


public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static int[] count;
	static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		count = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int prev = -1;
			for (int j = 0; j < n; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if (j == 0) {
					prev = cur;
					continue;
				}
				
				count[cur]++;
				
				if (!graph.containsKey(prev)) {
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(cur);
					graph.put(prev, tmp);
				} else {
					ArrayList<Integer> tmp = graph.get(prev);
					tmp.add(cur);
					graph.put(prev, tmp);
				}
				prev = cur;
			}
		}
		
		Deque<Integer> deque = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if (count[i] == 0) deque.add(i);
		}
		
		int ans = 0;
		while (!deque.isEmpty()) {
			int num = deque.pollFirst();
			ans++;
			sb.append(num).append("\n");
			if (graph.containsKey(num)) {
				ArrayList<Integer> list = graph.get(num);
				
				for (int n : list) {
					if (--count[n] == 0) deque.add(n);
				}
			}
		}
		boolean flg = true;
		for (int i = 1; i <= N; i++) {
			if (count[i] != 0) {
				flg = false;
				break;
			}
		}
		if (flg)
			System.out.println(sb.toString());
		else
			System.out.println(0);
	}
}

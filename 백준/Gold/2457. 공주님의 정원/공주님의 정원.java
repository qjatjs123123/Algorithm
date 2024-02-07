
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> array = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			array.add(new int[] {a*100 + b, c*100 + d});
		}
		
		Collections.sort(array, (a, b) -> {
			if (a[0] == b[0]) return a[1] - b[1];
			return a[0] - b[0];
		});
		int target = 301;
		int ans = 0;
		boolean flg = false;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			int[] flower = array.get(i);
			int start = flower[0];
			int end = flower[1];
			if (start <= target) {
				pq.add(-end);
			}else {
				if (pq.size() == 0) {
					ans = 0;
					break;
				} else {
					int MAX_NUM = -pq.poll();
					ans += 1;
					target = MAX_NUM;
					if (MAX_NUM >= 1131) {
						flg = true;
						break;
					}
					pq.clear();
					i--;
				}
			}
		}
		if (!flg && pq.size() > 0) {
			int MAX_NUM = -pq.poll();
			if (MAX_NUM >= 1131) ans += 1;
			else ans = 0;
		}
		System.out.println(ans);
		//for (int[] a : array) System.out.println(Arrays.toString(a));
	}
}
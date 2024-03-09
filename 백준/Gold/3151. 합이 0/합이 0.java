import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int[] graph = new int[N];
		int[] dict = new int[20001];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
			dict[graph[i] + 10000] += 1;
		}
		
		Arrays.sort(graph);
//		System.out.println(Arrays.toString(graph));
		int[] use = new int[20001];
		long total = 0;
		for (int i = 0; i < N; i++) {
			if (graph[i] > 0) break;
			int target = -graph[i];
			int start = i + 1;
			int prev = -1;
			int end = N - 1;
			for (int j = 0; j < 20001; j++) use[j] = 0;
			dict[graph[i] + 10000] -= 1;

			
			while (start < end) {
//				use[graph[start] + 10000] += 1; 
				if (prev != start) use[graph[start] + 10000] += 1; 
				prev = start;
//				if (i == 3) {
//					System.out.println(start + " " +use[2 + 10000]);
//				}
				int hap = graph[start] + graph[end];
				if (hap == target) {
					int minus = 0;
					if (graph[start] == graph[end]) minus = use[graph[start] + 10000];
					total += dict[graph[end] + 10000] - minus;
					start++;
				}
				else if (hap > target) end--;
				else start++;
			}
			
		}
		
		System.out.println(total);
	}
	
}

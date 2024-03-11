import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		graph = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) graph[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(graph);
		
		long max_num = Long.MAX_VALUE;
		int one = 0;
		int two = 0;
		int three = 0;

		for (int i = 0; i < N - 2; i++) {
			int start = i + 1;
			int end = N - 1;
			int target = -graph[i];
			boolean isStart= false;
			boolean isEnd= false;
			long cur_max = Long.MAX_VALUE;
			while (start < end) {
				long hap = graph[start] + graph[end];
				long diff = Math.abs(target - hap);
				
				if (max_num >= diff) {
					max_num = diff;
					one = graph[i];
					two = graph[start];
					three = graph[end];
				}


				if (hap > target) {
					end--;
					isStart = true;
				}
				else if (hap < target) {
					start++;
					isStart = false;
				}
				else {
					break;
				}
			}


		}
		
		System.out.println(one + " " + two + " " + three);
	}
	
}

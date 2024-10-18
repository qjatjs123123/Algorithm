import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int N, M;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		TreeMap tm = new TreeMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
            Arrays.sort(graph[i]);  
		}
		
		for (int i = 0; i < N; i++) {
			tm.put(graph[i][0], i);
		}
		
		int CUR_MAX = getMax(tm);
		int[] pointer = new int[N];
		
		while (true) {
			Entry cur_map = tm.pollFirstEntry();
			int key = (int)cur_map.getKey();
			int value = (int) cur_map.getValue();
			
			
			if (pointer[value] + 1 == M ) break;
			
			pointer[value] = Math.min(pointer[value] + 1, M - 1);
			tm.put(graph[value][pointer[value]], value);
			
			CUR_MAX = Math.min(CUR_MAX, getMax(tm));
		}
		System.out.println(CUR_MAX);
	}
	
	static int getMax(TreeMap tm) {
		return (int)tm.lastKey() - (int)tm.firstKey();
	}
}

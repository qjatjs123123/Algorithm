
import java.io.*;
import java.util.*;


public class Main {
	static int N, M;
	static HashMap<Integer, Boolean> dict = new HashMap<>(); 
	static ArrayList<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			dict.put(num, true);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int[] tmp = new int[cnt];
			
			for (int j = 0; j < cnt; j++) {
				tmp[j] = Integer.parseInt(st.nextToken());
			}
			
			list.add(tmp);
		}
		int ans = list.size();
		boolean[] visited = new boolean[list.size()];
		while (true) {
			boolean flg = false;
			
			int idx = 0;
  			for (int[] arr : list) {
  				if (visited[idx++]) continue;
  				
				boolean flg1 = false;
				for (int k : arr) {
					if (dict.containsKey(k)) { 
						flg1 = true;
					}
				}
				
 				if (!flg1) continue;
 				visited[idx-1] = true;
 				
				ans -= 1;

       			for (int k : arr) {
					if (!dict.containsKey(k)) {
						dict.put(k, true);
						flg = true;
					}
				}
			}
			
        if (!flg) break;
		}
		
		System.out.println(ans);
	}

}

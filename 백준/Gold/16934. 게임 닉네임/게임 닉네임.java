import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] nxt = new int[1_000_000][27];
	static int[] visited = new int[1_000_000];
	static StringBuilder sb = new StringBuilder();
	static int node = 1;
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int cur_root = 0;
			String key = "";
			boolean flg = false;
			
			for (int j = 0; j < name.length(); j++) {
				char c = name.charAt(j);
				key += c;
				
				int idx = (int)c - 96;
				if (nxt[cur_root][idx] == 0) {
					nxt[cur_root][idx] = node++;
					cur_root = node - 1;
					if (!flg) {
						sb.append(key);
						sb.append("\n");
					}

					flg = true;
				} else {
					cur_root = nxt[cur_root][idx];
				}
			}
			visited[cur_root]++;
			
			if (!flg) {

				String new_key = key;
				if (visited[cur_root] > 1) new_key += (visited[cur_root]);
				sb.append(new_key);
				sb.append("\n");
				
			}
		}
		
		System.out.println(sb.toString());
		
	}

}

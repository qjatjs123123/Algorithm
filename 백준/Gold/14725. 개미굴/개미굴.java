import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static TreeMap root = new TreeMap();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cnt = Integer.parseInt(st.nextToken());
			TreeMap tmp = root;
			
			for (int j = 0; j < cnt; j++) {
				String str = st.nextToken();
				
				if (!tmp.containsKey(str)) {
					TreeMap new_map = new TreeMap();
					tmp.put(str, new_map);
					tmp = new_map;
				} else {
					TreeMap cur_map = (TreeMap) tmp.get(str);
					tmp = cur_map;
				}
			}
			
		}
		recursion(root, "");
		
		System.out.println(sb.toString());

	}
	
	static void recursion(TreeMap map, String EXP) {
		for (Object cur_set : map.keySet()) {
			String new_str = EXP + cur_set;
			sb.append(new_str);
			sb.append("\n");
			if (!map.containsKey(cur_set)) continue;
			recursion((TreeMap)map.get(cur_set), EXP + "--");
		}
	}

}

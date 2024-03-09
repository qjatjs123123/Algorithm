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
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<String> stack = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			ArrayList<int[]> tmp = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				tmp.add(new int[] {Integer.parseInt(st.nextToken()), j});
			}
			StringBuilder s = new StringBuilder();
			Collections.sort(tmp, (a, b) -> a[0] - b[0]);
			
			String prev = "";
			for (int idx = 0; idx < M; idx++) {
				int[] arr = tmp.get(idx);
				if (idx == 0) {
					s.append(Integer.toString(arr[1]));
					prev = Integer.toString(arr[1]);
					continue;
				}
				
				if (tmp.get(idx - 1)[0] == arr[0]) {
					s.append(prev);
				}
				else {
					prev = Integer.toString(arr[1]);
					s.append(prev);
				}
			}
			

			stack.add(s.toString());
		}
		
		int total = 0;

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (stack.get(i).equals(stack.get(j))) total++;
			}
		}
		System.out.println(total);
		
	}
	
}

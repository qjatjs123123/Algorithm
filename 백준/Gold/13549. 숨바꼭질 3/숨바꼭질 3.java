import java.util.*;
import java.io.*;

public class Main
{

	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int k = Integer.parseInt(str[1]);
		
		int[] dp = new int[100001];
		
		for (int i = 0; i < 100001; i ++) dp[i] = Integer.MAX_VALUE;
		
		Deque<int[]> q = new LinkedList<>();
		int[] tmp = {n, 0};
		q.add(tmp);
		
		while (!q.isEmpty()) {
			tmp = q.pollFirst();
			int cur_node = tmp[0];
			int cur_cnt = tmp[1];
			
			int[][] cases = {{cur_node - 1, 1}, {cur_node + 1, 1}, {cur_node * 2, 0}};
			if (dp[cur_node] < cur_cnt ) continue;
			if (cur_node == k) {
				System.out.println(cur_cnt);
				break;
			}
			
			for (int[] c : cases) {
				int new_node = c[0];
				int new_cnt = c[1];
				
				if (0 <= new_node && new_node <= 100000 && dp[new_node] > cur_cnt + new_cnt) {
					dp[new_node] = cur_cnt + new_cnt;
					int[] t = {new_node, cur_cnt + new_cnt};
					q.add(t);
				}
			}
			
		}
		
		
    }	
		
}
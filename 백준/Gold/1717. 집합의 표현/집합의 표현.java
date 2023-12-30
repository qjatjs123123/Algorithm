import java.util.*;
import java.io.*;

public class Main
{
	static int[] node;
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		
		node = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			node[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			str = br.readLine().split(" ");
			int cmd = Integer.parseInt(str[0]);
			int a = Integer.parseInt(str[1]);
			int b = Integer.parseInt(str[2]);	
			
			int pa = findParentNode(a);
			int pb = findParentNode(b);
			if (cmd == 0) {
				node[Math.min(pa, pb)] = Math.max(pa, pb);
			}else {
				if (pa == pb) System.out.println("YES");
				else System.out.println("NO");
			}
		}
		
    }	
	
	static int findParentNode(int cur_node) {
		if (cur_node == node[cur_node]) return cur_node;
		return findParentNode(node[cur_node]);
	}
	
}
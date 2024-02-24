import java.io.*; 
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] t = br.readLine().split("");
		String[] p = br.readLine().split("");
		
		int[] table = makeTable(p);
		KMP(t, p);
		System.out.println(ans);
		System.out.println(sb.toString());
	}	
	
	static int[] makeTable(String[] pattern) {
		int patternSize = pattern.length;
		int[] table = new int[patternSize];
		
		int j = 0;
		for (int i = 1; i < patternSize; i++) {
			while(j > 0 && !pattern[i].equals(pattern[j])) {
				j = table[j - 1];
			}
			if (pattern[i].equals(pattern[j])) {
				table[i] = ++j;

			}
		}
		return table;
	}
	static void KMP(String[] parent, String[] pattern) {
		int[] table = makeTable(pattern);
		int parentSize = parent.length;
		int patternSize = pattern.length;
		int j = 0;
		
		for (int i = 0; i < parentSize; i++) {
			while(j > 0 && !parent[i].equals(pattern[j])) {
				j = table[j - 1];
			}
			if(parent[i].equals(pattern[j])) {
				if (j == patternSize - 1) {
					ans++;
					sb.append(i - patternSize + 2).append(" ");
					j = table[j];
				} else {
					j++;
				}
			}
		}
	}
}

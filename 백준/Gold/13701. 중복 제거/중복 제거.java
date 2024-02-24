import java.io.*; 
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		boolean[] bitmask = new boolean[1 << 25];
		int size = st.countTokens();
		for (int i = 0 ; i < size; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (bitmask[num]) continue;
			bitmask[num] = true;
			sb.append(num + " ");
		}
		System.out.println(sb.toString());
	}	
	
}

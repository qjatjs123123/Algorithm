
import java.io.*;
import java.util.*;

public class Main
{	
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		HashMap<Integer, Boolean> dict = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				int sum = arr[x] + arr[y];
				
				if (!dict.containsKey(sum)) dict.put(sum, true);
			}
		}
		
		
		for (int i = n - 1; i >= 0; i--) {
			int target = arr[i];
			boolean flg = false;
			for (int num : arr) {
				int rest = target - num;
				
				if (dict.containsKey(rest)) {
					System.out.println(target);
					flg = true;
					break;
				}
			}
			
			if (flg) break;
		}

	}
	

}
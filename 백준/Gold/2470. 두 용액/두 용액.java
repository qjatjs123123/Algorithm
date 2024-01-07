
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{
	static long[] num;
	static int n;
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		n = Integer.parseInt(br.readLine());
		num = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		Arrays.sort(num);
		Long gap = Long.MAX_VALUE;
		Long[] ans = new Long[2]; 
		for (int i = 0; i < n; i++) {
			int idx = binary_search(num[i]*-1);
			int[] cases = {idx -1, idx, idx + 1};
			
			for (int c : cases) {
				if (i == c || c < 0 || c >= n) continue;
				long tmp = Math.abs(num[i] + num[c]);
				if (gap > tmp) {
					gap = tmp;
					Long[] a = {num[i], num[c]};
					ans = a;
				}
				
			}
		}
		Arrays.sort(ans);
		String a = "";
		for (Long an : ans) {
			a += Long.toString(an) + " ";
		}
		System.out.println(a);
    }
	
	static int binary_search(long target) {
		int start = 0;
		int end = n - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (num[mid] > target) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		
		return end;
	}
}
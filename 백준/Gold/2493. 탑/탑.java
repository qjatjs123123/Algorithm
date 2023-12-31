
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{

	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int n = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		
		int[] arr = new int[n];
		for (int i = 0; i<n; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[0] - b[0]);
		
		int[] ans = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			int[] tmp = {arr[i], i};
			
			if (pq.isEmpty()) {
				pq.add(tmp);
			}else {
				int[] cur_tmp = pq.poll();
				
				if (tmp[0] > cur_tmp[0]) {
					pq.add(cur_tmp);
					while (!pq.isEmpty()) {
						int[] t = pq.poll();
						
						if (tmp[0] <= t[0]) {pq.add(t); break;}
						ans[t[1]] = tmp[1] + 1;
						
					}
					pq.add(tmp);
				}else {
					pq.add(cur_tmp);
					pq.add(tmp);
				}
			}

		}
		
		String result = Arrays.stream(ans)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
		System.out.println(result);
    }	
		
}
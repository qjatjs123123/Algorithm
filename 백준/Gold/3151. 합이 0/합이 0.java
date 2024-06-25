import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		ArrayList<Integer> list = new ArrayList<>();
		long ans = 0;
		for (int i = 0; i < N - 2; i++) {
			int target = arr[i];
			
			int left = i + 1;
			int right = N - 1;
			
			while (left < right) {
				int hap = arr[left] + arr[right];
				int leftCount = 1;
				int rightCount = 1;
				if (hap == -target) {
					while (left < right && arr[left] == arr[left + 1]) {
						left++;
						leftCount++;
					}
					

					while(right > left && arr[right] == arr[right - 1]) {
						right--;
						rightCount++;
					}
					if (arr[left] == arr[right]) ans += (leftCount * (leftCount - 1))/2;

					else ans += (leftCount * rightCount);
				}
				
				if (hap + target > 0) right--;
				else left++;
			}
		}
		System.out.println(ans);
	}
}
import java.io.*; 
import java.util.*;


public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N];
		
		for (int i = 0; i < N; i++) arr[i] = i + 1;
		
//		Arrays.sort(arr);
		
		int mask[] = new int[N];
		for (int i = 0 ; i < M; i++) mask[N - i - 1] = 1;
		int res[] = new int[M];
		
		do {
			String ans = "";
			for (int i = 0, j = 0; i < N; i++) { 
				if (mask[i] == 1) ans += Integer.toString(arr[i]) + " ";
			}
			ans += "\n";
			sb.insert(0, ans);
			
		}while(np(mask));
		System.out.println(sb.toString());
	}	
	
	static boolean np(int[] arr) {
		//꼭지점 찾기
		int i = arr.length - 1;
		while(i > 0 && arr[i] <= arr[i - 1]) --i;
		
		if (i == 0) return false;
		
		// 교환위치 찾기
		int j = arr.length - 1;
		while(arr[i - 1] >= arr[j]) --j;
		
		// 교환
		swap(arr, i - 1, j);
		
		int k = arr.length - 1;
		while(i < k) swap(arr, i++, k--);
		return true;
	}
	
	static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

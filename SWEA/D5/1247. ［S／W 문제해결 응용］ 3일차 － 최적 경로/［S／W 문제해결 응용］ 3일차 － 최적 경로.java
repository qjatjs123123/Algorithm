import java.io.*;
import java.util.*;


public class Solution {
	private static StringBuilder sb = new StringBuilder();
	static int[][] visited;
	static int[][] score;
	static int n;
	static int[] companyPos;
	static int[] homePos;
	static ArrayList<int[]> customerPos;
	static int[] num;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int ans = Integer.MAX_VALUE;
			companyPos = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			homePos = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			customerPos = new ArrayList<>();
			num = new int[n];
			for (int i = 0; i < n; i++) {
				customerPos.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
				num[i]=i;
			}
			do {
				int start_row = companyPos[0];
				int start_col = companyPos[1];
				int end_row = 0;
				int end_col = 0;
				int total = 0;
				for (int i = 0; i < n; i++) {
					end_row = customerPos.get(num[i])[0];
					end_col = customerPos.get(num[i])[1];
					total += Math.abs(start_row - end_row) + Math.abs(start_col - end_col);
					start_row = end_row;
					start_col = end_col;
				}
				total += Math.abs(homePos[0] - end_row) + Math.abs(homePos[1] - end_col);
				ans = Math.min(ans, total);
			}while(np(num));
			System.out.println("#" + t + " " + ans);
		}	
	}
	public static boolean np(int[] p) {
		final int N = p.length;
		// 현순열의 사전 순 다음 순열 생성 p : 현 순열
		// step1:  교환위치 찾기(꼭대기를 찾으면 꼭대기 이전이 교환위치가 된다.ㄷ)
		int i = N - 1;
		while(i > 0 && p[i - 1] >= p[i]) --i;
		
		if (i == 0) return false; // 현순열의 상태가 가장 큰 순열이므로 np 없다.
		
		//step2: 교환위치(i - 1)에 넣을 값 뒤쪽부터 찾기 (  큰 값 중 최소값 )
		int j = N - 1;
		while(p[i - 1] >= p[j]) --j;
		
		//step 3 : 교환위치(i - 1) 값과 찾은 위치(j)값 교환
		swap(p, i - 1, j);
		
		//step 4 : 꼭대기 (i) 위치부터 맨뒤까지 오름차순 정렬
		int k = N - 1;
		while(i < k) swap(p, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] arr, int i , int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

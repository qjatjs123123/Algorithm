import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, S;
	
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		boolean[] isPrime = new boolean[4000001];
		ArrayList<Integer> prime = new ArrayList<>();
		
		for (int i = 2; i <= 4000000; i++) {
			if (isPrime[i]) continue;
			prime.add(i);
			for (int j = i*2; j <= 4000000; j += i) {
				isPrime[j] = true;
			}
		}
		prime.add(-1);
		int start = 0;
		int end = 0;
		int total = 0;
		int size = prime.size();
		int ans = 0;
		while (start < size && end < size) {
			if (total == N ) {
				ans += 1;
				total += prime.get(end);
				end++;
			}
			else if (total < N) {
				total += prime.get(end);
				end++;
			} else {
				total -= prime.get(start);
				start++;
			}
		}
		
		System.out.println(ans);
		
	}
}

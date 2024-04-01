import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static final int MOD = 1234567891;
    static int T;
    static int N, R;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	int N, R;
        	N = Integer.parseInt(st.nextToken());
        	R = Integer.parseInt(st.nextToken());
        	
        	long parent = 1;
        	for (int i = N; i > N-R; i--) {
        		parent *= i;
        		parent %= MOD;
        	}
        	
        	long child = 1;
        	
        	for (int i = 1; i <= R; i++) {
        		child *= i;
        		child %= MOD;
        	}
        	
        	long c = backtracking(child, MOD-2);
        	long a = (parent * c) % MOD ;
        	
        	System.out.println("#" + t + " " + a );
        }
    }
    
    static long backtracking(long num, int p) {
    	if (p == 1) return num;
    	long n = backtracking(num, p / 2) % MOD;
    	if (p % 2 == 0) {
    		return (n * n) % MOD;
    	}
    	else {
    		return ( (n * n) % MOD * num) % MOD;
    	}
    }
    
}
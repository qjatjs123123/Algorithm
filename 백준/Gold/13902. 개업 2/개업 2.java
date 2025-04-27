import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Integer> sizeArr = new ArrayList<>();
    static int[] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[M];
        dp = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < M - 1; i++) {
            sizeArr.add(arr[i]);
            for (int j = i + 1; j < M; j++) {
                sizeArr.add(arr[i] + arr[j]);
            }
        }
        sizeArr.add(arr[M - 1]);

        for (int num : sizeArr) {
            if (num > N) continue;
            dp[num] = 1;
        }
        dfs(N);

        // System.out.println(Arrays.toString(dp));
        
        if (dp[N] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[N]);
    }

    static int dfs(int num) {
        if (dp[num] != 0) return dp[num];
        if (num == 0) return 0;

        int total = Integer.MAX_VALUE;
        for (int n : sizeArr) {
            int target = num - n;
            if (target < 0) continue;
            if (dp[target] == Integer.MAX_VALUE) continue;
            int tmp = dfs(target) + 1;

            if (tmp <= Integer.MIN_VALUE + 1) continue;
            total = Math.min(tmp, total);
        }

        dp[num] = total;
        return total;
    }
}
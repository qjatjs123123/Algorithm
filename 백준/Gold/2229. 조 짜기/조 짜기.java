import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    static int[][] memo;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        memo = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int row = 0 ; row <= N; row++) {
            for(int col = 0; col <= N; col++) memo[row][col] = -1;
        }
        
        System.out.println(dp(0, 0, arr[0], arr[0])  );
    }

    static int dp(int left, int right, int min_num, int max_num) {

        if(right == N - 1) {
            return max_num - min_num;
        }
        if (memo[left][right] != -1 ) return memo[left][right];
        int new_min_num = Math.min(min_num, arr[right + 1]);
        int new_max_num = Math.max(max_num, arr[right + 1]);

        
        
        int total = Math.max( dp(left, right + 1, new_min_num, new_max_num) ,
                             dp(right + 1, right + 1, arr[right + 1], arr[right + 1]) + (max_num - min_num) );

        memo[left][right] = total;
        return total;
        
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] arr;
    static int[][][] memo;
    static int EXP = -99_999_9999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            arr[row] = Integer.parseInt(st.nextToken());
        }

        memo = new int[N][M + 1][2];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col <= M; col++) {
                for (int z = 0; z < 2; z++) memo[row][col][z] = -1;
            }
        }

        int a = dp(1,1,1) + arr[0];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col <= M; col++) {
                for (int z = 0; z < 2; z++) memo[row][col][z] = -1;
            }
        }
        int b = dp(1, 0, 0);
        System.out.println(Math.max(a, b));

    }

    static int dp(int idx, int count, int isSelected) {
        if (count > M) return EXP;
        if (idx == N) {
            if (count == M) return 0;
            return EXP;
        } 
        if (memo[idx][count][isSelected] != -1) return memo[idx][count][isSelected];
        
        
        int total = EXP;
        if (isSelected == 1) {
            total = Math.max( dp(idx + 1, count, 0), dp(idx + 1, count, 1) + arr[idx] );
        } else {
            total = Math.max( dp(idx + 1, count + 1, 1) + arr[idx], dp(idx + 1, count, 0) );
        }

        memo[idx][count][isSelected] = total;
        return total;
    }
}
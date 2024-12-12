import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N ;
    static int[] arr;
    static int[] memo;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        memo = new int[N];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            arr[row] = n; 
        }

        int LIS = 0;
        memo[0] = 1;
        for (int i = 1; i < N; i++) {
            memo[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) memo[i] = Math.max(memo[i], memo[j] + 1);
            }

            LIS = Math.max(LIS, memo[i]);
        }

        System.out.println(N - LIS);
    }

}
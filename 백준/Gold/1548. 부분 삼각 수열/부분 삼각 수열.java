import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        if (N == 1 || N == 2) {
            System.out.println(N);
            return;
        }
        
        Arrays.sort(arr);

        int ans = 2;
        for (int i = 0; i < N - 1; i++) {
            int hap = arr[i] + arr[i + 1];
            int len = 2;
            for (int j = i + 2; j < N; j++) {
                if (arr[j] >= hap) break;
                ans = Math.max(ans, ++len);
            }
        }
        System.out.println(ans);
    }
}
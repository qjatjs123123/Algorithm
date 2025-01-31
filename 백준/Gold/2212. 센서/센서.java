import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        
        
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        
        int[] arr_diff = new int[N - 1];

        for (int i = 0; i < N - 1; i++) arr_diff[i] = arr[i + 1] - arr[i];


        Arrays.sort(arr_diff);
        
        int ans = 0;
        for (int i = 0; i < N - K; i++) ans += arr_diff[i];
        System.out.println(ans);
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        int sum = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int start = 1;
        int end = sum;
        int answer = 0;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            int groupCnt = 0;
            int cur_sum = 0;

            for (int num : arr) {
                cur_sum += num;
            
                if (mid <= cur_sum) {
                    
                    groupCnt++;
                    cur_sum = 0;
                }
            }

            if (cur_sum >= mid) groupCnt++;
            
            if (groupCnt >= K) start = mid + 1;
            else end = mid - 1;
            
        }
        System.out.println(end);
    }
}
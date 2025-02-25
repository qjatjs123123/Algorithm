import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        
        int start = 0;
        int end = 10000;
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

        }

        while (start < end) {
            int mid = (start + end) / 2;
            
            int count = 1;
            int min_num = Integer.MAX_VALUE;
            int max_num = 0;
            for (int right = 0; right < N; right++){
                min_num = Math.min(min_num, arr[right]);
                max_num = Math.max(max_num, arr[right]);
                
                int gap = max_num - min_num;

                if (gap > mid) {
                    count++;
                    min_num = arr[right];
                    max_num = arr[right];
                    continue;
                }
            }

            if (count > M) start = mid + 1;
            else end = mid;

            
        }
        System.out.println(start);
    }
}
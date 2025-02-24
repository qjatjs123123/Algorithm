import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, L;
    static int[] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        arr[N + 1] = L;
        Arrays.sort(arr);


        int max_num = 0;
        int min_num = Integer.MAX_VALUE;
        int[] gapArr = new int[N + 1];

        for (int i = 1; i < arr.length; i++) {
            gapArr[i - 1] = arr[i] - arr[i - 1];
            max_num = Math.max(max_num, gapArr[i - 1]);
            min_num = Math.min(min_num, gapArr[i - 1]);
        }

        int start = 1;
        int end = 1000;
        
        while (start <= end) {
            int mid = (start + end) / 2;

            int count = 0;
            for (int gap : gapArr) {
                int tmp  = gap / mid;
                count += tmp;
                if (gap % mid == 0) count--;
            }
            
            if (count > M) start = mid + 1;
            else end = mid - 1;
            
        }

        System.out.println(start);
    }
}
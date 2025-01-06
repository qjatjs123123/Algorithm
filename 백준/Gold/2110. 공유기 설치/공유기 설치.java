import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, C;
    static int[] arr;    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);

        int start = 1;
        int end = arr[N - 1];

        while (start <= end) {
            int mid = (start + end) / 2;
            
            int count = 1;
            int cur_num = arr[0];
            
            while (true) {
                int idx = upperBound( cur_num + mid );
                if (idx == N) break;
                count++;
                cur_num = arr[idx];
            }

            if (count >= C) start = mid + 1;
            else end = mid - 1;
            

        }

        System.out.println(start);
    }

    static int upperBound(int target) {
        int start = 0;
        int end = N - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (target >= arr[mid]) start = mid + 1;
            else end =  mid - 1;
        }

        return start;
    }
}
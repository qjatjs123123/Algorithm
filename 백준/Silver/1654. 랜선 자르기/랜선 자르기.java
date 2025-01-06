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

        long start = 1;
        long end = arr[N - 1];

        while (start <= end) {
            long mid = (start + end) / 2;

            int count = 0;
            for (int length : arr) {
                count += (length / mid);
            }

            if (count >= C) start = mid + 1;
            else end = mid - 1;
        }

        System.out.println(end);
    }
}
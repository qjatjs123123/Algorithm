import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] arr = new int[] {1, 2, 3, 3, 3, 4};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        Arrays.sort(arr);
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int target = arr[i] + M;
            int idx = binarySearch(target);

            int[] tmp = new int[] {idx, idx - 1, idx + 1};

            for (int t : tmp) {
                if (t < 0 || t >= N || i == t) continue;
                if (Math.abs(arr[i] - arr[t]) < M) continue;
                answer = Math.min(answer, Math.abs(arr[i] - arr[t]));                
            }
        }

        System.out.println(answer);
    }

    static int binarySearch(int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }
}
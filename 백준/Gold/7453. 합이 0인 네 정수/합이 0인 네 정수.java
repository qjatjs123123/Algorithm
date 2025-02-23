import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n;
    static int[][] graph;
    static int[] right;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new int[n][4];
        right = new int[n * n];
        
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 4; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        

        int idx = 0;

        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                right[idx] = graph[row][2] + graph[col][3];
                idx++;
            }
        }

        Arrays.sort(right);


        long ans = 0;
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int num = graph[row][0] + graph[col][1];

                ans += (lower_bound(-num) - upper_bound(-num));
            }
        }
        
        System.out.println(ans);
    }

    static int upper_bound(int target) {
        int start = 0;
        int end = right.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (right[mid] >= target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }

    static int lower_bound(int target) {
        int start = 0;
        int end = right.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (right[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }
}
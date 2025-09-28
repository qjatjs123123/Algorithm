import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] graph = new int[N];
        int total = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            graph[i] = Integer.parseInt(st.nextToken());
            total += graph[i];
        }

        int left = 0;
        int right = total;

        while (left <= right) {
            int mid = (left + right) / 2;
            int groupCnt = 0;
            int cur_sum = 0;

            for (int score : graph) {
                cur_sum += score;

                if (cur_sum >= mid) {
                    groupCnt++;
                    cur_sum = 0;
                }
            }

            if (groupCnt < K) right = mid - 1;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            graph[i][0] = (sMonth * 100) + sDay;
            graph[i][1] = (eMonth * 100) + eDay;
        }

        Arrays.sort(graph, (a, b) -> a[0] - b[0]);

        int start = 0;
        int target = 301;
        int cnt = 0;
        while (start < N) {
            int new_end = binary_search(target) - 1;

            if (new_end < 0 || new_end == N) break;
            if (graph[new_end][0] <= target && graph[new_end][1] >= target) {

                int tmp = 0;
                for (int i = start; i <= new_end; i++) {
                    tmp = Math.max(tmp, graph[i][1]);
                }
    
                target = tmp;
                start = new_end + 1;
                cnt++;
            } else {
                break;
            }

            if (target > 1130) {
                System.out.println(cnt);
                return;
            }

            // System.out.println(target);
        }

        System.out.println(0);
    }

    static int binary_search(int target) {
        int start = 0;
        int end = N - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int sMid = graph[mid][0];

            if (sMid <= target) start = mid + 1;
            else end = mid - 1;

        }

        return start;
    }
}
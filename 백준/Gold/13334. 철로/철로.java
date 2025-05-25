import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[i][0] = Math.min(from, to);
            arr[i][1] = Math.max(to, from);
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int answer = 0;
        for (int[] pos : arr) {
            int start = pos[0];
            int end = pos[1];

            long lim = end - k;
            
            if (lim <= start) {
                pq.add(pos);

                while (true) {
                    if (pq.isEmpty() || pq.peek()[0] >= lim) break;
                    pq.poll();
                }
            }
            answer = Math.max(answer, pq.size());
            // System.out.println(pq.size());
        }

        System.out.println(answer);
        
        // System.out.println(Arrays.deepToString(arr));
    }
}
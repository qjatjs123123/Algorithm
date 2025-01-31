import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] timeArr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            timeArr[i][0] = start;
            timeArr[i][1] = end;
        }

        Arrays.sort(timeArr, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(timeArr[0]);
        
        for (int i = 1; i < N; i++) {
            int[] top = pq.peek();

            if (top[1] <= timeArr[i][0]){
                pq.poll();
                pq.add(timeArr[i]);
            } else {
                pq.add(timeArr[i]);
            }
        }
        System.out.println(pq.size());
    }
}
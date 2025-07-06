import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) pq.add(Long.parseLong(st.nextToken()));

            long answer = 0;
            while (true) {
                if (pq.size() <= 1) break;

                long a = pq.poll();
                long b = pq.poll();

                answer += a + b;
                pq.add(a + b);
            }

            System.out.println(answer);
        }
        
    }
}
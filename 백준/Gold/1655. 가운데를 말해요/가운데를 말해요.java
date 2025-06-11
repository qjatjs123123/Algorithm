import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> max_pq = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> min_pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if (i == 0) {
                max_pq.add(num);
                sb.append(num).append("\n");
                continue;
            }

            if (max_pq.peek() >= num) max_pq.add(num);
            else min_pq.add(num);

            int gap = Math.abs(max_pq.size() - min_pq.size());

            if (gap == 0) {
                sb.append(max_pq.peek()).append("\n");
            } else if(gap == 1) {
                if (max_pq.size() > min_pq.size())
                    sb.append(max_pq.peek()).append("\n");
                else 
                    sb.append(min_pq.peek()).append("\n");
            } else {
                if (max_pq.size() > min_pq.size())
                    min_pq.add(max_pq.poll());
                else 
                    max_pq.add(min_pq.poll());

                sb.append(max_pq.peek()).append("\n");
            }


        }
        System.out.println(sb.toString());
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> min_pq = new PriorityQueue<>();
            PriorityQueue<Integer> max_pq = new PriorityQueue<>((a, b) -> b - a);

            int totalCnt = 0;
            int rowCnt = M / 10 + 1;
            StringBuilder tmp = new StringBuilder();
            for (int row = 0; row < rowCnt; row++) {
                st = new StringTokenizer(br.readLine());

                int colCnt = st.countTokens();
                for (int col = 0; col < colCnt; col++) {
                    int num = Integer.parseInt(st.nextToken());

                    if (totalCnt == 0) {
                        max_pq.add(num);
                        tmp.append(num).append(" ");
                        totalCnt++;
                        continue;
                    }
                    
                    int middle = max_pq.peek();
                    if (middle >= num) max_pq.add(num);
                    else min_pq.add(num);

                    if (col % 2 != 0 && min_pq.size() < max_pq.size()) {
                        min_pq.add(max_pq.poll());
                    }
                    if (col % 2 != 0 && min_pq.size() > max_pq.size()) {
                        max_pq.add(min_pq.poll());
                    }

                    if (col % 2 == 0) {
                        if (max_pq.size() < min_pq.size()) max_pq.add(min_pq.poll());
                        
                        tmp.append(max_pq.peek() + " ");
                        totalCnt++;   
                        if (totalCnt % 10 == 0) tmp.append("\n");
                    }

                }
                
            }
            sb.append(totalCnt).append("\n");
            if (totalCnt % 10 != 0 ) tmp.append("\n");
            sb.append(tmp.toString());
        }
        System.out.println(sb.toString());
    }
}
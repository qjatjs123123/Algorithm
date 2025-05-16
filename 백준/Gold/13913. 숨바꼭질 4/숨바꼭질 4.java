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

        int[] dp = new int[300_001];
        for (int i = 0; i < 300_001; i++) dp[i] = -1;
        dp[N] = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(N);

        while (!deque.isEmpty()) {
            int cur_num = deque.pollFirst();
            if (cur_num == K) {
                int cnt = 0;
                Stack<Integer> stack = new Stack<>();
                
                while (cur_num != N) {
                    stack.push(cur_num);
                    cur_num = dp[cur_num];
                    cnt++;
                }

                stack.push(cur_num);

                StringBuilder sb = new StringBuilder();
                sb.append(cnt).append("\n");

                while (!stack.isEmpty()) {
                    sb.append(stack.pop()).append(" ");
                }

                System.out.println(sb.toString());
                break;
                
            }
            
            int[] tmp = new int[] {cur_num - 1, cur_num + 1, cur_num * 2};

            for (int n : tmp) {
                if (n < 0 || n > 300_000) continue;
                if (dp[n] != -1) continue;

                dp[n] = cur_num;
                deque.add(n);
            }
        }
        
    }
}
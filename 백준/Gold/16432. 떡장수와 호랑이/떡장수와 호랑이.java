import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static HashSet<Integer>[] graph;
    static Stack<Integer> stack = new Stack<>();
    static boolean flg = false;
    static boolean[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new HashSet[N];
        boolean isNoAnswer = false;
        dp = new boolean[N + 1][10];
            
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            graph[i] = new HashSet<>();
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }

            if (i >= 1) {
                if (graph[i-1].size() == 1 && graph[i].size() == 1) {
                    int prev = 0;
                    int next = 0;
                    for (int tmp : graph[i - 1]) prev = tmp;
                    for (int tmp : graph[i]) next = tmp;
                    
                    if (prev == next) isNoAnswer = true;
                }
            }
        }
        
        
         dfs(0, 0);
        if(!flg) System.out.println(-1);

    }

    static void dfs(int depth, int prev) {
        if (flg) return;
        if (dp[depth][prev]) return;
        if (depth == N) {
            StringBuilder sb = new StringBuilder();

            for (int num : stack) sb.append(num).append("\n");
            System.out.println(sb.toString());
            flg = true;
            return;
        }

        HashSet<Integer> list = graph[depth];
        for (int num : list){
            if (flg) return;
            if (num == prev) continue;
            stack.push(num);
            dfs(depth + 1, num);
            stack.pop();
        }
        dp[depth][prev] = true;
        
    }
}


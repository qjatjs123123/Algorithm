import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] graph, dp;
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();
        graph = new int[N];
        dp = new int[N];
        
        for (int i = 0; i < N; i++) {
            graph[i] = fs.nextInt();
            dp[i] = -1;
        }
        
        int answer = dfs(0);
        if (answer == -2) System.out.println(-1);
        else System.out.println(answer);
    }

    static int dfs(int p) {
        if (p == N) return 0;
        if (dp[p] != -1) return dp[p];
        

        ArrayList<Integer> list = new ArrayList<>();
        boolean isFlg = true;
        int answer = -2;
        for (int new_p = p; new_p < N; new_p++) {
            list.add(graph[new_p]);

            if (list.size() % 2 == 0) {
                int left = 0;
                int right = list.size() - 1;
                boolean isPalidrome = true;
                
                while (left < right) {
                    if (!list.get(left).equals(list.get(right))) {
                        isPalidrome = false;
                        break;
                    }

                    left++;
                    right--;
                }

                if (isPalidrome) {
                    isFlg = false;
                    int next = dfs(new_p + 1);

                    if (next != -2){
                        answer = Math.max(answer, 1 + next);
                        break;
                    }
                }
            }
        }

        dp[p] = answer;
        return answer;
    }
}
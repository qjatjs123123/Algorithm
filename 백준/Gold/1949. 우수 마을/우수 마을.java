import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] COST;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] selectedArr;
    static int[][] memo;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        COST = new int[N + 1];
        visited = new boolean[N + 1];
        selectedArr = new int[N + 1];
        memo = new int[N + 1][2];
        
        for (int i = 0; i <= N; i++) selectedArr[i] = -1;

        for (int row = 0; row <= N; row++) {
            for (int z = 0 ; z< 2; z++) memo[row][z] = -1;
        }
        
        st = new StringTokenizer(br.readLine());
        for (int col = 0; col <= N; col++) {
            ArrayList<Integer> list = new ArrayList<>();
            if (col == 0) {
                graph.add(list);
                continue;
            }
            
            graph.add(list);
            COST[col] = Integer.parseInt(st.nextToken());
        }

        for (int row = 0; row < N - 1; row++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ArrayList<Integer> cur_list = graph.get(start);
            cur_list.add(end);

            cur_list = graph.get(end);
            cur_list.add(start);
        }              

        System.out.println( Math.max(dp(1, 1) + COST[1], dp(1,0) ));
    }

    static int dp(int idx, int isSelected) {
        if (idx == N) return 0;
        if (visited[idx]) return 0;
        if (memo[idx][isSelected] != -1) return memo[idx][isSelected];
        
        visited[idx] = true;
        selectedArr[idx] = isSelected;
        int total = 0;
        ArrayList<Integer> cur_list = graph.get(idx);

        for (int new_idx : cur_list) {
            if (visited[new_idx]) continue;
            
            if (isSelected == 1) total += dp(new_idx, 0);
            else {
                ArrayList<Integer> new_list = graph.get(new_idx);
                int one = 0;
                int zero = 0;
                int minus = 0;

                for (int tmp_idx : new_list) {
                    if (selectedArr[tmp_idx] == 1) one++;
                    else if(selectedArr[tmp_idx] == 0) zero++;
                    else minus++;
                }

                if (minus == 1 && one == 1) total += dp(new_idx, 1) + COST[new_idx];
                else total += Math.max( dp(new_idx, 0), dp(new_idx, 1) + COST[new_idx] );
            }
        }

        memo[idx][isSelected] = total;
        visited[idx] = false;
        selectedArr[idx] = -1;
        return total;
    }
}
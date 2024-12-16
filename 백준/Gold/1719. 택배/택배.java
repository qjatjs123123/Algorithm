import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, m;
    static int EXP = 1000 * 1000;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] time;
    static int[][] ans;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = new int[n + 1][n + 1];
        ans = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            ArrayList<Integer> new_list = new ArrayList<>();
            graph.add(new_list);
        }

        for (int row = 0; row <= n; row++) {
            for (int col = 0; col <= n; col++) {
                if (row == col) time[row][col] = 0;
                else time[row][col] = EXP;
            }
        }
        
        for (int row = 0; row < m; row++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            time[from][to] = Math.min(weight, time[from][to]);
            time[to][from] = Math.min(weight, time[to][from]);
            ans[from][to] = to;
            ans[to][from] = from;
            
            ArrayList<Integer> cur_list = graph.get(from);
            cur_list.add(to);
            cur_list = graph.get(to);
            cur_list.add(from);
        }

        for (int mid = 1; mid <= n; mid++) {
            for(int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (time[from][to] > time[from][mid] + time[mid][to]) {
                        time[from][to] = time[from][mid] + time[mid][to];
                        ans[from][to] = ans[from][mid];
                    }
                    
                }
            }
        }

        for (int row = 1; row <= n; row++) {
 
            for(int col = 1; col <= n; col++) {
                if (ans[row][col] == 0) System.out.print("- ");
                else System.out.print(ans[row][col] + " ");
            }
            System.out.println();
        }
    }
}
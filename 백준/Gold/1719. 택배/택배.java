import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] weight;
    static int[][] ans;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        weight = new int[n + 1][n + 1];
        ans = new int[n + 1][n + 1];

        for (int row = 0; row <= n; row++) {
            ArrayList<Integer> new_list = new ArrayList<>();
            graph.add(new_list);
        }

        for (int row = 0 ; row <= n; row++) {
            for(int col = 0; col <= n; col++) {
                if (row == col) weight[row][col] = 0;
                else {
                    if (weight[row][col] == 0) weight[row][col] = 1000*1000;
                }
            }
        }
        
        for (int row = 0; row < m; row++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            ArrayList<Integer> cur_list = graph.get(from);
            cur_list.add(to);
            cur_list = graph.get(to);
            cur_list.add(from);

            weight[from][to] = Math.min(weight[from][to], w);
            weight[to][from] = Math.min(weight[to][from], w);
            ans[from][to] = to;
            ans[to][from] = from;
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (weight[from][to] > weight[from][mid] + weight[mid][to]) {
                        weight[from][to] = weight[from][mid] + weight[mid][to];
                        ans[from][to] = ans[from][mid];
                    }
                    
                }
            }
        } 
        StringBuffer sb = new StringBuffer();
        for (int row = 1; row <= n; row++) {
            for (int  col = 1; col <= n; col++) {
                if (ans[row][col] == 0) sb.append("-");
                else sb.append(ans[row][col]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
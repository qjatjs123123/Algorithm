import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static int cycle = Integer.MAX_VALUE;
    static int[][] map;

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[V + 1][V + 1];

        for (int row = 0; row < V + 1; row++) {
            for (int col = 0; col < V + 1; col++) {
                if (row == col) continue;
                
                map[row][col] = 999_999_999;
            }
        }
              
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            map[from][to] = value;
        }

        floyd();

        int ans = Integer.MAX_VALUE;
        for (int from = 1; from <= V; from++) {
            for (int to = 1; to <= V; to++) {
                if (from == to) continue;

                int go = map[from][to];
                int back = map[to][from];

                if (go != 999_999_999 && back != 999_999_999) ans = Math.min(ans, go + back);
            }
        }
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    static void floyd() {
        for (int mid = 1; mid <= V; mid++) {
            for (int from = 1; from <= V; from++) {
                for (int to = 1; to <= V; to++) {
                    map[from][to] = Math.min(map[from][to], map[from][mid] + map[mid][to]);
                }
            }
        }
    }
}
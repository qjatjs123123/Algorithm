import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col) - '0';
            }
        }

        int max_num = Math.max(N, M);
        int[] dy = new int[] {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dx = new int[] {0, 0, 1, -1, 1, -1, 1, -1};
        int answer = -1;
        
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                for (int len = 1; len <= max_num; len++) {
                    for (int row_len = 1; row_len <= max_num ; row_len++) {
                        for (int col_len = 1; col_len <= max_num; col_len++) {
                            for (int i = 0; i < 8; i++) {
                                String ss = "";
            
                                int cur_row = row;
                                int cur_col = col;
                                int cnt = 0;
            
                                while (len > cnt) {
                                    if (cur_row < 0 || cur_row >= N || cur_col < 0 || cur_col >= M) break;
                                    
                                    ss += Integer.toString(graph[cur_row][cur_col]);
                                    cur_row += dy[i] * row_len;
                                    cur_col += dx[i] * col_len;
                                    cnt++;
                                }
                                if (len == cnt) {
                                    int sqrt = (int)Math.sqrt(Integer.parseInt(ss));
                                    int pow = (int)Math.pow(sqrt, 2);
                                    if (pow == Integer.parseInt(ss)) {
                                        answer = Math.max(answer, Integer.parseInt(ss));
                                    }
                                }
                                
                            }
                        }
                    }
                }
                
                
                
            }
        }
        
        System.out.println(answer);
    }
}
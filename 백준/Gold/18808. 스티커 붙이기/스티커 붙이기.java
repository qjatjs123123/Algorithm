import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] rect = new int[R][C];

            for (int row = 0; row < R; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < C; col++) {
                    rect[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            boolean flg = false;
            for (int cnt = 0; cnt <= 3; cnt++) {
                int[][] new_rect = rotate(rect, cnt);
                if (flg) break;
                for (int row = 0; row < N; row++) {
                    if (flg) break;
                    
                    for (int col = 0; col < M; col++) {

                        if (isInside(row, col, new_rect) && isValid(row, col, new_rect)) {
                            for (int r = 0; r < new_rect.length; r++) {
                                for (int c = 0; c < new_rect[0].length; c++) {
                                    if (new_rect[r][c] == 0) continue;
                                    graph[row + r][col + c] = new_rect[r][c];
                                }
                            }
                            flg = true;
                            break;
                        }
                        
                    }
                }
            }
            

        
        }

        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if(graph[row][col] == 1) answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean isValid(int r, int c, int[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (graph[row + r][col + c] == 1 &&
                   arr[row][col] == 1) return false;
            }
        }
        return true;
    }
    
    static boolean isInside(int r, int c, int[][] arr) {
        int row_len = N - r;
        int col_len = M - c;

        if (row_len < arr.length || col_len < arr[0].length) return false;
        return true;
    }
    
    static void display(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) sb.append(arr[row][col]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    
    static int[][] rotate(int[][] arr, int k) {
        int[][] result = deepCopy(arr);

        for (int i = 0; i < k; i++) {
            int[][] tmp;

            if (i % 2 != 0) {
                tmp = new int[arr.length][arr[0].length];
            } else {
                tmp = new int[arr[0].length][arr.length];
            }

            
            for (int row = 0; row < result.length; row++) {
                for (int col = 0; col < result[0].length; col++) {
                    tmp[col][ tmp[0].length - row -1 ] = result[row][col];
                }
            }

            result = tmp;
        }

        return result;
    }

    static int[][] deepCopy(int[][] arr) {
        int[][] result = new int[arr.length][arr[0].length];

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                result[row][col] = arr[row][col];        
            }
        }

        return result;
    }
}
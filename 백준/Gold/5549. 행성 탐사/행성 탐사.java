import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int M, N, K;
    static int[][][] prefix;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        prefix = new int[M + 1][N + 1][3];
        
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            for (int j = 1; j <= N; j++) {
                char c = str.charAt(j - 1);
                int idx = findIndex(c);

                int[] top = prefix[i - 1][j];
                int[] left = prefix[i][j - 1];
                int[] side = prefix[i - 1][j - 1];
                
                prefix[i][j][0] = top[0] + left[0] - side[0];
                prefix[i][j][1] = top[1] + left[1] - side[1];
                prefix[i][j][2] = top[2] + left[2] - side[2];

                prefix[i][j][findIndex(c)] += 1;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int start_row = Integer.parseInt(st.nextToken());
            int start_col = Integer.parseInt(st.nextToken());
            int end_row = Integer.parseInt(st.nextToken());
            int end_col = Integer.parseInt(st.nextToken());

            int[] org = prefix[end_row][end_col];
            int[] top = prefix[start_row - 1][end_col];
            int[] left = prefix[end_row][start_col - 1];
            int[] side = prefix[start_row - 1][start_col - 1];
            
            sb.append(org[0] - top[0] - left[0] + side[0]).append(" ");
            sb.append(org[1] - top[1] - left[1] + side[1]).append(" ");
            sb.append(org[2] - top[2] - left[2] + side[2]).append("\n");
        }

        System.out.println(sb.toString());
        
    }

    static int findIndex(char c) {
        if (c == 'J') return 0;
        else if(c == 'O') return 1;
        return 2;
    }
}
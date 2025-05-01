import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int S;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());

        Deque<int[]> deque = new ArrayDeque<>();
        int[][] dp = new int[2_001][2_001];

        for (int i = 0; i <= 2000; i++) {
            for (int j = 0; j <= 2000; j++) dp[i][j] = Integer.MAX_VALUE;
        }
        deque.add(new int[] {1, 0, 0});

        
        while(!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int emo_cnt = cur_arr[0];
            int clip_cnt = cur_arr[1];
            int cur_cnt = cur_arr[2];

            if (emo_cnt > 2000 || clip_cnt > 2000) continue;
            if (dp[emo_cnt][clip_cnt] <= cur_cnt) continue;
            if (emo_cnt == S) {
                System.out.println(cur_cnt);
                break;
            }
            dp[emo_cnt][clip_cnt] = cur_cnt;
            
            deque.add(new int[] {emo_cnt, emo_cnt, cur_cnt + 1});

            if (clip_cnt > 0) {
                deque.add(new int[] {emo_cnt + clip_cnt, clip_cnt, cur_cnt + 1});
            } 

            if (emo_cnt > 0 ) {
                deque.add(new int[] {emo_cnt - 1, clip_cnt, cur_cnt + 1});
            }
            
        }

        
    }

}
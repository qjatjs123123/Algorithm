import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        char[] graph = new char[N];

        for (int i = 0; i < N; i++) graph[i] = str.charAt(i);

        int left = 0;
        int right = 0;

        int black_cnt = 0;
        int white_cnt = 0;
        int ans = 0;
        while (right < N) {
            if (black_cnt <= B) {
                if (graph[right] == 'B') black_cnt++;
                else white_cnt++;
                right++;
                if (black_cnt <= B && white_cnt >= W) ans = Math.max(ans, right - left);
            } else {
                while (true) {
                    if (graph[left] == 'B') {
                        black_cnt--;
                        left++;
                        break;
                    } else {
                        white_cnt--;
                        left++;
                        break;
                    }
                }
            }
            
        }

        System.out.println(ans);
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        int[][][][] dp = new int[3][3][3][3];
        int[] count = new int[4];
        dp[0][0][0][0] = 1;
        
        int answer = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int idx = getIndex(c);

            count[idx] = ++count[idx] % 3;
            answer += dp[ count[0] ][ count[1] ][ count[2] ][ count[3] ];
            dp[ count[0] ][ count[1] ][ count[2] ][ count[3] ]++;

        }

        System.out.println(answer);
    }

    static int getIndex(char c) {
        if (c == 'T') return 0;
        else if (c == 'G') return 1;
        else if(c == 'F') return 2;
        return 3;
    }
}
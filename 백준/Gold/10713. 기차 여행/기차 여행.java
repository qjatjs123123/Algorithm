import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] cityArr;
    static int[][] railArr;
    static int[] railCount;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cityArr = new int[N];
        railArr = new int[N - 1][3];
        railCount = new int[N - 1];

        long answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cityArr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            railArr[i][0] = Integer.parseInt(st.nextToken());
            railArr[i][1] = Integer.parseInt(st.nextToken());
            railArr[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M - 1; i++) {
            int from = Math.min(cityArr[i], cityArr[i + 1]);
            int to = Math.max(cityArr[i], cityArr[i + 1]);

            for (int j = from ; j < to; j++) railCount[j]++;
        }


        for (int i = 0; i < N - 1; i++) {
            int cnt = railCount[i];

            long value = Math.min(railArr[i][0] * (long)cnt, 
                                 (railArr[i][1] + (long)railArr[i][2]) + (railArr[i][1] * (long)(cnt - 1)) );

            answer += value;
        }

        System.out.println(answer);
        
    }
}
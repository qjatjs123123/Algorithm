import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int K, N;
    static char[] endArr;    
    static char[] startArr;
    static char[][] graph;
    static int middleRow;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        endArr = new char[K];
        startArr = new char[K];
        
        for (int i = 0; i < K; i++) {
            endArr[i] = str.charAt(i);
            startArr[i] = (char)('A' + i);
        }

        graph = new char[N][K - 1];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();

            for (int col = 0; col < K - 1; col++) {
                graph[row][col] = str1.charAt(col);
                if (graph[row][col] == '?') middleRow = row;
            }
        }


        for (int row = 0; row < middleRow; row++) {
            for (int col = 0; col < graph[0].length; col++) {
                if (graph[row][col] == '*') continue;

                char tmp = startArr[col];

                startArr[col] = startArr[col + 1];
                startArr[col + 1] = tmp;
            }
        }

        for (int row = N - 1; row > middleRow; row--) {
            for (int col = 0; col < graph[0].length; col++) {
                if (graph[row][col] == '*') continue;

                char tmp = endArr[col];

                endArr[col] = endArr[col + 1];
                endArr[col + 1] = tmp;
            }
        }

        String ans = "";
        int idx = 0;
        boolean flg = true;
        while (idx < K - 1) {
            if (startArr[idx] == endArr[idx]) {
                ans += "*";
                idx++;
            } else {
                if (startArr[idx] == endArr[idx + 1] && startArr[idx + 1] == endArr[idx]) {
                    ans += "-";
                    char tmp = startArr[idx];
                    startArr[idx] = startArr[idx + 1];
                    startArr[idx + 1] = tmp; 
                    idx++;
                    continue;
                } 

                flg = false;
                break;
            }
        }
        
        if (flg) System.out.println(ans);
        else {
            System.out.println("x".repeat(K - 1));
        }
        
    }

}
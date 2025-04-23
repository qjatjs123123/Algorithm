import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int R, C;
    static char[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        String[] strArr = new String[C];

        for (int col = 0; col < C; col++) strArr[col] = "";
        
        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < C; col++) {
                graph[row][col] = str.charAt(col);
            }
        }

        int cnt = 0;
        
        for (int r = R - 1; r >= 1; r--) {
            boolean flg = false;
            HashMap<String, Boolean> dict = new HashMap<>();

            for (int c = 0; c < C; c++) {
                strArr[c] += graph[r][c];
            }
            
            for (int c = 0; c < C; c++) {
                String ss = strArr[c];
                

                if (dict.containsKey(ss)) {
                    flg = true;
                    cnt++;
                    break;
                } else {
                    dict.put(ss, true);
                }
            }

            if (!flg) break;
        }
        System.out.println(R - cnt - 1);
    }
}
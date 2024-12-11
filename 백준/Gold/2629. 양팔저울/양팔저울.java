import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static ArrayList<Integer> chu_list = new ArrayList();
    static ArrayList<Integer> goo_list = new ArrayList();
    static boolean[][] memo;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        memo = new boolean[40501][N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) chu_list.add(Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) goo_list.add(Integer.parseInt(st.nextToken()));
    
        memo[chu_list.get(0)][0] = true;
        memo[0][0] = true;
        
        for (int i = 1; i < N; i++) {
            int chu = chu_list.get(i);
            
            for (int j = 0; j <= 40500; j++) {
                if(memo[j][i-1]) {
                    memo[j][i] = true;
                    memo[j + chu][i] = true;
                    memo[Math.abs(j - chu)][i] = true;
                }
            }
        }

        for (int goo : goo_list) {
            if (memo[goo][N - 1]) System.out.print("Y ");
            else System.out.print("N ");
        }
    }

}
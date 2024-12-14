import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] ans;
    static boolean[] visited;
    static boolean flg = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ans = new int[N * 2];
        visited = new boolean[17];
        
        for (int i = 0; i < N * 2 ; i++) ans[i] = -1;
        
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            list.add(n);
        }
        
        list.sort( (a, b) -> a - b);

        recursion(0, 0);

        if (!flg) System.out.println(-1);
    }

    static void recursion(int idx, int count) {
        if(flg) return;
        
        if (count == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2 * N; i++) {
                sb.append(ans[i]);
                sb.append(" ");
            }
            
            System.out.println(sb.toString());
            flg = true;
            return;
        }

        for (int num : list) {
            int new_idx = idx + num + 1;

            if (new_idx >= 2 * N) continue;
            if (visited[num]) continue;
            if (ans[new_idx] != -1 || ans[idx] != -1) {
                recursion(idx + 1, count );
                continue;
            }
            
            
            visited[num] = true;
            ans[idx] = num;
            ans[new_idx] = num;
    
            recursion(idx + 1, count + 1);

            ans[idx] = -1;
            ans[new_idx] = -1;
            visited[num] = false;
        }

        
    }

}
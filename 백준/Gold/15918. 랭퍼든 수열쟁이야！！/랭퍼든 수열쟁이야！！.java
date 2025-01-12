import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, x, y;
    static int ans = 0;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        arr = new int[n * 2];
        visited = new boolean[n + 1];
        arr[x - 1] = (y - x) - 1;
        arr[y - 1] = (y - x) - 1;
        visited[(y - x - 1)] = true;
        
        backtracking(0);
        System.out.println(ans);
    }

    static void backtracking(int idx) {
 
        if (idx == n * 2) {
            ans++;
            return;
        }
        if (arr[idx] == 0) {
            for (int num = 1; num <= n; num++) {
                if (!visited[num] && idx + num + 1 < n * 2 && arr[idx + num + 1] == 0) {
                    visited[num] = true;
                    arr[idx] = num;
                    arr[idx + num + 1] = num; 
                    backtracking(idx + 1);
                    visited[num] = false;
                    arr[idx] = 0;
                    arr[idx + num + 1] = 0; 
                } 
            }
        } else {
            backtracking(idx + 1);
        }
        
        
    }
}
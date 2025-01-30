import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[i][0] = start;
            arr[i][1] = end;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int[] cur_arr = new int[2];
        int idx = 0;
        int ans = 0;
        
        for (int[] a : arr) {
            
            if (idx == 0) {
                cur_arr = a;
                ans = 1;
                idx++;
                continue;
            }

            if (a[0] >= cur_arr[1]) {
                cur_arr = a;
                ans++;
            }
            idx++;
        }
        System.out.println(ans);
    }
}
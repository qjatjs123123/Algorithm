import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, x, y;
    static int ans = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        arr = new int[n * 2];
        arr[x - 1] = (y - x) - 1;
        arr[y - 1] = (y - x) - 1;
        
        backtracking(1);
        System.out.println(ans);
    }

    static void backtracking(int num) {
        if (num > n) {
            ans++; 
            return;
        }

        if (num == (y - x - 1)) backtracking(num + 1);
        
        for (int i = 0; i < n * 2; i++) {
            if (arr[i] == 0 && i + num + 1 < n * 2 && arr[i + num + 1] == 0) {
                arr[i] = num;
                arr[i + num + 1] = num;
                backtracking(num + 1);
                arr[i] = 0;
                arr[i + num + 1] = 0;
            }
        }
    }
}
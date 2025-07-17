import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static char[] banWord = new char[] {'a' , 'n', 't', 'i', 'c'}; 
    static boolean[] visited = new boolean[27];
    static int bitmask = 0;
    static int[] bitArr;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        for (char c : banWord) {
            int n = c - 'a' + 1;
            visited[n] = true;
            bitmask |= 1 << n;
        }

        bitArr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            int cur_bit = 0;
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);

                cur_bit |= 1 << (c - 'a' + 1);
            }

            bitArr[i] = cur_bit;
        }

        
        backtracking(1, 5);
        System.out.println(answer);
     }


    static void backtracking(int idx, int depth) {
        if (depth == K) {
            int count = 0;
            for (int cur_bit : bitArr) {
                int result = bitmask & cur_bit;

                if (cur_bit == result) count++;
            }

            
            answer = Math.max(answer , count);
            return;
        }

        for (int i = idx; i <= 26; i++) {
            if (visited[i]) continue;
            int tmp = bitmask;
            bitmask |= 1 << i;

            backtracking(i + 1, depth + 1);
            bitmask = tmp;
        }
    }
}
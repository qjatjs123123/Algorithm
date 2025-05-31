import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static long length, width, height;
    static int N;
    static boolean state = false;
    static int[][] cubeArr;
    static long total = 0;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        length = Long.parseLong(st.nextToken());
        width = Long.parseLong(st.nextToken());
        height = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        cubeArr = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            cubeArr[i][0] = parent;
            cubeArr[i][1] = count;
        }
        
        Arrays.sort(cubeArr, (a, b) -> a[0] - b[0]);
        
        recursion(length, width, height);
        
        if (!state) System.out.println(-1);
        else System.out.println(answer);
    }

    static void recursion(long length, long width, long height) {
        if (length <= 0 || width <= 0 || height <= 0) return ;
        
        long min_num = Math.min(length, width);
        min_num = Math.min(min_num, height);

        int pow_num = 0;
        state = false;
        for (int k = N - 1; k >= 0; k--) {
            int i = cubeArr[k][0];
            if (cubeArr[i][1] == 0) continue;
            
            pow_num = 1 << i;
            if (min_num < pow_num) continue;
            cubeArr[i][1]--;
            state = true;
            answer++;
            break;
        }

        
        if (state) {

            recursion(length - pow_num, pow_num, pow_num);
            recursion(length, width - pow_num, pow_num);
            recursion(length, width, height - pow_num);
        }else {
            return;
        }
    }
}
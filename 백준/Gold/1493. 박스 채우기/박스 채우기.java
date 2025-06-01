import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int length, width, height;
    static int N;
    static int cubeArr[][];
    static long answer;
    static boolean state;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        cubeArr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int pow = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            cubeArr[i][0] = pow;
            cubeArr[i][1] = count;
        }

        Arrays.sort(cubeArr, (a, b) -> b[0] - a[0]);

        recursion(length, width, height);
        if (state)
            System.out.println(answer);
        else System.out.println(-1);
    }

    static void recursion(int length, int width, int height) {
        if (length == 0 || width == 0 || height == 0) return;

        int cube_len = 1;
        boolean tmp = false;
        for (int[] curArr : cubeArr) {
            int pow = curArr[0];
            int count = curArr[1];
            cube_len = 1 << pow;

            if (count <= 0 || cube_len > length || cube_len > width ||
               cube_len > height) continue;

            state = true;
            curArr[1]--;
            answer++;
            tmp = true;
            break;
        }

        if (tmp) {
            recursion(length - cube_len, cube_len, cube_len);
            recursion(length, width - cube_len, cube_len);
            recursion(length, width, height - cube_len);
        } else {
            state = false;
        }
    }
}
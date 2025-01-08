import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int total = 0;

        if (N % 2 == 0) {
            for (int i = N / 2; i < N; i++) total += arr[i] * 2;
        } else {
            total += arr[N / 2];
            for (int i = N / 2 + 1; i < N; i++) total += arr[i] * 2;
        }

        System.out.println(total);
    }
}
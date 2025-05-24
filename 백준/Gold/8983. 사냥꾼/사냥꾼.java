import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int M, N, X;
    static int[] shooterArr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        shooterArr = new int[M];
        HashMap<Integer, Boolean> visited = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            shooterArr[i] = Integer.parseInt(st.nextToken());
            visited.put(shooterArr[i], true);
        }

        Arrays.sort(shooterArr);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int minus = X - y;
            if (minus < 0) continue;

            else if (minus == 0) {
                if (visited.containsKey(x)) answer++;
            } else {
                int start = x - minus;
                int end = x + minus;
    
                int s = binarySearch(start);
                int e = binarySearch(end);
    
                if (s < M && shooterArr[s] >= start && shooterArr[s] <= end) 
                    answer += 1;
            }

            // System.out.println(i + " " + answer);
        }

        System.out.println(answer);
    }

    static int binarySearch(int target) {
        int start = 0;
        int end = M - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (shooterArr[mid] >= target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }
}
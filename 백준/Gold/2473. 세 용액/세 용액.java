import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    static int[][] graph;
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);


        
        long min_num = Long.MAX_VALUE;
        int[] answer = new int[3];

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int pos[] = new int[3];
                pos[0] = arr[i] + arr[j];
                pos[1] = i;
                pos[2] = j;

                int start = 0;
                int end = N - 1;
                int target = pos[0] * -1;
    
                
                while (start <= end) {
                    int mid = (start + end) / 2;
    
                    if (arr[mid] <= target) start = mid + 1;
                    else end = mid - 1;
                }
    
                int s = Math.max(0, start - 2);
                int e = Math.min(start + 2, N - 1);
                for (int k = s; k <= e; k++) {
                    if (k == pos[1] || k == pos[2]) continue;
                    
                    long absNum = Math.abs((long)pos[0] + (long)arr[k]);
                    if (min_num > absNum) {
                        min_num = absNum;
                        answer[0] = arr[pos[1]];
                        answer[1] = arr[pos[2]];
                        answer[2] = arr[k];
                    }
                    
                }
            }
        }

        Arrays.sort(answer);

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
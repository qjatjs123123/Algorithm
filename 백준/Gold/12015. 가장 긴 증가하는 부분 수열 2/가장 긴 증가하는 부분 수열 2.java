import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        list.add(arr[0]);

        for (int i = 1; i < N; i++) {
            int idx = lower_bound(arr[i]);

            
            if (idx >= list.size()) list.add(arr[i]);
            else list.set(idx, arr[i]);
        }

        System.out.println(list.size());
    }

    static int lower_bound(int target) {
        int start = 0;
        int end = list.size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) >= target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }
}
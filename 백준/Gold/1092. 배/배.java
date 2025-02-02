import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] machine = new int[N];
        for (int i = 0; i < N; i++) machine[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] box = new int[M];
        for (int i = 0; i < M; i++) box[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(machine);
        Arrays.sort(box);

        machine = reverse(machine, machine.length);
        box = reverse(box, box.length);

        if (machine[0] < box[0]) {
            System.out.println(-1);
            return;
        }

        int key = 0;

        TreeSet<int[]> ts = new TreeSet<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        for (int i = 0; i < M; i++) {
            ts.add(new int[] { box[i], key++ });    
        }
        // System.out.println(ts.size());
        int ans = 0;
        
        while(!ts.isEmpty()) {
            for (int m : machine) {
                int[] new_m = new int[] {m,Integer.MAX_VALUE};
                int[] b = ts.lower(new_m);
 
                if (b == null) continue;
                ts.remove(b);

            }    

            ans++;
        }

        System.out.println(ans);
    }

    static int[] reverse(int[] arr, int n) {
        int[] new_arr = new int[n];

        for (int i = 0; i < n; i++) new_arr[i] = arr[n - i - 1];
        return new_arr;
    }
}
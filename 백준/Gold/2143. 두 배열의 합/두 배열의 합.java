import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        HashMap<Long, Long> countA = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] arrA = new long[n1];
        for (int i = 0; i < n1; i++) arrA[i] = Long.parseLong(st.nextToken());

        HashMap<Long, Long> countB = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int n2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] arrB = new long[n2];
        for (int i = 0; i < n2; i++) arrB[i] = Long.parseLong(st.nextToken());
        
        makeTreeMap(countA, arrA);
        makeTreeMap(countB, arrB);

        long total = 0;
        
        for (long key : countA.keySet()) {
            long target = T - key;

            if (countB.containsKey(target)) {
                total += countA.get(key) * countB.get(target);
            }
        }

        System.out.println(total);
    }

    static void makeTreeMap(HashMap<Long, Long> tm, long[] arr) {
        int len = arr.length;
        
        for (int gap = 1; gap <= len; gap++) {

            for (int start = 0; start < len - gap + 1; start++) {
                long total = 0;
                for (int i = start ; i < start + gap; i++) {
                    total += arr[i];
                }

                if (tm.containsKey(total)) {
                    tm.put(total, tm.get(total) + 1);
                } else 
                    tm.put(total, 1L);
            }
        }
    }
}
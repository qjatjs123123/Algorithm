import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, C;
    static int[] arr;    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        HashMap<Integer, HashMap<Integer, Boolean>> memo = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (memo.containsKey(arr[i])) {
                HashMap<Integer, Boolean> memo_tmp = memo.get(arr[i]);
                memo_tmp.put(i, true);
                
            } else {
                HashMap<Integer, Boolean> memo_tmp = new HashMap<>();
                memo_tmp.put(i, true);
                memo.put(arr[i], memo_tmp);
            }
            
            
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int hap = arr[i] + arr[j];
                if (arr[i] == C || arr[j] == C || hap == C) {
                    System.out.println(1);
                    return;
                }
    
                int target = C - hap;
                if (memo.containsKey(target)){
                    HashMap<Integer, Boolean> memo_tmp = memo.get(target);
                    if (memo_tmp.containsKey(i) && memo_tmp.size() == 1) continue;
                    if (memo_tmp.containsKey(j) && memo_tmp.size() == 1) continue;
                    if (i == j && memo_tmp.containsKey(i) && memo_tmp.size() == 2) continue;
                    System.out.println(1);
                    return;
                }            

            }
        }
        System.out.println(0);
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, K;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) arr[i] = Integer.parseInt(st.nextToken());

        HashMap<Integer, Boolean> multiTab = new HashMap<>();
        int ans = 0;
        
        for (int i = 0; i < K; i++) {
            
            if (multiTab.size() < N) {
                multiTab.put(arr[i], true);
                continue;
            }
            if (multiTab.containsKey(arr[i])) continue;

            HashMap<Integer, Integer> usedDict = new HashMap<>();

            for (int key : multiTab.keySet()) usedDict.put(key, 101);
            
            for (int j = i + 1; j < K; j++) {
                if (usedDict.containsKey(arr[j]) && usedDict.get(arr[j]) == 101) usedDict.put(arr[j], j);
            }

            int removeNum = 0;
            int removeIdx = -1;

            for (int key : usedDict.keySet()) {
                int key_idx = usedDict.get(key);

                if (removeIdx < key_idx) {
                    removeNum = key;
                    removeIdx = key_idx;
                }
            }

            ans++;
            multiTab.remove(removeNum);
            multiTab.put(arr[i], true);
            

        }

        System.out.println(ans);
    }
}
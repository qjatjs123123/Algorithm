import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class Info {
        int value;
        int idx = 0;

        Info(int value, int i) {
            this.value = value;
            this.idx = i;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        HashMap<Integer, ArrayList<Info>> infoDict = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            arr[i] = num;
            
            if (!infoDict.containsKey(num)) {
                ArrayList<Info> list = new ArrayList<>();
                list.add(new Info(num, i));
                infoDict.put(num, list);
            } else {
                infoDict.get(num).add(new Info(num, i));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int hap = arr[i] + arr[j];

                if (arr[i] == C || arr[j] == C || hap == C) {
                    System.out.println(1);
                    return;
                }
                 int num = C - hap;
                if (!infoDict.containsKey(num)) continue; 

                ArrayList<Info> list = infoDict.get(num);

                for (Info info : list) {
                    if (info.idx == i || info.idx == j) continue;

                    System.out.println(1);
                    return;
                }
            }
        }



        System.out.println(0);
    }
}
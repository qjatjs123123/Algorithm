import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            int max_ans = 0;
            int min_ans = Integer.MAX_VALUE;
            
            HashMap<Character, ArrayList<Integer>> dict = new HashMap<>();
            for (int right = 0; right < str.length(); right++) {
                char alpha = str.charAt(right);

                if (!dict.containsKey(alpha)) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(right);
                    dict.put(alpha, list);
                } else {
                    dict.get(alpha).add(right);
                }

                ArrayList<Integer> idxList = dict.get(alpha);
                if (idxList.size() >= K) {
                    int end = idxList.get(idxList.size() - 1);
                    int start = idxList.get(idxList.size() - K);
                    
                    max_ans = Math.max(max_ans, end - start + 1);
                    min_ans = Math.min(min_ans, end - start + 1);
                    
                }
            }

            if (min_ans == Integer.MAX_VALUE) sb.append(-1).append("\n");
            else sb.append(min_ans + " " + max_ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
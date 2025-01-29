import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) break;
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            ArrayList<Integer> cur_list = new ArrayList<>();
            cur_list.add(root);
            int prev = root;

            for (int i = 1; i < n; i++) {
                int node = Integer.parseInt(st.nextToken());

                if (node - prev != 1) {
                    list.add(cur_list);
                    cur_list = new ArrayList<>();
                } 
                
                cur_list.add(node);
                prev = node;
            }

            if (!cur_list.isEmpty()) list.add(cur_list);

            ArrayList<ArrayList<ArrayList<Integer>>> graph = new ArrayList();
            ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
            tmp.add(list.get(0));
            graph.add(tmp);
            tmp = new ArrayList<>();

            
            int depth = 1;
            int pointer = 1;
            
            for (int i = 0; i < list.size(); i++) {
                if (pointer >= list.size()) break;
                ArrayList<Integer> tmp_list = list.get(i);

                int tmp_cnt = tmp_list.size();

                for (int j = pointer; j < pointer + tmp_cnt; j++) {
                    if (j >= list.size()) break;
                    tmp.add(list.get(j));
                }

                pointer += tmp_cnt;
                graph.add(tmp);
                tmp = new ArrayList<>();
            }

            sb.append(getAns(graph, k)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int getAns(ArrayList<ArrayList<ArrayList<Integer>>> graph, int target) {
        int total = 0;
        int minus = 0;
         for (int depth = 0; depth < graph.size(); depth++) {
             ArrayList<ArrayList<Integer>> depth_list = graph.get(depth);
             total = 0;

             for (int i = 0; i < depth_list.size(); i++) {
                 ArrayList<Integer> inner_list = depth_list.get(i);
                 
                 for (int j = 0; j < inner_list.size(); j++) {
                     if (inner_list.get(j) == target) {
                         minus = inner_list.size();
                         break;
                     }
                 }

                 total += inner_list.size();
             }

             if (minus != 0) {
                 total -= minus;
                 break;
             }
         }

        return total;
    }
    
}
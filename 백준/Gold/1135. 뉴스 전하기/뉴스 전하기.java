import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static Node[] nodeArr;
    static int answer = 0;
    static int[] childCountArr; 
    static class Node {
        int no;
        ArrayList<Integer> child = new ArrayList<>();

        Node(int no) {
            this.no = no;
        }
    }
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();

        childCountArr = new int[N];
        nodeArr = new Node[N];
        for (int i = 0; i < N; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int no = 0; no < N; no++) {
            int parent = fs.nextInt();

            if (parent == -1) continue;
            nodeArr[parent].child.add(no);
        }

      

        System.out.println(dfs(0));
    }

    static int dfs(int no) {
        ArrayList<Integer> child = nodeArr[no].child;
        if (child.size() == 0) {
            return 0;
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int child_no : child) {
            list.add(dfs(child_no));
        }

        Collections.sort(list, Collections.reverseOrder());

        int maxTime = 0;
        for (int i = 0; i < list.size(); i++) {
            maxTime = Math.max(maxTime, list.get(i) + i + 1);
        }
        
        return maxTime;
    }
    
}
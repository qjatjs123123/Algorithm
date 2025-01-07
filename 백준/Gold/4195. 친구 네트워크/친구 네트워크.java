import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int T;
    static HashMap<String, String> parent;
    static HashMap<String, Integer> memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int F = Integer.parseInt(st.nextToken());

            parent = new HashMap<>();
            memo = new HashMap<>();

            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String nameA = st.nextToken();
                String nameB = st.nextToken();

                if (!parent.containsKey(nameA)) {
                    parent.put(nameA, nameA);
                    memo.put(nameA, 1);
                }

                if (!parent.containsKey(nameB)) {
                    parent.put(nameB, nameB);
                    memo.put(nameB, 1);
                }

                int networkSize = union(nameA, nameB);
                sb.append(networkSize).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    static String find(String name) {
        if (!parent.get(name).equals(name)) {
            parent.put(name, find(parent.get(name))); // Path compression
        }
        return parent.get(name);
    }

    static int union(String nameA, String nameB) {
        String rootA = find(nameA);
        String rootB = find(nameB);

        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
            memo.put(rootA, memo.get(rootA) + memo.get(rootB));
        }

        return memo.get(rootA); // Return the size of the network
    }
}

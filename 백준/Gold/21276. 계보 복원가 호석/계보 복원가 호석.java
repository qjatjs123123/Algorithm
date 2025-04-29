import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static String[] nameArr;
    static int[] countArr;
    static HashMap<String, ArrayList<String>> parentGraph = new HashMap<>();
    static HashMap<String, ArrayList<String>> childGraph = new HashMap<>();
    static HashMap<String, ArrayList<String>> answer = new HashMap<>();
    static HashMap<String, Integer> indexDict = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nameArr = new String[N];
        countArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int col = 0; col < N; col++) {
            nameArr[col] = st.nextToken();
            indexDict.put(nameArr[col], col);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent = st.nextToken();

            if (parentGraph.containsKey(child)) {
                parentGraph.get(child).add(parent);
            } else {
                parentGraph.put(child, new ArrayList<>());
                parentGraph.get(child).add(parent);
            }

            if (childGraph.containsKey(parent)) {
                childGraph.get(parent).add(child);
            } else {
                childGraph.put(parent, new ArrayList<>());
                childGraph.get(parent).add(child);
            }

            int parentIdx = indexDict.get(parent);
            countArr[parentIdx]++;
        }

        Deque<String> deque = new ArrayDeque<>();
        ArrayList<String> rootList = new ArrayList<>();
        boolean[] visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            if (countArr[i] == 0) deque.add(nameArr[i]);
        }

        while (!deque.isEmpty()) {
            String cur_name = deque.pollFirst();

            if (!parentGraph.containsKey(cur_name)) {
                rootList.add(cur_name);
            } else {
                ArrayList<String> parentList = parentGraph.get(cur_name);

                for (String parent : parentList) {
                    int parentIdx = indexDict.get(parent);
                    countArr[parentIdx]--;

                    if (countArr[parentIdx] == 0) {
                        deque.add(parent);
                        
                        ArrayList<String> childList = childGraph.get(parent);
                        for (String child : childList) {
                            int childIdx = indexDict.get(child);

                            if (visited[childIdx]) continue;
                            visited[childIdx] = true;
                            
                            if (answer.containsKey(parent)) {
                                answer.get(parent).add(child);
                            } else {
                                answer.put(parent, new ArrayList<>());
                                answer.get(parent).add(child);
                            }
                        }
                        
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(rootList);
        
        sb.append(rootList.size()).append("\n");
        for (String root : rootList) sb.append(root + " ");
        sb.append("\n");

        Arrays.sort(nameArr);

        for (String name : nameArr) {
            if (answer.containsKey(name)) {
                ArrayList<String> list = answer.get(name);
                Collections.sort(list);
                sb.append(name).append(" ").append(list.size()).append(" ");
                for (String s : list) sb.append(s + " ");
            } else {
                sb.append(name + " 0");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());

    }
}
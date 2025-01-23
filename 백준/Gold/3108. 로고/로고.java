import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] parents;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   
        parents = new int[N];
        int isZero = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
        
            list.add(new int[] {x1, y1, x2, y2});
            if ((x1 <= 0 && x2 >= 0 && (y1 == 0 || y2 == 0)) || 
                (y1 <= 0 && y2 >= 0 && (x1 == 0 || x2 == 0))) isZero = 1; 
        }

        for (int i = 0 ; i < N; i++) parents[i] = i;

        for (int i = 0; i < N - 1; i++) {
            int[] arr1 = list.get(i);
            for (int j = i + 1; j < N; j++) {
                int[] arr2 = list.get(j);

                boolean isConnect = isConnected(arr1, arr2);

                if(isConnect) union(i, j);
                
                
            }


        }

        
        HashMap<Integer, Boolean> visited = new HashMap<>();

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int root = find(i);

            if (!visited.containsKey(root))  {
                ans++;
                visited.put(root, true);
            }
        }

        System.out.println(ans - isZero);
        
    }

     static boolean isConnected(int[] arr1, int[] arr2) {
        
        if (arr1[0] > arr2[2] || arr1[2] < arr2[0] || arr1[1] > arr2[3] || arr1[3] < arr2[1]) return false;
        if (arr2[0] > arr1[2] || arr2[2] < arr1[0] || arr2[1] > arr1[3] || arr2[3] < arr1[1]) return false;
         if (arr1[0] < arr2[0] && arr1[2] > arr2[2] && arr1[1] < arr2[1] && arr1[3] > arr2[3]) {
        return false;
    }
        // arr2가 arr1을 완전히 포함하는 경우
        if (arr2[0] < arr1[0] && arr2[2] > arr1[2] && arr2[1] < arr1[1] && arr2[3] > arr1[3]) {
            return false;
        }
         return true;
        
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }
}
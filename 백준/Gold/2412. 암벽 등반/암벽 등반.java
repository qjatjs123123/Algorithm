import java.util.*;
import java.lang.*;
import java.io.*;

//y 좌표 해시맵 저장 x 좌표 ArrayList저장
// 가능한 y좌표, (y + 2, y + 1, y, y - 1, y - 2)
// x hashSet, (1, 2, 3, 4)
// deque => (0, 0)
// visited 체크 해시맵 2개
// 있음연 continue
// 가능한 y좌표 for문 x좌표 순회 x 좌표 가능한거 찾기
// visited 체크 continue
// deque.add(y, x, cnt + 1)
// 만약 y == T break




// The main method must be in a class named "Main".
class Main {
    static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    static HashMap<Integer, HashMap<Integer, Boolean>> visited = new HashMap<>();
    static int n, T;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (!graph.containsKey(y)) {
                HashSet<Integer> hs = new HashSet<>();
                hs.add(x);

                graph.put(y, hs);
                visited.put(y, new HashMap<>());
            } else {
                HashSet<Integer> hs = graph.get(y);
                hs.add(x);
            }
            
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {0, 0, 0});
        HashMap<Integer, Boolean> tmp = new HashMap<>();
        tmp.put(0, true);
        visited.put(0, tmp);

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            if (cur_arr[1] == T) {
                System.out.println(cur_arr[2]);
                return;
            }

            for (int i = -2; i <= 2; i++) {
                int y = cur_arr[1] + i;
                HashSet<Integer> xHashSet = graph.get(y);


                if (xHashSet == null) continue;
                for (int x : xHashSet) {
                    if (!isValidPos(cur_arr[0], x)) continue;
                    if (isVisited(x, y)) continue;
                    
                    deque.add(new int[] {x, y, cur_arr[2] + 1});
                }
            }
        }

        System.out.println(-1);
    }

    static boolean isValidPos (int origin, int check) {
        if (origin - 2 <= check && origin + 2 >= check) return true;
        return false;
    }
    
    static boolean isVisited(int x, int y) {
        HashMap<Integer, Boolean> xMap = visited.get(y);
        if (!xMap.containsKey(x)) {
            xMap.put(x, true);
            return false;
        }
   
        return true;
    }
}
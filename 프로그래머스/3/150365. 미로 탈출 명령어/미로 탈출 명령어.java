import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        Deque<int[]> deque = new ArrayDeque<>();
        int cur_k = 0;
        deque.add(new int[] {x, y});
        int[] dy = new int[] {1, 0, 0, -1};
        int[] dx = new int[] {0, -1, 1, 0};
        String[] dir = new String[] {"d", "l", "r", "u"};
        
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            int[] posArr = deque.pollFirst();
            int dist = Math.abs(posArr[0] - r) + Math.abs(posArr[1] - c);
            
            int remain = k - cur_k;
            if (dist > remain) break;
            
            for (int i = 0; i < 4; i++) {
                int new_x = posArr[0] + dy[i];
                int new_y = posArr[1] + dx[i];
                
                if (new_x < 1 || new_x > n || new_y < 1 || new_y > m) continue;
                
                int new_dist = Math.abs(new_x - r) + Math.abs(new_y - c);
                int new_remain = k - cur_k - 1;
                
                if (new_dist > new_remain) continue;
                
                sb.append(dir[i]);
                cur_k++;
                deque.add(new int[] {new_x, new_y});
                break;
            }
        }
        
        if (sb.length() != k) return "impossible";
        return sb.toString();
    }
}

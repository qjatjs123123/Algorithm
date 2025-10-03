import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        PriorityQueue<int[]> deliveryPQ = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        PriorityQueue<int[]> pickupPQ = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        
        for (int i = 0; i < n; i++) {
            int cnt = deliveries[i];
            if (cnt == 0) continue;
            deliveryPQ.add(new int[] {i + 1, cnt});
        }
        
        for (int i = 0; i < n; i++) {
            int cnt = pickups[i];
            if (cnt == 0) continue;
            pickupPQ.add(new int[] {i + 1, cnt});
        }
        
        while (true) {
            if (deliveryPQ.isEmpty() && pickupPQ.isEmpty()) break;
            
            int cur_cap = cap;
            int delivery_dist = 0;
            while(!deliveryPQ.isEmpty()) {
                int[] cur_arr = deliveryPQ.poll();
                
                delivery_dist = Math.max(delivery_dist, cur_arr[0]);
                if (cur_arr[1] > cur_cap) {
                    cur_arr[1] -= cur_cap;
                    cur_cap = 0;
                    deliveryPQ.add(cur_arr);
                } else {
                    cur_cap -= cur_arr[1];
                }
                
                if (cur_cap == 0) break;
            }
            
            cur_cap = cap;
            int pick_dist = delivery_dist;
            while(!pickupPQ.isEmpty()) {
                int[] cur_arr = pickupPQ.poll();
                      
                pick_dist = Math.max(pick_dist, cur_arr[0]);
                if (cur_arr[1] > cur_cap) {
                    cur_arr[1] -= cur_cap;
                    cur_cap = 0;
                    pickupPQ.add(cur_arr);
                } else {
                    cur_cap -= cur_arr[1];
                }
                if (cur_cap == 0) break;
            }
            
            answer += Math.max(delivery_dist, pick_dist) * 2;


        }
        
        return answer;
    }
}
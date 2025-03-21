import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = distance;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        Arrays.sort(rocks);
        
        for (int i = 0; i < rocks.length; i++ ) list.add(rocks[i]);
        list.add(distance);
        
        
        int start = 0;
        int end = distance;
        
        while (start <= end) {
            answer = (start + end) / 2;
            
            int jumpCnt = 0;
            int cur_pos = 0;
            
            while (true) {
                cur_pos += answer;
                int idx = binary_search(list, cur_pos);
                jumpCnt++;
                if (idx >= list.size()) break;
                cur_pos = list.get(idx);
                                 
            }
            
            if (jumpCnt <= rocks.length - n + 1) end = answer - 1;
            else start = answer + 1;
        } 
        return start;
    }
    
    static int binary_search(ArrayList<Integer> rocks, int target) {
        int start = 0;
        int end = rocks.size() - 1;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (rocks.get(mid) > target) end = mid - 1;
            else start = mid + 1;
        }
        
        return start;
    }
}
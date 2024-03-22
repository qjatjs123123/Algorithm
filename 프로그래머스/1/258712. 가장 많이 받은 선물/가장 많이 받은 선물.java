import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        HashMap<String, Integer> dict = new HashMap<>();
        HashMap<Integer, Integer> give_count_dict = new HashMap<>(); 
        HashMap<Integer, Integer> take_count_dict = new HashMap<>(); 
        
        int[][] table = new int[N][N];
        
        // friends 배열 dict에 저장하기
        for (int i = 0; i < friends.length; i++) {
            dict.put(friends[i], i);
            give_count_dict.put(i, 0);
            take_count_dict.put(i, 0);
        }
        
        // 주고받은 선물과 선물 지수 표 나타내기
        for (String str : gifts) {
            String[] tmp = str.split(" ");
            int give_idx = dict.get(tmp[0]);
            int take_idx = dict.get(tmp[1]);
            
            table[give_idx][take_idx] += 1;
            give_count_dict.put(give_idx, give_count_dict.get(give_idx) + 1);
            take_count_dict.put(take_idx, take_count_dict.get(take_idx) + 1);    
        }
        int answer = 0;
        
        for (int give_idx = 0; give_idx < N; give_idx++) {
            int total = 0;
            for (int to_idx = 0; to_idx < N; to_idx++) {
                if (give_idx == to_idx) continue;
                
                int take_count = table[to_idx][give_idx];
                int give_count = table[give_idx][to_idx];
                
                int give_point = give_count_dict.get(give_idx) - take_count_dict.get(give_idx);
                int take_point = give_count_dict.get(to_idx) - take_count_dict.get(to_idx);
                // 
                if (give_count > take_count) total++;
                else if (( give_count == take_count ) && ( give_point > take_point )) {
                    total++;
                }
            }

            answer = Math.max(answer, total);
        }
        
        
        return answer;
    }
    
    
}
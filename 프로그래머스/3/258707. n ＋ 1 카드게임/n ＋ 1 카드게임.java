import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        HashMap<Integer, Boolean> own_card = new HashMap<>();
        HashMap<Integer, Boolean> res_card = new HashMap<>();
        int N = cards.length + 1;
        // 초기 4개 저장
        for (int i = 0; i < cards.length/3; i++) {
            own_card.put(cards[i], true);
        }
        boolean flg1 = false;
        int idx = cards.length/3;
        while (true) {
            answer++;
            if (idx >= cards.length) break;
            // 2장 뽑은 카드 res_card_list저장하기
            if (idx < cards.length){
                res_card.put(cards[idx++], true);
                res_card.put(cards[idx++], true);
            }
            
            // 해시맵을 리스트로
            ArrayList<Integer> own_card_list = getArrayList(own_card);
            ArrayList<Integer> res_card_list = getArrayList(res_card);

            boolean flg = false;
            // 먼저 소유하고 있는 카드에서 찾을 수 있는지 확인 => 코인 0개 없앰
            for (int own_num : own_card_list) {
                int target = N - own_num;
                
                // 찾을 수 있다면 own_card_list에서 지우기
                if (own_card.containsKey(target)) {
                    own_card.remove(target);
                    own_card.remove(own_num);
                    flg = true;
                    break;
                }
            }
            
            if (flg) continue;
            
            // 소유하고 있는 카드 기준으로 넘긴 카드에서 찾을 수 있는지 확인 => 코인 1개 없앰
            for (int own_num : own_card_list) {
                // 코인이 1개 미만이면 나가기
                if (coin < 1) break;
                int target = N - own_num;
                
                // 찾을 수 있다면 own_card_list와 ,res_card_list에 지우기
                if (res_card.containsKey(target)) {
                    own_card.remove(own_num);
                    res_card.remove(target);
                    coin--;
                    flg = true;
                    break;
                }
            }
            
            if (flg) continue;
            
            
            //넘긴 카드에서 찾을 수 있는지 확인 => 코인 2개 없앰
            for (int res_num : res_card_list) {
                //코인이 2개미만이면 나가기
                if (coin < 2) break;
                int target = N - res_num;
                
                // 찾을 수 있다면 res_card_list에 지우기
                 if (res_card.containsKey(target)) {
                    res_card.remove(res_num);
                    res_card.remove(target);
                    flg = true;
                    coin -= 2;
                    break;
                }
            }
            
            if (!flg){
                
                break;
            } 
            
        }
        

        
        return answer;
    }
    
    static ArrayList<Integer> getArrayList (HashMap<Integer, Boolean> map) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int n : map.keySet()) tmp.add(n);
        return tmp;
    }
}
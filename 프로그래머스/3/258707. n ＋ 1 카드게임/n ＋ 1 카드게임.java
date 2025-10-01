import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        HashSet<Integer> mineSet = new HashSet<>();
        HashSet<Integer> trashSet = new HashSet<>();
        ArrayDeque<Integer> cardList = new ArrayDeque<>();
        
        int answer = 0;
        int len = cards.length;
        int point = len / 3;
        
        for (int i = 0; i < point; i++) mineSet.add(cards[i]);
        for (int i = point; i < len; i++) cardList.addLast(cards[i]);
        
        while (true) {
            answer++;
            if (cardList.isEmpty() || mineSet.isEmpty()) {
                break;
            }
            
            // 2장 뽑기
            trashSet.add(cardList.pollFirst());
            trashSet.add(cardList.pollFirst());

            
            boolean flg = false;
            int target = -1;
            for (int mine : mineSet) {
                int tmp = (len + 1) - mine;
                if (mineSet.contains(tmp)) {
                    target = mine;
                    flg = true;
                    break;
                }
            }
            
            if (flg) {
                mineSet.remove(target);
                mineSet.remove( (len + 1) - target );
                continue;
            }
            
            // 1개 뽑기
            if (coin >= 1) {
                
                for (int mine: mineSet) {
                    int tmp = (len + 1) - mine;
                    if (trashSet.contains(tmp)) {
                        target = mine;
                        flg = true;
                        break;
                    }
                }

                if (flg) {
                    mineSet.remove(target);
                    trashSet.remove( (len + 1) - target );
                    coin--;
                    continue;
                }
                
            }
            
            // 2개 뽑기
            if (coin >= 2) {
                
                for (int mine: trashSet) {
                    int tmp = (len + 1) - mine;
                    if (trashSet.contains(tmp)) {
                        target = mine;
                        flg = true;
                        break;
                    }
                }

                if (flg) {
                    trashSet.remove(target);
                    trashSet.remove( (len + 1) - target );
                    coin--;
                    coin--;
                    continue;
                }
                
            }
            
            break;
        }

        return answer;
    }
}
import java.util.*;

class Solution {
    static final int MONTH = 28;
    static HashMap<String, Integer> termMap = new HashMap<>();
    
    static int getDay(String date) {
        String[] dateArr = date.split("\\.");

        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        
        return (year * 12 * MONTH) + MONTH * ( month - 1 ) + day; 
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        for (String term : terms) {
            String[] termArr = term.split(" ");
            String type = termArr[0];
            int tmp = Integer.parseInt(termArr[1]);
            
            termMap.put(type, tmp);
        }
        
        int today_num = getDay(today);
        int idx = 1;
        
        Deque<Integer> deque = new ArrayDeque<>();
        for (String privacy : privacies) {
            String[] privacyArr = privacy.split(" ");
            String date = privacyArr[0];
            String type = privacyArr[1];
            
            int term = termMap.get(type);
            int privacy_num = getDay(date) + (MONTH * term) - 1;
            
            if (today_num > privacy_num) deque.addLast(idx);
            idx++;
        }
        
        int[] answer = new int[deque.size()];
        
        idx = 0;
        while (!deque.isEmpty()) answer[idx++] = deque.pollFirst();
        return answer;
    }
}
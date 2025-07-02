import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, borrowTime, F;
    static int[] prefix = new int[12];
    static int[] months = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static HashMap<String, Info> info_dict = new HashMap<>();
    static HashMap<String, Long> money_dict = new HashMap<>();
    
    static class Info {
        String name;
        int minutes;

        Info(String name, int minutes) {
            this.name = name;
            this.minutes = minutes;
        }
    }

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= 11; i++) prefix[i] = prefix[i - 1] + months[i - 1];

        N = Integer.parseInt(st.nextToken());
        String[] tmp = st.nextToken().split("/");
        borrowTime = (Integer.parseInt(tmp[0]) * 1440) + getMinuteByTime(tmp[1]);
        F = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String date = st.nextToken();
            String time = st.nextToken();
            String type = st.nextToken();
            String name = st.nextToken();
            String key = type + "-" + name;
            int cur_minutes = getMinuteByDate(date) + getMinuteByTime(time);

            
            if (!info_dict.containsKey(key)) {
                info_dict.put(key, new Info(name, cur_minutes ));
            } else {
                Info prev_info = info_dict.get(key);
                
                int prev_minutes = prev_info.minutes;
                long cur_money = ((cur_minutes - prev_minutes) - borrowTime) * F;

                if (cur_money > 0) {
                    if (money_dict.containsKey(name)) money_dict.put(name, cur_money + money_dict.get(name));
                    else {
                        money_dict.put(name, cur_money);
                    }
                } 
                
                info_dict.remove(key);
            }
        }

        if (money_dict.size() == 0) System.out.println(-1);
        else {
            ArrayList<String> list = new ArrayList<>();

            
            for (String name : money_dict.keySet()) list.add(name);

            Collections.sort(list);
            StringBuilder sb = new StringBuilder();

            for (String name : list) sb.append(name).append(" ").append(money_dict.get(name)).append("\n");

            System.out.println(sb.toString());
        }
        
    }

    static int getMinuteByDate(String date) {
        String[] tmp = date.split("-");
        int year = Integer.parseInt(tmp[0]);
        int month = Integer.parseInt(tmp[1]);
        int day = Integer.parseInt(tmp[2]);

        return (prefix[month - 1] + day) * 1440;
    }
    
    static int getMinuteByTime(String time) {
        String[] tmp = time.split(":");
        int hour = Integer.parseInt(tmp[0]);
        int minute = Integer.parseInt(tmp[1]);

        return hour * 60 + minute;
    }
}
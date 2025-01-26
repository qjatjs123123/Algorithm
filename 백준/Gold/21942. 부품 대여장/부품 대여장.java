import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, DDD, hh, mm, F;
    static int[] MONTHS = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] prefix;
    static int ONE_DAY_MINUTE = 1440;
    static HashMap<String, String[]> dict = new HashMap<>();
    static HashMap<String, Long> fee = new HashMap<>();
    static int saveTime = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        prefix = new int[13];

        for (int i = 1; i <= 12; i++) prefix[i] = prefix[i - 1] + MONTHS[i - 1];

        String data = st.nextToken();
        
        DDD = Integer.parseInt(data.split("/")[0]);
        hh = Integer.parseInt(data.split("/")[1].split(":")[0]);
        mm = Integer.parseInt(data.split("/")[1].split(":")[1]);
        saveTime += ONE_DAY_MINUTE * DDD + hh * 60 + mm;
        
        F = Integer.parseInt(st.nextToken());

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            String dateStr = st.nextToken();
            String timeStr = st.nextToken();
            String itemStr = st.nextToken();
            String nameStr = st.nextToken();

            int totalMinute = parseDateToMinute(dateStr) + parseTimeToMinute(timeStr);

            if (!isReturn(itemStr, nameStr)) itemBorrow(itemStr, nameStr, totalMinute);
            else itemReturn(itemStr, nameStr, totalMinute);
        
        }

        display();
    }

    static void display() {
        ArrayList<String> list = new ArrayList<>();
        for(String key : fee.keySet()) {
            list.add(key);
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        boolean isflg = false;
        for (String key : list) {
            isflg = true;
            sb.append(key).append(" ").append(fee.get(key) * F);
            sb.append("\n");
        }
        
        if(!isflg) System.out.println(-1);
        else System.out.println(sb.toString());
    }
    
    static void itemReturn(String itemStr, String nameStr, int returnMinute) {
        String[] data = dict.get(itemStr + nameStr);
        String dataName = data[0];
        int borrowMinute = Integer.parseInt(data[1]);

        long timeGap = returnMinute - borrowMinute - saveTime;

        if (timeGap > 0) {
            if (fee.containsKey(dataName)) {
                long cur_fee = fee.get(dataName);
                fee.put(dataName, cur_fee + timeGap);
            } else {
                fee.put(dataName, timeGap);
            }
        }

        dict.remove(itemStr + nameStr);
    }
    
    static void itemBorrow(String itemStr, String nameStr, int totalMinute) {
        String[] data = new String[] {nameStr, Integer.toString(totalMinute)};
        dict.put(itemStr + nameStr, data);
    }
    
    static boolean isReturn (String itemStr, String nameStr) {
        if (dict.containsKey(itemStr + nameStr)) return true;
        return false;
    }
    
    static int parseDateToMinute(String dateStr) {
        int year = Integer.parseInt(dateStr.split("-")[0]);
        int month = Integer.parseInt(dateStr.split("-")[1]);
        int day = Integer.parseInt(dateStr.split("-")[2]);

        return (prefix[month - 1] + (day - 1)) * ONE_DAY_MINUTE;
    }

    static int parseTimeToMinute(String timeStr) {
        int hour = Integer.parseInt(timeStr.split(":")[0]);
        int minute = Integer.parseInt(timeStr.split(":")[1]);

        return hour * 60 + minute;
    }
}
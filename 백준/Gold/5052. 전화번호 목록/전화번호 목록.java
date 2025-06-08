import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Info {
        boolean isLast = false;;
        HashMap<Character, Info> dict = new HashMap<>();

        Info() {
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            HashMap<Character, Info> root = new HashMap<>();
            boolean isflg = false;
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                String phone = st.nextToken();
                list.add(phone);
            }

            Collections.sort(list, (a, b) -> a.length() - b.length());
            
            for (int j = 0; j < n; j++){
                String phone = list.get(j);
                if (isflg) continue;
                Info cur_info = null;

                for (int k = 0; k < phone.length(); k++) {
                    char c = phone.charAt(k);
                    if (k == 0) {
                        if (root.containsKey(c)) cur_info = root.get(c);
                        else {
                            cur_info = new Info();
                            root.put(c, cur_info);
                        }
                    }else {
                        HashMap<Character, Info> cur_dict = cur_info.dict;
                        if (!cur_dict.containsKey(c)) {
                            cur_dict.put(c, new Info());
                        } 
                        
                        cur_info = cur_dict.get(c);
                    }
                    

                    if (cur_info.isLast) {
                        isflg = true;
                        break;
                    }

                    if (k == phone.length() - 1) {
                        cur_info.isLast = true;
                    }
                } 
            }

            if(isflg) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }

        System.out.println(sb.toString());
    }
}
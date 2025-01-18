import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                list.add(Integer.parseInt(st.nextToken()));
            }
        } catch(Exception e) {
            
        }
        
        recursion(0, list.size() - 1);
        System.out.println(sb.toString());
    }

    static void recursion(int left, int right) { 
        if (left >= list.size() || right >= list.size() || left > right) return;
        int cur_root = list.get(left);
        if (left == right) {
            sb.append(cur_root).append("\n");
            return;
        }
        
        int new_left = left + 1;
        for (; new_left <= right; new_left++) {
            if (cur_root < list.get(new_left)) break;
            
        }
        recursion(left + 1, new_left - 1);
        recursion(new_left, right);
        sb.append(cur_root).append("\n");
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int starCountArr[], numArr[];
    static ArrayList<int[]> s1_list = new ArrayList<>();
    static ArrayList<int[]> s2_list = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static int idx = 0;
    static boolean[] visited = new boolean[10];
    static int answer = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        starCountArr = new int[N];
        for (int i = 0; i < N; i++) starCountArr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numArr = new int[K];
        for (int i = 0; i < K; i++) numArr[i] = Integer.parseInt(st.nextToken());

        for (int n : numArr) visited[n] = true;
        
        controller();

        System.out.println(answer);
    }

    static void controller() {
        permutation(0, starCountArr[0], s1_list);
        permutation(0, starCountArr[1], s2_list);

        for (int[] s1_arr : s1_list) {
            for (int[] s2_arr : s2_list) {
                calculator(s1_arr, s2_arr);
            }
        }
    }

    static void calculator(int[] s1_arr, int[] s2_arr) {
        int s1_number = convertNumber(s1_arr);
        
        
        int s2_len = s2_arr.length;
        int total = 0;
        
        for (int i = 0; i < s2_len; i++) {
            int new_num = s1_number * s2_arr[i];

            if (!isValidLength(new_num, starCountArr[2 + i])) return;
            if (!isValidNumber(new_num)) return;

            total += new_num * (int) Math.pow(10, s2_len - i - 1);
        }

        if (isValidLength(total, starCountArr[starCountArr.length - 1]) && isValidNumber(total)) answer++;
    }

    static boolean isValidNumber(int new_num) {
        String s = Integer.toString(new_num);

        for (int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i);

            int n  = ss - '0';
            if (!visited[n]) return false;
        }

        return true;
    }
    
    static boolean isValidLength(int new_num, int max_length) {
        String s = Integer.toString(new_num);

        return s.length() == max_length;
    }
    
    static int convertNumber(int[] arr) {
        int len = arr.length;
        int total = 0;
        
        for (int i = 0; i < arr.length; i++) {
            total += arr[i] * (int) Math.pow(10, len - i - 1);
        }

        return total;
    }
    
    static void display(int[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

    static int[] copyArr(int length) {
        int[] arr = new int[length];

        int idx = 0;
        for (int ss : stack) {
            arr[idx++] = ss;
        } 

        return arr;
    }
    
    static void permutation(int depth, int end, ArrayList<int[]> list) {
        if (depth == end) {
            list.add(copyArr(stack.size()));
            return;
        }

        for (int i = 0; i < K; i++) {
            stack.push(numArr[i]);
            permutation(depth + 1, end, list);
            stack.pop();
        }
    }
}
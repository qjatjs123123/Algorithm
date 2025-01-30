import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int K, M;
    static int MAX_NUM = 98765;
    static ArrayList<Integer> primeList = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static boolean[] visited = new boolean[10];
    static HashMap<Integer, Boolean> primeDict = new HashMap<>();
    static int ans = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        getPrime();
        backtracking(0);

        System.out.println(ans);
    }

    static int getNumber() {
        int pow = stack.size() - 1;
        int num = 0;
        
        for (int n : stack) num += Math.pow(10, pow--) * n;

        return num;
    }

    static int divide () {
        int cur_num = getNumber();
        
        while (true) {
            if (cur_num % M != 0) break;

            cur_num /= M;
        }

        return cur_num;
    }
    
    static boolean isValidMulti() {
        int cur_num = divide();
        int middle = cur_num / 2 + 1;
        boolean isValid = false;

        for (int p : primeList) {
            if (p > middle) break;
            if (cur_num % p != 0) continue;
            
            int other = cur_num / p;
            if (primeDict.containsKey(other)) {
                isValid = true;
                break;
            }
        }

        return isValid;
    }
    
    static boolean isValidAdd() {
        int cur_num = getNumber();
        int middle = cur_num / 2 + 1;
        boolean isValid = false;
        
        for (int p : primeList) {
            if (p >= middle) break;

            int other = cur_num - p;
            if (primeDict.containsKey(other) && p != other) {
                isValid = true;
                break;
            }
        }

        return isValid;
    }
    
    static void backtracking(int depth) {
        if (depth == K) {
            if (isValidAdd() && isValidMulti()) {

                ans++;
            }
            return;
        }
        
        for (int i = 0; i < 10; i++) {
            if (i == 0 && stack.isEmpty()) continue;
            if (visited[i]) continue;
            
            stack.push(i);
            visited[i] = true;
            backtracking(depth + 1);
            stack.pop();
            visited[i] = false;
        }
    }
    
    static void getPrime() {
        boolean[] isPrime = new boolean[MAX_NUM + 1];

        for (int i = 0; i <= MAX_NUM; i++) isPrime[i] = true;

        for (int i = 2; i <= MAX_NUM; i++) {

            if (isPrime[i]) {
                for (int j = i * 2; j <= MAX_NUM; j += i) isPrime[j] = false;
            }
            if (isPrime[i]){
                primeList.add(i);
                primeDict.put(i, true);
            }
                
        }
    }
}
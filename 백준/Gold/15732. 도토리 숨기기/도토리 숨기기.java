import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K, D;
    static Rule[] ruleArr;
    static class Rule {
        int boxA, boxB, gap;

        Rule(int boxA, int boxB, int gap) {
            this.boxA = boxA;
            this.boxB = boxB;
            this.gap = gap;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        ruleArr = new Rule[K];
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int boxA = Integer.parseInt(st.nextToken());
            int boxB = Integer.parseInt(st.nextToken());
            int gap = Integer.parseInt(st.nextToken());

            ruleArr[i] = new Rule(boxA, boxB, gap);
        }

        int start = 1;
        int end = N;

        while (start <= end) {
            int mid = (start + end) / 2;
            long count = 0;

            for (Rule rule : ruleArr) {
                if (rule.boxA > mid) continue;

                int dist = Math.min(rule.boxB, mid) - rule.boxA;
                count += (dist / rule.gap) + 1;
            }

            if (count >= D) end = mid - 1;
            else start = mid + 1;
            // System.out.println(mid + " " + count);
        }

        System.out.println(start);
    }
}
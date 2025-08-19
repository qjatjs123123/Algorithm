import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] heightArr = new int[N + 1];
        int[] countArr = new int[N + 1];
        PriorityQueue<int[]>[] idxArr = new PriorityQueue[N + 1];

        for (int i = 0; i <= N; i++) idxArr[i] = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            heightArr[i] = Integer.parseInt(st.nextToken());
        }
        
        
        for (int i = 1; i <= N; i++) {
            int height = heightArr[i];

            if (stack.isEmpty()) stack.push(new int[] {height, i});
            else {
                while (!stack.isEmpty()) {
                    int[] topArr = stack.peek();
                    
                    if (topArr[0] > height) {
                        idxArr[i].add(new int[] {Math.abs(i - topArr[1]), topArr[1]});
                        break;
                    } else {
                        stack.pop();
                    }
                    
                }

                countArr[i] += stack.size();
                stack.push(new int[] {height, i});
            }
        }


        stack.clear();
        for (int i = N; i > 0; i--) {
            int height = heightArr[i];

            if (stack.isEmpty()) stack.push(new int[] {height, i});
            else {
                while (!stack.isEmpty()) {
                    int[] topArr = stack.peek();
                    
                    if (topArr[0] > height) {
                        idxArr[i].add(new int[] {Math.abs(i - topArr[1]), topArr[1]});
                        break;
                    } else {
                        stack.pop();
                    }
                    
                }

                countArr[i] += stack.size();
                stack.push(new int[] {height, i});
            }
        }

        for (int i = 1; i <= N; i++) {
            
            if (idxArr[i].isEmpty()) sb.append(0);
            else {
                int[] a = idxArr[i].poll();
                sb.append(countArr[i] + " " + a[1]);
            }

            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
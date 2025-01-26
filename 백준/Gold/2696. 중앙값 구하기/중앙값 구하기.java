import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int T;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            int rowCnt = m / 10 + 1;
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = 0;
            StringBuilder sbb = new StringBuilder();
            
            for (int row = 0; row < rowCnt; row++) {
                st = new StringTokenizer(br.readLine());
                int colCnt = st.countTokens();
                for (int col = 0; col < colCnt; col++) {
                    list.add(Integer.parseInt(st.nextToken()));
                    if (col % 2 == 0) {
                        Collections.sort(list);
                        cnt++;
                        sbb.append(list.get(list.size() / 2) + " ");
                        if (cnt % 10 == 0) sbb.append("\n");
                        
                    }
                    
                }
                
            }
            if (cnt % 10 != 0)
                sbb.append("\n");
            sb.append(cnt).append("\n");
            sb.append(sbb.toString());
            
        }

        System.out.println(sb.toString());
    }
}
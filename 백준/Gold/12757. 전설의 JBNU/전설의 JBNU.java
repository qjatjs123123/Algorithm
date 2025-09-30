import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() { br = new BufferedReader(new InputStreamReader(System.in)); }

        String next() throws IOException{
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
    
    static class Database {
        TreeMap<Integer, Integer> db = new TreeMap<>();
        
        Database(FastScanner fs) throws IOException{
            for (int i = 0; i < N; i++) {
                int key = fs.nextInt(); 
                int value = fs.nextInt();

                db.put(key, value);
            }
        }

        void insert(int key, int value) {
            db.put(key, value);
        }

        String findKey(int key) {
            int lowerKey = db.lowerKey(key) == null ? Integer.MAX_VALUE : db.lowerKey(key);
            int higherKey = db.higherKey(key)== null ? Integer.MAX_VALUE : db.higherKey(key);

            if (Math.abs(lowerKey - key) == Math.abs(higherKey - key)) return "?";
            if (Math.abs(lowerKey - key) > Math.abs(higherKey - key) &&
                   Math.abs(higherKey - key) <= K) return Integer.toString(higherKey);
            if (Math.abs(lowerKey - key) < Math.abs(higherKey - key) &&
                   Math.abs(lowerKey - key) <= K) return Integer.toString(lowerKey);

            return "-1";
        }
        
        String select(int key) {
            if (db.containsKey(key)) return Integer.toString(db.get(key));

            String fKey = findKey(key);
            
            if (fKey.equals("?") || fKey.equals("-1")) return fKey;
            return Integer.toString(db.get(Integer.parseInt(fKey)));
        }

        void update(int key, int value) {
            String fKey = findKey(key);

            if (fKey.equals("?") || fKey.equals("-1")) return;

            db.put(Integer.parseInt(fKey), value);
        }
    }
    
    public static void main(String[] args) throws IOException{

        FastScanner fs = new FastScanner();
        N = fs.nextInt(); M = fs.nextInt(); K = fs.nextInt();

        Database db = new Database(fs);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int cmd = fs.nextInt();

            if (cmd == 3) {
                int key = fs.nextInt();
                sb.append(db.select(key)).append("\n");
            }else if (cmd == 2) {
                int key = fs.nextInt();
                int value = fs.nextInt();

                db.update(key, value);
            } else {
                int key = fs.nextInt();
                int value = fs.nextInt();

                db.insert(key, value);
            }     
        }

        System.out.println(sb.toString());
    }
}
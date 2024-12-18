import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static HashMap<String, Integer> dict = new HashMap<>();
	static int[] count;
	static int K;
	static HashMap<String, Integer> zero_dict = new HashMap<>(); 
	static HashMap<String, Integer> one_dict = new HashMap<>(); 
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
    	StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new int[N + 1];
        
        
        for (int row = 0; row < N; row++) {
        	st = new StringTokenizer(br.readLine());
        	String str = st.nextToken();
        	
        	if (dict.containsKey(str)) {
        		int num = dict.get(str);
        		dict.put(str, num + 1);
        	} else {
        		dict.put(str, 1);
        	}
        	
        	int zero_cnt = 0;
        	int one_cnt = 0;
        	
        	for (int i = 0; i < M; i++) {
        		if (str.charAt(i) == '0') zero_cnt++;
        		else one_cnt++;
        	}
        	
        	if (!zero_dict.containsKey(str)) {
        		zero_dict.put(str, zero_cnt);
        	}
        	
        	if (!one_dict.containsKey(str)) {
        		one_dict.put(str, one_cnt);
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        
        int total = 0;
        for (String key : dict.keySet()) {
        	int num = dict.get(key);
        	int zero_cnt= zero_dict.get(key);
        	int one_cnt= one_dict.get(key);
        	

        	
    		if ((K - zero_cnt) >= 0 && (K - zero_cnt) % 2 == 0 ) total = Math.max(num, total);
        	
        }
        
        System.out.println(total);
    }
}

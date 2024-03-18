import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
//      BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N, S;
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        int left = 0;
        int total = 0;
        int ans = Integer.MAX_VALUE;
        for (int right = 0; right < N; right++) {
        	total += arr[right];
        	
        	if (total >= S) {
        		int diff = right - left + 1;
        		ans = Math.min(ans, diff);
        		
        		while(left <= right) {
        			total -= arr[left++];
        			
        			if (total < S) break;
        			ans = Math.min(ans, right - left + 1);
                    if (total == S) break;
        		}

        	}
        }
        if (ans == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(ans);
    }
    
}

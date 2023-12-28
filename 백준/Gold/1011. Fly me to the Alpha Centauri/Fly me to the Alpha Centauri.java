import java.util.*;
import java.io.*;

public class Main
{

	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		long t = Long.parseLong(br.readLine());
		
		for (int i = 0; i<t; i++ ) {
			String[] str = br.readLine().split(" ");
			long x = Long.parseLong(str[0]);
			long y = Long.parseLong(str[1]);
			
			long distance = y - x;
			long start = 0;
			long end = 2;
			long idx = 1;
			while (true) {
				if (distance > start && distance <= end) {
					if (start < distance && distance <= start + idx) {
						System.out.println(2*idx - 1);
					}else {
						System.out.println(2*idx);
					}
					break;
				}
				start = end;
				idx++;
				end += 2*idx;
				
			}
			
		}
	}

}
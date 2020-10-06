package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_4530_D4_극한의청소작업 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= TC; t++) {			
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());

			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			long result=0;

			if(start<0) {
				result = calc(-start)+calc(end);
			}
			else {
				result= calc(end)-calc(start);
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}
	private static int calc(long num) {
		int cnt=0;
		int length = (int) Math.log10(num);
		for (int i = length; i >=0; i--) {
			long tempNum = (long) (num/Math.pow(10, i));
			if(tempNum<4) 
				cnt++;
			else {
				
			}
			
		}
		return length;
		
	}
	
	private static String src =	"3\n" + 
			"-1 1\n" + 
			"-5 3\n" + 
			"-123123123123 789789789789";
}

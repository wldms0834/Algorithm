package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_7194_D4_화섭이의미생물배양 {

	static int startNum;
	static int targetNum;
	static int plus;
	static int multi;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			result=Integer.MAX_VALUE;
			startNum = Integer.parseInt(st.nextToken());
			targetNum = Integer.parseInt(st.nextToken());
			plus = Integer.parseInt(st.nextToken());
			multi = Integer.parseInt(st.nextToken());
			
			if(multi==1) {
				if((targetNum-startNum)/plus==0) result=(targetNum-startNum)/plus;
				else result=-1;
			}
			else {
				increase(targetNum,0);
				if(result==Integer.MAX_VALUE) result =-1;
			}
			sb.append(result).append("\n");
			
		}
		System.out.println(sb);
		System.out.println(Integer.MAX_VALUE);

	}
	private static void increase(int num,int index) {
		if(num<startNum) {
			return;
		}
		if(num==startNum) {
			result = Math.min(result,index);
			return;
		}
		if(num%multi==0) {
			increase(num/multi, index+1);
			increase(num-plus, index+1);
		}
		else
			increase(num-plus, index+1);
		
		
	}
}
package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5604_D4_구간합 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long []nums = new long[10];
		int TC = Integer.parseInt(st.nextToken());	
		
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			long from = Integer.parseInt(st.nextToken());
			long to = Integer.parseInt(st.nextToken());
			long point=0;
			
		
			while(from<=to) {
				while(to%10!=9 && from<=to) {
					calc(to,nums);
					to--;
					
				}
				if(from>to) break;
				while(from%10==0&& from<=to) {
					calc(to,nums);
					from++;					
				}
				from/=10;
				to/=10;
				for (int i = 0; i <=9; i++) {
					nums[i]+=(to-from+1)*point;
				}
				point*=10;
			}
			long sum=0;
			for (int i = 0; i < nums.length; i++) {
				sum+=(nums[i]*i);
			}

			System.out.println(from +" "+to);


		}System.out.println(sb);
	}

	private static void calc(long num, long[] nums) {
		while(num>0) {
			//nums[(int) (num%10)]+=;
			num=num/10;
		}		
	}

	private static String src= "3\n" + 
			"0 10\n" + 
			"8 12\n" + 
			"33 133";
}

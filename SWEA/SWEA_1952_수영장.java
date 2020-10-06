package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1952_수영장 {

	static int[] ticketPrice = new int[4];
	static int[] month = new int[13];
	static int result;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1일, 1달, 3개월 , 1년 
		int TC = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < ticketPrice.length; i++) {
				ticketPrice[i] = Integer.parseInt(st.nextToken());
			}
			result = ticketPrice[3];
			st = new StringTokenizer(br.readLine());
			//모두 1일이용권이거나 1달이용권 
			for (int i = 1; i <=12; i++) {
				month[i]=Integer.parseInt(st.nextToken());
			}
				
			calcPrice(1, 0);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}


	static void calcPrice(int start, int cost) {
		if(start>12)  {
			result = Math.min(result, cost);
			return;
		}else {
			calcPrice(start+1, cost+ticketPrice[0]*month[start]);
			calcPrice(start+1, cost+ticketPrice[1]);
			calcPrice(start+3, cost+ticketPrice[2]);
		}
	}

	private static String src = "10\n" + 
			"10 40 100 300\n" + 
			"0 0 2 9 1 5 0 0 0 0 0 0\n" + 
			"10 100 50 300\n" + 
			"0 0 0 0 0 0 0 0 6 2 7 8\n" + 
			"10 70 180 400\n" + 
			"6 9 7 7 7 5 5 0 0 0 0 0\n" + 
			"10 70 200 550\n" + 
			"0 0 0 0 8 9 6 9 6 9 8 6\n" + 
			"10 80 200 550\n" + 
			"0 8 9 15 1 13 2 4 9 0 0 0\n" + 
			"10 130 360 1200\n" + 
			"0 0 0 15 14 11 15 13 12 15 10 15\n" + 
			"10 180 520 1900\n" + 
			"0 18 16 16 19 19 18 18 15 16 17 16\n" + 
			"10 100 200 1060\n" + 
			"12 9 11 13 11 8 6 12 8 7 15 6\n" + 
			"10 170 500 1980\n" + 
			"19 18 18 17 15 19 19 16 19 15 17 18\n" + 
			"10 200 580 2320\n" + 
			"12 28 24 24 29 25 23 26 26 28 27 22";
}

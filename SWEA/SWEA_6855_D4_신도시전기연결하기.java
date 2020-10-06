package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6855_D4_신도시전기연결하기 {
	static int N;
	static int K;
	static int[]house;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //집 갯수 
			K = Integer.parseInt(st.nextToken()); //발전소 갯수
			house = new int[N];
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < house.length; i++) {
				house[i]=Integer.parseInt(st.nextToken());
			}
			combi(0, K, 1, new int[K]);
			if(min==Integer.MAX_VALUE)
				min=0;
			sb.append(min).append("\n");
			
		}
		System.out.println(sb);
	}
	
	private static void combi(int idx, int limit,int start,int[]temp) {
		if(idx==limit) {
			int result=0;
			result=calc(temp);
			min = Math.min(result, min);
			
		}else {
			for (int i = start; i < house.length; i++) {
				temp[idx]=i;
				combi(idx+1, limit, i+1,temp);
			}
		}
	}
	private static int calc(int[] temp) {
		int total=0;
		int []result = new int[N];
		for (int i = 0; i < house.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				int t =Math.abs(house[i]-house[temp[j]]);
				if(j==0) {
					result[i]=t;
				}else {
					result[i]=Math.min(result[i], t);
				}
			}
			total+=result[i];
		}
		return total;
		
	}
	private static String src="6\n" + 
			"5 2\n" + 
			"20 40 50 80 110\n" + 
			"7 3\n" + 
			"4 7 11 18 22 27 29\n" + 
			"1 1\n" + 
			"105\n" + 
			"2 1\n" + 
			"0 100\n" + 
			"3 5\n" + 
			"33 77 99\n" + 
			"6 4\n" + 
			"0 100 200 300 400 500";
}

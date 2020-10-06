package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class BAEKJOON_2579_계단오르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		int stairCnt = Integer.parseInt(br.readLine());
		int []score = new int[stairCnt+1];
		int []result = new int[stairCnt+1];
		for (int i = 1; i < score.length; i++) {
			score[i]=Integer.parseInt(br.readLine());
		}
		
		if (stairCnt==1) {
			System.out.println(score[1]);
			return;
		}
				
		result[1]=score[1];
		result[2]=Math.max(result[0]+score[2], result[1]+score[2]);
		
		
		for (int i = 3; i < result.length; i++) {
			result[i]=Math.max(result[i-2]+score[i], result[i-3]+score[i-1]+score[i]);
		}
		System.out.println(result[result.length-1]);

	}
	private static String src="1\n" + 
			"10";
}

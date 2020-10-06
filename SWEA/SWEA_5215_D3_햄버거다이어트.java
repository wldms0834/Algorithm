package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5215_D3_햄버거다이어트 {

	static int w[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb= new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t<=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());		
			
			w=new int[N][2];
			for (int i = 0; i <N; i++) {
				st = new StringTokenizer(br.readLine());
				w[i][0]=Integer.parseInt(st.nextToken()); //가치
				w[i][1]=Integer.parseInt(st.nextToken()); //칼로리
			}
			
			int[][]dp = new int[N+1][W+1];
			
			for (int i = 1; i <=N; i++) {//물건의 종류
		         for (int j = 1; j < w[i-1][1]; j++) { //임시 배낭의 무게가 물건의 무게보다 적으면 이전차수에서 값을 가져옴
		            dp[i][j] = dp[i-1][j];
		         }
		         
		         for (int j = w[i-1][1]; j <=W; j++) {
		            int now = dp[i-1][j-w[i-1][1]]+w[i-1][0];//물건 선책을 고려한 경우
		            int pre = dp[i-1][j]; //물건을 고려하지 않은 경우
		            if(now>=pre) {
		            	dp[i][j]=now;
		            }
		            else 
		            	dp[i][j]=pre;
		         }
		         for (int j = 0; j < dp.length; j++) {
					System.out.println(Arrays.toString(dp[j]));
				}
		         System.out.println();
		      }
		      
		
			
			sb.append(dp[N][W]).append("\n");
		}System.out.println(sb);

	}

	
	private static String src="1\n" + 
			"5 10\n" + 
			"100 2\n" + 
			"300 5\n" + 
			"250 3\n" + 
			"500 10\n" + 
			"400 4";
}

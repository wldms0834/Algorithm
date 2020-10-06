package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1798_D5_범준이의제주도여행계획 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			int placeCnt = Integer.parseInt(st.nextToken());
			int period = Integer.parseInt(st.nextToken());
			int airportPosition = 0;
			
			List<Position> info = new ArrayList<>();
			int [][]moveTime = new int[placeCnt+1][placeCnt+1];
			for (int i = 1; i < moveTime.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = i+1; j < moveTime.length; j++) {
					int temp = Integer.parseInt(st.nextToken());
					moveTime[i][j]=temp;
					moveTime[j][i]=temp;
				}
			}//각 지점당 이동시간 
			
			for (int i = 0; i <placeCnt; i++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				if (c=='P') {
					info.add(new Position(c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				}else {
					if (c=='A') {
						airportPosition = i+1;
					}
					info.add(new Position(c, 0, 0));
				}
				
			}//지점당 소유시간,만족도 조사
			
			
			
			
		}
	}
	private static void findRoute(){
		//if
	}
	
	private static class Position{
		char category;
		int t, s;
		
		public Position(char category, int t, int s) {
			super();
			this.category = category;
			this.t = t;
			this.s = s;
		}
		
	}
	private static String src="1\n" + 
			"10 3 \n" + 
			"60 90 100 110 240 40 30 60 30\n" + 
			"60 120 140 200 120 100 60 60\n" + 
			"90 110 170 100 100 30 90\n" + 
			"50 110 40 80 90 90\n" + 
			"70 30 50 90 90\n" + 
			"100 140 180 120\n" + 
			"40 90 40\n" + 
			"100 20\n" + 
			"70\n" + 
			"A \n" + 
			"P 240 6\n" + 
			"P 120 4\n" + 
			"P 240 5\n" + 
			"P 60 4\n" + 
			"P 200 6\n" + 
			"P 180 1\n" + 
			"P 180 1\n" + 
			"H \n" + 
			"H";
}

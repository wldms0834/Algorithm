package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import BAEKJOON.BAEKJOON_3190_뱀.position;

/*
 * 첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.

둘째 줄부터 N개의 줄에는 도시의 정보가 주어진다.

도시의 정보는 0, 1, 2로 이루어져 있고, 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다. 
집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다. 
치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
 * 
 * 
 * */

public class BAEKJOON_15686_치킨배달 {
	static List<Position> chicken;
	static List<Position> home;
	static int M;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //남아있는 치킨집 수
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		int [][]map = new int[N][N];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					chicken.add(new Position(i, j));
				}
				else if(map[i][j]==1) {
					home.add(new Position(i, j));
				}
			}
		} //입력완료
		
		makeCom(0, 0, new Position[M]);
		System.out.println(ans);
		

	}
	
	private static void makeCom(int idx, int start,Position[]temp) {
		if(idx==M) {
			ans=Math.min(ans, calcChickenDis(temp));
		}else {
			for (int i = start; i < chicken.size(); i++) {
				temp[idx]=chicken.get(i);
				makeCom(idx+1,i+1,temp);
			}
		}
	}
	
	private static int calcChickenDis(Position[] temp) {
		int total=0;
		for (int i = 0; i < home.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < temp.length; j++) {
				min = Math.min(min, Math.abs(home.get(i).r-temp[j].r)+Math.abs(home.get(i).c-temp[j].c));
			}
			total+=min;
		}
		return total;
		
	}

	private static class Position{
		int r,c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	private static String src="5 1\n" + 
			"1 2 0 2 1\n" + 
			"1 2 0 2 1\n" + 
			"1 2 0 2 1\n" + 
			"1 2 0 2 1\n" + 
			"1 2 0 2 1";
}

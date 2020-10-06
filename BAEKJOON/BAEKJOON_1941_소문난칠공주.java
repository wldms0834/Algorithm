package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.StringTokenizer;

public class BAEKJOON_1941_소문난칠공주 {

	/*
	 * 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다. 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야
	 * 한다. 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다. 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야
	 * 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
	 * 
	 * 'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값
	 */

	static char[][] map;
	static int ans=0;
	static int[][]dirs = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String temp = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = temp.charAt(j);
			}
		}//입력완료
		
		dfs(0,0,0,0,0,0<<25);
		System.out.println(ans);
	}

	private static void dfs(int r, int c,int total, int lee, int lim, int check) {
		if (total==7) {
			ans++;
			return;
		}
		else if (lim ==4) 
			return;
		
		else {
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				/*if (isIn(nr, nc) && check) { 
				
					if (map[nr][nc]=='S') {
						dfs(nr, nc, total+1, lee+1, lim, visited);
					}
					else {
						dfs(nr, nc, total+1, lee, lim+1, visited);
					}
				}*/
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<5;
	}

	private static String src = 
							  "YYYYY\n"
							+ "SYSYS\n"
							+ "YYYYY\n"
							+ "YSYYS\n"
							+ "YYYYY";
}

package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_2636_치즈 {

	static int R, C;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;
	static int total = 0;
	static List<Position> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					total++;
				}

			}
		} // 입력완료
		List<Integer> result = new ArrayList<>();
		result.add(total);
			
		while (true) {
			if (total==0) {
				System.out.println(result.size());
				if (result.size()==0) {
					System.out.println(0);
				}
				else
					System.out.println(result.get(result.size()-2));
				return;
			}
			list = new ArrayList<>();
			visited = new boolean[R][C];
			
			visited[0][0] = true;
			map[0][0] = -1;
			
			air(0, 0); // 외부공기체크
			
			int temp=melt();
			total-=temp;
			result.add(total);
			
			//System.out.println(total);
			//for (int i = 0; i <R; i++) {
			//	System.out.println(Arrays.toString(map[i]));
			//}
		}
		
		

	}

	private static int melt() {
		int cnt=0;
		for (int i = 0; i < list.size(); i++) {
			if (map[list.get(i).r][list.get(i).c]==1) {
				map[list.get(i).r][list.get(i).c]=0;
				cnt++;
			}
		}
		return cnt;
	}

	private static void air(int r, int c) {
		for (int i = 0; i < dirs.length; i++) {
			int nr = r + dirs[i][0];
			int nc = c + dirs[i][1];

			if (isIn(nr, nc) && !visited[nr][nc]) {
				if (map[nr][nc] !=1) {
					visited[nr][nc] = true;
					map[nr][nc] = -1;
					air(nr, nc);
				}
				else if (map[nr][nc] == 1) {
					list.add(new Position(nr, nc));
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	private static class Position {
		int r, c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	private static String src = "5 5 \n" + 
			"0 0 0 0 0 \n" + 
			"0 1 1 0 0\n" + 
			"0 1 0 1 0\n" + 
			"0 1 1 1 0\n" + 
			"0 0 0 0 0";
}

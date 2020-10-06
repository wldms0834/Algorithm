package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BAEKJOON_16234_인구이동 {

	static int N;
	static int[][] land;
	static int min;
	static int max;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		land = new int[N][N];
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		int totalCnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				land[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int check = 0;
		int out;
		do {
			out = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						totalCnt = makeUnion(i, j);
						if (totalCnt != 1) {
							out++;
						}
					}
				}
			}
		if(out>0) check++;
		} while (out > 0);

		System.out.println(check);
	}

	static int makeUnion(int r, int c) {
		boolean [][]checked = new boolean[N][N];
		
		int sum = land[r][c];
		int cnt = 1;
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(r, c));
		visited[r][c] = true;
		
		checked[r][c] = true;
		
		while (!queue.isEmpty()) {
			Position np = queue.poll();
			checked[np.r][np.c] = true;
			for (int i = 0; i < dir.length; i++) {
				int nr = np.r + dir[i][0];
				int nc = np.c + dir[i][1];

				if (isIn(nr, nc) && !visited[nr][nc] && Math.abs(land[np.r][np.c] - land[nr][nc]) >= min
						&& Math.abs(land[np.r][np.c] - land[nr][nc]) <= max) {
					queue.offer(new Position(nr, nc));
					visited[nr][nc] = true;
					sum += land[nr][nc];
					cnt++;
				}
			}
		}
		
		if (cnt != 1) {
			int input = sum / cnt;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {				
					if (checked[i][j]==true) {
						land[i][j] = input;
					}
				}
			}
		}
		return cnt;

	}

	static class Position {
		int r, c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	
	private static String src = "4 10 50\n" + 
			"10 100 20 90\n" + 
			"80 100 60 70\n" + 
			"70 20 30 40\n" + 
			"50 20 100 10";
}

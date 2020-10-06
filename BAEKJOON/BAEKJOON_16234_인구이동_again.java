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

public class BAEKJOON_16234_인구이동_again {

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
		int answer = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				land[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			int c=0;
			visited=new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						c+=bfs(i, j);
					}
				}
			}
			if(c==0)
				break;
			else answer++;
		}

		System.out.println(answer);
	}

	private static int bfs(int r, int c) {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(r, c));
		int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int plus = land[r][c];
		int cnt = 1;
		visited[r][c] = true;
		List<Position> list = new ArrayList<>();
		list.add(new Position(r, c));

		while (!queue.isEmpty()) {
			Position np = queue.poll();
			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r + dirs[i][0];
				int nc = np.c + dirs[i][1];
				if (isIn(nr, nc) && !visited[nr][nc]) {
					int temp = Math.abs(land[np.r][np.c] - land[nr][nc]);
					if (temp <= max && temp >= min) {
						queue.offer(new Position(nr, nc));
						visited[nr][nc] = true;
						list.add(new Position(nr, nc));
						cnt++;
						plus += land[nr][nc];
					}
				}
			}
		}
		
		if (cnt == 1) {
			return 0;
		}
		int result = plus / cnt;
		for (int i = 0; i < list.size(); i++) {
			land[list.get(i).r][list.get(i).c]=result;
		}
		return 1;

	}

	private static class Position {
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

	private static String src = "3 10 20\n" + 
			"30 10 40\n" + 
			"97 36 97\n" + 
			"58 58 58";
}

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

public class BAEKJOON_2146_다리만들기 {
	static int N;
	static int[][] map;
	static boolean[][] border;
	static boolean[][] visited;
	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	static int min = Integer.MAX_VALUE;
	static int temp;
	static List<Position> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 배열의 크기
		map = new int[N][N]; // 맵 만들기
		visited = new boolean[N][N]; // 방문 체크 배열 만들기
		border = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵에 입력완료

		int idx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					numbering(i, j, idx);
					idx++;
				}
			}
		} // 섬에 넘버링 하기
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (border[i][j]) {
					if (map[i][j]==idx-1) {
						continue;
					}
					else
						bfs(i, j);
				}
			}
		}

		System.out.println(min);
	}

	private static void bfs(int r, int c) {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(r, c, 0));

		while (!queue.isEmpty()) {
			Position np = queue.poll();

			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r + dirs[i][0];
				int nc = np.c + dirs[i][1];
				
				if (isIn(nr, nc)) {
					if (map[nr][nc] == 0) {
						queue.offer(new Position(nr, nc, np.length + 1));
					} else if(map[nr][nc] > map[np.r][np.c] && border[nr][nc]){
						min = Math.min(min, np.length);
						return;
					}
				}
			}
		}

	}

	private static void numbering(int r, int c, int idx) {

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(r, c, idx));

		while (!queue.isEmpty()) {
			Position np = queue.poll();
			map[np.r][np.c] = np.length;
			int cnt=0;
			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r + dirs[i][0];
				int nc = np.c + dirs[i][1];
				if (isIn(nr, nc) && map[nr][nc]!=0) {
					cnt++;
				}
				if (isIn(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Position(nr, nc, idx));
				}

			}
			if (cnt!=4) {
				border[np.r][np.c]=true;
			}

		}

	}

	private static class Position {
		int r, c, length;

		public Position(int r, int c, int length) {
			super();
			this.r = r;
			this.c = c;
			this.length = length;
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static String src = "10\n" + 
			"1 1 1 0 0 0 0 1 1 1\n" + 
			"1 1 1 1 0 0 0 0 1 1\n" + 
			"1 0 1 1 0 0 0 0 1 1\n" + 
			"0 0 1 1 1 0 0 0 0 1\n" + 
			"0 0 0 1 0 0 0 0 0 1\n" + 
			"0 0 0 0 0 0 0 0 0 1\n" + 
			"0 0 0 0 0 0 0 0 0 0\n" + 
			"0 0 0 0 1 1 0 0 0 0\n" + 
			"0 0 0 0 1 1 1 0 0 0\n" + 
			"0 0 0 0 0 0 0 0 0 0";
}

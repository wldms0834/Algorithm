package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 0은 빈 칸, 6은 벽, 1~5는 CCTV
 * 1 : 한방향 (4개)
 * 2 : 일직선 (2개)
 * 3 : 직각(4개)
 * 4: 세방향(4개) 
 * 5: 네방향 (1개)
 * */
public class BAEKJOON_15683_감시 {
	static int R, C;
	static int[][] map;
	static int[][] visited;
	static List<position> list = new ArrayList<>();
	static int[][] dirs1_1 = { { 0, 1 } };
	static int[][] dirs1_2 = { { 0, -1 } };
	static int[][] dirs1_3 = { { 1, 0 } };
	static int[][] dirs1_4 = { { -1, 0 } };
	static int[][] dirs2_1 = { { 1, 0 }, { -1, 0 } };
	static int[][] dirs2_2 = { { 0, -1 }, { 0, 1 } };
	static int[][] dirs3_1 = { { 1, 0 }, { 0, 1 } };
	static int[][] dirs3_2 = { { 0, 1 }, { -1, 0 } };
	static int[][] dirs3_3 = { { -1, 0 }, { 0, -1 } };
	static int[][] dirs3_4 = { { 1, 0 }, { 0,-1 } };
	static int[][] dirs4_1 = { { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 하
	static int[][] dirs4_2 = { { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 상
	static int[][] dirs4_3 = { { -1, 0 }, { 0, -1 }, { 1, 0 } }; // 좌
	static int[][] dirs4_4 = { { -1, 0 }, { 1, 0 }, { 0,1 } }; // 우
	static int[][] dirs5 = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int zeroCnt = 0;
		map = new int[R][C];
		visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new position(i, j, map[i][j]));
				} else if (map[i][j] == 0) {
					zeroCnt++;
				}
			}
		} // 입력완료
		if (zeroCnt == 0) {
			System.out.println(0);
		} else {
			dfs(0);
			System.out.println(min);
		}
	}

	private static void dfs(int idx) {
		if (idx == list.size()) {
			int cnt = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 0) {
						cnt++;
					}
				}
			}
			min = Math.min(min, cnt);
		} else {
			int c = list.get(idx).category;
			int tempr = list.get(idx).r;
			int tempc = list.get(idx).c;

			boolean[][] visited = null;
			if (c == 2) {
				visited = bfs(tempr, tempc, dirs2_1);
				dfs(idx + 1);
				reset(tempr, tempc, dirs2_1, visited);

				visited = bfs(tempr, tempc, dirs2_2);
				dfs(idx + 1);
				reset(tempr, tempc, dirs2_2, visited);

			} else if (c == 5) {
				visited = bfs(tempr, tempc, dirs5);
				dfs(idx + 1);
				reset(tempr, tempc, dirs5, visited);

			} else if (c == 1) {
				visited = bfs(tempr, tempc, dirs1_1);
				dfs(idx + 1);
				reset(tempr, tempc, dirs1_1, visited);

				visited = bfs(tempr, tempc, dirs1_2);
				dfs(idx + 1);
				reset(tempr, tempc, dirs1_2, visited);
				
				visited = bfs(tempr, tempc, dirs1_3);
				dfs(idx + 1);
				reset(tempr, tempc, dirs1_3, visited);

				visited = bfs(tempr, tempc, dirs1_4);
				dfs(idx + 1);
				reset(tempr, tempc, dirs1_4, visited);
			} else if (c == 3) {
				visited = bfs(tempr, tempc, dirs3_1);
				dfs(idx + 1);
				reset(tempr, tempc, dirs3_1, visited);

				visited = bfs(tempr, tempc, dirs3_2);
				dfs(idx + 1);
				reset(tempr, tempc, dirs3_2, visited);

				visited = bfs(tempr, tempc, dirs3_3);
				dfs(idx + 1);
				reset(tempr, tempc, dirs3_3, visited);

				visited = bfs(tempr, tempc, dirs3_4);
				dfs(idx + 1);
				reset(tempr, tempc, dirs3_4, visited);
			} else {
				visited = bfs(tempr, tempc, dirs4_1);
				dfs(idx + 1);
				reset(tempr, tempc, dirs4_1, visited);

				visited = bfs(tempr, tempc, dirs4_2);
				dfs(idx + 1);
				reset(tempr, tempc, dirs4_2, visited);

				visited = bfs(tempr, tempc, dirs4_3);
				dfs(idx + 1);
				reset(tempr, tempc, dirs4_3, visited);

				visited = bfs(tempr, tempc, dirs4_4);
				dfs(idx + 1);
				reset(tempr, tempc, dirs4_4, visited);
			}
		}
	}

	private static boolean[][] bfs(int r, int c, int[][] dirs) {
		Queue<position> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		for (int i = 0; i < dirs.length; i++) {
			queue.offer(new position(r, c, i));
		}

		while (!queue.isEmpty()) {
			position np = queue.poll();

			int nr = np.r + dirs[np.category][0];
			int nc = np.c + dirs[np.category][1];
			if (isIn(nr, nc)) {
			if (map[nr][nc] == 6) {
				continue;
				} else if (map[nr][nc] == 0) {
					map[nr][nc] = -1;
					visited[nr][nc] = true;
				}
				queue.offer(new position(nr, nc, np.category));
			}

		}
		return visited;
	}

	private static void reset(int r, int c, int[][] dirs, boolean[][] visited) {
		Queue<position> queue = new LinkedList<>();
		for (int i = 0; i < dirs.length; i++) {
			queue.offer(new position(r, c, i));
		}

		while (!queue.isEmpty()) {
			position np = queue.poll();

			int nr = np.r + dirs[np.category][0];
			int nc = np.c + dirs[np.category][1];
			if (isIn(nr, nc)) {
				if (map[nr][nc] == 6) {
					continue;
				} else if (visited[nr][nc]) {
					map[nr][nc] = 0;
				}
				queue.offer(new position(nr, nc, np.category));
			}

		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	private static class position {
		int r, c, category;

		public position(int r, int c, int category) {
			super();
			this.r = r;
			this.c = c;
			this.category = category;
		}

	}

	private static String src = "2 4\n" + 
			"1 1 0 5\n" + 
			"0 0 0 0";
}

package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {

	static int R, C, holeR, holeC, T;
	static int[][] map;
	static int answer;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			holeR = Integer.parseInt(st.nextToken());
			holeC = Integer.parseInt(st.nextToken());

			T = Integer.parseInt(st.nextToken());

			map = new int[R][C];
			answer = 0;

			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 완료

			bfs(holeR, holeC);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);

	}

	// 1 : 상하좌우 2 : 상하 3 : 좌,우 4 : 상,우 5 : 하,우 6: 하,좌 7 : 상,좌
	private static void bfs(int holeR, int holeC) {
		// 하 우 상 좌
		int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		Queue<Position> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		queue.offer(new Position(holeR, holeC, 0));
		visited[holeR][holeC] = true;
		int cnt = 0;

		while (!queue.isEmpty()) {
			Position np = queue.poll();
			if (np.t == T) {
				break;
			}
			cnt++;
			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r + dirs[i][0];
				int nc = np.c + dirs[i][1];
				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
					if (map[np.r][np.c] == 2) {
						if (i == 1 || i == 3)
							continue;
						else if (i == 0) {
							if (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6)
								continue;
						}
						else if (i == 2) {
							if (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7)
								continue;
						}
					} else if (map[np.r][np.c] == 3) {
						if (i == 0 || i == 2)
							continue;
						else if (i == 1) {
							if (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5)
								continue;
						}
						else if (i == 3) {
							if (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7)
								continue;
						}
					} else if (map[np.r][np.c] == 4) {
						if (i == 0 || i == 3)
							continue;
						else if (i == 1) {
							if (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5)
								continue;
						}
						else if (i == 2) {
							if (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7)
								continue;
						}
					} else if (map[np.r][np.c] == 5) {
						if (i == 2 || i == 3)
							continue;
						else if (i == 1) {
							if (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5)
								continue;
						}
						else if (i == 0) {
							if (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6)
								continue;
						}
					} else if (map[np.r][np.c] == 6) {
						if (i == 1 || i == 2)
							continue;
						else if (i == 0) {
							if (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6)
								continue;
						}
						else if (i == 3) {
							if (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7)
								continue;
						}
					} else if (map[np.r][np.c] == 7) {
						if (i == 1 || i == 0)
							continue;
						else if (i == 3) {
							if (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7)
								continue;
						}
						else if (i == 2) {
							if (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7)
								continue;
						}
					}else {
						if (i == 0) {
							if (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6)
								continue;
						}
						else if (i == 1) {
							if (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5)
								continue;
						}
						else if (i == 2) {
							if (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7)
								continue;
						}
						else if (i == 3) {
							if (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7)
								continue;
						}
					}
					queue.offer(new Position(nr, nc, np.t + 1));
					visited[nr][nc] = true;

				}
			}
		}
		answer = cnt;

	}

	private static class Position {
		int r, c, t;

		public Position(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	private static String src = "5\n" + 
			"5 6 2 1 3\n" + 
			"0 0 5 3 6 0\n" + 
			"0 0 2 0 2 0\n" + 
			"3 3 1 3 7 0\n" + 
			"0 0 0 0 0 0\n" + 
			"0 0 0 0 0 0\n" + 
			"5 6 2 2 6\n" + 
			"3 0 0 0 0 3\n" + 
			"2 0 0 0 0 6\n" + 
			"1 3 1 1 3 1\n" + 
			"2 0 2 0 0 2\n" + 
			"0 0 4 3 1 1\n" + 
			"10 10 4 3 9\n" + 
			"0 0 0 0 0 0 0 0 0 0\n" + 
			"0 0 0 7 5 0 5 0 0 0\n" + 
			"0 0 3 2 2 6 0 0 0 0\n" + 
			"0 4 7 2 2 2 7 0 0 4\n" + 
			"0 3 0 1 1 2 2 0 0 5\n" + 
			"0 5 6 1 1 1 1 6 2 5\n" + 
			"7 4 1 2 0 0 4 6 0 0\n" + 
			"5 3 1 7 0 2 2 6 5 7\n" + 
			"7 3 2 1 1 7 1 0 2 7\n" + 
			"3 4 0 0 4 0 5 1 0 1\n" + 
			"20 20 13 11 13\n" + 
			"0 0 0 1 4 4 4 0 0 0 0 0 0 0 0 1 2 3 1 0\n" + 
			"0 0 0 0 0 0 0 0 0 0 0 4 2 7 7 2 0 1 1 0\n" + 
			"0 0 0 0 0 0 0 0 0 6 2 4 4 2 0 4 7 0 6 0\n" + 
			"0 0 0 7 5 5 3 0 0 7 5 0 5 6 4 2 6 3 1 5\n" + 
			"0 0 0 1 2 6 3 3 7 0 3 6 2 4 5 6 7 7 5 7\n" + 
			"0 0 0 3 7 6 1 5 3 3 4 5 7 6 0 4 3 3 1 1\n" + 
			"0 1 2 1 5 6 1 6 1 6 5 1 6 0 0 3 4 1 7 6\n" + 
			"0 2 3 2 2 7 3 0 0 3 2 5 2 1 0 6 5 1 6 5\n" + 
			"0 2 5 7 0 7 1 3 3 4 1 3 3 0 2 3 3 2 4 1\n" + 
			"4 0 0 7 2 4 2 2 1 3 1 6 5 5 6 2 5 1 1 6\n" + 
			"5 6 4 0 3 6 5 2 2 6 1 2 0 1 7 5 7 2 2 2\n" + 
			"1 6 3 1 4 4 1 0 3 0 4 2 7 2 0 2 3 6 2 5\n" + 
			"1 5 7 2 1 1 4 4 2 1 0 2 7 1 6 2 6 6 2 2\n" + 
			"3 7 0 6 5 0 4 0 6 6 7 1 3 1 1 1 5 1 6 6\n" + 
			"0 4 0 1 6 2 1 0 7 0 4 2 5 2 7 0 2 7 1 6\n" + 
			"0 7 3 0 1 7 6 2 0 0 4 2 4 1 3 3 7 0 1 3\n" + 
			"0 1 1 4 3 7 4 5 2 2 4 7 4 7 7 4 6 0 1 6\n" + 
			"0 5 2 2 1 4 6 3 7 0 6 3 5 0 0 6 4 4 2 1\n" + 
			"0 1 2 4 5 6 0 2 0 0 5 6 2 4 6 4 7 6 3 7\n" + 
			"7 7 4 2 3 0 0 4 0 0 7 2 7 5 6 1 4 5 5 4\n" + 
			"50 50 20 12 18\n" + 
			"0 0 0 0 0 0 0 0 0 0 0 0 4 5 0 0 0 0 0 4 2 0 5 2 1 5 3 3 0 0 4 0 5 1 7 2 6 0 7 0 0 0 2 0 0 0 0 0 0 0\n" + 
			"6 7 0 0 0 0 0 0 0 0 0 0 4 5 5 3 6 3 0 2 3 3 0 0 5 6 1 5 3 4 7 6 2 2 1 1 6 5 6 4 6 2 0 0 0 0 2 3 1 0\n" + 
			"0 2 6 5 7 6 0 0 0 0 0 0 6 2 0 5 6 2 0 4 1 5 0 0 2 0 7 7 0 6 0 6 2 2 4 1 2 2 1 6 6 6 0 2 2 5 0 6 5 0\n" + 
			"0 0 0 4 7 2 7 3 7 0 0 0 0 6 7 6 5 1 1 1 2 2 1 3 1 2 7 6 1 2 1 2 4 1 6 1 1 7 3 1 6 6 6 1 1 1 7 0 0 0\n" + 
			"0 0 0 5 4 0 6 3 3 7 0 0 0 6 4 3 2 5 3 1 6 1 0 4 1 0 5 7 6 3 1 1 3 6 1 1 6 3 6 7 3 3 6 5 0 7 2 2 4 6\n" + 
			"0 6 0 7 6 0 7 4 0 5 3 0 4 3 2 0 5 7 3 0 1 3 6 7 7 5 1 7 5 2 0 5 3 1 3 7 1 1 1 5 2 5 1 3 6 7 7 6 4 3\n" + 
			"5 2 0 2 6 5 0 5 6 1 6 5 5 1 7 1 2 3 6 5 1 6 7 7 6 4 1 7 5 2 0 1 3 4 6 4 5 7 2 6 5 6 2 5 6 5 6 5 1 6\n" + 
			"1 2 0 7 0 5 5 0 7 6 2 2 1 3 5 5 3 6 3 7 6 4 1 3 1 3 7 0 3 7 0 2 5 6 1 3 4 1 5 1 7 4 1 7 7 0 4 7 5 5\n" + 
			"7 6 0 3 5 1 4 0 5 2 5 0 1 3 5 5 4 4 6 1 6 5 7 6 2 1 6 5 5 3 0 5 7 1 1 3 6 2 2 2 4 5 7 4 5 1 1 0 7 3\n" + 
			"2 5 4 0 3 1 4 5 6 3 7 0 4 5 3 6 4 5 1 7 4 7 3 1 1 7 7 1 1 5 6 4 7 1 2 6 4 1 7 2 7 1 6 0 5 0 0 0 1 0\n" + 
			"3 0 2 5 1 7 1 1 1 6 5 1 3 1 3 1 1 7 1 3 6 5 5 3 1 3 1 6 2 3 2 6 6 1 1 7 5 7 5 7 1 6 0 3 5 1 5 3 0 0\n" + 
			"0 0 3 2 0 1 4 1 4 1 0 7 3 2 2 4 2 4 4 6 1 1 1 7 2 4 7 4 3 6 3 5 1 6 1 3 7 7 2 6 3 2 1 0 4 6 2 6 3 0\n" + 
			"0 0 5 4 7 2 4 6 4 1 6 7 2 2 1 6 2 1 5 4 7 2 2 1 0 7 6 1 7 2 5 7 0 4 1 6 4 0 3 0 0 5 5 0 7 7 0 3 0 0\n" + 
			"0 0 6 4 3 1 3 1 4 7 2 1 2 4 3 4 1 6 2 1 5 1 1 6 0 7 2 7 2 4 7 4 0 3 7 7 3 3 5 2 0 4 3 0 4 2 0 1 3 5\n" + 
			"0 1 0 5 6 4 4 6 5 7 0 6 1 4 5 6 2 1 2 4 4 1 1 2 6 1 6 2 0 3 7 3 0 0 5 1 7 6 6 6 1 3 4 2 1 0 7 0 5 5\n" + 
			"0 7 2 1 4 2 7 3 0 2 1 4 3 5 1 1 1 1 7 1 4 4 1 7 6 0 1 2 0 5 2 0 0 0 5 4 0 3 7 5 3 1 4 1 2 7 2 6 6 4\n" + 
			"0 1 3 0 3 4 6 3 4 2 4 0 7 5 1 1 2 7 1 6 4 2 2 0 5 6 3 3 1 1 0 0 0 3 0 4 5 4 3 1 1 6 1 6 2 0 1 4 7 7\n" + 
			"0 3 0 0 2 6 1 4 7 5 1 4 3 2 5 1 4 3 6 3 0 2 4 5 7 5 6 2 0 5 6 3 6 4 6 2 0 0 6 0 7 2 2 6 0 0 0 0 0 0\n" + 
			"0 6 7 1 6 4 3 6 0 2 6 7 6 2 1 6 6 6 2 0 0 7 3 0 1 1 2 0 0 0 3 1 6 7 5 6 4 1 7 5 2 0 2 6 0 0 0 0 4 0\n" + 
			"0 6 7 7 3 3 0 2 0 1 6 4 1 1 1 6 2 3 3 4 2 3 5 0 5 7 7 6 2 7 2 7 3 1 0 5 6 7 1 6 4 1 5 0 0 0 0 0 0 0\n" + 
			"0 7 3 0 4 3 0 0 6 6 0 5 1 1 1 1 1 6 0 0 7 0 0 0 2 4 3 2 3 3 6 0 0 1 0 2 6 7 3 4 0 3 2 4 0 0 0 0 0 7\n" + 
			"0 0 4 7 2 0 0 0 1 4 2 4 7 7 2 4 2 4 0 5 6 0 0 0 7 0 2 7 4 4 1 6 1 4 2 3 6 2 0 6 5 3 5 0 3 5 6 0 0 1\n" + 
			"0 0 7 4 7 0 3 0 4 4 6 2 4 7 0 5 7 1 3 6 5 6 6 7 3 3 6 6 4 2 0 0 3 0 4 7 2 6 4 0 6 2 4 6 7 1 7 2 7 1\n" + 
			"0 0 2 6 0 0 6 5 0 4 1 2 2 2 2 7 2 1 0 4 6 4 1 0 1 1 2 2 0 4 4 2 0 0 3 0 3 6 2 2 7 6 6 0 4 6 0 2 2 2\n" + 
			"0 0 4 4 7 1 1 1 7 3 7 6 2 3 3 0 5 0 0 6 1 2 6 3 1 7 0 4 7 4 3 6 1 5 1 0 3 7 4 0 3 0 5 6 2 0 0 3 0 5\n" + 
			"0 0 7 3 0 5 4 0 7 4 0 0 4 5 7 1 3 2 3 3 5 3 5 3 5 5 5 5 4 2 3 6 0 3 1 7 2 4 5 3 0 0 5 3 6 0 0 7 3 6\n" + 
			"0 0 3 5 0 0 1 1 1 0 0 0 5 3 5 5 1 2 7 0 4 3 1 6 7 1 5 7 4 4 5 7 0 3 6 3 3 7 7 4 1 3 5 2 0 0 0 7 7 4\n" + 
			"0 0 7 6 3 5 0 7 2 7 7 5 4 0 0 7 0 4 0 0 3 2 3 1 5 7 4 6 0 3 5 5 2 0 6 0 0 0 2 1 1 4 3 6 2 0 5 1 1 6\n" + 
			"0 0 1 0 4 1 0 2 5 0 0 0 6 7 3 7 0 0 0 0 4 3 3 3 0 1 0 0 0 1 5 1 5 4 5 1 7 0 0 5 0 5 6 0 3 2 5 0 3 4\n" + 
			"0 0 0 0 0 4 0 2 3 1 6 6 6 3 5 3 6 0 0 0 4 7 0 6 1 7 1 0 0 5 5 2 5 1 0 1 1 3 3 4 1 4 2 0 6 3 0 0 6 4\n" + 
			"6 4 2 2 0 0 0 3 3 0 0 1 4 0 5 0 2 0 7 0 1 7 7 1 5 7 0 0 0 3 1 5 5 6 0 6 2 6 4 0 7 6 5 1 3 3 7 0 2 5\n" + 
			"0 0 0 7 7 0 0 4 4 3 1 6 1 0 1 3 3 1 4 5 7 3 7 0 0 4 0 0 0 7 3 7 2 2 0 1 5 0 7 5 5 2 5 1 0 2 0 0 3 2\n" + 
			"0 0 0 3 0 0 0 0 1 2 6 7 1 6 7 0 3 5 2 7 3 0 4 5 2 0 0 0 0 2 5 7 3 7 5 6 0 0 2 2 5 4 7 6 4 5 1 4 4 6\n" + 
			"0 4 3 0 0 0 0 3 5 6 3 2 0 3 6 0 6 0 0 1 4 3 6 2 4 7 4 7 1 5 0 4 0 0 2 0 0 0 3 7 6 1 2 5 3 5 2 3 3 3\n" + 
			"0 0 0 1 4 0 0 2 1 0 2 0 0 1 7 3 4 3 3 4 7 0 6 7 4 7 3 1 6 1 7 3 4 4 7 5 2 1 3 7 2 5 2 3 3 2 3 0 1 2\n" + 
			"0 0 0 0 1 1 0 0 5 7 3 6 6 0 0 6 5 4 2 7 0 0 4 5 5 0 5 7 3 3 0 3 5 5 3 6 0 0 3 5 4 0 0 7 5 1 6 0 0 7\n" + 
			"0 0 0 0 5 6 3 1 5 2 0 7 7 7 0 0 1 0 3 6 4 1 6 7 2 1 6 5 2 0 0 7 4 5 0 0 0 0 0 6 6 0 0 5 6 0 2 3 4 5\n" + 
			"0 0 7 1 0 1 6 5 6 0 0 5 4 5 7 1 1 6 5 2 2 0 3 7 4 5 2 6 4 0 0 3 4 0 0 0 0 0 0 7 7 7 7 6 4 3 4 4 0 0\n" + 
			"0 0 0 1 3 0 0 3 7 1 1 0 4 1 4 4 2 6 1 6 2 2 7 4 2 4 1 7 1 6 4 3 3 1 3 4 0 0 3 2 0 2 0 1 3 3 4 7 1 5\n" + 
			"0 0 0 3 4 0 0 2 0 5 5 0 0 1 4 4 0 4 0 1 6 6 4 2 1 0 0 3 7 0 4 3 3 2 3 5 3 5 0 4 0 5 0 3 0 7 7 3 5 6\n" + 
			"0 0 0 7 2 0 0 4 2 2 6 2 2 5 0 5 0 3 4 3 5 5 2 0 4 0 0 5 0 0 4 1 6 4 4 3 4 0 0 5 0 1 1 2 0 7 3 4 0 4\n" + 
			"0 0 1 1 4 4 1 0 0 0 3 5 4 5 4 2 7 4 6 1 6 7 0 3 0 7 1 7 6 6 3 0 5 7 6 6 4 7 3 4 5 0 0 0 0 6 1 1 5 3\n" + 
			"0 0 4 2 5 7 4 4 2 1 2 1 3 4 7 2 7 2 1 6 3 3 0 7 5 6 6 4 5 5 3 3 2 7 5 3 1 4 7 0 0 0 0 0 0 3 1 5 6 5\n" + 
			"0 0 0 4 4 1 0 0 6 0 0 7 5 7 5 1 7 3 6 0 2 4 3 4 7 7 3 0 0 0 1 5 5 0 6 7 7 7 4 4 3 6 3 7 5 0 1 1 0 2\n" + 
			"0 0 0 1 3 4 7 2 5 0 0 4 4 0 5 2 2 0 1 7 0 1 1 3 6 5 2 6 2 7 7 3 6 7 1 3 4 6 7 5 3 7 4 6 0 0 0 4 3 1\n" + 
			"0 0 0 2 1 6 3 5 4 0 0 6 0 0 6 7 0 0 5 2 0 7 7 0 7 0 0 7 7 6 0 0 1 1 0 1 0 1 3 1 0 0 4 7 7 0 0 0 2 6\n" + 
			"0 0 0 2 4 0 6 7 2 4 1 5 6 3 0 0 0 0 4 2 7 1 1 5 2 0 0 7 2 2 3 1 3 5 5 7 7 4 0 3 4 2 3 0 0 4 6 6 0 1\n" + 
			"0 0 0 4 6 1 0 3 6 4 7 3 5 0 0 0 0 0 0 7 0 0 3 6 2 1 0 2 3 4 6 7 5 0 7 0 5 4 5 1 5 0 0 0 0 4 5 3 1 0\n" + 
			"1 3 6 5 5 2 3 7 6 1 0 6 7 3 2 5 6 7 6 6 0 0 7 1 0 5 5 0 3 0 2 0 7 4 5 3 2 5 1 5 3 0 0 0 1 2 0 1 0 0\n" + 
			"1 7 3 0 2 0 7 0 4 6 1 1 5 0 7 0 5 7 7 2 6 0 0 1 0 2 3 3 4 2 7 5 3 7 0 0 4 6 6 6 3 0 0 0 7 7 7 5 7 2\n" + 
			"";
}

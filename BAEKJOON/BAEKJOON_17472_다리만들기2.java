package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_17472_다리만들기2 {

	/*
	 * 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 또, 다리의 길이는 2 이상이어야 한다.
	 * 0은 바다, 1은 땅을 의미 다리의 최소 길이 구하기, 못 만들때는 -1
	 * 
	 */
	static int N;
	static int M;
	static int[][] map;
	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	static boolean[][] visited;
	static int[][] checked;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		visited = new boolean[N][M];

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 입력완료

		int idx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					numbering(i, j, idx);
					idx++;
				}
			}
		}
		checked = new int[idx-1][idx-1];

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					// visited[i][j]=true;
					bfs(i, j, map[i][j]);
				}
			}
		}
		/*for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		for (int i = 0; i < checked.length; i++) {
			System.out.println(Arrays.toString(checked[i]));
		}*/

		int[] connected = new int[idx-1]; 
		// 연결여부 확인 
		connected[0] = 1;
		// 0번 노드부터 시작 
		int min = prim(checked, connected, 0, 1); 
		System.out.println(min);


	}

	public static int prim(int[][] graphs, int[] connected, int value, int count) {
		// 모든 노드를 방문하면 종료
		if (count == connected.length)
			return value;
		int to = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < connected.length; i++) {
			if (connected[i] == 1) {
				// 현재 간선이 연결되어 있으면 처리
				for (int j = 0; j < graphs.length; j++) {
					// 현재 연결된 간선들과 연결된 노드중 최소값을 가지는 간선
					if (connected[j] == 0 && graphs[i][j] != 0 && min > graphs[i][j]) {
						to = j;
						min = graphs[i][j];
					}
				}
			}
		}
		if(to==-1) return -1;
		connected[to] = 1;
		// 최소값 간선을 선택
		value += min;
		// 최소값 추가
		count++;
		// 연결된 간선 개수 추가
		return prim(graphs, connected, value, count);
	}


	private static void numbering(int r, int c, int idx) {

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(r, c, idx, 0, 0));

		while (!queue.isEmpty()) {
			Position np = queue.poll();
			map[np.r][np.c] = np.length;

			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r + dirs[i][0];
				int nc = np.c + dirs[i][1];

				if (isIn(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Position(nr, nc, idx, 0, 0));
				}

			}

		}

	}

	private static void bfs(int r, int c, int start) {
		Queue<Position> queue = new LinkedList<>();
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
		queue.offer(new Position(r, c, 0, 0, start));
		queue.offer(new Position(r, c, 0, 1, start));
		queue.offer(new Position(r, c, 0, 2, start));
		queue.offer(new Position(r, c, 0, 3, start));

		while (!queue.isEmpty()) {
			Position np = queue.poll();

			int nr = np.r + dirs[np.dir][0];
			int nc = np.c + dirs[np.dir][1];

			if (isIn(nr, nc)) {
				if (map[nr][nc] == 0) {
					queue.offer(new Position(nr, nc, np.length + 1, np.dir, start));
				} else if (map[nr][nc] != np.to) {
					if (np.length != 1) {
						if (checked[start-1][map[nr][nc]-1] != 0) {
							checked[start-1][map[nr][nc]-1] = Math.min(np.length, checked[start-1][map[nr][nc]-1]);
						} else {
							checked[start-1][map[nr][nc]-1] = np.length;
						}
					}
				}

			}
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Position {
		int r, c, length, dir, to;

		public Position(int r, int c, int length, int dir, int to) {
			super();
			this.r = r;
			this.c = c;
			this.length = length;
			this.dir = dir;
			this.to = to;
		}

	}

	private static String src = "4 4\n" + 
			"0 1 1 0\n" + 
			"1 0 0 1\n" + 
			"1 0 0 0\n" + 
			"0 1 1 1";
}

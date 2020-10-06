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

import javax.swing.plaf.ViewportUI;

public class BAEKJOON_17142_연구소3 {

	/*
	 * 0은 빈 칸, 1은 벽, 2는 바이러스의 위치
	 */
	static int size, virus, totalCnt;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	static List<Position> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		size = Integer.parseInt(st.nextToken());
		map = new int[size][size];
		virus = Integer.parseInt(st.nextToken());
		totalCnt = 0;
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Position(i, j, 0));
				} else if (map[i][j] == 0) {
					totalCnt++;
				}
			}
		} // 입력완료 및 바이러스 위치 큐에저장

		if (totalCnt == 0) {
			System.out.println(0);
		} else {
			combi(0, 0, new int[virus]);
			if (answer == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else
				System.out.println(answer);
		}
	}

	private static void combi(int start, int idx, int[] temp) {
		if (idx == virus) {
			// System.out.println(Arrays.toString(temp));
			bfs(temp);
		} else {
			for (int i = start; i < list.size(); i++) {
				temp[idx] = i;
				combi(i + 1, idx + 1, temp);
			}
		}
	}

	private static void bfs(int[] temp) {
		Queue<Position> queue = new LinkedList<>();
		int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int minTime = 0;
		int check = 0;
		boolean visited[][] = new boolean[size][size];
		for (int i = 0; i < temp.length; i++) {
			int r = list.get(temp[i]).r;
			int c = list.get(temp[i]).c;
			queue.add(new Position(r, c, 0));
			visited[r][c] = true;
		}
		while (!queue.isEmpty()) {
			Position np = queue.poll();
			minTime=np.t;
		
			if (check == totalCnt) {
				continue;
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r + dirs[i][0];
				int nc = np.c + dirs[i][1];
				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					visited[nr][nc] = true;
					queue.add(new Position(nr, nc, np.t + 1));
					if (map[nr][nc] == 0) {
						check++;
					}

				}
			}
		}
		if (check == totalCnt) {
			answer = Math.min(answer, minTime);
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < size && c >= 0 && c < size;
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

	private static String src = "4 1\n" + 
			"2 1 1 1\n" + 
			"1 1 1 1\n" + 
			"1 1 1 1\n" + 
			"1 1 1 1";
}

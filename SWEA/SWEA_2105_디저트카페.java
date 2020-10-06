package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페 {

	static int N;
	static int[][] land;
	static boolean[] desserts;
	static boolean[][] visited;
	static int max;
	static boolean trueflag;
	static int startR;
	static int startC;
	static int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			desserts = new boolean[101];
			N = Integer.parseInt(br.readLine());
			land = new int[N][N];
			max = Integer.MIN_VALUE;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					land[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startR = i;
					startC = j;
					visited[i][j] = true;
					desserts[land[i][j]] = true;
					solve(i, j, 1, 0);
					visited[i][j] = false;
					desserts[land[i][j]] = false;
				}
			}
			if (max < 4)
				max = -1;
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb);
	}

	private static void solve(int x, int y, int cnt, int d) {
		if (d == 4)
			return;
		int tx = x + dir[d][0];
		int ty = y + dir[d][1];
		if (!isIn(tx, ty))
			return;
		if (visited[tx][ty] || desserts[land[tx][ty]]) {
			if (tx == startR && ty == startC)
				max = Math.max(max, cnt);
			return;
		}
		desserts[land[tx][ty]] = true;
		visited[tx][ty] = true;
		// 한 방향으로 쭉
		solve(tx, ty, cnt + 1, d);
		// 그 때 그 때 다음 방향으로 틀기
		solve(tx, ty, cnt + 1, d + 1);
		visited[tx][ty] = false;
		desserts[land[tx][ty]] = false;

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 & r < N && c >= 0 && c < N;
	}

	private static String src = "2\n" + "8\n" + "18 18 7 16 15 3 5 6\n" + "3 6 8 3 15 13 15 2\n"
			+ "4 1 11 17 3 4 3 17\n" + "16 2 18 10 2 3 11 12\n" + "11 17 16 2 9 16 5 4\n" + "17 7 6 16 16 11 15 8\n"
			+ "2 1 7 18 12 11 6 2\n" + "13 12 12 15 9 11 12 18\n" + "9\n" + "12 3 10 8 11 12 5 3 11\n"
			+ "8 6 4 9 8 2 4 7 6\n" + "6 10 12 8 3 8 11 3 3\n" + "6 10 5 5 5 11 8 10 2\n" + "5 13 3 7 5 6 5 12 6\n"
			+ "6 1 5 4 4 13 8 7 2\n" + "12 7 13 3 5 1 11 7 3\n" + "13 12 7 5 6 12 12 9 6\n" + "1 12 13 13 11 3 4 10 9";
}

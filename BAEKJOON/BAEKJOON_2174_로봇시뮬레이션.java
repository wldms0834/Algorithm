package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEKJOON_2174_로봇시뮬레이션 {

	static int row;
	static int col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken()); // 세로 길이
		row = Integer.parseInt(st.nextToken()); // 가로 길이
		int[][] land = new int[row][col];

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 로봇개수
		int M = Integer.parseInt(st.nextToken()); // 명령개수
		Robot[] robots = new Robot[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int robotC = Integer.parseInt(st.nextToken());
			int robotR = Integer.parseInt(st.nextToken());
			char robotDir = st.nextToken().charAt(0);
			if (robotDir == 'E')
				robots[i] = new Robot(row - robotR, robotC - 1, 0);
			else if (robotDir == 'S')
				robots[i] = new Robot(row - robotR, robotC - 1, 1);
			else 
				if (robotDir == 'W')
				robots[i] = new Robot(row - robotR, robotC - 1, 2);
			else
				robots[i] = new Robot(row - robotR, robotC - 1, 3);
			
			land[row - robotR][robotC - 1] = i;
		}

		// L : 왼쪽으로 90도 회전 R: 오른쪽으로 90도 회전 F : 앞으로한칸
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, {-1,0}};
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int robotNum = Integer.parseInt(st.nextToken());
			char order = st.nextToken().charAt(0);
			int repeatCnt = Integer.parseInt(st.nextToken());

			if (order == 'L') {
				for (int j = 0; j < repeatCnt; j++) {
					robots[robotNum].dir--;
					if (robots[robotNum].dir < 0)
						robots[robotNum].dir = 3;
				}
			} else if (order == 'R') {
				for (int j = 0; j < repeatCnt; j++) {
					robots[robotNum].dir++;
					if (robots[robotNum].dir > 3)
						robots[robotNum].dir = 0;
				}
			} else {
				for (int j = 0; j < repeatCnt; j++) {
					int nr = robots[robotNum].r + dirs[robots[robotNum].dir][0];
					int nc = robots[robotNum].c + dirs[robots[robotNum].dir][1];

					if (!isIn(nr, nc)) {
						System.out.println("Robot "+ robotNum +" crashes into the wall");
						return;
					} else if (land[nr][nc] != 0) {
						System.out.println("Robot "+ robotNum +" crashes into robot "+land[nr][nc]);
						return;
					}
					else {
						land[robots[robotNum].r][robots[robotNum].c]=0;
						land[nr][nc]=robotNum;
						robots[robotNum].r = nr;
						robots[robotNum].c = nc;
						
					}
					
				}
			}

		}
		System.out.println("OK");

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < row && c >= 0 && c < col;
	}

	private static class Robot {
		int r, c;
		int dir;

		public Robot(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

	}

	private static String src = "5 4\n" + 
			"2 2\n" + 
			"1 1 E\n" + 
			"5 4 W\n" + 
			"1 L 96\n" + 
			"1 F 2\n";
}

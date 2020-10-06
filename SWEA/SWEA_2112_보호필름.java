package SWEA;

import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름 {
	static int R, C, K;
	static int[][] map;
	static boolean[] visited;
	static int min;

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
			K = Integer.parseInt(st.nextToken());
			if (K == 1) {
				sb.append(0).append("\n");
				continue;
			}
			min = Integer.MAX_VALUE;
			map = new int[R][C];
			visited = new boolean[C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} 
			injection(0, new int[R]);
			sb.append(min).append("\n");
		}
		System.out.println(sb);

	}

	private static void injection(int depth, int[] temp) {
		if (depth == R) {
			if (check(temp)) {
				int result = 0;
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] != -1) {
						result++;
					}
				}
				min = Math.min(min, result);
			}

		} else {
			temp[depth] = -1;
			injection(depth + 1, temp);
			temp[depth] = 0;
			injection(depth + 1, temp);
			temp[depth] = 1;
			injection(depth + 1, temp);
		}

	}

	private static boolean check(int []temp) {
		for (int i = 0; i <C; i++) {
			int stand=0;
			if (temp[0]==-1) {
				stand = map[0][i];
			}
			else {
				stand = temp[0];
			}
			int max=1;
			int cnt=1;
			for (int j = 1; j <R; j++) {
				
			}
		}
		return false;
		
	}

	private static String src = "10\n" + "6 8 3\n" + "0 0 1 0 1 0 0 1\n" + "0 1 0 0 0 1 1 1\n" + "0 1 1 1 0 0 0 0\n"
			+ "1 1 1 1 0 0 0 1\n" + "0 1 1 0 1 0 0 1\n" + "1 0 1 0 1 1 0 1\n" + "6 8 3\n" + "1 1 1 1 0 0 1 0\n"
			+ "0 0 1 1 0 1 0 1\n" + "1 1 1 1 0 0 1 0\n" + "1 1 1 0 0 1 1 0\n" + "1 1 0 1 1 1 1 0\n"
			+ "1 1 1 0 0 1 1 0\n" + "6 8 4\n" + "1 1 0 0 0 1 1 0\n" + "1 0 1 0 0 1 1 1\n" + "0 1 0 0 1 1 0 0\n"
			+ "1 0 1 0 0 0 0 0\n" + "1 1 0 0 0 0 0 0\n" + "1 0 0 0 1 1 1 1\n" + "6 4 4\n" + "1 1 0 0\n" + "0 1 0 1\n"
			+ "0 0 0 1\n" + "1 1 1 1\n" + "1 1 0 1\n" + "1 0 1 0\n" + "6 10 3\n" + "0 1 0 0 0 1 0 0 1 1\n"
			+ "0 1 1 0 0 1 0 0 1 0\n" + "0 1 0 0 1 0 1 1 1 1\n" + "0 0 0 0 0 1 1 1 1 0\n" + "0 1 0 0 1 1 1 1 1 1\n"
			+ "1 0 0 0 1 1 0 0 1 1\n" + "6 6 5\n" + "0 0 0 0 0 0\n" + "0 0 0 0 0 0\n" + "0 0 0 0 0 0\n"
			+ "0 0 0 0 0 0\n" + "0 0 0 0 0 0\n" + "0 0 0 0 0 0\n" + "6 6 4\n" + "1 1 1 1 1 1\n" + "0 0 0 0 0 1\n"
			+ "0 1 1 1 0 1\n" + "0 1 0 1 0 1\n" + "0 1 0 0 0 1\n" + "0 1 1 1 1 1\n" + "8 15 3\n"
			+ "0 1 1 0 0 1 1 0 1 1 0 0 0 0 0\n" + "1 0 0 0 1 1 0 0 0 0 0 1 0 1 1\n" + "1 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n"
			+ "0 1 1 1 0 0 1 0 0 0 0 1 0 0 0\n" + "0 0 0 0 0 0 1 0 0 0 1 1 0 0 1\n" + "1 0 1 0 0 1 0 1 1 1 1 0 1 1 1\n"
			+ "0 0 0 0 0 1 1 1 0 0 0 0 0 1 0\n" + "0 0 1 0 1 1 0 1 1 0 0 0 1 0 0\n" + "10 20 4\n"
			+ "1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 0 1\n" + "1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 0\n"
			+ "1 1 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0\n" + "0 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0\n"
			+ "0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 0 1 1 1 1\n" + "1 0 1 0 1 0 1 1 0 0 0 0 1 1 1 0 0 0 0 0\n"
			+ "0 1 0 0 1 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1\n" + "1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 0 1 1 1 0\n"
			+ "0 1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 1 1 0 1\n" + "0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0\n" + "13 20 5\n"
			+ "1 1 0 1 0 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0\n" + "1 1 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0\n"
			+ "1 0 1 0 1 1 0 1 0 1 1 0 0 0 0 1 1 0 1 0\n" + "0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 0 1 1 1 1\n"
			+ "0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 1\n" + "0 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 1 0 1\n"
			+ "0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1 0 0 1 0\n" + "1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 1 0 0 1 1\n"
			+ "0 1 1 1 1 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1\n" + "0 0 0 0 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 1\n"
			+ "0 1 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0\n" + "0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 0 0\n"
			+ "0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 0\n" + "";
}

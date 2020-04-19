package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_9659_D4_다항식계산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		int num = 998244353;
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t);
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] infoN = new int[N - 1][3];
			for (int i = 0; i < infoN.length; i++) {
				st = new StringTokenizer(br.readLine());
				infoN[i][0] = Integer.parseInt(st.nextToken());
				infoN[i][1] = Integer.parseInt(st.nextToken());
				infoN[i][2] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			long[][] infoM = new long[M][N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				infoM[i][0] = 1;
				infoM[i][1] = (Long.parseLong(st.nextToken())) % num;

			}

			for (int j = 2; j < infoM[0].length; j++) {
				int ti = infoN[j - 2][0];
				int ai = infoN[j - 2][1];
				int bi = infoN[j - 2][2];
				for (int k = 0; k < M; k++) {
					if (ti == 1) {
						infoM[k][j] = (infoM[k][ai] + infoM[k][bi]) % num;
					} else if (ti == 2) {
						infoM[k][j] = (ai * infoM[k][bi]) % num;
					} else {
						infoM[k][j] = (infoM[k][ai] * infoM[k][bi]) % num;
					}

				}

			}

			for (int i = 0; i < infoM.length; i++) {
				sb.append(" ").append(infoM[i][N]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static String src = "1\n" + "4\n" + "1 0 1\n" + "2 2 2\n" + "3 2 3\n" + "4\n" + "0 1 2 3";
}

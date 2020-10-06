package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BAEKJOON_6987_월드컵 {

	static int[][] result;
	static int[][] score;
	static int[] ans;
	static int idx=0;
	static int[] t1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] t2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st;
		ans = new int[4];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			score = new int[6][3];
			result = new int[6][3];
			for (int j = 0; j < 6; j++) {
				score[j][0] = Integer.parseInt(st.nextToken());
				score[j][1] = Integer.parseInt(st.nextToken());
				score[j][2] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			idx++;

		}

		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}

	static void dfs(int count) {
		if(ans[idx]==1)
			return;
		else if (count == 15) { 
			if (ans[idx] != 1) {
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 3; j++) {
						if (score[i][j] != result[i][j]) {
							return;
						}
					}
				}
				ans[idx] = 1;
				return;

			} 
		}
		else {
			int temp1 = t1[count];
			int temp2 = t2[count];

			result[temp1][0]++;
			result[temp2][2]++;
			dfs(count + 1);
			result[temp1][0]--;
			result[temp2][2]--;

			result[temp1][1]++;
			result[temp2][1]++;
			dfs(count + 1);
			result[temp1][1]--;
			result[temp2][1]--;

			result[temp1][2]++;
			result[temp2][0]++;
			dfs(count + 1);
			result[temp1][2]--;
			result[temp2][0]--;
		}

	}

	private static String src = "5 0 0 3 0 2 2 0 3 0 0 5 4 0 1 1 0 4\n" + "4 1 0 3 0 2 4 1 0 1 1 3 0 0 5 1 1 3\n"
			+ "5 0 0 4 0 1 2 2 1 2 0 3 1 0 4 0 0 5\n" + "5 0 0 3 1 1 2 1 2 2 0 3 0 0 5 1 0 4";
}

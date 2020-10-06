package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_3234_D4_준환이의양팔저울 {
	static int cnt, answer;
	static int[] weight;
	// static boolean[] visited;
	static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			weight = new int[cnt];
			answer = 0;
			total = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < cnt; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
				total += weight[i];
			}
			share(0, new boolean[cnt], 0, 0, weight,total);
			System.out.println("#" + t + " " + answer);
		}
	}

	private static void share(int idx, boolean[]visited, int left, int right,int[]weight,int total) {
		if (idx == cnt) {
				answer++;
				return;
		} 	
		else {
			for (int i = 0; i < weight.length; i++) {
				if (!visited[i]) {
					visited[i] = true;		
					total-=weight[i];
					share(idx + 1, visited, left + weight[i], right,weight,total);
					
					if (right+weight[i]<=left) {
						share(idx + 1, visited, left, right + weight[i],weight,total);
					}
					visited[i] = false;
					total+=weight[i];

				}

			}
		}
	}

	private static String src = "3\n" + 
			"3\n" + 
			"1 2 4\n" + 
			"3\n" + 
			"1 2 3\n" + 
			"9\n" + 
			"1 2 3 5 6 4 7 8 9";
}

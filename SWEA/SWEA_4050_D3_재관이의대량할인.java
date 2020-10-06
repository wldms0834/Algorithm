package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_4050_D3_재관이의대량할인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] price = new int[N];
			int total = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				total += price[i];
			}
			Arrays.sort(price);
			for (int i = price.length - 3; i >= 0; i -= 3) {

				total -= price[i];

			}
			sb.append(total).append("\n");
		}
		System.out.println(sb);
	}

	private static String src = "2\n" + "4\n" + "3 2 3 2\n" + "6\n" + "6 4 5 5 5 5";
}

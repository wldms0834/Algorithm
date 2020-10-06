package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_6782_D5_현주가좋아하는제곱근놀이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			int cnt = 0;
			while (true) {
				if (N == 2)
					break;
				else {
					double ans = Math.sqrt(N);
					if (ans == (int) ans) {
						N = (int) ans;
						cnt++;
					} 
					else {	
						long temp = (long) Math.ceil(ans);
						cnt+=temp*temp-N;
						N=temp*temp;
					}

				}
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);

	}

	private static String src = "1\n" + 
			"8540842157399";
}

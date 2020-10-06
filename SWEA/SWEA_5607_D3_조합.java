package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_5607_D3_조합 {
	static int num=1234567891;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= TC; t++) {
			
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long []factorial = new long[N+1];
			factorial[0]=1;
			for (int i = 1; i < factorial.length; i++) {
				factorial[i] = (factorial[i-1]*i)%num;
			}
			long result=calc((factorial[N-R]*factorial[R])%num, num-2);
			sb.append((factorial[N]*result)%num).append("\n");						
		}
		System.out.println(sb);
	}
	private static long calc(long a, long p) {
		if(p==0)
			return 1;
		else if(p%2==0) {
			long temp = calc(a, p/2)%num;
			return (temp*temp)%num;
		}else {
			long temp = calc(a, p/2)%num;
			return ((temp*temp)%num*a)%num;
		}
	}
	private static String src = "1\n" + 
			"10 2";
}

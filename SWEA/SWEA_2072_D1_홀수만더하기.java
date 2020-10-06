package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_2072_D1_홀수만더하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb= new StringBuilder(); 
		
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {	
			int sum=0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i <10; i++) {
				int num=Integer.parseInt(st.nextToken());
				if(num%2==1) {
					sum+=num;
				}
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}System.out.println(sb);
	}
	private static String src="3\n" + 
			"3 17 1 39 8 41 2 32 99 2\n" + 
			"22 8 5 123 7 2 63 7 3 46\n" + 
			"6 63 2 3 58 76 21 33 8 1";
}

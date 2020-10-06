package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_6719_D4_성수의프로그래밍강좌시청 {
	//(A+M)/2

	static int lectureCnt;
	static int chooseCnt;
	static double[] difficulty;
	static boolean[] visited;
	static double answer;
	public static void main(String[] args) throws IOException {
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			st = new StringTokenizer(br.readLine());
			lectureCnt = Integer.parseInt(st.nextToken());
			chooseCnt = Integer.parseInt(st.nextToken());
			difficulty = new double[lectureCnt];
			answer=0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i <lectureCnt; i++) {
				difficulty[i]=Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(difficulty);
			for (int i = lectureCnt-chooseCnt; i <lectureCnt; i++) {
				answer=(answer+difficulty[i])/2;
			}
			
			System.out.println("#"+t+" "+answer);
			
		}

	}
	
	private static String src ="3\n" + 
			"2 2\n" + 
			"2 3\n" + 
			"2 1\n" + 
			"2 3\n" + 
			"5 3\n" + 
			"9 5 2 3 5";
}

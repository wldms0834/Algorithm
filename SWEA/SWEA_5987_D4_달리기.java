package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_5987_D4_달리기 {
	
	static int personCnt;
	static int infoCnt;
	static List<Integer>[] infos;
	static boolean []visited;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			personCnt = Integer.parseInt(st.nextToken());
			infoCnt = Integer.parseInt(st.nextToken());
			visited = new boolean[personCnt+1];
			result = 0;
			infos = new ArrayList[personCnt+1];
			
			for (int i = 0; i <infos.length; i++) {
				infos[i] = new ArrayList<>();
			}			

			for (int i = 0; i <infoCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				infos[first].add(second);				
			}
			makePermu(0, personCnt, new int[personCnt]);
			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}
	private static void makePermu(int idx, int target, int []temp) {
		if(idx==target) {
			result++;
		}
		else {
			for (int i = 1; i <=target; i++) {
				if (!visited[i]) {
					
				}
					
			}
		}
		
	}
	private static int checkRank(int[] temp) {
		for (int i = 0; i < infos.length; i++) {
			int first = 0;
			int last = 0;
			int firstIndex=0;
			int lastIndex=0;
			for (int j = 0; j < temp.length; j++) {
				if(temp[j]==first) firstIndex=j;
				else if(temp[j]==last) lastIndex=j;
			}
			if(firstIndex>lastIndex) return 0;
		}
		return 1;
		
	}
	private static String src ="3\n" + 
			"3 2\n" + 
			"1 2\n" + 
			"1 3\n" + 
			"5 5\n" + 
			"1 2\n" + 
			"2 5\n" + 
			"1 3\n" + 
			"3 4\n" + 
			"4 5\n" + 
			"16 1\n" + 
			"5 9";
}

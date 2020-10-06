package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {

	static int honeyBoxLength;
	static int[][] honeyBox;
	static int choiceCnt;
	static int maxHoney;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());

			honeyBoxLength = Integer.parseInt(st.nextToken());
			honeyBox = new int[honeyBoxLength][honeyBoxLength];
			//선택할 수 있는 벌통의 갯수 
			choiceCnt = Integer.parseInt(st.nextToken());
			//꿀을 채취할 수 있는 최대의 
			maxHoney = Integer.parseInt(st.nextToken());

			//벌통들의 크기 전체 입력 
			for (int row = 0; row < honeyBoxLength; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < honeyBoxLength; col++) {
					honeyBox[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			

		}

	}

	private static String src = "1\n" +
					"4 2 13\n" + 
					"6 1 9 7\n" + 
					"9 8 5 8\n" + 
					"3 4 5 3\n" + 
					"8 2 6 7\n";
}

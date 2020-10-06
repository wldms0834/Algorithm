package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.text.Position;

public class SWEA_1949_등산로조성 {

	static int N;
	static int K;
	static int[][]originalLand;
	static int max;
	static int maxLength;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t<=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //지도 한변의 길이
			K = Integer.parseInt(st.nextToken()); //최대 공사 가능 깊이
			max = Integer.MIN_VALUE;
			maxLength = Integer.MIN_VALUE;
			//int [][]land = new int[N][N];
			originalLand = new int[N][N];
			for (int i = 0; i < originalLand.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j< originalLand.length; j++) {
					originalLand[i][j]=Integer.parseInt(st.nextToken());
					//originalLand[i][j]=land[i][j];
					max = Math.max(max,originalLand[i][j]);
				}
			}
			
			for (int i = 0; i < originalLand.length; i++) {
				for (int j = 0; j < originalLand.length; j++) {
					if (originalLand[i][j]==max) {	
						boolean[][]visited = new boolean[N][N];
						visited[i][j]=true;
						findRoute(i, j, 1, false, visited);
					}
				}
			}			
			
			sb.append(maxLength).append("\n");
						
		}System.out.println(sb);

	}
	
	static void findRoute(int r, int c,int length ,boolean check,boolean[][]visited) {
		int [][]dir = {{1,0},{-1,0},{0,-1},{0,1}};
		
		for (int i = 0; i < dir.length; i++) {
			int nr = r+dir[i][0];
			int nc = c+dir[i][1];
			
			if(isIn(nr, nc) && !visited[nr][nc])  {
				if (originalLand[r][c]>originalLand[nr][nc]) {
					visited[nr][nc]=true;
					findRoute(nr, nc,length+1,check,visited);
					visited[nr][nc]=false;
				}
				else {
					if (check) {
						maxLength=Math.max(length, maxLength);
						//return;
					}
					else {
						if(originalLand[nr][nc]-originalLand[r][c]<=K-1) {
							int temp = originalLand[nr][nc];
							originalLand[nr][nc]=originalLand[r][c]-1;
							visited[nr][nc]=true;
							findRoute(nr, nc,length+1,true,visited);
							visited[nr][nc]=false;
							originalLand[nr][nc]=temp;
						}
						else {
							maxLength=Math.max(length, maxLength);
							//return;
						}
					}
				}			
			}
		}
	}
	
	static class Position{
		int r,c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	private static String src = "10\n" + 
			"5 1\n" + 
			"9 3 2 3 2\n" + 
			"6 3 1 7 5\n" + 
			"3 4 8 9 9\n" + 
			"2 3 7 7 7\n" + 
			"7 6 5 5 8\n" + 
			"3 2\n" + 
			"1 2 1\n" + 
			"2 1 2\n" + 
			"1 2 1\n" + 
			"5 2\n" + 
			"9 3 2 3 2\n" + 
			"6 3 1 7 5\n" + 
			"3 4 8 9 9\n" + 
			"2 3 7 7 7\n" + 
			"7 6 5 5 8\n" + 
			"4 4\n" + 
			"8 3 9 5\n" + 
			"4 6 8 5\n" + 
			"8 1 5 1\n" + 
			"4 9 5 5\n" + 
			"4 1\n" + 
			"6 6 1 7\n" + 
			"3 6 6 1\n" + 
			"2 4 2 4\n" + 
			"7 1 3 4\n" + 
			"5 5\n" + 
			"18 18 1 8 18\n" + 
			"17 7 2 7 2\n" + 
			"17 8 7 4 3\n" + 
			"17 9 6 5 16\n" + 
			"18 10 17 13 18\n" + 
			"6 4\n" + 
			"12 3 12 10 2 2\n" + 
			"13 7 13 3 11 6\n" + 
			"2 2 6 5 13 9\n" + 
			"1 12 5 4 10 5\n" + 
			"11 10 12 8 2 6\n" + 
			"13 13 7 4 11 7\n" + 
			"7 3\n" + 
			"16 10 14 14 15 14 14\n" + 
			"15 7 12 2 6 4 9\n" + 
			"10 4 11 4 6 1 1\n" + 
			"16 4 1 1 13 9 14\n" + 
			"3 12 16 14 8 13 9\n" + 
			"3 4 17 15 12 15 1\n" + 
			"6 6 13 6 6 17 12\n" + 
			"8 5\n" + 
			"2 3 4 5 4 3 2 1\n" + 
			"3 4 5 6 5 4 3 2\n" + 
			"4 5 6 7 6 5 4 3\n" + 
			"5 6 7 8 7 6 5 4\n" + 
			"6 7 8 9 8 7 6 5\n" + 
			"5 6 7 8 7 6 5 4\n" + 
			"4 5 6 7 6 5 4 3\n" + 
			"3 4 5 6 5 4 3 2\n" + 
			"8 2\n" + 
			"5 20 15 11 1 17 10 14\n" + 
			"1 1 11 16 1 14 7 5\n" + 
			"17 2 3 4 5 13 19 20\n" + 
			"6 18 5 16 6 7 8 5\n" + 
			"10 4 5 4 9 2 10 16\n" + 
			"2 7 16 5 8 9 10 11\n" + 
			"12 19 18 8 7 11 15 12\n" + 
			"1 20 18 17 16 15 14 13";
}


/*
 * #1 6
#2 3
#3 7
#4 4
#5 2
#6 12
#7 6
#8 7
#9 10
#10 19
 * 
 * */
 
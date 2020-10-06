package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA_5656_벽돌깨기_self {

	static int N;
	static int W;
	static int H;
	static int[][] gameboard;
	static int min;
	static int[][]tempboard;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			gameboard = new int[H][W];
			tempboard = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					gameboard[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			makeBrick(0, N, new int[N]);
			System.out.println("#"+t+" "+ min);
		}

	}

	private static void makeBrick(int index, int limit, int[] temp) {
		if (index == limit) {			
			int cnt=breakBrick(temp);			
			min= Math.min(min, cnt);
			return;
		} else {
			for (int i = 0; i <W; i++) {
				temp[index] = i;
				makeBrick(index + 1, limit, temp);
			}
		}
	}

	private static int breakBrick(int[] temp) {
		Queue<Position> queue = new LinkedList<>();
		int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int total=0;
		tempboard = cloneMap(gameboard);

		for (int i = 0; i <temp.length; i++) {
			for (int j = 0; j <H; j++) {
				if (tempboard[j][temp[i]] != 0) {
					queue.offer(new Position(j, temp[i], tempboard[j][temp[i]]-1));
					tempboard[j][temp[i]] = 0;
					break;
				}
			}
			while (!queue.isEmpty()) {
				Position np = queue.poll();		
				for (int d = 0; d < dirs.length; d++) {	
					
					for (int j = 1; j <= np.d; j++) {
						
						int nr = np.r + (dirs[d][0]) * j;
						int nc = np.c + (dirs[d][1]) * j;
						
						if (isIn(nr, nc) && tempboard[nr][nc]!= 0) {
							
							queue.offer(new Position(nr, nc, tempboard[nr][nc] - 1));
							tempboard[nr][nc] = 0;
						}
					}
				}
			}
			
		cleanMap(tempboard);		
		}
		
		for (int j = 0; j <H; j++) {
			for (int k = 0; k <W; k++) {
				if(tempboard[j][k]!=0) {
					total++;
				}
			}
		}
	
		return total;

	}
	
	private static int[][] cloneMap(int[][] map) {
		int [][]temp = new int[H][W];
		for (int i = 0; i < map.length; i++) {
			temp[i]=map[i].clone(); //deepCopy ->내용 복사 
		}
		return temp;
	}
	
	private static void cleanMap(int[][] map) {
		for(int c=0; c<W; c++) {
			for(int r=H-1, nr=H-1; r>=0; r--) {
				if(map[r][c] != 0) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[nr--][c] = temp;
				}
			}
		}
		
	}
		
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}

	private static class Position {
		int r, c, d;

		public Position(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	private static String src = "5\n" + 
			"3 10 10\n" + 
			"0 0 0 0 0 0 0 0 0 0\n" + 
			"1 0 1 0 1 0 0 0 0 0\n" + 
			"1 0 3 0 1 1 0 0 0 1\n" + 
			"1 1 1 0 1 2 0 0 0 9\n" + 
			"1 1 4 0 1 1 0 0 1 1\n" + 
			"1 1 4 1 1 1 2 1 1 1\n" + 
			"1 1 5 1 1 1 1 2 1 1\n" + 
			"1 1 6 1 1 1 1 1 2 1\n" + 
			"1 1 1 1 1 1 1 1 1 5\n" + 
			"1 1 7 1 1 1 1 1 1 1\n" + 
			"2 9 10\n" + 
			"0 0 0 0 0 0 0 0 0\n" + 
			"0 0 0 0 0 0 0 0 0\n" + 
			"0 1 0 0 0 0 0 0 0\n" + 
			"0 1 0 0 0 0 0 0 0\n" + 
			"1 1 0 0 1 0 0 0 0\n" + 
			"1 1 0 1 1 1 0 1 0\n" + 
			"1 1 0 1 1 1 0 1 0\n" + 
			"1 1 1 1 1 1 1 1 0\n" + 
			"1 1 3 1 6 1 1 1 1\n" + 
			"1 1 1 1 1 1 1 1 1\n" + 
			"3 6 7\n" + 
			"1 1 0 0 0 0\n" + 
			"1 1 0 0 1 0\n" + 
			"1 1 0 0 4 0\n" + 
			"4 1 0 0 1 0\n" + 
			"1 5 1 0 1 6\n" + 
			"1 2 8 1 1 6\n" + 
			"1 1 1 9 2 1\n" + 
			"4 4 15\n" + 
			"0 0 0 0 \n" + 
			"0 0 0 0 \n" + 
			"0 0 0 0 \n" + 
			"1 0 0 0 \n" + 
			"1 0 0 0 \n" + 
			"1 0 0 0 \n" + 
			"1 0 0 0 \n" + 
			"1 0 5 0 \n" + 
			"1 1 1 0 \n" + 
			"1 1 1 9 \n" + 
			"1 1 1 1 \n" + 
			"1 6 1 2 \n" + 
			"1 1 1 5 \n" + 
			"1 1 1 1 \n" + 
			"2 1 1 2 \n" + 
			"4 12 15\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9";
}
